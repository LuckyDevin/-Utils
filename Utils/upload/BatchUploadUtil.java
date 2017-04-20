package com.ccx.credit.util.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ccx.credit.assess.model.AuthorizeBean;
import com.ccx.credit.model.PerReportBean;
import com.ccx.credit.model.UserBean;
import com.ccx.credit.util.MD5;


public class BatchUploadUtil {
	
	private static String zipFile_PATH = "/tmp/showpic/WX/image";
	
	private static final Logger logger = LoggerFactory.getLogger(BatchUploadUtil.class);
	
	public static List<AuthorizeBean> unPackZipFile(MultipartFile uploadZipFile,UserBean user) throws IOException {
		//解压文件
		Charset gbk = Charset.forName("GBK");
		ZipInputStream zis =new ZipInputStream(uploadZipFile.getInputStream(),gbk);
		SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
    	SimpleDateFormat sdf_m = new SimpleDateFormat("MM");
    	String y = sdf_y.format(new Date());
    	String m = sdf_m.format(new Date());
		ZipEntry entry = null;
		List<AuthorizeBean> list = new ArrayList<>();
    	String path = zipFile_PATH + File.separator+y + File.separator + m + File.separator;
		while((entry=zis.getNextEntry())!=null && !entry.isDirectory()){
			File folder = new  File(path);
			if(!folder.exists()){
				folder.mkdirs();
			}
			File pdfFile = new File(path,entry.getName());
			@SuppressWarnings("resource")
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(pdfFile));
			int read = 0;
			byte[] buffer = new byte[1024 * 10];
			while((read = zis.read(buffer,0,buffer.length))!=-1){
				os.write(buffer,0,read);
			}
			os.flush();
			AuthorizeBean authorize = new AuthorizeBean();
			authorize.setFilePath(path);
			authorize.setAccount(user.getAccount());
			authorize.setInstitutionId(user.getInstitutionid());
			//检查文件
			authorize = checkAuthorize(pdfFile, authorize);
			list.add(authorize);
		}
		zis.closeEntry();
		return list;
	}
	
	//检查授权文件
	public static AuthorizeBean checkAuthorize(File file,AuthorizeBean authorize){
		HashMap<String, String> extMap = new HashMap<String, String>();
    	extMap.put("image", "jpg,png,bmp");//图片允许上传的格式
    	extMap.put("file", "pdf");//文件允许上传的格式
    	String newFileName = null;
    	long maxSize = 1000000;
    	
    	try{
    		if ( !file.exists()){
	    		logger.info("没有得到上传文件!");
	    		authorize.setUpType("0");
	    		return authorize;
	    	}
    		
    		//检查扩展名
	    	String fileName = file.getName();
	    	String fileExt = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//扩展名
	    	fileExt = fileExt.toLowerCase();//扩展名转化为小写
	    	fileName = fileName.substring(0, fileName.lastIndexOf("."));
	        if(!Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt) && !Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
				logger.info("上传文件扩展名是不允许的扩展名!");
	    		authorize.setUpType("-1");
				return authorize;
			}
	        authorize.setCid(fileName);
	        
	        //检查图片大小
	    	long fileSize = file.length();
	    	if( fileSize > maxSize ){
	    		logger.info("上传文件大小超过限制!");
	    		authorize.setUpType("-2");
	    		return authorize;
	    	}
	    	
	    	
	    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    	String cid = MD5.encryption(authorize.getCid()).substring(8, 24);
		    newFileName = cid + authorize.getInstitutionId() + df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;//保存的图像名
		
		    authorize.setState(1);
		    authorize.setFileName(newFileName);
		    authorize.setFileType(fileExt);
		    authorize.setFileSize((int) fileSize);
		    authorize.setUpType("1");
    		
    	}catch(Exception e){
    		logger.error("上传文件过程中异常", e);
    		authorize.setUpType("-3");
			return authorize;
    	}
    	
    	return authorize;
	}
	
	public static List<PerReportBean> getComparison(List<AuthorizeBean> list_auth,List<PerReportBean> list_per){
		List<PerReportBean> list = new ArrayList<>();
		for(int i=0;i<list_per.size();i++){
			PerReportBean bean = new PerReportBean();
			String cid = list_per.get(i).getCid();
			bean.setName(list_per.get(i).getName());
			bean.setCard(list_per.get(i).getCard());
			bean.setMobile(list_per.get(i).getMobile());
			bean.setCid(cid);
			for(int j =0;j<list_auth.size();j++){
				if(cid.equals(list_auth.get(j).getCid())){
					bean.setSearchFlag("1");
					break;
				}
				bean.setSearchFlag("0");
			}
			list.add(bean);
		}
		return list;
	}
	
	public static List<AuthorizeBean> sortListAuthBean(List<AuthorizeBean> list_auth,List<PerReportBean> list_per){
		for(int i=0;i<list_per.size();i++){
			String cid = list_per.get(i).getCid();
			String name = list_per.get(i).getName();
			for(int j =0;j<list_auth.size();j++){
				if(cid.equals(list_auth.get(j).getCid())){
					list_auth.get(j).setName(name);
					break;
				}
			}
		}
		return list_auth;
	}
	
	public static List<PerReportBean> changeSearchList(String name,String cid,List<PerReportBean> list){
		for(int i = 0;i<list.size();i++){
			String cid_search = list.get(i).getCid();
			String name_search = list.get(i).getName();
			if(cid_search.equals(cid) && name_search.equals(name)){
				list.get(i).setSearchFlag("1");
			}
		}
		return list;
	}
	
	
	public static List<PerReportBean> setRerportFlag(List<PerReportBean> list){
		for(int i = 0;i<list.size();i++){
			list.get(i).setReportFlag("1");
		}
		return list;
	}
	public static List<PerReportBean> setSearchFlag(List<PerReportBean> list){
		for(int i = 0;i<list.size();i++){
			list.get(i).setSearchFlag("1");
		}
		return list;
	}
	//list去重
	public static List<PerReportBean> removeDuplicate(List<PerReportBean> list){
		for(int i = 0 ; i  <  list.size()- 1 ; i ++ ){ 
		    for(int j = list.size() - 1 ; j  >  i; j -- ){ 
		      if(list.get(j).toString().equals(list.get(i).toString())){ 
		        list.remove(j); 
		      } 
		    } 
		}
	    return list;
	}
}
