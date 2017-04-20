package com.ccx.credit.util.pdf;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * 导出PDF文件工具类，根据模板和数据创建pdf文件
 * 
 * @author guopeng
 *
 */
public class ExportPDFUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ExportPDFUtil.class);
	
	public static String PDF_IMG_PATH = "/mnt/nfs/wxcredit/export/img/";
	public static String PDF_FILE_PATH = "/mnt/nfs/wxcredit/export/pdf/";
	
	static{
		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			PDF_IMG_PATH = "D:" + PDF_IMG_PATH;
			PDF_FILE_PATH = "D:" + PDF_FILE_PATH;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void createPdfFile(HttpServletRequest request, String templateName, String fileName, Map resultmap){
		
		Configuration config = new Configuration();
		config.setServletContextForTemplateLoading(request.getServletContext(), "WEB-INF/templates/pdf");
		config.setEncoding(Locale.CHINA, "utf-8");
		
		StringWriter sw = new StringWriter();
		try {
			
			Template tpl = config.getTemplate(templateName);
			tpl.process(resultmap, sw);
			sw.flush();
			String html = sw.toString().replaceAll("&", "&amp;");//ITextRenderer的缺陷，不能分辨&和&amp;(Html语法字符)
			ITextRenderer renderer = new ITextRenderer();
			OutputStream os = new FileOutputStream( PDF_FILE_PATH + fileName );
			ITextFontResolver fontResolver = renderer.getFontResolver();
			fontResolver.addFont(PDF_IMG_PATH+"basic/MicrosoftYaHei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.setDocumentFromString(html.toString());
			renderer.getSharedContext().setBaseURL("file:" + PDF_IMG_PATH);
			renderer.layout();
			renderer.createPDF(os);
			renderer.finishPDF();
			os.close();
			sw.close();
			
			logger.info("创建PDF文件：" + PDF_FILE_PATH + fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
