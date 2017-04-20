package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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

import com.ccx.credit.assess.model.CashOutPersonal;
import com.ccx.credit.assess.model.IndexProperty;
import com.ccx.credit.assess.model.IndexTransBehavior;
import com.ccx.credit.assess.model.ReportCardInfoModel;
import com.ccx.credit.assess.model.ReportConsumeCategoryModel;
import com.ccx.credit.assess.model.ReportConsumeCityModel;
import com.ccx.credit.assess.model.ReportMonthConsumeModel;
import com.ccx.credit.assess.model.ReportNewTranModel;
import com.ccx.credit.assess.model.ReportTransCreditModel;
import com.ccx.credit.negative.model.TransUtil;
import com.ccx.credit.util.DateUtils;

@SuppressWarnings("deprecation")
public class ExportExcelForNewTransReport {

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
	public byte[] exportIcInfoExcel(String jsonStr,ReportNewTranModel model,String name, OutputStream out, String pattern) {

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

			Map<String, List<JSONObject>> jsonMap = jsonToMap(model,name);

			//个人交易报告
			String[] rp_t = { 
					"姓名#name",
					"报告编号#sn#报告时间#time" 
			};
			
			//核心消费指标
			String[] pro_t = {
					"首次交易日期#firstTransDate#消费总金额#transTotalAmt", 
					"消费总笔数#transTotalCnt#取现总金额#cashTotalAmt", 
					"取现总笔数#cashTotalCnt#还款总金额#refundTotalAmt",
					"还款总笔数#refundTotalCnt#刚需类消费占比#rigidTransAmtPre",
					"常住城市#fromCity#近 12 月没有发生交易的周数占比#noTransWeekPre",
					"最大单月消费金额#monthCardLargeAmt"
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
					"月平均消费金额#avgAmountStr#月平均消费笔数#avgCountStr"
					//"消费金额本市排名#amountRankingStr#消费笔数本市排名#countRankingStr"

			};
			//消费特征画像(推断分析)
			String[] img_t = {
					"有无出差#ifHasBusinessTrip#有无婚庆消费#ifHasWeddingTrans",
					"有无家庭特征#ifHasFamily#有无境外消费#ifHasOverseasTrans",
					"有无夜消费#ifHasNightTrans#有无博彩业消费#ifHasLotteryTrans",
					"有无无业特征#ifHasUnemployed"
			};
			
			
			//消费特征画像(推断分析)
			String[] tx_t = {
					"近一年疑似套现金额#yearMisCreditCardCashAmt#近一年疑似套现笔数#yearMisCreditCardCashCnt",
					"近一年套现高危行业交易金额占比#yearMisHighRiskCashAmtPre#近一年套现高危行业交易笔数占比#yearMisHighRiskCashAmtPre"
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

			rowIndex = setCellValueMethod(jsonMap.get("pro"), pro_t, "核心消费指标", rowIndex, sheet) + 1;
			
			rowIndex = setCellValueMethod(jsonMap.get("ksx"), ca_t, "银联卡属性信息", rowIndex, sheet) + 1;

			rowIndex = setCellValueMethod(jsonMap.get("mont"), mon_t, "近12月交易统计", rowIndex, sheet) + 1;


			ReportMonthConsumeModel[] arr = model.getData().getMonthConsumeList();
			//ReportMonthConsumeModel[] arr = model.getData().getTransReport().getMonthConsumeList();
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
			Collection<ReportConsumeCategoryModel>  consumeCategory = new ArrayList<ReportConsumeCategoryModel>();
			if(model.getData().getConsumeCategoryList()!=null){
				List<ReportConsumeCategoryModel> consumeCategoryList = Arrays.asList(model.getData().getConsumeCategoryList());
				///List<ReportConsumeCategoryModel> consumeCategoryList = Arrays.asList(model.getData().getTransReport().getConsumeCategoryList());
				for (ReportConsumeCategoryModel ob : consumeCategoryList) {//过滤无消费的类别
					if (ob.getAmount()>0) {
						consumeCategory.add(ob);
					}
				}
				
			}
			ExcelLineDown<ReportConsumeCategoryModel> ex = new ExcelLineDown<ReportConsumeCategoryModel>(); 
			ex.doExcuteData(style,style2,style3,sheet,headers_ConsumeCategory,consumeCategory,out,pattern,propertys_ConsumeCategory,"消费类型统计");
			Collection<ReportConsumeCityModel> consumeCity = new ArrayList<ReportConsumeCityModel>();
			if(model.getData().getConsumeCityList()!=null){
				List< ReportConsumeCityModel> consumeCityList = Arrays.asList(model.getData().getConsumeCityList());
				for (ReportConsumeCityModel ob : consumeCityList) {//过滤无消费的地域
					if (ob.getAmount()>0) {
						consumeCity.add(ob);
					}
				}
			}
			ExcelLineDown<ReportConsumeCityModel> ex1 = new ExcelLineDown<ReportConsumeCityModel>(); 
			ex1.doExcuteData(style,style2,style3,sheet,headers_ConsumeCity,consumeCity,out,pattern,propertys_ConsumeCity,"消费地域统计");
			
			
			Collection<ReportTransCreditModel> transCredit=new ArrayList<ReportTransCreditModel>();
			if(model.getData().getIndexTransCredits()!=null){
				
				ReportTransCreditModel[] transCreditArg = TransUtil.buildTransCreditArg(model.getData().getIndexTransCredits());
				List<ReportTransCreditModel> transCreditList = Arrays.asList(transCreditArg);
				for (ReportTransCreditModel ob : transCreditList) {//过滤无消费的信用卡消费方式
					if (ob.getAmount()>0) {
						transCredit.add(ob);
					}
				}
			}
			ExcelLineDown<ReportTransCreditModel> ex2 = new ExcelLineDown<ReportTransCreditModel>(); 
			ex2.doExcuteData(style,style2,style3,sheet,headers_TransCredit,transCredit,out,pattern,propertys_TransCredit,"信用相关交易统计");
			rowIndex=sheet.getLastRowNum()+1;
			
			
			rowIndex = setCellValueMethod(jsonMap.get("imgt"), img_t, "消费特征画像(推断分析)", rowIndex, sheet) + 1;

			rowIndex = setCellValueMethod(jsonMap.get("txt"), tx_t, "疑似套现行为甄别", rowIndex, sheet) + 1;
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

	public Map<String, List<JSONObject>> jsonToMap(ReportNewTranModel model,String name){
		//JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		JSONObject header = new JSONObject();
		header.put("sn", model.getTid());
		header.put("time",  new SimpleDateFormat(DateUtils.DATE_FORMAT_STR).format(new Date(Long.parseLong(model.getReqTid()))));
		header.element("name", name);
		
		header.element("name", name);
		jsonMap = setJsonMap(jsonMap, list, header, "rpt");//报告主体封装
		
		IndexProperty indexProperty = model.getData().getIndexProperty();
		if(indexProperty!=null){
			JSONObject proJson = new JSONObject();
			proJson.element("firstTransDate", indexProperty.getFirstTransDate());
			proJson.element("transTotalAmt", indexProperty.getTransTotalAmt());
			proJson.element("transTotalCnt", indexProperty.getTransTotalCnt());
			proJson.element("cashTotalAmt", indexProperty.getCashTotalAmt());
			proJson.element("cashTotalCnt", indexProperty.getCashTotalCnt());
			proJson.element("refundTotalAmt", indexProperty.getRefundTotalAmt());
			proJson.element("refundTotalCnt", indexProperty.getRefundTotalCnt());
			proJson.element("rigidTransAmtPre", indexProperty.getRigidTransAmtPre());
			proJson.element("fromCity", indexProperty.getFromCity());
			proJson.element("noTransWeekPre", indexProperty.getNoTransWeekPre());
			proJson.element("monthCardLargeAmt", indexProperty.getMonthCardLargeAmt());
			jsonMap = setJsonMap(jsonMap, list, proJson, "pro");//核心消费指标
			
			
			JSONObject monthJson = new JSONObject();
			String count = indexProperty.getTransTotalCnt();
			if(count == null){
				count="0";
			}
			double avgCount = Integer.parseInt(count)/12;
			double amoutStr = indexProperty.getTransTotalAmt() == null?0.0:indexProperty.getTransTotalAmt();
			
			monthJson.element("amoutStr", "￥"+amoutStr);
			monthJson.element("count", ""+count);
			monthJson.element("avgAmountStr", count);
			/*monthJson.element("amountRankingStr", "未知");
			monthJson.element("countRankingStr", "未知");*/
			monthJson.element("avgCountStr", avgCount);
			jsonMap = setJsonMap(jsonMap, list, monthJson, "mont");//近12月交易数据封装
			
		}
		
		ReportCardInfoModel[] cardInfoList = model.getData().getCardInfoList();
		if(cardInfoList!=null && cardInfoList.length>0){
			ReportCardInfoModel cardInfo = cardInfoList[0];
			String card = cardInfo.getCard();
			String bankLocation = cardInfo.getBankLocation();
			String cardProd = cardInfo.getCardProd();
			String cardLevel = cardInfo.getCardLevel();
			String cardAttr = cardInfo.getCardAttr();
			String cardBrand = cardInfo.getCardBrand();
			
			JSONObject cardInfoJson = new JSONObject();
			cardInfoJson.element("card", card);
			cardInfoJson.element("bankLocation", bankLocation);
			cardInfoJson.element("cardProd", cardProd);
			cardInfoJson.element("cardLevel", cardLevel);
			cardInfoJson.element("cardAttr", cardAttr);
			cardInfoJson.element("cardBrand", cardBrand);
			jsonMap = setJsonMap(jsonMap, list, cardInfoJson, "ksx");//银联卡属性信息
		}
		
		
		IndexTransBehavior indexTransBehavior = model.getData().getIndexTransBehavior();
		if(indexTransBehavior!=null){
			JSONObject behaviorJson = new JSONObject();
			String ifHasBusinessTrip = indexTransBehavior.getIfHasBusinessTrip() == null?"未知":(indexTransBehavior.getIfHasBusinessTrip() == true ? "有":"无");
			String ifHasWeddingTrans = indexTransBehavior.getIfHasWeddingTrans() == null?"未知":(indexTransBehavior.getIfHasWeddingTrans() == true ? "有":"无");
			String ifHasFamily = indexTransBehavior.getIfHasFamily() == null?"未知":(indexTransBehavior.getIfHasFamily() == true ? "有":"无");
			String ifHasOverseasTrans = indexTransBehavior.getIfHasOverseasTrans() == null?"未知":(indexTransBehavior.getIfHasOverseasTrans() == true ? "有":"无");
			String ifHasNightTrans = indexTransBehavior.getIfHasNightTrans() == null?"未知":(indexTransBehavior.getIfHasNightTrans() == true ? "有":"无");
			String ifHasLotteryTrans = indexTransBehavior.getIfHasLotteryTrans() == null?"未知":(indexTransBehavior.getIfHasLotteryTrans() == true ? "有":"无");
			String ifHasUnemployed = indexTransBehavior.getIfHasUnemployed() == null?"未知":(indexTransBehavior.getIfHasUnemployed() == true ? "有":"无");
			
			behaviorJson.element("ifHasBusinessTrip", ifHasBusinessTrip);
			behaviorJson.element("ifHasWeddingTrans", ifHasWeddingTrans);
			behaviorJson.element("ifHasFamily", ifHasFamily);
			behaviorJson.element("ifHasOverseasTrans", ifHasOverseasTrans);
			behaviorJson.element("ifHasNightTrans", ifHasNightTrans);
			behaviorJson.element("ifHasLotteryTrans", ifHasLotteryTrans);
			behaviorJson.element("ifHasUnemployed", ifHasUnemployed);
			jsonMap = setJsonMap(jsonMap, list, behaviorJson, "imgt");//消费特征画像数据封装
		}
		
		CashOutPersonal cashout = model.getData().getCashout();
		if(cashout!=null){
			JSONObject cashJson = new JSONObject();
			cashJson.element("yearMisCreditCardCashAmt", cashout.getYearMisCreditCardCashAmt());
			cashJson.element("yearMisCreditCardCashCnt", cashout.getYearMisCreditCardCashCnt());
			cashJson.element("yearMisHighRiskCashAmtPre", cashout.getYearMisHighRiskCashAmtPre());
			cashJson.element("yearMisHighRiskCashCntPre", cashout.getYearMisHighRiskCashCntPre());
			jsonMap = setJsonMap(jsonMap, list, cashJson, "txt");//消费特征画像数据封装
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