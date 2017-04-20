package com.ccx.credit.util.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ccx.credit.assess.model.AuthorizeBean;
import com.ccx.credit.model.UserBean;
import com.ccx.credit.util.MD5;
import com.ccx.credit.util.PropertiesUtil;

public class UploadUtil {
    private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);
    
	public static String UPLOAD_EXCEL_FILE_PATH = "/mnt/nfs/wxcredit/upload/excel/";
	
	static{
		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			UPLOAD_EXCEL_FILE_PATH = "D:" + UPLOAD_EXCEL_FILE_PATH;
		}
	}

	public static String UPLOAD_EXCEL_ZIP_PATH = "/mnt/nfs/wxcredit/upload/zip/";
	
	static{
		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			UPLOAD_EXCEL_ZIP_PATH = "D:" + UPLOAD_EXCEL_ZIP_PATH;
		}
	}
	
	public String uploadTool ( HttpServletRequest request, HttpServletResponse response ){
	        String newFileName = null;	
		    //文件保存目录路径
	    	String savePath = "/opt/upload/";
            //定义允许上传的文件扩展名
	    	HashMap<String, String> extMap = new HashMap<String, String>();
	    	extMap.put("image", "jpg,png,bmp");//图片允许上传的格式
	    	extMap.put("file", "pdf");//文件允许上传的格式
	    	//最大文件大小 - 1M
	    	long maxSize = 1000000;
	    	try {
	    		if(!ServletFileUpload.isMultipartContent(request)){
		    		logger.info("上传文件为空");
		    		return "0";
		    	}
		    	
	    		MultipartHttpServletRequest mulipartReuqest = (MultipartHttpServletRequest )request;
		    	MultipartFile uploadFile = mulipartReuqest.getFile("localUrl");
		    	if ( uploadFile == null || uploadFile.isEmpty()){
		    		logger.info("没有得到上传文件!");
		    		return "0";
		    	}
		    	
		    	//检查扩展名
		    	String fileName = uploadFile.getOriginalFilename();
		    	String fileExt = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//扩展名
		    	fileExt = fileExt.toLowerCase();//扩展名转化为小写
		        if(!Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt) && !Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
					logger.info("上传文件扩展名是不允许的扩展名。");
					return "-1";
				}
		        
		        //检查图片大小
		    	long fileSize = uploadFile.getSize();
		    	if( fileSize > maxSize ){
		    		logger.info("上传文件大小超过限制!");
		    		return "-2";
		    	}
		        
		    	//根据扩展名设定上传文件的分目录 image file等 
		        String dirName = null;
		        if(Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt) ){
		        	dirName = "file";
		        } else if (Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
		        	dirName = "image";
		        }
		    	
		    	//更新文件存放目录 - 文件存放目录按天和类型存放 - 目录一级一级创建
		    	savePath += dirName + "/";
		    	File saveDirFile = new File(savePath);
		    	if (!saveDirFile.exists()) {
		    		saveDirFile.mkdirs();
		    	}
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    	String ymd = sdf.format(new Date());
		    	savePath += ymd + "/";
		    	File dirFile = new File(savePath);
		    	if (!dirFile.exists()) {
		    		dirFile.mkdirs();
		    	}
		    	
		    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			    newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;//保存的图像名
			
			    File uploadedFile = new File(savePath, newFileName);//创建新图片文件
			    uploadFile.transferTo(uploadedFile);//保存图片内容
			    
			} catch (Exception e) {
				logger.error("上传文件过程中异常", e);
				return "-3";
			}
	    	
	    	return newFileName;
        }
	
 	public AuthorizeBean uploadAuthorize ( HttpServletRequest request, HttpServletResponse response, AuthorizeBean authorize ,String fileId){
        String newFileName = null;
	    //文件保存目录路径
        String savePath = PropertiesUtil.getProperty("authorize.savePath");
        if(authorize.getFilePath().equals("")){
        	savePath +="other"+File.separator;
        }else{
        	savePath += authorize.getFilePath() + File.separator;
        }
        //定义允许上传的文件扩展名
    	HashMap<String, String> extMap = new HashMap<String, String>();
    	extMap.put("image", "jpg,png,bmp");//图片允许上传的格式
    	extMap.put("file", "pdf");//文件允许上传的格式
    	//最大文件大小 - 1M
    	long maxSize = 1000000;
    	try {
    		if(!ServletFileUpload.isMultipartContent(request)){
	    		logger.info("上传文件为空");
	    		authorize.setUpType("0");
	    		return authorize;
	    	}
	    	
    		MultipartHttpServletRequest mulipartReuqest = (MultipartHttpServletRequest )request;
    		MultipartFile uploadFile = null;
    		if( fileId==null||fileId.equals("")){
    			uploadFile = mulipartReuqest.getFile("localUrl");
    		}else{
    			uploadFile = mulipartReuqest.getFile(fileId);
    			
    		}
	    	if ( uploadFile == null || uploadFile.isEmpty()){
	    		logger.info("没有得到上传文件!");
	    		authorize.setUpType("0");
	    		return authorize;
	    	}
	    	
	    	//检查扩展名
	    	String fileName = uploadFile.getOriginalFilename();
	    	String fileExt = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//扩展名
	    	fileExt = fileExt.toLowerCase();//扩展名转化为小写
	    	fileName = fileName.substring(0, fileName.lastIndexOf("."));
	        if(!Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt) && !Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
				logger.info("上传文件扩展名是不允许的扩展名!");
	    		authorize.setUpType("-1");
				return authorize;
			}
	        
	    	//检查文件名是否和身份证号一致
	        System.out.println(fileName);
	        if(!fileName.equals(authorize.getCid())){
	        	logger.info("上传的文件名必须和身份证号一致");
	        	authorize.setUpType("-4");
	        	return authorize;
	        }
	        
	        //检查图片大小
	    	long fileSize = uploadFile.getSize();
	    	if( fileSize > maxSize ){
	    		logger.info("上传文件大小超过限制!");
	    		authorize.setUpType("-2");
	    		return authorize;
	    	}
	        
	    	//根据扩展名设定上传文件的分目录 image file等 
	        String dirName = null;
	        if(Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt) ){
	        	dirName = "file";
	        } else if (Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
	        	dirName = "image";
	        }
	    	
	    	//更新文件存放目录 - 文件存放目录按天和类型存放 - 目录一级一级创建
	    	savePath += dirName + File.separator;
	    	File saveDirFile = new File(savePath);
	    	if (!saveDirFile.exists()) {
	    		saveDirFile.mkdirs();
	    	}
	    	SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
	    	SimpleDateFormat sdf_m = new SimpleDateFormat("MM");
	    	String y = sdf_y.format(new Date());
	    	String m = sdf_m.format(new Date());
	    	savePath += y + File.separator + m + File.separator;
	    	File dirFile = new File(savePath);
	    	if (!dirFile.exists()) {
	    		dirFile.mkdirs();
	    	}
	    	
	    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    	String cid = MD5.encryption(authorize.getCid()).substring(8, 24);
		    newFileName = cid + authorize.getInstitutionId() + df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;//保存的图像名
		
		    File uploadedFile = new File(savePath, newFileName);//创建新图片文件
		    uploadFile.transferTo(uploadedFile);//保存图片内容
		    
		    authorize.setState(1);
		    authorize.setFileName(newFileName);
		    authorize.setFilePath(savePath);
		    authorize.setFileType(fileExt);
		    authorize.setFileSize((int) fileSize);
		    authorize.setUpType("1");
		    
		} catch (Exception e) {
			logger.error("上传文件过程中异常", e);
    		authorize.setUpType("-3");
			return authorize;
		}
    	
    	return authorize;
    }
 	
	public String uploadPhoto ( String type,String file,HttpServletRequest request, HttpServletResponse response ){
        String newFileName = null;	
	    //文件保存目录路径
        //String savePath = "/opt/upload/";
       String savePath = PropertiesUtil.getProperty("authorize.savePath");
       // String savePath = "/mnt/nfs/wxcredit/aliian/img";
    	if(type=="debt"){
    		savePath = "/mnt/nfs/wxcredit/allian/";
    		//savePath=savePath+"debt/";
    	}
        //定义允许上传的文件扩展名
    	HashMap<String, String> extMap = new HashMap<String, String>();
    	extMap.put("image", "jpg,png,bmp");//图片允许上传的格式
    	extMap.put("file", "pdf");//文件允许上传的格式
    	//最大文件大小 - 1M
    	long maxSize = 1000000;
    	try {
    		if(!ServletFileUpload.isMultipartContent(request)){
	    		logger.info("上传文件为空");
	    		return "0";
	    	}
	    	
    		MultipartHttpServletRequest mulipartReuqest = (MultipartHttpServletRequest )request;
	    	MultipartFile uploadFile = mulipartReuqest.getFile(file);
	    	if ( uploadFile == null || uploadFile.isEmpty()){
	    		logger.info("没有得到上传文件!");
	    		return "0";
	    	}
	    	
	    	//检查扩展名
	    	String fileName = uploadFile.getOriginalFilename();
	    	String fileExt = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//扩展名
	    	fileExt = fileExt.toLowerCase();//扩展名转化为小写
	        if(!Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt) && !Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
				logger.info("上传文件扩展名是不允许的扩展名。");
				return "-1";
			}
	        
	        //检查图片大小
	    	long fileSize = uploadFile.getSize();
	    	if( fileSize > maxSize ){
	    		logger.info("上传文件大小超过限制!");
	    		return "-2";
	    	}
	        
	    	//根据扩展名设定上传文件的分目录 image file等 
	        String dirName = null;
	        if(Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt) ){
	        	dirName = "file";
	        } else if (Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
	        	//dirName = "image";
	        	dirName = "img";
	        }
	    	
	    	//更新文件存放目录 - 文件存放目录按天和类型存放 - 目录一级一级创建
	    	savePath += dirName + "/";
	    	File saveDirFile = new File(savePath);
	    	if (!saveDirFile.exists()) {
	    		saveDirFile.mkdirs();
	    	}
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    	String ymd = sdf.format(new Date());
	    	savePath += ymd + "/";
	    	File dirFile = new File(savePath);
	    	if (!dirFile.exists()) {
	    		dirFile.mkdirs();
	    	}
	    	
	    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		    newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;//保存的图像名
		
		    File uploadedFile = new File(savePath, newFileName);//创建新图片文件
		    uploadFile.transferTo(uploadedFile);//保存图片内容
		    
		    newFileName = ymd + "/" + newFileName;
		} catch (Exception e) {
			logger.error("上传文件过程中异常", e);
			return "-3";
		}
    	return newFileName;
    }
	
	/**
	 * 上传文件返回结果集XML
	 * @param type		返回类型
	 * @param content	返回信息
	 * @return
	 */
	public String buildResponseMessage(int type, String content) {
		Element rootElement = new Element("root");
		Element typeElement = new Element("type");
		typeElement.addContent(String.valueOf(type));
		Element contentElement = new Element("content");
		contentElement.addContent(content);
		rootElement.addContent(typeElement);
		rootElement.addContent(contentElement);
		Document doc = new Document(rootElement);
		Format format = Format.getPrettyFormat();
		format.setIndent("  ");
		format.setEncoding("UTF-8");
		XMLOutputter xmlOuter = new XMLOutputter(format);
		return xmlOuter.outputString(doc);
	}
	
	/**
	 * 将上传文件存到服务器中，方便以后查询问题
	 */
	public static void saveUploadFile(MultipartFile uploadFile,UserBean user){
		
		String nowTime = getNowTime();
		String account = user.getAccount();
		
		try {
			System.out.println("上传的文件名字："+uploadFile.getOriginalFilename());
			String fileName = account + "_" + nowTime + "_" + uploadFile.getOriginalFilename();
			String savaFilePath = null;
			
			//判断上传文件是excel还是zip，存储在服务器不同目录
			if(fileName.indexOf("xls")!= -1 || fileName.indexOf("xlxs")!= -1){
				savaFilePath = UploadUtil.UPLOAD_EXCEL_FILE_PATH+fileName;
			}else if(fileName.indexOf("zip")!= -1){
				savaFilePath = UploadUtil.UPLOAD_EXCEL_ZIP_PATH+fileName;
			}
			logger.info("上传的文件/保存路径:"+savaFilePath);
			File file = new File(savaFilePath);
			
			InputStream is =  uploadFile.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = new byte[1024];
			while(is.read(b)!=-1){
				fos.write(b);
			}
			fos.flush();
			fos.close();
			is.close();
		} catch (IOException e) {
			logger.error("上传的文件保存到服务器异常");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getNowTime(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}
