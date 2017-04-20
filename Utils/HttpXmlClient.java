package com.ccx.credit.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import net.sf.json.JSONObject;

public class HttpXmlClient {

	private static final Logger logger = LoggerFactory.getLogger(HttpXmlClient.class);
   
	public String get(String url) {
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(url);
	    logger.debug("HttpGetURL: " + httpGet.getURI());
	    try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
//			if(entity != null){
//				logger.debug("response内容：");
//				logger.debug(EntityUtils.toString(entity));
//			}
			return  EntityUtils.toString(response.getEntity(),"utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("get方法出错了", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("httpClient.close方法出错了", e);
			}
		}
		return "";
	}
	
	public String post(String url, String xmlParameter){
		//构造报文信息
//		String str = buildRequestBody("张三","12345612345678123x","13800138000,13100131000,18800188000");
		CloseableHttpClient httpClient = HttpClients.createDefault();
//		String URL = "http://10.1.90.39:8080/data-service/api/score";
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.addHeader("content-type", "application/xml");
		    StringEntity userEntity = new StringEntity(xmlParameter);
	        httpPost.setEntity(userEntity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			InputStream in = response.getEntity().getContent();
			//inputStream 转化为 String
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		    int i=-1; 
		    while((i=in.read())!=-1){
		       baos.write(i); 
		    } 
		    String result = baos.toString();
		    logger.debug("返回报文：" + result);
		    return result;
			//解析报文
			//paseXML(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("UnsupportedEncodingException出错了", e);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error("ClientProtocolException出错了", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException出错了", e);
		}
		return "";
	}
	public String postJson(String url, JSONObject json){
		//构造报文信息
//		String str = buildRequestBody("张三","12345612345678123x","13800138000,13100131000,18800188000");
		CloseableHttpClient httpClient = HttpClients.createDefault();
//		String URL = "http://10.1.90.39:8080/data-service/api/score";
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.addHeader("content-type", "application/json");
		    StringEntity userEntity = new StringEntity(json.toString());
	        httpPost.setEntity(userEntity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			InputStream in = response.getEntity().getContent();
			//inputStream 转化为 String
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		    int i=-1; 
		    while((i=in.read())!=-1){
		       baos.write(i); 
		    } 
		    String result = baos.toString();
		    logger.debug("返回报文：" + result);
		    return result;
			//解析报文
			//paseXML(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("UnsupportedEncodingException出错了", e);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error("ClientProtocolException出错了", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException出错了", e);
		}
		return "";
	}
	//解析报文
	private void paseXML( String xmlStr ){
		StringReader sr = new StringReader(xmlStr);
		InputSource is  = new InputSource(sr);
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			List<Element> identity = root.getChildren();
			for(int j = 0; j<identity.size(); j++) {
				List<Element> elist = identity.get(j).getChildren();
				for (int i = 0 ; i < elist.size(); i++){
					switch(i){
					case 0: System.out.println("姓名:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
					break;
					case 1: System.out.println("性别:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
					break;
					case 2: System.out.println("身份证号:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
					break;
					case 3: System.out.println("生日:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
					break;
					case 4: System.out.println("籍贯:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
					break;
					case 5: System.out.println("照片:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
					break;
					default: System.out.println("是否实名:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
					}
				}
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	//java - URLEncoder -编码
	private void encode( String para ) {
	    try {
	    	//编码
			para = URLEncoder.encode(para, "utf-8");
			//解码
			String para1 = URLDecoder.decode(para, "utf-8");
			//System.out.println(para);
		    //System.out.println(para1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//解析报文
	private void buildNegativeSearchRequestBody( String xmlStr ){
		StringReader sr = new StringReader(xmlStr);
		InputSource is  = new InputSource(sr);
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			Element identity = root.getChild("identity");
			List<Element> elist = identity.getChildren();
			for( int i = 0 ;i < elist.size();i++){
				switch(i){
				case 0: System.out.println("姓名:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
				break;
				case 1: System.out.println("性别:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
				break;
				case 2: System.out.println("身份证号:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
				break;
				case 3: System.out.println("生日:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
				break;
				case 4: System.out.println("籍贯:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
				break;
				case 5: System.out.println("照片:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
				break;
				default: System.out.println("是否实名:" + URLDecoder.decode(elist.get(i).getText(),"utf-8"));
				}
			}
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	private String buildRequestBody(String name,String identityid){
		/**********************URLEncode - begin************************/
		try {
			name = URLEncoder.encode(name, "utf-8");
			identityid = URLEncoder.encode(identityid, "utf-8");
//			mobileids = URLEncoder.encode(mobileids, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element nameElement = new Element("recordid");
		nameElement.addContent(name);
		Element idElement = new Element("type");
		idElement.addContent(identityid);
//		Element moElement = new Element("mobileids");
//		moElement.addContent(mobileids);
		Element requestElement = new Element("request");
		requestElement.addContent(nameElement).addContent(idElement);
        Document doc = new Document(requestElement);
		/*********************输出流-输出文件 - begin***************************/
        XMLOutputter XMLOut = new XMLOutputter();
        Format f = Format.getPrettyFormat();
        f.setEncoding("UTF-8");//设置XML文件的字符为UTF-8
        f.setIndent("    ");//设置缩进  
        XMLOut.setFormat(f);
        try {
			XMLOut.output(doc, new FileOutputStream("D:\\test.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*********************输出流-输出文件 - end***************************/
        /*********************XML TO String - begin **********************/
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");//设置xml文件的字符为gb2312，解决中文问题
        XMLOutputter xmlout = new XMLOutputter(format);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
			xmlout.output(doc,bo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String xmlStr = bo.toString();
       // System.out.println(xmlStr);
        /*********************XML TO String - end **********************/
		return xmlStr;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpXmlClient obj = new HttpXmlClient();
		String xmlParameter = obj.buildRequestBody("323456","5");
		obj.paseXML(obj.post("http://10.1.90.39:8080/data-service/api/discredit/details", xmlParameter));
		//obj.get();
		//obj.buildRequestBody("汉字", "中文", "乱码");
		//obj.encode("乱码");
		//obj.post();
		
	}

}
