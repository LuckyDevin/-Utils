package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSONObject;
import com.ccx.credit.wisdom.model.merchantModel.IndexArea;
import com.ccx.credit.wisdom.model.merchantModel.IndexContrast;
import com.ccx.credit.wisdom.model.merchantModel.IndexLoyalty;
import com.ccx.credit.wisdom.model.merchantModel.RspReportMerchant;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
public class ExportExcel_merchant {

	/*
	 * public void exportExcel(Collection<T> dataset, OutputStream out) {
	 * exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd",); }
	 * 
	 * 
	 * public void exportExcel(String[] headers, Collection<T> dataset,
	 * OutputStream out, String pattern) { exportExcel("测试POI导出EXCEL文档",
	 * headers, dataset, out, pattern); }
	 */

	/**
	 * 
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 *
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
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
	public byte[] exportIcInfoExcel(String jsonStr, OutputStream out, String pattern) {

		RspReportMerchant model = null;
		try {
			model = new ObjectMapper().readValue(jsonStr, RspReportMerchant.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个样式

		style = workbook.createCellStyle();

		// 设置这些样式
		// HSSFColor.SKY_BLUE，CORNFLOWER_BLUE
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
		style4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);

		JSONObject jsonArr = JSONObject.parseObject(jsonStr);// 通过jsonstr字符串构造JSONArray对象
		String resCode = (String) jsonArr.get("resCode");
		if (resCode.equals("0000")) {
			// JSONArray header = (JSONArray)jsonArr.get("header");
			// JSONArray data = (JSONArray)jsonArr.get("data");

			Map<String, List<JSONObject>> jsonMap = jsonToMap(jsonStr);

			// 商户交易分析报告
			String[] dxpf_t = { "商户名称#mname", "报告编号#tid#报告时间#time", "每月还贷能力预测#repaymentAbility", };

			// 核心经营指标
			String[] bg_t = { "首次交易日期#firstTransDate#交易总人数#transPersonCount", "交易总金额（元）#transAmountTotal#交易总笔数（笔）#transCount", "周均交易金额（元）#weekTransAmountAvg#周均交易笔数（笔）#weekTransCountAvg", "月均交易天数（天）#monthDaysAvg#客单价（元）#personPrice", "笔单价（元）#countPrice#  #kong" };

			// 经营稳定性指标
			String[] hd_t = { "低于周均交易额的周数#weekLowThanAvgCount#最近连续低于周均交易额的周数#weekLatelyLowThanAvgCount", "周交易额中值（元）#weekTransAmountMedian#交易额峰值时间段众数#transAmountHighestTendays", "周均交易金额变异系数#weekTransAmountAvgCv#周均交易笔数变异系数#weekTransCountAvgCv", "月均交易天数变异系数#monthTransDaysAvgCv##kong"

			};

			// 异常交易指标
			String[] ab_t = { "反向交易金额（元）#reverseTransAmount#反向交易笔数（笔）#reverseTransCount", "交易金额前五的客户的金额占比#top5TransAmountProp#交易金额前五的客户的笔数占比#top5TransCountProp", "贷记卡的交易总金额（元）#creditCardTransAmount#贷记卡的交易总笔数（笔）#creditCardTransCount",
					"借记卡的交易总金额（元）#debitCardTransAmount#借记卡的交易总笔数（笔）#debitCardTransCount", "非正常时间的交易总金额（元）#abnormalTimeTransAmount#非正常时间的交易总笔数（笔）#abnormalTimeTransCount" };

			// 客户忠诚度
			String[] headers_indexLoyalty = { "客户分类", "消费笔数", "交易金额占比(%)", "交易笔数占比(%)", "交易人数占比(%)" };
			String[] propertys_indexLoyalty = { "customerType", "consumeCount", "amountProp", "countProp", "personProp" };

			// 客户地域分布
			String[] headers_indexArea = { "客户分类", "交易金额占比(%)", "交易笔数占比(%)", "交易人数占比(%)" };
			String[] propertys_indexArea = { "customerType", "amountProp", "countProp", "personProp" };

			Integer rowIndex = 0;

			HSSFSheet sheet = workbook.createSheet("商户交易分析报告");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);

			rowIndex = setCellValueMethod(jsonMap.get("dxpf"), dxpf_t, "商户交易分析报告", rowIndex, sheet) + 1;

			rowIndex = setCellValueMethod(jsonMap.get("bg"), bg_t, "核心经营指标", rowIndex, sheet) + 1;

			rowIndex = setCellValueMethod(jsonMap.get("hd"), hd_t, "经营稳定性指标", rowIndex, sheet) + 1;

			rowIndex = setCellValueMethod(jsonMap.get("ab"), ab_t, "异常交易指标", rowIndex, sheet) + 1;

			TreeMap<String, IndexContrast> indexContrasts = model.getData().getIndexContrast();
			// 导出本市同行对比
			if (indexContrasts != null && indexContrasts.size() != 0) {
				CellRangeAddress cra;
				cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
				HSSFRow row = sheet.createRow(rowIndex++);
				row.setHeight((short) 450);
				HSSFCell cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("本市同行对比");
				sheet.addMergedRegion(cra);
				IndexContrast inco = new IndexContrast();
				List<String> indexContrast = new ArrayList<String>();
				List<String> monthAmount = new ArrayList<String>();
				List<String> monthAmountRank = new ArrayList<String>();
				List<String> monthCount = new ArrayList<String>();
				List<String> monthCountRank = new ArrayList<String>();
				for (String str : indexContrasts.keySet()) {
					inco = indexContrasts.get(str);
					indexContrast.add(str + "");
					DecimalFormat bd = new DecimalFormat("0.00");
					monthAmount.add(bd.format(inco.getMonthAmount()) + "");
					monthAmountRank.add("前" + inco.getMonthAmountRank() + "%");
					monthCount.add(inco.getMonthCount() + "");
					monthCountRank.add("前" + inco.getMonthCountRank() + "%");
				}
				excelTree(sheet, indexContrast, "交易指标");
				excelTree(sheet, monthAmount, "交易金额");
				excelTree(sheet, monthAmountRank, "交易金额在本市同行业的排名");
				excelTree(sheet, monthCount, "交易笔数");
				excelTree(sheet, monthCountRank, "交易笔数在本市同行业的排名");

				row = sheet.createRow(sheet.getLastRowNum() + 1);
				row.setHeight((short) 350);
				row.createCell(0);
			}

			Collection<IndexArea> indexarea = model.getData().getIndexArea();
			ExcelLineDown<IndexArea> ex = new ExcelLineDown<IndexArea>();
			ex.doExcuteData(style, style2, style3, sheet, headers_indexArea, indexarea, out, pattern, propertys_indexArea, "客户地域分布");

			Collection<IndexLoyalty> indexLoyalty = model.getData().getIndexLoyalty();
			ExcelLineDown<IndexLoyalty> ex1 = new ExcelLineDown<IndexLoyalty>();
			ex1.doExcuteData(style, style2, style3, sheet, headers_indexLoyalty, indexLoyalty, out, pattern, propertys_indexLoyalty, "客户忠诚度");

		} else {
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-商户交易分析报告-详细信息");
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
		}

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
			// workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
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
	public byte[] exportExcel (Map<String, Object> map) {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 生成一个样式
		style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);// 把字体应用到当前的样式

		// 生成并设置另一个样式
		style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style2.setFont(font2);		// 把字体应用到当前的样式

		
		style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style4 = workbook.createCellStyle();
		style4.setFillForegroundColor(HSSFColor.WHITE.index);
		style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		

		// 商户交易分析报告
		String[] reporthead_T = { 
				"商户编号#reportBody", 
				"报告时间#reportDate#查询机构#reportShortName", 
				"报告编号#reportTransId#查询人员#reportAccount" 
		};

		// 核心经营指标
		String[] indexCore_T = { 
				"首次交易日期#firstTransDate#有效交易POS终端台数#effectivePosNum", 
				"交易总金额（元）#totalAmt#交易总卡数#totalCardNum", 
				"交易总笔数#totalCnt#月均交易天数#avgTransDay", 
				"笔单价#cntPrice#客单价#cusPrice", 
				"近12月单月最大交易金额#transMaxAmt#近12月单月最小交易金额#transMinAmt", 
				"近12月法定节假日交易金额占比#holidayTransAmtPre#近12月法定节假日交易笔数占比#holidayTransCntPre", 
				"近12月卡均交易次数#avgCardTransCnt" 
		};

		// 经营稳定性指标
		String[] indexStability_T = { 
				"近24周低于周均交易额的周数#underAvgWenkAmtNum#三个月移动平均交易金额变异系数#transAmtCv", 
				"三个月移动平均交易卡数变异系数#transCardNumCv#三个月移动平均交易笔数变异系数#transCntCv"
		};

		// 异常交易指标
		String[] indexAbnormal_T = { 
				"反向交易金额#reverseTransTotalAmt#反向交易笔数#reverseTransTotalCnt",
				"近12月交易金额前五的客户的金额占比#transAmtTop5Pre#近12月交易金额前五的客户的笔数占比#transCntTop5Pre",
				"贷记卡的交易总金额占比#debitCardTotalAmtPre#贷记卡的交易总笔数占比#debitCardTotalCntPre",
				"非正常时间的交易总金额占比#improperTransAmtPre#非正常时间的交易总笔数占比#improperTransCntPre",
				"同卡大额交易金额汇总#cardLargeTotalAmt#同卡大额交易笔数汇总#cardLargeTotalCnt",
				"非正常交易卡交易金额汇总#improperTransTotalAmt#非正常交易卡交易笔数汇总#improperTransTotalCnt",
				"交易失败总金额#transFailedTotalAmt#交易失败总笔数#transFailedTotalCnt"
		};
		
		// 每月经营状况
		String[] indexChange_T = { 
				"月份#transYM",
				"每月交易额#transAmt",
				"每月交易额同比增长率#transAmtFellPre",
				"每月交易额本市同行业排名#transAmtPeerRank",
				"每月交易笔数#transCnt",
				"每月交易笔数同比增长率#transCntFellPre",
				"每月交易笔数本市同行业排名#transCntPeerRank",
				"每月交易卡数#transCardNum",
				"每月交易卡数本市同行业排名#transCardNumPeerRank",
		};
		
//			周交易额中值#transMidAmt
//			周均交易金额#transAvgAmt
		
		// 每周经营状况
		String[] indexContrast_T = { 
				"最近周#transContrast",
				"每周交易金额#transAmt",
				"每周交易笔数#transCnt",
		};
		
		// 客户忠诚度分析
		String[] indexLoyalty_T = { 
				"客户分类#cusType",
				"交易金额占比#transAmtPre",
				"交易笔数占比#transCntPre",
				"交易人数占比#transCusNumPre",
		};
		
		// 客户地域分布
		String[] indexArea_T = { 
				"地区分类#areaType",
				"交易金额占比#transAmtPre",
				"交易笔数占比#transCntPre",
				"交易人数占比#transCusNumPre",
		};
		
		
		
		// 疑似信用卡套现甄别
		String[] cashout_T = { 
				"首次交易日期#cashAmt#有效交易POS终端台数#cashCnt",
				"交易总金额#cashAmtPre#交易总卡数#cashCntPre",
				"交易总笔数#debitCreditCusPricePre"
		};


		Integer rowIndex = 0;

		HSSFSheet sheet = workbook.createSheet("商户交易分析报告");
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);

		rowIndex = setCellValueMethod_NEW(map.get("reporthead"), reporthead_T, "商户交易分析报告", rowIndex, sheet) + 1;

		rowIndex = setCellValueMethod_NEW(map.get("indexCore"), indexCore_T, "核心经营指标", rowIndex, sheet) + 1;

		rowIndex = setCellValueMethod_NEW(map.get("indexStability"), indexStability_T, "经营稳定性指标", rowIndex, sheet) + 1;

		rowIndex = setCellValueMethod_NEW(map.get("indexAbnormal"), indexAbnormal_T, "异常交易指标", rowIndex, sheet) + 1;
		
		rowIndex = setRowCellValueMethod(map.get("indexChange"), indexChange_T, "每月经营状况", rowIndex, sheet);
		
		rowIndex = setRowCellValueMethod(map.get("indexContrast"), indexContrast_T, "每周经营状况", rowIndex, sheet);
		
		rowIndex = setRowCellValueMethod(map.get("indexLoyalty"), indexLoyalty_T, "客户忠诚度分析", rowIndex, sheet);
		
		rowIndex = setRowCellValueMethod(map.get("indexArea"), indexArea_T, "客户地域分布", rowIndex, sheet);
		
		rowIndex = setCellValueMethod_NEW(map.get("cashout"), cashout_T, "疑似信用卡套现甄别", rowIndex, sheet) + 1;
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
			// workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
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
	public Integer setCellValueMethod_NEW(Object data, String[] arrayData, String title, Integer rowIndex, HSSFSheet sheet) {
		rowIndex = rowIndex + 1;
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

		if (data != null) {
			JSONObject object = (JSONObject) data;
			array++;
			for (int i = 0; i < arrayData.length; i++) {
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);
				String[] cellData = arrayData[i].split("#");
				int cellIndex = cellData.length;

				for (int m = 0; m < cellIndex; m++) {
					if (cellIndex == 4) {
						cell = row.createCell(m);
						if (array % 2 == 0) {
							cell.setCellStyle(style2);
						} else {
							cell.setCellStyle(style3);
						}
						if (m % 2 == 0) {
							cell.setCellValue(cellData[m]);
						} else {
							String fieldName = cellData[m];
							if ("transAmountTotal".equals(fieldName)) {
								cell.setCellValue((object.get(fieldName) == null || object.get(fieldName).equals("")) ? "--" : object.get(fieldName) + "");
							} else {
								cell.setCellValue((object.get(fieldName) == null || object.get(fieldName).equals("")) ? "--" : object.get(fieldName) + "");
							}
							cell.setCellStyle(style4);
						}
					} else {
						cell = row.createCell(m);
						if (array % 2 == 0) {
							cell.setCellStyle(style2);
						} else {
							cell.setCellStyle(style3);
						}
						if (m % 2 == 0) {
							cell.setCellValue(cellData[m]);
						} else {
							cra = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 1, 3);
							sheet.addMergedRegion(cra);
							String fieldName = cellData[m];
							cell.setCellValue((object.get(fieldName) == null || object.get(fieldName).equals("")) ? "--" : object.get(fieldName) + "");
							cell.setCellStyle(style4);
							cell = row.createCell(2);
							cell.setCellStyle(style4);
							cell = row.createCell(3);
							cell.setCellStyle(style4);
						}
					}
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
		
		return rowIndex;
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
	public Integer setRowCellValueMethod(Object result, String[] arrayData, String title, Integer rowIndex, HSSFSheet sheet) {
		rowIndex = rowIndex + 1;
		// 设置表格默认列宽度为25个字节
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

		if (result != null) {
			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 350);
			List<Object> list = (List<Object>) result;
			if(null != arrayData){
				for (int i = 0; i < arrayData.length; i++) {
					String titles = arrayData[i].split("#")[0];
					cell = row.createCell(i);
					cell.setCellStyle(style3);
					cell.setCellValue(titles);
				}
			}
			
			for (Object datas : list) {
				row.setHeight((short) 350);
				row = sheet.createRow(rowIndex++);
				JSONObject data = (JSONObject) JSONObject.toJSON(datas);
				for (int i = 0; i < arrayData.length; i++) {
					String fieldName = arrayData[i].split("#")[1];
					cell = row.createCell(i);
					cell.setCellStyle(style3);
				    cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");	
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
		return rowIndex;
	}
	
	
	

	public Map<String, List<JSONObject>> jsonToMap(String jsonStr) {
		JSONObject jsonArr = JSONObject.parseObject(jsonStr);// 通过jsonstr字符串构造JSONArray对象
		Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject header = (JSONObject) jsonArr.get("header");
		JSONObject data = (JSONObject) jsonArr.get("data");
		String repaymentAbilitys = (String) data.get("repaymentAbility") + "万元";
		String repaymentAbility = repaymentAbilitys.replace(",", "~");
		header.put("repaymentAbility", repaymentAbility);
		jsonMap = setJsonMap(jsonMap, list, header, "dxpf");

		JSONObject indexCore = (JSONObject) data.get("indexCore");
		String kong = "";
		indexCore.put("kong", kong);
		jsonMap = setJsonMap(jsonMap, list, indexCore, "bg");

		JSONObject indexStability = (JSONObject) data.get("indexStability");
		indexStability.put("kong", kong);
		String weekTransAmountAvgCv = indexStability.get("weekTransAmountAvgCv") + "%";
		String weekTransCountAvgCv = indexStability.get("weekTransCountAvgCv") + "%";
		String monthTransDaysAvgCv = indexStability.get("monthTransDaysAvgCv") + "%";
		indexStability.put("weekTransAmountAvgCv", weekTransAmountAvgCv);
		indexStability.put("weekTransCountAvgCv", weekTransCountAvgCv);
		indexStability.put("monthTransDaysAvgCv", monthTransDaysAvgCv);
		jsonMap = setJsonMap(jsonMap, list, indexStability, "hd");

		JSONObject indexAbnormal = (JSONObject) data.get("indexAbnormal");
		DecimalFormat bd = new DecimalFormat("0.00");
		String reverseTransAmount = "￥" + indexAbnormal.get("reverseTransAmount");
		String creditCardTransAmount = "￥" + bd.format(indexAbnormal.get("creditCardTransAmount"));
		String debitCardTransAmount = "￥" + indexAbnormal.get("debitCardTransAmount");
		String abnormalTimeTransAmount = "￥" + indexAbnormal.get("abnormalTimeTransAmount");
		String top5TransAmountProp = indexAbnormal.get("top5TransAmountProp") + "%";
		String top5TransCountProp = indexAbnormal.get("top5TransCountProp") + "%";
		indexAbnormal.put("reverseTransAmount", reverseTransAmount);
		indexAbnormal.put("creditCardTransAmount", creditCardTransAmount);
		indexAbnormal.put("debitCardTransAmount", debitCardTransAmount);
		indexAbnormal.put("abnormalTimeTransAmount", abnormalTimeTransAmount);
		indexAbnormal.put("top5TransAmountProp", top5TransAmountProp);
		indexAbnormal.put("top5TransCountProp", top5TransCountProp);
		jsonMap = setJsonMap(jsonMap, list, indexAbnormal, "ab");

		return jsonMap;
	}

	public Map<String, List<JSONObject>> setJsonMap(Map<String, List<JSONObject>> jsonMap, List<JSONObject> list, JSONObject data, String name) {
		list = jsonMap.get(name);
		if (list == null) {
			list = new ArrayList<JSONObject>();
		}
		list.add(data);
		jsonMap.put(name, list);
		return jsonMap;
	}

	public Integer setCellValueMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex, HSSFSheet sheet) {

		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra;
		// rowIndex = 6;
		cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 450);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);
		Integer array = 0;

		DecimalFormat bd = new DecimalFormat("0.00");
		if (list != null) {
			for (JSONObject data : list) {
				if (data != null) {
					array++;
					for (int i = 0; i < arrayData.length; i++) {
						row = sheet.createRow(rowIndex++);
						row.setHeight((short) 350);
						String[] cellData = arrayData[i].split("#");
						int cellIndex = cellData.length;

						for (int m = 0; m < cellIndex; m++) {
							if (cellIndex == 4) {
								cell = row.createCell(m);
								if (array % 2 == 0) {
									cell.setCellStyle(style2);
								} else {
									cell.setCellStyle(style3);
								}
								if (m % 2 == 0) {
									cell.setCellValue(cellData[m]);
								} else {
									String fieldName = cellData[m];
									if ("transAmountTotal".equals(fieldName)) {
										cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : bd.format(data.get(fieldName)) + "");
									} else {
										cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "");
									}
									cell.setCellStyle(style4);
								}
							} else {
								cell = row.createCell(m);
								if (array % 2 == 0) {
									cell.setCellStyle(style2);
								} else {
									cell.setCellStyle(style3);
								}
								if (m % 2 == 0) {
									cell.setCellValue(cellData[m]);
								} else {
									cra = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 1, 3);
									sheet.addMergedRegion(cra);
									String fieldName = cellData[m];
									cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "");
									cell.setCellStyle(style4);
									cell = row.createCell(2);
									cell.setCellStyle(style4);
									cell = row.createCell(3);
									cell.setCellStyle(style4);
								}
							}
						}
					}
				}
			}
		}
		return rowIndex;
	}


	public Integer setRowCellValueMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex, HSSFWorkbook workbook) {

		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);

		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra;

		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 350);
		for (int i = 0; i < arrayData.length; i++) {
			String titles = arrayData[i].split("#")[0];
			cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(titles);
		}

		if (list != null) {
			for (JSONObject data : list) {
				row.setHeight((short) 350);
				row = sheet.createRow(rowIndex++);
				for (int i = 0; i < arrayData.length; i++) {
					String fieldName = arrayData[i].split("#")[1];
					cell = row.createCell(i);
					cell.setCellStyle(style3);
					cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "");
				}
			}
		} else {
			cra = new CellRangeAddress(rowIndex, rowIndex, 0, arrayData.length - 1);
			sheet.addMergedRegion(cra);
			row = sheet.createRow(rowIndex);
			row.setHeight((short) 350);
			cell = row.createCell(0);
			cell.setCellStyle(style3);
			cell.setCellValue("暂未查到该项信息");
		}
		return rowIndex;
	}

	private void excelTree(HSSFSheet sheet, List<String> list, String title) {
		HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
		row.setHeight((short) 350);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);
		if (list != null) {
			int i = 1;
			for (String s : list) {
				cell = row.createCell(i++);
				cell.setCellStyle(style3);
				cell.setCellValue(s);
			}
		}
	}

}