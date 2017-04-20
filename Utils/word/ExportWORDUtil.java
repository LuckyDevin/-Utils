package com.ccx.credit.util.word;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.DocumentException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * 导出WORD文件工具类，根据模板和数据创建doc文件
 * 
 * @author guopeng
 *
 */
public class ExportWORDUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ExportWORDUtil.class);
	
	public static String WORD_FILE_PATH = "/mnt/nfs/wxcredit/export/word/";
	
	static{
		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			WORD_FILE_PATH = "D:" + WORD_FILE_PATH;
		}
	}

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
	@SuppressWarnings({"rawtypes" })
	public static void createWordFile(HttpServletRequest request, String templateName, String fileName, Map map) {
		Configuration config = new Configuration();
		config.setServletContextForTemplateLoading(request.getServletContext(), "WEB-INF/templates/word");
		config.setEncoding(Locale.CHINA, "utf-8");
		Template tpl;
		try {
			tpl = config.getTemplate(templateName);
			Writer out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream( WORD_FILE_PATH + fileName ), "utf-8"));
			tpl.process(map,out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		logger.info("创建WORD文件：" + WORD_FILE_PATH + fileName);
	}
	
}
