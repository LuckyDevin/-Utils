package com.ccx.credit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccx.credit.util.pdf.ExportPDFUtil;
import com.ccx.credit.util.word.ExportWORDUtil;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 返回base64解码图片string
	 * @param url
	 * @return
	 */
	public static String getImageBase64Str(String url) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(url);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);

	}
	
	/**
	 * 设置导出文件名称
	 * 根据浏览器不同编码方式不同
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String formatFileName(HttpServletRequest request, String fileName) {
		
		try {
			String upperCase = request.getHeader("User-Agent").toUpperCase();
			if (upperCase.indexOf("MSIE") > 0 || upperCase.indexOf("EDGE") > 0 || upperCase.indexOf("RV:11.0")>0) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
		} catch (Exception e) {
			logger.error("获取文件名称错误：");
			e.printStackTrace();
		}
		
		return fileName;
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param exportType		导出文件后缀（目前之后pdf和doc）
	 * @param bodyName		报告主体名称
	 * @param titleName		模块名称
	 * @param transId			订单号
	 * @param ftlFileName		模板文件名称
	 * @param map				数据集合
	 */
	public static void exportUtils(HttpServletRequest request, HttpServletResponse response, String exportType, String bodyName, String titleName, String transId, String ftlFileName, Map<String, Object> map) {

		File file = null;
		String saveName = titleName + "-" + bodyName + "-" + transId + "." + exportType; // 用freemarker生成文件名称
		String downloadName = FileUtil.formatFileName(request, titleName + "-" + bodyName + "." + exportType);
		if(exportType.equals("pdf")){
			ExportPDFUtil.createPdfFile(request, ftlFileName, saveName, map);
			file = new File(ExportPDFUtil.PDF_FILE_PATH + saveName);
		}else if(exportType.equals("doc")){
			ExportWORDUtil.createWordFile(request, ftlFileName, saveName, map);
			file = new File(ExportWORDUtil.WORD_FILE_PATH + saveName);
		}
		
		// 文件下载
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment; fileName=" + downloadName);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				outputStream.write(b, 0, length);
			}
			outputStream.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			outputStream = null;
			inputStream = null;
		}

	}
	
	
}
