package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
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

import com.ccx.credit.monitor.model.entity.ChangeDetail;
import com.ccx.credit.monitor.model.entity.MonitorReport;

@SuppressWarnings("deprecation")
public class ExcelForMonitor {

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
	 * @param monitorReport
	 * @param path
	 * @param flag 是否生成文件   true 是  ，false 否
	 * @return
	 */
	public static byte[] exportMonitorExcel(MonitorReport monitorReport,String path,boolean flag) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		initwork( workbook);
		boolean b = false;
		if(monitorReport.getChangeDetails_IC().size()!=0){
			createSheet("工商信息变更",workbook,monitorReport.getChangeDetails_IC());
			b=true;
		}
		if(monitorReport.getChangeDetails_JUD().size()!=0){
			createSheet("司法信息变更",workbook,monitorReport.getChangeDetails_JUD());
			b=true;
		}
		if(monitorReport.getChangeDetails_KNO()!=null&&monitorReport.getChangeDetails_KNO().size()!=0){
			createSheet("知识产权信息变更",workbook,monitorReport.getChangeDetails_KNO());
			b=true;
		}
		if(monitorReport.getChangeDetails_BLA()!=null&&monitorReport.getChangeDetails_BLA().size()!=0){
			createSheet("网贷黑名单信息变更",workbook,monitorReport.getChangeDetails_BLA());
			b=true;
		}
		if(monitorReport.getChangeDetails_Negative()!=null&&monitorReport.getChangeDetails_Negative().size()!=0){
			createSheet("网络负面信息变更",workbook,monitorReport.getChangeDetails_Negative());
			b=true;
		}
		
		if(b&flag){
			File file = new File(path+monitorReport.getMonitor().get(0).getName()+".xls"); 
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
	
	 
	private static void createSheet(String title,HSSFWorkbook workbook,List<Map<String, List<ChangeDetail>>> list){
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		
		HSSFRow row = null;
		HSSFCell cell = null;
		CellRangeAddress  cra= null;
		Integer rowIndex = 0;
		
		for(int i=0;i<list.size();i++){
			
			for(int k=0;k<entityField.length;k++){
				if(list.get(i).get(entityField[k]) != null){
					List<ChangeDetail> changeDetails =list.get(i).get(entityField[k]);
					if(changeDetails!=null&&changeDetails.size()!=0){
						//创建小标题。
						rowIndex = createTablehead( rowIndex, entityName[k]+"("+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(changeDetails.get(0).getDate())+")",sheet,row,cell,cra);
						for(int j=0;j<changeDetails.size();j++){
							rowIndex = createTableContent( rowIndex,sheet,row,cell,cra,changeDetails.get(j));
						}
					}
					
				}
				
			}
			
			
		}
		
		
	}
	
	
	private static Integer createTablehead(Integer rowIndex,String title,HSSFSheet sheet,HSSFRow row,HSSFCell cell,CellRangeAddress cra){
		cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++); row.setHeight((short) 450);
		cell = row.createCell(0); cell.setCellStyle(style);
		cell.setCellValue(title);
		
		row = sheet.createRow(rowIndex++); row.setHeight((short) 300);
		cell = row.createCell(0); cell.setCellStyle(style2);cell.setCellValue("变更类型");
		cell = row.createCell(1); cell.setCellStyle(style2);cell.setCellValue("变更项");
		cell = row.createCell(2); cell.setCellStyle(style2);cell.setCellValue("变更前内容");
		cell = row.createCell(3); cell.setCellStyle(style2);cell.setCellValue("变更后内容");
		
		return rowIndex;
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
	
}