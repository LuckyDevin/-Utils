package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSONObject;
import com.ccx.credit.util.StringUtil;

@SuppressWarnings("deprecation")
public class ExcelForNegativeNew {

	private static HSSFCellStyle style;
	private static HSSFCellStyle style2;
	private static HSSFCellStyle style3;
	private static HSSFCellStyle style4;

	private void initwork(HSSFWorkbook workbook) {
		// 声明一个工作薄

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
		style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style4.setWrapText(true);

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);
	}

	public Map<String, Object> exportNegativeNewHF(String jsonStr, OutputStream out, String pattern) {
		Map<String, Object> map = new HashMap<String, Object>();
		HSSFWorkbook workbook = new HSSFWorkbook();
		initwork(workbook);
		JSONObject jsonArr = JSONObject.parseObject(jsonStr);// 通过jsonstr字符串构造JSONArray对象
		String resCode = (String) jsonArr.get("resCode");
		Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject data = (JSONObject) jsonArr.get("data");
		String type = data.getString("type");
		String hfSmallTitle = "";
		String sheetName = "";
		if (type.equals("21")) {
			hfSmallTitle = "newPpjws";
			sheetName = "判决文书";
		} else if (type.equals("22") || type.equals("6") || type.equals("11")) {
			hfSmallTitle = "newPSxbzx";
			sheetName = "失信被执行人";
		} else if (type.equals("23") || type.equals("10")) {
			hfSmallTitle = "newPZgfzx";
			sheetName = "被执行人";
		} else if (type.equals("24")) {
			hfSmallTitle = "newPCqgg";
			sheetName = "催欠公告";
		} else if (type.equals("25")) {
			hfSmallTitle = "newPXgxfbzxr";
			sheetName = "限制高消费被执行人";
		} else if (type.equals("26")) {
			hfSmallTitle = "newPXzcjbzxr";
			sheetName = "限制出境被执行人";
		} else if (type.equals("27")) {
			hfSmallTitle = "newPLlxx";
			sheetName = "老赖信息";
		} else if (type.equals("28")) {
			hfSmallTitle = "newPQtzxxx";
			sheetName = "其他执行信息";
		} else if (type.equals("29")) {
			hfSmallTitle = "newPLaxx";
			sheetName = "立案信息";
		} else if (type.equals("30")) {
			hfSmallTitle = "newPQsgg";
			sheetName = "欠税公告";
		} else if (type.equals("31")) {
			hfSmallTitle = "newPSznsr";
			sheetName = "失踪纳税人";
		} else if (type.equals("32")) {
			hfSmallTitle = "newPZxxx";
			sheetName = "税务注销信息";
		} else if (type.equals("33")) {
			hfSmallTitle = "newPXzcfjd";
			sheetName = "税务行政处罚决定书";
		} else if (type.equals("34")) {
			hfSmallTitle = "newPSxnsr";
			sheetName = "失信纳税人";
		} else if (type.equals("35")) {
			hfSmallTitle = "newPWfaj";
			sheetName = "税务违法信息";
		} else if (type.equals("36")) {
			hfSmallTitle = "newPZlxqgz";
			sheetName = "行政违法信息";
		} else if (type.equals("37")) {
			hfSmallTitle = "newPYqxx";
			sheetName = "税务逾期信息";
		} else if (type.equals("38")) {
			hfSmallTitle = "newPWdyqmd";
			sheetName = "网贷逾期信息";
		} else if (type.equals("39")) {
			hfSmallTitle = "newPKtxx";
			sheetName = "开庭信息";
		} else if (type.equals("40")) {
			hfSmallTitle = "newPTzgg";
			sheetName = "送达公告";
		}

		if (resCode.equals("0000")) {
			JSONObject entity = (JSONObject) data.get("entity");
			if (type.equals("21")) {// 添加判决文书导出时的内容
				String content = StringUtil.jsonFileToString(entity.getString("our_contenthref"), "content").replace("<p>", "").replace("</p>", "");
				entity.put("content", content);
			}
			jsonMap = setJsonMap(jsonMap, list, entity, hfSmallTitle);
			Integer rowIndex = 0;
			HSSFSheet sheet = workbook.createSheet(sheetName);
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
			HfNewNegative hfnew = new HfNewNegative();
			rowIndex = setCellValueMethod(jsonMap.get(hfSmallTitle), getFieldValueByName((hfSmallTitle + "_t"), hfnew), sheetName, rowIndex, sheet) + 1;
		} else {
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-风险信息查询-" + sheetName);
			// 设置表格默认列宽度为25个字节
			sheet.setDefaultColumnWidth((short) 40);
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			workbook.write(byteOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("bytes", byteOut.toByteArray());
		map.put("sheetName", sheetName);
		return map;
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

		if (list != null && list.size() != 0) {

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
									cell.setCellValue((data.getString(fieldName) == null || data.getString(fieldName).equals("null") || data.getString(fieldName).equals("")) ? "--" : data.getString(fieldName));
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
									cell.setCellValue((data.getString(fieldName) == null || data.getString(fieldName).equals("null") || data.getString(fieldName).equals("")) ? "--" : data.getString(fieldName));
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

	public Integer setCellValueMethod_old(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex, HSSFWorkbook workbook) {

		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);

		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra;

		cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 450);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);
		Integer array = 0;

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
									cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "");
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
									if (array % 2 == 0) {
										cell = row.createCell(2);
										cell.setCellStyle(style2);
										cell = row.createCell(3);
										cell.setCellStyle(style2);
										cell.setCellStyle(style2);
									} else {
										cell.setCellStyle(style3);
										cell = row.createCell(2);
										cell.setCellStyle(style3);
										cell = row.createCell(3);
										cell.setCellStyle(style3);
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

	private class HfNewNegative {

		public HfNewNegative() {
			super();
			// 判决文书
			newPpjws_t = new String[] { "标题#title", "案由#anyou", "当事人名称#name", "身份证号/组织机构代码证#cid", "当事人住址#address", "诉讼地位#type", "法定代表人/负责人#leader", "审理程序#trialprocedure", "审理法院#court", "文书字号#casenum", "文书类型#casetype", "审结日期#time", "文书内容#content", "源文件#ownfile",
					// "异议内容#objection",
					// "异议时间#objectiontime"
			};

			// 失信被执行人
			newPSxbzx_t = new String[] { "当事人名称#name", "身份证号码/组织机构代码#cid", "法定代表人/负责人#leader", "住所地#address", "执行法院#court", "时间#time", "执行案号#casenum", "执行标的#money", "执行依据文号#base", "做出执行依据单位#basecompany", "生效法律文书确定的义务#obligation", "生效法律文书确定的最后履行义务截止时间#lasttime", "被执行人的履行情况#performance",
					"失信被执行人行为具体情形#concretesituation", "认定失信时间#breaktime", "发布时间#posttime", "异议内容#objection", "异议时间#objectiontime" };

			// 被执行人
			newPZgfzx_t = new String[] { "被执行人姓名/名称#name", "身份证号码/组织机构代码#cid", "执行法院#court", "立案时间#time", "执行案号#casenum", "执行标的#money", "案件状态#statute", "执行依据#basic", "做出执行依据的机构#basiccourt", "异议内容#objection", "异议时间#objectiontime" };

			// 催欠公告
			newPCqgg_t = new String[] { "被催欠人名称/姓名#name", "身份证/住址机构代码证#cid", "被催欠单位法定代表人#leader", "被催欠人电话号码#phone", "电子邮箱#email", "详细地址#address", "催欠金额#money", "催欠状态#statute", "催欠人身份#type", "催欠时间#time", "异议内容#objection", "异议时间#objectiontime" };

			// 限制高消费被执行人
			newPXgxfbzxr_t = new String[] { "被限制人#name", "身份证号/组织机构代码证#cid", "法定代表人/负责人#leader", "住所地#address", "执行法院#court", "案号#casenum", "案由#anyou", "标的#money", "立案时间#time", "发布时间#posttime", "具体内容#content", "执行依据#basic", "异议内容#objection", "异议时间#objectiontime" };

			// 限制出境被执行人
			newPXzcjbzxr_t = new String[] { "被限制人#name", "被限制人地址#address", "边控措施#control", "边控日期#controltime", "身份证号/护照号#cid", "承办法院#court", "案号#casenum", "执行依据编号#basic", "立案时间#time", "执行标的#money", "具体内容#content", "异议内容#objection", "异议时间#objectiontime" };

			// 老赖信息
			newPLlxx_t = new String[] { "被执行人#name", "法定代表人#leader", "头像/照片#imgurl", "组织机构代码证/身份证#cid", "住址#address", "执行标的/未履行标的#money", "执行法院#court", "执行案号#casenum", "立案时间#time", "公布时间#posttime", "执行依据文号#basic", "失信情形#situation", "生效文书确定的义务#obligation", "异议内容#objection", "异议时间#objectiontime" };

			// 其他执行信息
			newPQtzxxx_t = new String[] { "执行申请人#applyname", "被执行人#name", "法定代表人/负责人#leader", "身份证号/组织机构代码证号#cid", "异议申请人#applyname2", "执行法院#court", "执行案号#casenum", "执行标的#money", "未履行标的（万元）#money2", "执行依据文号#basic", "立案时间#time", "执行状态#statute", "执行依据制作单位#unit", "公开日期#opentime",
					"生效文书确定的义务#obligation", "住所地#address", "异议内容#objection", "异议时间#objectiontime" };

			// 立案信息
			newPLaxx_t = new String[] { "当事人身份类型/当事人名称#plaintiff",
					/* "被告（被上诉人）#defendant", */
					"第三人#thirdpeople", "原审原告#plaintiff2", "原审被告#defendant2", "原审第三人#thirdpeople2", "受理法院#court", "立案时间#time", "案由#anyou", "案号#casenum", "异议内容#objection", "异议时间#objectiontime" };

			// 欠税公告
			newPQsgg_t = new String[] { "纳税人名称#name", "纳税人识别号#taxnum", "经营地点#address", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cid", "所欠税种#taxtype", "期初陈欠#money", "当期发生欠税余额（元）#money2", "欠税余额#money3", "应征发生日期#time", "认定日期#time2", "主管税务机关#unit", "分管领导#leadership", "主管税务人员#taxpeople", "所属市县区#region",
					"认定字号#casenum", "公告期次#period", "异议内容#objection", "异议时间#objectiontime" };

			// 失踪纳税人
			newPSznsr_t = new String[] { "失踪纳税人名称#name", "纳税人识别号#taxnum", "经营地址#address", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cid", "主管税务机关#unit", "认定失踪日期#misstime", "失踪通知书编号#num", "偷逃欠税税额#money", "公告时间#time", "异议内容#objection", "异议时间#objectiontime" };

			// 税务注销信息
			newPZxxx_t = new String[] { "失踪纳税人名称#name", "纳税人识别号#taxnum", "纳税户类型#peopletype", "经营地址#address", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cid", "主管税务机关#unit", "注销日期#cancletime", "注销类型#cancletype", "注销原因#canclereason", "公告时间#time", "异议内容#objection", "异议时间#objectiontime" };

			// 税务行政处罚决定书
			newPXzcfjd_t = new String[] { "纳税人名称#name", "纳税人识别号#taxnum", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cid", "主管税务机关#unit", "违法事实#situation", "处罚时间#time", "处罚类别#punishmenttype", "处罚结果#punishmentresult", "公告时间#opentime", "生产经营地址#address", "异议内容#objection", "异议时间#objectiontime" };

			// 失信纳税人
			newPSxnsr_t = new String[] { "纳税人名称#name", "纳税人识别号#taxnum", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cid", "主管税务机关#unit", "是否评定为D级#isd", "评定时间#time", "异议内容#objection", "异议时间#objectiontime" };

			// 税务违法信息
			newPWfaj_t = new String[] { "纳税人名称#name", "纳税人识别号#taxnum", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cidorcode", "主管税务机关#unit", "检查/稽查年度#year", "稽查文书编号#num", "违法违章事实#fact", "违法违章手段#means", "处理处罚决定日期#punishtime", "处理处罚限定履行日期#decisiontime", "罚款金额#money", "处罚处理实际履行时间#performtime",
					"实缴税款/入库金额（税、罚、滞合计）#money2", "未缴税款/未入库金额(税、罚、滞合计)#money3", "限改状态#statute", "纳税人当前状态#statute2", "公告时间#time", "异议内容#objection", "异议时间#objectiontime" };

			// 行政违法信息
			newPZlxqgz_t = new String[] { "纳税人名称#name", "纳税人识别号#taxnum", "生产经营地址#address", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cid", "主管税务机关#unit", "责令限改通知书号#num", "责令限改状态#statute", "公布日期#time", "异议内容#objection", "异议时间#objectiontime" };

			// 税务逾期信息
			newPYqxx_t = new String[] { "纳税人名称#name", "纳税人识别号#taxnum", "海关代码#code", "经营地址#address", "法定代表人（业主）#leader", "证件类别#type", "证件号码#cid", "主管税务机关#unit", "申报期限#timelimit", "未申报项目#project", "未申报税种#taxtype", "欠缴税额#objection", "处罚金额#money2", "处罚时间#time", "异议内容#objection", "异议时间#objectiontime" };

			// 网贷逾期信息
			newPWdyqmd_t = new String[] { "出借方#lender", "借款人名称#name", "法定代表人（负责人）#leader", "身份证/组织机构代码证#cid", "性别#sex", "居住地址#address", "电话号码#phone", "网络昵称#networkname", "QQ号码#qq", "电子邮箱#email", "淘宝账户#taobao", "借款时间#time", "借入本金#money", "借款到期时间#time2", "欠款本息总额#totalmoney", "首次逾期日期#time3",
					"最长逾期天数#days", "信息更新时间#time4", "描述#description", "工作单位#workunit", "身份证地址#idaddress", "单位地址#workunitaddress", "单位电话#workunitphone", "共同借款人#other", "共同借款人身份证号#othercidorcode", "共同借款人电话#otherphone", "异议内容#objection", "异议时间#objectiontime" };

			// 开庭信息
			newPKtxx_t = new String[] { "当事人身份类型/当事人名称#plaintiff",
					/* "被告（被上诉人）#defendant", */
					"原审被告#plaintiff2", "原审第三人#thirdperple", "审理法院#court", "开庭时间#time", "案由#anyou", "案号#casenum", "异议内容#objection", "异议时间#objectiontime" };

			// 送达公告
			newPTzgg_t = new String[] { "标题#title", "送达类型#type", "当事人名称#name", "身份证号、组织机构代码#cid", "送达内容#content", "送达法院#court", "刊登媒体#media", "刊登日期#time", "刊登版面#banmian", "异议内容#objection", "异议时间#objectiontime" };

		}

		private String[] newPpjws_t;
		private String[] newPSxbzx_t;
		private String[] newPCqgg_t;
		private String[] newPZgfzx_t;
		private String[] newPXgxfbzxr_t;
		private String[] newPXzcjbzxr_t;
		private String[] newPLlxx_t;
		private String[] newPQtzxxx_t;
		private String[] newPLaxx_t;
		private String[] newPQsgg_t;
		private String[] newPSznsr_t;
		private String[] newPZxxx_t;
		private String[] newPXzcfjd_t;
		private String[] newPSxnsr_t;
		private String[] newPWfaj_t;
		private String[] newPZlxqgz_t;
		private String[] newPYqxx_t;
		private String[] newPWdyqmd_t;
		private String[] newPKtxx_t;
		private String[] newPTzgg_t;

		public String[] getNewPpjws_t() {
			return newPpjws_t;
		}

		public void setNewPpjws_t(String[] newPpjws_t) {
			this.newPpjws_t = newPpjws_t;
		}

		public String[] getNewPSxbzx_t() {
			return newPSxbzx_t;
		}

		public void setNewPSxbzx_t(String[] newPSxbzx_t) {
			this.newPSxbzx_t = newPSxbzx_t;
		}

		public String[] getNewPCqgg_t() {
			return newPCqgg_t;
		}

		public void setNewPCqgg_t(String[] newPCqgg_t) {
			this.newPCqgg_t = newPCqgg_t;
		}

		public String[] getNewPZgfzx_t() {
			return newPZgfzx_t;
		}

		public void setNewPZgfzx_t(String[] newPZgfzx_t) {
			this.newPZgfzx_t = newPZgfzx_t;
		}

		public String[] getNewPXgxfbzxr_t() {
			return newPXgxfbzxr_t;
		}

		public void setNewPXgxfbzxr_t(String[] newPXgxfbzxr_t) {
			this.newPXgxfbzxr_t = newPXgxfbzxr_t;
		}

		public String[] getNewPXzcjbzxr_t() {
			return newPXzcjbzxr_t;
		}

		public void setNewPXzcjbzxr_t(String[] newPXzcjbzxr_t) {
			this.newPXzcjbzxr_t = newPXzcjbzxr_t;
		}

		public String[] getNewPLlxx_t() {
			return newPLlxx_t;
		}

		public void setNewPLlxx_t(String[] newPLlxx_t) {
			this.newPLlxx_t = newPLlxx_t;
		}

		public String[] getNewPQtzxxx_t() {
			return newPQtzxxx_t;
		}

		public void setNewPQtzxxx_t(String[] newPQtzxxx_t) {
			this.newPQtzxxx_t = newPQtzxxx_t;
		}

		public String[] getNewPLaxx_t() {
			return newPLaxx_t;
		}

		public void setNewPLaxx_t(String[] newPLaxx_t) {
			this.newPLaxx_t = newPLaxx_t;
		}

		public String[] getNewPQsgg_t() {
			return newPQsgg_t;
		}

		public void setNewPQsgg_t(String[] newPQsgg_t) {
			this.newPQsgg_t = newPQsgg_t;
		}

		public String[] getNewPSznsr_t() {
			return newPSznsr_t;
		}

		public void setNewPSznsr_t(String[] newPSznsr_t) {
			this.newPSznsr_t = newPSznsr_t;
		}

		public String[] getNewPZxxx_t() {
			return newPZxxx_t;
		}

		public void setNewPZxxx_t(String[] newPZxxx_t) {
			this.newPZxxx_t = newPZxxx_t;
		}

		public String[] getNewPXzcfjd_t() {
			return newPXzcfjd_t;
		}

		public void setNewPXzcfjd_t(String[] newPXzcfjd_t) {
			this.newPXzcfjd_t = newPXzcfjd_t;
		}

		public String[] getNewPSxnsr_t() {
			return newPSxnsr_t;
		}

		public void setNewPSxnsr_t(String[] newPSxnsr_t) {
			this.newPSxnsr_t = newPSxnsr_t;
		}

		public String[] getNewPWfaj_t() {
			return newPWfaj_t;
		}

		public void setNewPWfaj_t(String[] newPWfaj_t) {
			this.newPWfaj_t = newPWfaj_t;
		}

		public String[] getNewPZlxqgz_t() {
			return newPZlxqgz_t;
		}

		public void setNewPZlxqgz_t(String[] newPZlxqgz_t) {
			this.newPZlxqgz_t = newPZlxqgz_t;
		}

		public String[] getNewPYqxx_t() {
			return newPYqxx_t;
		}

		public void setNewPYqxx_t(String[] newPYqxx_t) {
			this.newPYqxx_t = newPYqxx_t;
		}

		public String[] getNewPWdyqmd_t() {
			return newPWdyqmd_t;
		}

		public void setNewPWdyqmd_t(String[] newPWdyqmd_t) {
			this.newPWdyqmd_t = newPWdyqmd_t;
		}

		public String[] getNewPKtxx_t() {
			return newPKtxx_t;
		}

		public void setNewPKtxx_t(String[] newPKtxx_t) {
			this.newPKtxx_t = newPKtxx_t;
		}

		public String[] getNewPTzgg_t() {
			return newPTzgg_t;
		}

		public void setNewPTzgg_t(String[] newPTzgg_t) {
			this.newPTzgg_t = newPTzgg_t;
		}

	}

	/**
	 * 使用反射根据属性名称获取属性值
	 * 
	 * @param fieldName
	 *            属性名称
	 * @param o
	 *            操作对象
	 * @return Object 属性值
	 */

	private String[] getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			String[] value = (String[]) method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			e.printStackTrace();
			return null;
		}
	}

}