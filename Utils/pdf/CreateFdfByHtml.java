package com.ccx.credit.util.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CreateFdfByHtml {

	public static final String PDF_PATH =CreateFdfByHtml.class.getResource("").getPath();
/*	/**
	 * 使用flying saucer 和 iText生产pdf
	 * @param html 传入的模板路径
	 * @param path 产生pdf的路径
	 * @param pdfName pdf的名称
	 * @return
	 *//*
	public static void html2Pdf(String html,String path,String pdfName) {
		try {
			ITextRenderer renderer = new ITextRenderer();
			OutputStream os = new FileOutputStream(path + pdfName);
			ITextFontResolver fontResolver = renderer.getFontResolver();
			if(System.getProperty("os.name").indexOf("Windows") > -1){
				fontResolver.addFont("C:/work/workspace/echarts/MicrosoftYaHei.ttf",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			}else{
				fontResolver.addFont("/usr/share/fonts/chinese/TrueType/MicrosoftYaHei.ttf",
						BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			}
			renderer.getSharedContext().setBaseURL("file:/"+PDF_PATH+"img/");
			renderer.setDocumentFromString(html.toString());
			renderer.layout();
			renderer.createPDF(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * 使用flying saucer 和 iText生产pdf
	 * @param html 传入的模板路径
	 * @param path 产生pdf的路径
	 * @param pdfName pdf的名称
	 * @return
	 * @throws IOException 
	 * @throws TemplateException 
	 * @throws DocumentException 
	 */
	@SuppressWarnings({"rawtypes", "deprecation" })
	public static void freeMarker2Pdf(HttpServletRequest request,String path,Map map,String pdfName,String templateName) throws TemplateException, IOException, DocumentException {
			Configuration config = new Configuration();
			config.setServletContextForTemplateLoading(request.getServletContext(), "WEB-INF/templates");
			config.setEncoding(Locale.CHINA, "utf-8");
			StringWriter sw = new StringWriter();
			Template tpl = config.getTemplate(templateName);
			tpl.process(map,sw);
			sw.flush();
			String html = sw.toString().replaceAll("&", "&amp;");//ITextRenderer的缺陷，不能分辨&和&amp;(Html语法字符)
			ITextRenderer renderer = new ITextRenderer();
			OutputStream os = new FileOutputStream( path + pdfName );
			ITextFontResolver fontResolver = renderer.getFontResolver();

			fontResolver.addFont(PDF_PATH+"resources/MicrosoftYaHei.ttf",
			BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.setDocumentFromString(html.toString());
			renderer.getSharedContext().setBaseURL("file:"+PDF_PATH+"img/");
			renderer.layout();
			renderer.createPDF(os);
			renderer.finishPDF();
			os.close();
	}
	
	
}
