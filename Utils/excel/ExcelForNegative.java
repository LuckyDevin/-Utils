package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

@SuppressWarnings("deprecation")
public class ExcelForNegative {

	
	
	private static HSSFCellStyle style;
	private static HSSFCellStyle style2;
	private static HSSFCellStyle style3;
	private static HSSFCellStyle style4;
	private void initwork(HSSFWorkbook workbook) {
		// 声明一个工作薄
		
				
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
	}
	/**
	 * @param title
	 * @param headers
	 * @param cellNames
	 * @param dataset
	 * @param out
	 * @param pattern
	 * @return
	 */
	public byte[] exportFayuangonggao(String jsonStr, OutputStream out, String pattern) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		initwork( workbook);
		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		String resCode = (String)jsonArr.get("resCode");
		if(resCode.equals("0000")){
			Map<String, List<JSONObject>> jsonMap = jsonToMap(jsonStr);
				//法院公告
				String[] fayuan_t = { 
						"公告类型#classtype",
						"公告人#ggperson", 
						"当事人#party",
						"公告时间#ggdate",
						"公告内容#executiondoc"
				};
				Integer rowIndex = 0;
				HSSFSheet sheet = workbook.createSheet("法院公告");
				// 设置表格默认列宽度为25个字节
				sheet.setDefaultColumnWidth((short) 40);
				rowIndex = setCellValueMethod(jsonMap.get("fayuan"), fayuan_t, "法院公告", rowIndex, sheet) + 1;
		}else{
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-风险信息查询-法院公告");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}
	
	public byte[] exportBlackname(String jsonStr, OutputStream out, String pattern) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		initwork( workbook);
		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		String resCode = (String)jsonArr.get("resCode");
		if(resCode.equals("0000")){
				
	    	Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
			List<JSONObject> list = new ArrayList<JSONObject>();
			JSONObject data = (JSONObject)jsonArr.get("data");	
			JSONObject entity = (JSONObject) data.get("entity");	
	/*		String sex = (String)data.get("sex");
			if(sex!=null){
				if(sex.equals("1")){
					sex="男";
				}else if(sex.equals("2")){
					sex="女";
				}
			}else{
				sex = "未知";
			}
			entity.element("sex", sex);*/
			jsonMap = setJsonMap(jsonMap, list, entity, "blackname");
			
			//网贷黑名单
			String[] blackname_t = { 
					"姓名#black_name",
					"性别#sex", 
					"年龄#age",
					"身份证号#card_code",
					"手机号#cell_phone",
					"欠款总额#qk_amt",
					"逾期笔数#over_num",
					"逾期总罚息#penalty_amt",
					"应还款日期#over_date",
					"来源#source"
			};
				
				Integer rowIndex = 0;
				HSSFSheet sheet = workbook.createSheet("网贷黑名单");
				// 设置表格默认列宽度为25个字节
				sheet.setDefaultColumnWidth((short) 40);
				rowIndex = setCellValueMethod(jsonMap.get("blackname"), blackname_t, "网贷黑名单", rowIndex, sheet) + 1;
		}else{
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-风险信息查询-失信被执行信息");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}	
	
	
	public byte[] exportBeizhixing(String jsonStr, OutputStream out, String pattern) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		initwork( workbook);
		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		String resCode = (String)jsonArr.get("resCode");
		if(resCode.equals("0000")){
				
	    	Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
			List<JSONObject> list = new ArrayList<JSONObject>();
			JSONObject data = (JSONObject)jsonArr.get("data");	
			JSONObject entity = (JSONObject) data.get("entity");	
			jsonMap = setJsonMap(jsonMap, list, entity, "beizhixing");
			
			
			//被执行信息
			String[] beizhixing_t = { 
					"被执行人姓名/名称#name",
					"身份证号码/组织机构代码#regno", 
					"执行法院#enfcourt",
					"立案时间#date",
					"案号#classnumber",
					"状态#status",
					"执行标的#money"
			};
				
				Integer rowIndex = 0;
				HSSFSheet sheet = workbook.createSheet("失信被执行信息");
				// 设置表格默认列宽度为25个字节
				sheet.setDefaultColumnWidth((short) 40);
				rowIndex = setCellValueMethod(jsonMap.get("beizhixing"), beizhixing_t, "失信被执行信息", rowIndex, sheet) + 1;
		}else{
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-风险信息查询-失信被执行信息");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}	
	
	public byte[] exportShixin(String jsonStr, OutputStream out, String pattern) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		initwork( workbook);
		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		String resCode = (String)jsonArr.get("resCode");
		if(resCode.equals("0000")){
				
	    	Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
			List<JSONObject> list = new ArrayList<JSONObject>();
			JSONObject data = (JSONObject)jsonArr.get("data");	
			JSONObject entity = (JSONObject) data.get("entity");	
			String sex = (String)data.get("sex");
			if(sex!=null){
				if(sex.equals("1")){
					sex="男";
				}else if(sex.equals("2")){
					sex="女";
				}
			}else{
				sex = "未知";
			}
			entity.element("sex", sex);
			jsonMap = setJsonMap(jsonMap, list, entity, "shixin");
			
				//失信被执行信息
				String[] shixin_t = { 
						"被执行人姓名/名称#name",
						"性别#sex", 
						"年龄#age",
						"身份证号码/组织机构代码#identityid",
						"执行法院#executionunit",
						"省份#province",
						"执行依据文号#executeid",
						"立案时间#filingdate",
						"案号#caseid",
						"判决法院#court",
						"生效法律文书确定的义务#obligationfor",
						"被执行人的履行情况#executionperformance",
						"失信被执行人行为具体情形#situationofacter",
						"发布时间#publishdate",
				};
				
				Integer rowIndex = 0;
				HSSFSheet sheet = workbook.createSheet("失信人信息");
				// 设置表格默认列宽度为25个字节
				sheet.setDefaultColumnWidth((short) 40);
				rowIndex = setCellValueMethod(jsonMap.get("shixin"), shixin_t, "失信人信息", rowIndex, sheet) + 1;
		}else{
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-风险信息查询-失信人信息");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}	
	
	
	
	
	
	
	
	
	 public Map<String, List<JSONObject>> jsonToMap(String jsonStr){
	    	JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
	    	Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
			List<JSONObject> list = new ArrayList<JSONObject>();
			JSONObject data = (JSONObject)jsonArr.get("data");	
			JSONObject entity = (JSONObject) data.get("entity");	
			jsonMap = setJsonMap(jsonMap, list, entity, "fayuan");
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