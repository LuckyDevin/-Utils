package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.ccx.credit.assess.model.ReportConsumeCategoryModel;
import com.ccx.credit.assess.model.ReportConsumeCityModel;
import com.ccx.credit.assess.model.ReportModel;
import com.ccx.credit.assess.model.ReportMonthConsumeModel;
import com.ccx.credit.assess.model.ReportTransCreditModel;
import com.ccx.credit.negative.model.CertUpdateRecord;

@SuppressWarnings("deprecation")
public class ExportExcelForCertReport {

	/*public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd",);
	}


	public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
	}*/



	/**
	 * 
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 *
	 * @param title 表格标题名
	 * @param headers 表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */

	private static HSSFCellStyle style;
	private static HSSFCellStyle style2;
	private static HSSFCellStyle style3;
	private static HSSFCellStyle style4;

	/**
	 * @param title
	 * @param headers
	 * @param cellNames
	 * @param dataset
	 * @param out
	 * @param pattern
	 * @return
	 */
	@SuppressWarnings({ "resource" })
	public byte[] exportIcInfoExcel(String jsonStr, OutputStream out, String pattern) {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

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
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

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
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style4 = workbook.createCellStyle();
		style4.setFillForegroundColor(HSSFColor.WHITE.index);
		style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);

		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		String resCode = (String)jsonArr.get("resCode");
		if(resCode.equals("0000")){
			//JSONArray header = (JSONArray)jsonArr.get("header");
			//JSONArray data = (JSONArray)jsonArr.get("data");


			//个人职业资格证书
			String[] rp_t = { 
					"报告时间#time#报告编号#sn" 
			};

			//基本信息
			String[] base_t = {
					"姓名#name#性别#gender", 
					"身份证号码#id##kong", 
			};
			//证书基本信息
			String[] bs_t = {
					"姓名#RPI_NAME#性别#SCO_NAME", 
					"执业机构#AOI_NAME#证书编号#CER_NUM",
					"职业岗位#PTI_NAME#学历#ECO_NAME",
					"证书取得日期#OBTAIN_DATE#证书有效截止日期#ARRIVE_DATE"
			};
			//注册变更记录
			String[] up_t = {
					"证书编号#CER_NUM#取得日期#OBTAIN_DATE",
					"执业机构#AOI_NAME#执业岗位#PTI_NAME",
					"证书状态#CERTC_NAME##kong"
			};
			
			//注册变更记录
			String[] headers_updateRecord = {
					"证书编号","取得日期","执业机构","执业岗位","证书状态"	
			};
			String[] propertys_updateRecord  = {
					"CER_NUM","OBTAIN_DATE",  "AOI_NAME","PTI_NAME","CERTC_NAME"
			};

			//证书信息
			String[] re_t = {
					"证书编号#certificateNum#文化程度#educatLevel",
					"级别#level#职业名称#jobName",
					"理论知识考核成绩#theoryGrade#操作知识考核成绩#oprationGrade",
					"评定成绩#grade#发证日期#publishDate",
					"职业技能鉴定机构名称#instName#数据上报单位#dataUpdateInst",
					"数据上报时间#dataUpdateTime##kong",

			};
			
			
			Integer rowIndex = 0;

			HSSFSheet sheet = workbook.createSheet("职业资格证书");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
			
			JSONArray dataArray =  (JSONArray)jsonArr.get("data");
			
			for (int i = 0; i < dataArray.size(); i++) {
				JSONObject ob = (JSONObject) dataArray.get(i);
				Map<String, List<JSONObject>> jsonMap = jsonToMap(jsonStr,ob);
				JSONObject data = (JSONObject) ob.get("data");
				
				Integer type = (Integer) ob.get("type");
				
				rowIndex = setCellValueMethod(jsonMap.get("rpt"), rp_t, "职业资格证书", rowIndex, sheet) + 1;
				
				if (type == 1){
					JSONArray retArr = (JSONArray) data.get("records");
					rowIndex = setCellValueMethod(jsonMap.get("baset"), base_t, "基本信息", rowIndex, sheet) + 1;
					for (int j = 0; j < retArr.size(); j++) {
						rowIndex = setCellValueMethod(jsonMap.get("ret"+j), re_t, "证书信息（"+(j+1)+"）", rowIndex, sheet) + 1;
					}
				}else if(type == 2) {
					rowIndex = setCellValueMethod(jsonMap.get("bst"), bs_t, "证书基本信息", rowIndex, sheet) + 1;
					
					//rowIndex = setCellValueMethod(jsonMap.get("upt"), up_t, "注册变更记录", rowIndex, sheet) + 1;
					
					List<CertUpdateRecord> updateRecordList = new ArrayList<CertUpdateRecord>();
					JSONArray uptArr =  (JSONArray) data.get("regAltRecords");
					for (int k = 0; k <uptArr.size(); k++) {
						JSONObject object = (JSONObject) uptArr.get(k);
						CertUpdateRecord record = new CertUpdateRecord();
						record.setAOI_NAME((String) object.get("AOI_NAME")==null?"--":(String) object.get("AOI_NAME"));
						record.setCER_NUM((String) object.get("CER_NUM")==null?"--":(String) object.get("CER_NUM"));
						record.setCERTC_NAME((String) object.get("CERTC_NAME")==null?"--":(String) object.get("CERTC_NAME"));
						record.setOBTAIN_DATE((String) object.get("OBTAIN_DATE")==null?"--":(String) object.get("OBTAIN_DATE"));
						record.setPTI_NAME((String) object.get("PTI_NAME")==null?"--":(String) object.get("PTI_NAME"));
						updateRecordList.add(record);
					}
					
					ExcelLineDown<CertUpdateRecord> ex = new ExcelLineDown<CertUpdateRecord>(); 
					ex.doExcuteData(style,style2,style3,sheet,headers_updateRecord,updateRecordList,out,pattern,propertys_updateRecord,"注册变更记录");
				}
			}

		}else{
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-职业资格证书-个人职业资格证书");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
		}

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
			//			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}

	public Map<String, List<JSONObject>> jsonToMap(String jsonStr,JSONObject ob){
		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject header =  (JSONObject)jsonArr.get("header");
		jsonMap = setJsonMap(jsonMap, list, header, "rpt");//报告主体封装
		Integer type = (Integer) ob.get("type");
		String kong ="";
		if(type == 1){//多证书情况
			JSONObject data =  (JSONObject) ob.get("data");
			JSONArray retArr = (JSONArray) data.get("records");
		for (int i = 0; i < retArr.size(); i++) {
			JSONObject ret = (JSONObject) retArr.get(i);
			ret.element("kong", kong);
			jsonMap = setJsonMap(jsonMap, list, ret, "ret"+i);//证书详情封装
		}
			JSONObject baset = (JSONObject) data.get("generalInfo");
			baset.element("kong", kong);
			jsonMap = setJsonMap(jsonMap, list, baset, "baset");//基本信息封装
		}else if (type == 2) {
			JSONObject data =  (JSONObject) ob.get("data");
			JSONObject bst =  (JSONObject) data.get("basicInfo");
			jsonMap = setJsonMap(jsonMap, list, bst, "bst");//证书基本信息封装
			/*JSONArray uptArr =  (JSONArray) data.get("regAltRecords");
			for (int j = 0; j < uptArr.size(); j++) {
				JSONObject upt = (JSONObject) uptArr.get(j);
				upt.element("kong", kong);
				jsonMap = setJsonMap(jsonMap, list, upt, "upt"+j);//注册变更记录数据封装
			}*/
		}
		return jsonMap;
	}

	public Map<String, List<JSONObject>> setJsonMap(Map<String, List<JSONObject>> jsonMap, List<JSONObject> list, JSONObject data, String name){
		list = jsonMap.get(name);
		if(list == null){
			list = new ArrayList<JSONObject>();
		}
		list.add(data);
		jsonMap.put(name, list);
		return jsonMap;
	}



	public Integer setCellValueMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex,HSSFSheet sheet){

		HSSFRow row ;
		HSSFCell cell;
		CellRangeAddress  cra;
		//rowIndex = 6;
		cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++); row.setHeight((short) 450);
		cell = row.createCell(0); cell.setCellStyle(style);
		cell.setCellValue(title);
		Integer array = 0;

		if(list!=null){

			for (JSONObject data : list) {
				if(data!=null){
					array++;
					for(int i = 0;i<arrayData.length;i++){
						row = sheet.createRow(rowIndex++); row.setHeight((short) 350);
						String [] cellData = arrayData[i].split("#"); 
						int cellIndex = cellData.length;
						for(int m = 0 ; m < cellIndex ; m++){
							if(cellIndex == 4){
								cell = row.createCell(m);
								if(array%2==0){
									cell.setCellStyle(style2);
								}else{
									cell.setCellStyle(style3);
								}
								if(m%2==0){
									cell.setCellValue(cellData[m]);
								}else{
									String fieldName =  cellData[m];
									cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");
									cell.setCellStyle(style4);
								}
							}else{
								cell = row.createCell(m);
								if(array%2==0){
									cell.setCellStyle(style2);
								}else{
									cell.setCellStyle(style3);
								}
								if(m%2==0){
									cell.setCellValue(cellData[m]);
								}else{
									cra = new CellRangeAddress(rowIndex-1, rowIndex-1, 1, 3);
									sheet.addMergedRegion(cra);
									String fieldName =  cellData[m];
									cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");
									/*if(array%2==0){
										cell = row.createCell(2); cell.setCellStyle(style2);
										cell = row.createCell(3); cell.setCellStyle(style2);
										cell.setCellStyle(style2);
									}else{
										cell.setCellStyle(style3);
										cell = row.createCell(2); cell.setCellStyle(style3);
										cell = row.createCell(3); cell.setCellStyle(style3);
									}*/
									cell.setCellStyle(style4);
									cell = row.createCell(2); cell.setCellStyle(style4);
									cell = row.createCell(3); cell.setCellStyle(style4);
								}
							}
						}
					}
				}
			}
		}
		return rowIndex;
	}


	public Integer setCellValueMethod_old(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex,HSSFWorkbook workbook){

		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);

		HSSFRow row ;
		HSSFCell cell;
		CellRangeAddress  cra;

		cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++); row.setHeight((short) 450);
		cell = row.createCell(0); cell.setCellStyle(style);
		cell.setCellValue(title);
		Integer array = 0;

		if(list!=null){

			for (JSONObject data : list) {
				if(data!=null){
					array++;
					for(int i = 0;i<arrayData.length;i++){
						row = sheet.createRow(rowIndex++); row.setHeight((short) 350);
						String [] cellData = arrayData[i].split("#"); 
						int cellIndex = cellData.length;
						for(int m = 0 ; m < cellIndex ; m++){
							if(cellIndex == 4){
								cell = row.createCell(m);
								if(array%2==0){
									cell.setCellStyle(style2);
								}else{
									cell.setCellStyle(style3);
								}
								if(m%2==0){
									cell.setCellValue(cellData[m]);
								}else{
									String fieldName =  cellData[m];
									cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");
								}
							}else{
								cell = row.createCell(m);
								if(array%2==0){
									cell.setCellStyle(style2);
								}else{
									cell.setCellStyle(style3);
								}
								if(m%2==0){
									cell.setCellValue(cellData[m]);
								}else{
									cra = new CellRangeAddress(rowIndex-1, rowIndex-1, 1, 3);
									sheet.addMergedRegion(cra);
									String fieldName =  cellData[m];
									cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");
									if(array%2==0){
										cell = row.createCell(2); cell.setCellStyle(style2);
										cell = row.createCell(3); cell.setCellStyle(style2);
										cell.setCellStyle(style2);
									}else{
										cell.setCellStyle(style3);
										cell = row.createCell(2); cell.setCellStyle(style3);
										cell = row.createCell(3); cell.setCellStyle(style3);
									}
								}
							}
						}
					}
				}
			}
		}
		return rowIndex;
	}
	public Integer setRowCellValueMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex,HSSFWorkbook workbook){

		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);

		HSSFRow row ;
		HSSFCell cell;
		CellRangeAddress  cra;

		row = sheet.createRow(rowIndex++); 
		row.setHeight((short) 350);
		for(int i = 0;i<arrayData.length;i++){
			String titles = arrayData[i].split("#")[0];
			cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(titles);
		}

		if(list!=null){
			for (JSONObject data : list) {
				row.setHeight((short) 350);
				row = sheet.createRow(rowIndex++);
				for(int i = 0;i<arrayData.length;i++){
					String fieldName = arrayData[i].split("#")[1];
					cell = row.createCell(i);
					cell.setCellStyle(style3);
					cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");
				}
			}
		}else{
			cra = new CellRangeAddress(rowIndex, rowIndex, 0, arrayData.length-1);
			sheet.addMergedRegion(cra);
			row = sheet.createRow(rowIndex); 
			row.setHeight((short) 350);
			cell = row.createCell(0);
			cell.setCellStyle(style3);
			cell.setCellValue("暂未查到该项信息");
		}
		return rowIndex;
	}


	private void excelTree(HSSFSheet sheet, List<String> list,String title ){
		HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1); row.setHeight((short) 350);
		HSSFCell cell = row.createCell(0); cell.setCellStyle(style); cell.setCellValue(title);
		if(list!=null){
			int i = 1;
			for (String s : list) {
				cell = row.createCell(i++);
				cell.setCellStyle(style3);
				cell.setCellValue(s);
			}
		}
	}



}