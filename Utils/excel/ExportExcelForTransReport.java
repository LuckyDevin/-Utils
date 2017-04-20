package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import com.ccx.credit.assess.model.ReportMonthConsumeModel;
import com.ccx.credit.assess.model.ReportTranModel;
import com.ccx.credit.assess.model.ReportTransCreditModel;

@SuppressWarnings("deprecation")
public class ExportExcelForTransReport {

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
	public byte[] exportIcInfoExcel(String jsonStr,ReportTranModel model,String name, OutputStream out, String pattern) {

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

			Map<String, List<JSONObject>> jsonMap = jsonToMap(jsonStr,name);

			//个人交易报告
			String[] rp_t = { 
					"姓名#name",
					"报告编号#sn#报告时间#time" 
			};

			//银联卡属性信息
			String[] ca_t = {
					"卡号#card#发卡行所在地#bankLocation", 
					"卡产品#cardProd#卡等级#cardLevel", 
					"卡性质#cardAttr#卡品牌#cardBrand"
			};

			// 近12月交易统计
			String[] mon_t = {
					"消费总金额#amoutStr#消费总笔数#count",
					"月平均消费金额#avgAmountStr#月平均消费笔数#avgCountStr",
					"消费金额本市排名#amountRankingStr#消费笔数本市排名#countRankingStr"

			};
			//消费特征画像(推断分析)
			String[] img_t = {
					"有无房产#hasHouseStr#房产预估购买时间#housePurTimeStr",
					"有无汽车#hasCarStr#汽车预估购买时间#carPurTimeStr",
					"有无出差#hasBusinessTripStr#有无婚庆消费#hasMarriageConsumeStr",
					"有无家庭特征#hasFamilyFeatureStr#有无夜消费#hasNightConsumeStr",
					"有无无业特征#isUnemployedStr#常住城市#cityStr",
					"工作时间消费区域#workRegionStr#非工作时间消费区域#freeRegionStr"
			};


			//消费类型统计
			String[] headers_ConsumeCategory = {
					"类别","消费金额",  "金额比例","消费次数","次数比例"
			};
			String[] propertys_ConsumeCategory= {
					"name","amount",  "amountRatio","count","countRatio"
			};

			//消费地域统计
			String[] headers_ConsumeCity = {
					"城市名称","消费金额",  "金额比例","消费笔数","次数比例"
			};
			String[] propertys_ConsumeCity = {
					"name","amount",  "amountRatio","count","countRatio"
			};
			//信用相关交易统计
			String[] headers_TransCredit = {
					"消费方式名称","消费金额","金额比例","消费笔数","笔数比例"	
			};
			String[] propertys_TransCredit = {
					"name","amount",  "amountRatio","count","countRatio"
			};

			Integer rowIndex = 0;

			HSSFSheet sheet = workbook.createSheet("银联交易报告");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);

			rowIndex = setCellValueMethod(jsonMap.get("rpt"), rp_t, "银联交易报告", rowIndex, sheet) + 1;

			rowIndex = setCellValueMethod(jsonMap.get("ksx"), ca_t, "银联卡属性信息", rowIndex, sheet) + 1;

			rowIndex = setCellValueMethod(jsonMap.get("mont"), mon_t, "近12月交易统计", rowIndex, sheet) + 1;



			ReportMonthConsumeModel[] arr = model.getData().getTransReport().getMonthConsumeList();
			if(arr != null && arr.length > 0){
				CellRangeAddress  cra;
				cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
				HSSFRow row = sheet.createRow(rowIndex++); row.setHeight((short) 450);
				HSSFCell cell = row.createCell(0); cell.setCellStyle(style);
				cell.setCellValue("月度消费统计");
				sheet.addMergedRegion(cra);
				List<String> month = new ArrayList<String>();
				List<String> amount = new ArrayList<String>();
				List<String> count = new ArrayList<String>();
				List<String> amountRanking = new ArrayList<String>();
				List<String> countRanking = new ArrayList<String>();
				for (int i = 0; i < arr.length-1; i++) {
					ReportMonthConsumeModel ob = arr[i];
					month.add(ob.getMonth()+"");
					amount.add("￥"+ob.getAmount());
					count.add(ob.getCount()+"");
					amountRanking.add(ob.getAmountRanking()+"%");
					countRanking.add(ob.getCountRanking()+"%");
				}
				excelTree(sheet,month,"数据指标");
				excelTree(sheet,amount,"消费金额（元）");
				excelTree(sheet,count,"消费笔数");
				excelTree(sheet,amountRanking,"月度消费金额排名");
				excelTree(sheet,countRanking,"月度消费笔数排名");
				row = sheet.createRow(sheet.getLastRowNum()+1); row.setHeight((short) 350);
				row.createCell(0);

			}

			rowIndex+=8;

			List<ReportConsumeCategoryModel> consumeCategoryList = Arrays.asList(model.getData().getTransReport().getConsumeCategoryList());
			Collection<ReportConsumeCategoryModel>  consumeCategory = new ArrayList<ReportConsumeCategoryModel>();
			for (ReportConsumeCategoryModel ob : consumeCategoryList) {//过滤无消费的类别
				if (ob.getAmount()>0) {
					consumeCategory.add(ob);
				}
			}

			ExcelLineDown<ReportConsumeCategoryModel> ex = new ExcelLineDown<ReportConsumeCategoryModel>(); 
			ex.doExcuteData(style,style2,style3,sheet,headers_ConsumeCategory,consumeCategory,out,pattern,propertys_ConsumeCategory,"消费类型统计");

			List< ReportConsumeCityModel> consumeCityList = Arrays.asList(model.getData().getTransReport().getConsumeCityList());
			Collection<ReportConsumeCityModel> consumeCity = new ArrayList<ReportConsumeCityModel>();
			for (ReportConsumeCityModel ob : consumeCityList) {//过滤无消费的地域
				if (ob.getAmount()>0) {
					consumeCity.add(ob);
				}
			}
			ExcelLineDown<ReportConsumeCityModel> ex1 = new ExcelLineDown<ReportConsumeCityModel>(); 
			ex1.doExcuteData(style,style2,style3,sheet,headers_ConsumeCity,consumeCity,out,pattern,propertys_ConsumeCity,"消费地域统计");

			List<ReportTransCreditModel> transCreditList = Arrays.asList(model.getData().getTransReport().getTransCreditList());
			Collection<ReportTransCreditModel> transCredit=new ArrayList<ReportTransCreditModel>();
			for (ReportTransCreditModel ob : transCreditList) {//过滤无消费的信用卡消费方式
				if (ob.getAmount()>0) {
					transCredit.add(ob);
				}
			}

			ExcelLineDown<ReportTransCreditModel> ex2 = new ExcelLineDown<ReportTransCreditModel>(); 
			ex2.doExcuteData(style,style2,style3,sheet,headers_TransCredit,transCredit,out,pattern,propertys_TransCredit,"信用相关交易统计");
			rowIndex=sheet.getLastRowNum()+1;

			rowIndex = setCellValueMethod(jsonMap.get("imgt"), img_t, "消费特征画像(推断分析)", rowIndex, sheet) + 1;

		}else{
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-银联交易报告-详细信息");
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

	public Map<String, List<JSONObject>> jsonToMap(String jsonStr,String name){
		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject report =  (JSONObject)jsonArr.get("data");
		//处理历史数据
		if(report== null){
			report = (JSONObject)jsonArr.get("report");
		}
		JSONObject header = (JSONObject) report.get("header");
		JSONObject data = (JSONObject)report.get("transReport");
		header.element("name", name);
		jsonMap = setJsonMap(jsonMap, list, header, "rpt");//报告主体封装

		JSONArray monthConsumeArr =  (JSONArray)data.get("monthConsumeList");
		JSONObject monthConsume = monthConsumeArr.getJSONObject(12);

		//"amount":13092.06,"count":31,"amountRanking":40,"countRanking":30}
		;
		String amoutStr = "￥" + monthConsume.get("amount");
		double amountRanking =  (double) monthConsume.get("amountRanking");
		String amountRankingStr = "";
		if (amountRanking > 0) {
			if(amountRanking <= 10){
				amountRankingStr = "前10%";	
			}else if(amountRanking <= 30){
				amountRankingStr = "前30%";
			}else if(amountRanking >= 90){
				amountRankingStr = "后10%";
			}else if(amountRanking >= 70){
				amountRankingStr = "后30%";
			}else{
				amountRankingStr = "中等";
			}
		}else {
			amountRankingStr = "未知";
		}
		double countRanking =  (double) monthConsume.get("countRanking");
		String countRankingStr = "";
		if (countRanking > 0) {
			if(countRanking <= 10){
				countRankingStr = "前10%";	
			}else if(countRanking <= 30){
				countRankingStr = "前30%";
			}else if(countRanking >= 90){
				countRankingStr = "后10%";
			}else if(countRanking >= 70){
				countRankingStr = "后30%";
			}else{
				countRankingStr = "中等";
			}
		}else {
			countRankingStr = "未知";
		}
		double amount = (double) monthConsume.get("amount");
		int count = (int) monthConsume.get("count"); 
		DecimalFormat df = new DecimalFormat("#.00");
		String avgAmountStr = df.format(amount/12);
		String avgCountStr = df.format(count/12);
		monthConsume.element("amoutStr", amoutStr);
		monthConsume.element("avgAmountStr", "￥"+avgAmountStr);
		monthConsume.element("amountRankingStr", amountRankingStr);
		monthConsume.element("countRankingStr", countRankingStr);
		monthConsume.element("avgCountStr", avgCountStr);
		jsonMap = setJsonMap(jsonMap, list, monthConsume, "mont");//近12月交易数据封装

		JSONObject transBhvrAnls =  (JSONObject)data.get("transBhvrAnls");
		String hasHouseStr = transBhvrAnls.getBoolean("hasHouse") == true ? "有":"无";
		String housePurTimeStr = (String) (transBhvrAnls.get("housePurTime") == null ? "未知":transBhvrAnls.get("housePurTime"));
		String hasCarStr = transBhvrAnls.getBoolean("hasCar") == true ? "有":"无";
		String carPurTimeStr = (String) (transBhvrAnls.get("carPurTime") == null ? "未知":transBhvrAnls.get("carPurTime"));
		String hasBusinessTripStr = transBhvrAnls.getBoolean("hasBusinessTrip") == true ? "有":"无";
		String hasMarriageConsumeStr = transBhvrAnls.getBoolean("hasMarriageConsume") == true ? "有":"无";
		String hasFamilyFeatureStr = transBhvrAnls.getBoolean("hasFamilyFeature") == true ? "有":"无";
		String hasNightConsumeStr = transBhvrAnls.getBoolean("hasNightConsume") == true ? "有":"无";
		String isUnemployedStr = transBhvrAnls.getBoolean("isUnemployed") == true ? "有":"无";
		String cityStr = (String) (transBhvrAnls.get("city") == null ? "未知":transBhvrAnls.get("city"));
		String workRegionStr = (String) (transBhvrAnls.get("workRegion") == null ? "未知":transBhvrAnls.get("workRegion"));
		String freeRegionStr = (String) (transBhvrAnls.get("carPurTime") == null ? "未知":transBhvrAnls.get("freeRegion"));
		transBhvrAnls.element("hasHouseStr", hasHouseStr);
		transBhvrAnls.element("housePurTimeStr", housePurTimeStr);
		transBhvrAnls.element("hasCarStr", hasCarStr);
		transBhvrAnls.element("carPurTimeStr", carPurTimeStr);
		transBhvrAnls.element("hasBusinessTripStr", hasBusinessTripStr);
		transBhvrAnls.element("hasMarriageConsumeStr", hasMarriageConsumeStr);
		transBhvrAnls.element("hasFamilyFeatureStr", hasFamilyFeatureStr);
		transBhvrAnls.element("hasNightConsumeStr", hasNightConsumeStr);
		transBhvrAnls.element("isUnemployedStr", isUnemployedStr);
		transBhvrAnls.element("cityStr", cityStr);
		transBhvrAnls.element("workRegionStr", workRegionStr);
		transBhvrAnls.element("freeRegionStr", freeRegionStr);
		jsonMap = setJsonMap(jsonMap, list, transBhvrAnls, "imgt");//信用相关交易统计数据封装
		
		JSONArray cardInfoList = (JSONArray) data.get("cardInfoList");
		if(cardInfoList!=null && cardInfoList.size()>0){
			JSONObject cardInfo = (JSONObject) cardInfoList.get(0);
			String card = (String)cardInfo.get("card");
			String bankLocation = (String)cardInfo.get("bankLocation");
			String cardProd = (String)cardInfo.get("cardProd");
			String cardLevel = (String)cardInfo.get("cardLevel");
			String cardAttr = (String)cardInfo.get("cardAttr");
			String cardBrand = (String)cardInfo.get("cardBrand");
			cardInfo.element("card", card);
			cardInfo.element("bankLocation", bankLocation);
			cardInfo.element("cardProd", cardProd);
			cardInfo.element("cardLevel", cardLevel);
			cardInfo.element("cardAttr", cardAttr);
			cardInfo.element("cardBrand", cardBrand);
			jsonMap = setJsonMap(jsonMap, list, cardInfo, "ksx");//银联卡属性信息
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