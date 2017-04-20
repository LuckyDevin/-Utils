package com.ccx.credit.util.excel;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSONObject;
import com.ccx.credit.monitor.model.entity.ChangeDetail;
import com.ccx.credit.monitor.model.entity.MonitorFullReport;
import com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditBasic;
import com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditInfoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
public class ExcelForMonitorAllInfo {

	private static final String[] entityName = {
			"失信被执行信息","被执行信息",  "裁判文书信息","开庭公告信息","执行公告信息","失信公告信息","法院公告信息","网贷黑名单信息","案件流程信息","曝光台信息",//新系统企业司法
			"法院公告信息","被执行信息","失信被执行信息",//旧系统企业司法//个人司法信息
			"网贷黑名单信息",//个人网贷黑名单
			"公司注册信息","股东信息","主要人员信息","分支机构信息","清算信息","动产抵押登记信息 ","股权出质登记信息","行政处罚信息","经营异常信息","严重违法信息","抽查检查信息","工商变更信息",//新系统企业工商
			"公司注册信息","股东信息","高管信息","法定代表人对外投资信息","法定代表人其他任职信息","企业对外投资信息","分支机构信息","股权出质历史信息","动产抵押信息","动产抵押物信息","股权冻结历史信息","清算信息","行政处罚历史信息","变更信息 ",//旧系统企业工商
			"专利信息","商标信息",//企业知识产权
			"网络负面信息",//网络负面信息
			"法人信息","股东信息","高管信息 ","行政处罚历史信息"//个人工商
		};
	private static final String[] entityField = {
			"NewExtRiskPSxbzx","NewExtRiskPZgfzx",  "FaHaiMonitorCpwsInfo","FaHaiMonitorKtggInfo","FaHaiMonitorZxggInfo","FaHaiMonitorSxggInfo","FaHaiMonitorFyggInfo","FaHaiMonitorWdhmdInfo","FaHaiMonitorAjlcInfo","FaHaiMonitorBgtInfo", //新系统企业司法
			"CourtAnnc","EnforcedInfo","DishonestInfo",  //旧系统企业司法//个人司法信息
			"NetLoanBL",//个人网贷黑名单
			"EntCreditBasic","EntCreditShareHolder","EntCreditPersonnel","EntCreditBranch","EntCreditClear","EntCreditMortgage","EntCreditPledge","EntCreditPunish","EntCreditAbnormal","EntCreditViolation","EntCreditCheck","EntCreditAlt",//新系统企业工商
			"Basic","Shareholder","Person","FrInv","FrPosition","EntInv","Filiation","SharesImpawn","MorDetail","MorGuaInfo","SharesFrost","Liquidation","CaseInfo","Alter",//旧系统企业工商
			"PatentHeader","TradeMarkHeader",//企业知识产权
			"CheaterBean",//网络负面信息
			"RyPosFr","RyPosSha","RyPosPer","PersonCaseInfo"//个人工商
		};
	
	private static HSSFCellStyle style;
	private static HSSFCellStyle style2;
	private static HSSFCellStyle style3;
	private static HSSFCellStyle style4;
	private static void initwork(HSSFWorkbook workbook) {
		// 声明一个工作薄
		
				
		HSSFPalette customPalette = workbook.getCustomPalette();  
				// 生成一个样式
				
				style = workbook.createCellStyle();
				
				// 设置这些样式
				//HSSFColor.SKY_BLUE，CORNFLOWER_BLUE
				style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				// 生成一个字体
				HSSFFont font = workbook.createFont();
				font.setColor(HSSFColor.BLACK.index);
				font.setFontHeightInPoints((short) 12);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				// 把字体应用到当前的样式
				style.setFont(font);
				
				// 生成并设置另一个样式
				style2 = workbook.createCellStyle();
				style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
				style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				style3 = workbook.createCellStyle();
				style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
				style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				style3.setWrapText(true);
				
				style4 = workbook.createCellStyle();
				customPalette.setColorAtIndex(HSSFColor.RED.index, (byte) 248, (byte) 205, (byte) 207);  
				style4.setFillForegroundColor(HSSFColor.RED.index);
				style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				style4.setWrapText(true);
				
				// 生成另一个字体
				HSSFFont font2 = workbook.createFont();
				font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
				
				// 把字体应用到当前的样式
				style2.setFont(font2);
	}

	/**
	 * 
	 * @param MonitorFullReport
	 * @param path
	 * @param flag 是否生成文件   true 是  ，false 否
	 * @return
	 */
	public static byte[] exportMonitorExcel(MonitorFullReport monitorReport,String path,boolean flag) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		initwork( workbook);
		
		//创建工商信息sheet
		EntCreditInfoEntity  entInfo = monitorReport.getEntCreditInfo();
		createSheetOneStyle("工商信息报告", workbook, entInfo);
	
		
		if(flag){
			File file = new File(path+monitorReport.getName()+".xls"); 
			try {
				OutputStream fos = new FileOutputStream(file); 
				workbook.write(fos);
				fos.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}
	
	 
	/** 
	 * 创建工商信息主体信息excel sheet
	 * */
	private static void createSheetOneStyle(String title,HSSFWorkbook workbook,EntCreditInfoEntity entInfo){
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		
		HSSFRow row = null;
		HSSFCell cell = null;
		CellRangeAddress  cra= null;
		Integer rowIndex = 0;
		
		/**
		 * 工商信息
		 **/

		//主体信息
			ArrayList<EntCreditBasic> list = new ArrayList<EntCreditBasic>();
			list.add(entInfo.getBasics());
			setCellValueMethod(list.toArray(),"主体信息",sheet,"EntCreditBasic");
		
		//股东信息
			setRowCellValueMethod(entInfo.getShareholders().toArray(),"股东信息",sheet,"EntCreditShareHolder");
		//工商变更信息
			setRowCellValueMethod(entInfo.getAlts().toArray(),"工商变更信息",sheet,"EntCreditAlt");
		//主要人员信息（高管信息）
			setRowCellValueMethod(entInfo.getKeypersonnels().toArray(),"主要人员信息（高管信息）",sheet,"EntCreditPersonnel");
		//分支机构信息
			setRowCellValueMethod(entInfo.getBranches().toArray(),"分支机构信息",sheet,"EntCreditBranch");
		//清算信息
			setRowCellValueMethod(entInfo.getClears().toArray(),"清算信息",sheet,"EntCreditClear");
		//动产抵押信息
			setRowCellValueMethod(entInfo.getMortgages().toArray(),"动产抵押信息",sheet,"EntCreditMortgage");
		//股权出质登记信息
			setRowCellValueMethod(entInfo.getPledges().toArray(),"股权出质登记信息",sheet,"EntCreditPledge");
		//行政处罚信息
			setRowCellValueMethod(entInfo.getPunishes().toArray(),"行政处罚信息",sheet,"EntCreditPunish");
		//经营异常信息
			setRowCellValueMethod(entInfo.getAbnormals().toArray(),"经营异常信息",sheet,"EntCreditAbnormal");
		//严重违法信息
			setRowCellValueMethod(entInfo.getViolations().toArray(),"严重违法信息",sheet,"EntCreditViolation");
		//抽查检查信息
			setRowCellValueMethod(entInfo.getChecks().toArray(),"抽查检查信息",sheet,"EntCreditCheck");
	}
	
	
	
	private static Integer createTableContent(Integer rowIndex,HSSFSheet sheet,HSSFRow row,HSSFCell cell,CellRangeAddress cra,ChangeDetail changeDetail ){
			Map<String, String>  map = null;
			Object obj = getClassExample(getFullClassNameBySimpleName(changeDetail.getProject()));
			
			if(changeDetail.getBeforeContent()!=null&&changeDetail.getBeforeContent().size()!=0){
				map = changeDetail.getBeforeContent();
			}else{
				map = changeDetail.getAfterContent();
			}
			int i = 0;
			String beforecontent="";
			String aftercontent="";
			for (String key : map.keySet()) {
				row = sheet.createRow(rowIndex++); row.setHeight((short) 260);
				if(changeDetail.getBeforeContent()!=null){beforecontent=changeDetail.getBeforeContent().get(key);}
				if(changeDetail.getAfterContent()!=null){aftercontent=changeDetail.getAfterContent().get(key);}
				
				
				if(i==0){
					cell = row.createCell(0);cell.setCellStyle(style3);cell.setCellValue(changeDetail.getType());
				}
				if(changeDetail.getFieldNames().contains(key)){
					cell = row.createCell(1); cell.setCellStyle(style4);cell.setCellValue(getFieldValueByName(key+"_String",obj));
					cell = row.createCell(2); cell.setCellStyle(style4);cell.setCellValue(beforecontent);
					cell = row.createCell(3); cell.setCellStyle(style4);cell.setCellValue(aftercontent);
				}else{
					cell = row.createCell(1); cell.setCellStyle(style3);cell.setCellValue(getFieldValueByName(key+"_String",obj));
					cell = row.createCell(2); cell.setCellStyle(style3);cell.setCellValue(beforecontent);
					cell = row.createCell(3); cell.setCellStyle(style3);cell.setCellValue(aftercontent);
				}
				
			}
			cra = new CellRangeAddress(rowIndex-map.size(), rowIndex-1, 0, 0);
			sheet.addMergedRegion(cra);
			
		return rowIndex+1;
	}

	
	/** 
	 * 根据属性名获取属性值 
	 * */  
	   private static String getFieldValueByName(String fieldName, Object o) {  
	      try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	          String getter = "get" + firstLetter + fieldName.substring(1);    
	          Method method = o.getClass().getMethod(getter, new Class[] {});    
	          String value = (String) method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	          e.printStackTrace();
	          return null;    
	      }    
	  }  
	   
   private static String[] getFieldnames(Object o) { 
	   List<String> arr = new ArrayList<String>();
	   Field[] fields = o.getClass().getDeclaredFields();
	   for(int i = 0;i<fields.length;i++){
		   if(fields[i].getName().endsWith("_String")){
			   arr.add(fields[i].getName().replace("_String", ""));
		   }
	   }
	   String[] s = new String[arr.size()]; 
	   for(int i = 0;i<arr.size();i++){
		   s[i] = arr.get(i);
	   }
	return s ;
	   
   }  
		    
	   
   /** 
	 * 根据完整类名创建实例化对象
	 * */  
	private static Object getClassExample(String className) {  
		   Class<?> clazz;
		   Object obj = null;
			try {
				clazz = Class.forName(className);
				obj =  clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return obj;
	}  

	
	/**
	 * 根据简单类名补全完整类名
	 */
	private static String getFullClassNameBySimpleName(String simpleName){
		String fullName = "";
		if(simpleName.equals("NewExtRiskPSxbzx")){fullName="com.ccx.credit.monitor.model.riskNewModel.NewExtRiskPSxbzx";}
		else if(simpleName.equals("NewExtRiskPZgfzx")){fullName="com.ccx.credit.monitor.model.riskNewModel.NewExtRiskPZgfzx";}
		else if(simpleName.equals("FaHaiMonitorCpwsInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorCpwsInfo";}
		else if(simpleName.equals("FaHaiMonitorKtggInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorKtggInfo";}
		else if(simpleName.equals("FaHaiMonitorZxggInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorZxggInfo";}
		else if(simpleName.equals("FaHaiMonitorSxggInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorSxggInfo";}
		else if(simpleName.equals("FaHaiMonitorFyggInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorFyggInfo";}
		else if(simpleName.equals("FaHaiMonitorWdhmdInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorWdhmdInfo";}
		else if(simpleName.equals("FaHaiMonitorAjlcInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorAjlcInfo";}
		else if(simpleName.equals("FaHaiMonitorBgtInfo")){fullName="com.ccx.credit.monitor.model.risk_fahai.FaHaiMonitorBgtInfo";}
		else if(simpleName.equals("CourtAnnc")){fullName="com.ccx.credit.monitor.model.riskOldModel.CourtAnnc";}
		else if(simpleName.equals("EnforcedInfo")){fullName="com.ccx.credit.monitor.model.riskOldModel.EnforcedInfo";}
		else if(simpleName.equals("DishonestInfo")){fullName="com.ccx.credit.monitor.model.riskOldModel.DishonestInfo";}
		else if(simpleName.equals("NetLoanBL")){fullName="com.ccx.credit.monitor.model.riskNewModel.NetLoanBL";}
		else if(simpleName.equals("EntCreditBasic")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditBasic";}
		else if(simpleName.equals("EntCreditShareHolder")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditShareHolder";}
		else if(simpleName.equals("EntCreditPersonnel")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditPersonnel";}
		else if(simpleName.equals("EntCreditBranch")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditBranch";}
		else if(simpleName.equals("EntCreditClear")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditClear";}
		else if(simpleName.equals("EntCreditMortgage")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditMortgage";}
		else if(simpleName.equals("EntCreditPledge")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditPledge";}
		else if(simpleName.equals("EntCreditPunish")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditPunish";}
		else if(simpleName.equals("EntCreditAbnormal")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditAbnormal";}
		else if(simpleName.equals("EntCreditViolation")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditViolation";}
		else if(simpleName.equals("EntCreditCheck")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditCheck";}
		else if(simpleName.equals("EntCreditAlt")){fullName="com.ccx.credit.monitor.model.icFreeModel.entCredit.EntCreditAlt";}
		else if(simpleName.equals("Basic")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.Basic";}
		else if(simpleName.equals("Shareholder")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.Shareholder";}
		else if(simpleName.equals("Person")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.Person";}
		else if(simpleName.equals("FrInv")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.FrInv";}
		else if(simpleName.equals("FrPosition")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.FrPosition";}
		else if(simpleName.equals("EntInv")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.EntInv";}
		else if(simpleName.equals("Filiation")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.Filiation";}
		else if(simpleName.equals("SharesImpawn")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.SharesImpawn";}
		else if(simpleName.equals("MorDetail")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.MorDetail";}
		else if(simpleName.equals("MorGuaInfo")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.MorGuaInfo";}
		else if(simpleName.equals("SharesFrost")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.SharesFrost";}
		else if(simpleName.equals("Liquidation")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.Liquidation";}
		else if(simpleName.equals("CaseInfo")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.CaseInfo";}
		else if(simpleName.equals("Alter")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.Alter";}
		else if(simpleName.equals("PatentHeader")){fullName="com.ccx.credit.monitor.model.knowledgeright.PatentHeader";}
		else if(simpleName.equals("TradeMarkHeader")){fullName="com.ccx.credit.monitor.model.knowledgeright.TradeMarkHeader";}
		else if(simpleName.equals("RyPosFr")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.RyPosFr";}
		else if(simpleName.equals("RyPosSha")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.RyPosSha";}
		else if(simpleName.equals("RyPosPer")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.RyPosPer";}
		else if(simpleName.equals("PersonCaseInfo")){fullName="com.ccx.credit.monitor.model.icNotFreeModel.entCredit.PersonCaseInfo";}
		else if(simpleName.equals("CheaterBean")){fullName="com.ccx.credit.monitor.model.riskNewModel.CheaterBean";}
		return fullName;
	}
	
	/**
	 * 竖版表格 setValue
	 * 
	 * @param list			当前分类内容
	 * @param arrayData		标题-字段对应
	 * @param title			分类标题
	 * @param rowIndex		行数
	 * @param sheet			大标题
	 * @return
	 */
	public static void setCellValueMethod(Object[] objects, String title, HSSFSheet sheet,String clsName) {
		int rowIndex = sheet.getLastRowNum()+1;
		List<Map<String, Object>> list = beanToMapList(objects);
		// 设置表格默认列宽度为25个字节
		Object obj = getClassExample(getFullClassNameBySimpleName(clsName));
		
		String[] arrayData = getFieldnames(obj);
		
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		//sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);
		Integer array = 0;

		if (list != null) {

			for (int k = 0;k<list.size();k++) {
					for (int i = 0; i < arrayData.length; i++) {
						row = sheet.createRow(rowIndex++);
						row.setHeight((short) 350);
						cell = row.createCell(0);
						cell.setCellStyle(style2);
						cell.setCellValue(getFieldValueByName(arrayData[i]+"_String",obj));
						
						cell = row.createCell(1);
						cell.setCellStyle(style3);
						cell.setCellValue((String)list.get(k).get(arrayData[i]));
				}
			}
			
		}else{
			
			if(arrayData != null){
				
				cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
				sheet.addMergedRegion(cra);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);
				cell = row.createCell(0);
				cell.setCellStyle(style3);
				cell.setCellValue("暂无信息");
				
			}else{
				rowIndex--;
			}
			
		}
		
	}

	
	
	/**
	 * 横版表格 setValue
	 * @param list
	 * @param arrayData
	 * @param title
	 * @param rowIndex
	 * @param sheet
	 * @return
	 */
	public static void setRowCellValueMethod(Object[] objects, String title, HSSFSheet sheet,String clsName) {
		List<Map<String, Object>> list = beanToMapList(objects);
		Integer rowIndex = sheet.getLastRowNum() + 2;
		// 设置表格默认列宽度为25个字节
		Object obj = getClassExample(getFullClassNameBySimpleName(clsName));
		
		String[] arrayData = getFieldnames(obj);
		
		sheet.setDefaultColumnWidth((short) 40);
		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, arrayData.length-1);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 450);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);

		if (list != null) {
			
			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 350);
			if(null != arrayData){
				for (int i = 0; i < arrayData.length; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(style2);
					cell.setCellValue(getFieldValueByName(arrayData[i]+"_String",obj));
				}
			} 
			
			for (Map<String, Object> datas : list) {
				row.setHeight((short) 350);
				row = sheet.createRow(rowIndex++);
				for (int i = 0; i < arrayData.length; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(style3);	
				    cell.setCellValue((datas.get(arrayData[i])==null||datas.get(arrayData[i]).equals(""))?"--":datas.get(arrayData[i])+"");	
				}
			}
		} else {
			
			if(arrayData != null){
				
				cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
				sheet.addMergedRegion(cra);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);
				cell = row.createCell(0);
				cell.setCellStyle(style3);
				cell.setCellValue("暂无信息");
				
			}
			
		}
	}
	
	
	private static List<Map<String, Object>>  beanToMapList(Object[] objects){
		List<Map<String, Object>>  lists =  new ArrayList<Map<String,Object>>();
		for(int i = 0;i<objects.length;i++){
			Map<String, Object> map = new HashMap<String,Object>();
			
			BeanInfo beanInfo;
			try {
				beanInfo = Introspector.getBeanInfo(objects[i].getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for(PropertyDescriptor property:propertyDescriptors){
					String key = property.getName();
					if(!key.equals("class")){
						Method getter = property.getReadMethod();
						Object value = getter.invoke(objects[i]);
						map.put(key,value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			lists.add(map);
		}
		return lists;
	} 
	
	
	
	
}




