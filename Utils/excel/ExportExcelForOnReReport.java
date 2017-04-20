package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.ccx.credit.util.EcomUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("deprecation")
public class ExportExcelForOnReReport<T> {

	public void exportIcInfoOnReExcel(String name, String phone, String cid, String result, JSONObject jsonObjMap,
			OutputStream out) {
		exportIcInfoOnReExcel(name, phone, cid, result, jsonObjMap, out, "yyyy-MM-dd");
	}

	private static HSSFCellStyle style;
	private static HSSFCellStyle style2;
	private static HSSFCellStyle style3;

	public byte[] exportIcInfoOnReExcel(String name, String phone, String cid, String result, JSONObject jsonObjMap,
			OutputStream out, String pattern) {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个样式

		style = workbook.createCellStyle();

		//
		// 大标题 设置样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 居左显示--黄色
		style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style2.setWrapText(true);

		// 居左显示--蓝色
		style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style3.setWrapText(true); // 自动换行

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 电商消费画像报告
		Integer rowIndex = -1;
		HSSFSheet sheet = workbook.createSheet("电商消费画像");

		JSONObject paramJsonObjMap = JSONObject.fromObject(jsonObjMap);
		// 查询条件
		rowIndex = setRowCellValueTermMethod(paramJsonObjMap, name, phone, cid, result, "查询条件", rowIndex, sheet);

		// 重要时间点
		String[] On_c = { "最早支付时间:#minPayTime", "最大无消费间隔月数:#maxNoPayMonth", "最高消费金额月份:#maxPayMonth",
				"最多订单月份:#maxOrderMonth", "最近支付时间:#maxPayTime" };

		JSONObject jsonObjMap1 = JSONObject.fromObject(jsonObjMap);
		rowIndex = setRowCellValueMethod(jsonObjMap, On_c, "重要时间点", rowIndex, sheet);

		// 年度统计
		String[] On_d = { "消费总金额（元）#yearSpentSum", "消费金额排名#yearSpentCity", "订单总数（笔）#yearOrderSum",
				"订单数排名#yearOrderCity", "收货城市#yearCity", };

		rowIndex = setCellValueMethod(jsonObjMap, On_d, "年度统计", rowIndex, sheet);

		// 平台top3
		String[] On_e = { "平台消费金额占比#avgSpent", "平台订单数占比#avgOrderCount", "平台订单均额（元）#avgOrderSpent" };

		rowIndex = setCellValueMethod(jsonObjMap, On_e, "平台top3", rowIndex, sheet);

		// 上年订单行为统计
		String[] On_h = { "年度订单数（笔）#ocYear", "年退货次数#roCount", "年优惠次数#pcYear", "年退货次数占比#roPercent",
				"年优惠次数占比#pcPercent" };

		rowIndex = setRowCellValueMethod(jsonObjMap1, On_h, "上年度订单行为统计 ", rowIndex, sheet);

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
	 * 横版表格 查询条件-->单独导出
	 * 
	 * @param list
	 * @param arrayData
	 * @param title
	 * @param rowIndex
	 * @param sheet
	 * @return
	 */
	public byte[] setRowCellValueTermOnlyMethod(String name, String phone, String cid, String resMsg) {
		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个样式

		style = workbook.createCellStyle();

		//
		// 大标题 设置样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 居左显示--蓝色
		style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style3.setWrapText(true); // 自动换行

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		HSSFSheet sheet = workbook.createSheet("电商消费画像");
		// 把字体应用到当前的样式
		style3.setFont(font2);
		Integer rowIndex = -1;
		rowIndex = rowIndex + 1;
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 450);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("查询条件");

		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 350);
		cell = row.createCell(0);
		cell.setCellStyle(style3);
		cell.setCellValue("姓名");
		cell = row.createCell(1);
		cell.setCellStyle(style3);
		cell.setCellValue("身份证");
		cell = row.createCell(2);
		cell.setCellStyle(style3);
		cell.setCellValue("手机号");
		cell = row.createCell(3);
		cell.setCellStyle(style3);
		cell.setCellValue("核查结果");

		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 350);
		cell = row.createCell(0);
		cell.setCellStyle(style3);
		cell.setCellValue(name);
		cell = row.createCell(1);
		cell.setCellStyle(style3);
		cell.setCellValue(cid);
		cell = row.createCell(2);
		cell.setCellStyle(style3);
		cell.setCellValue(phone);
		cell = row.createCell(3);
		cell.setCellStyle(style3);
		cell.setCellValue(resMsg);

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
	 * 横版表格 查询条件
	 * 
	 * @param list
	 * @param arrayData
	 * @param title
	 * @param rowIndex
	 * @param sheet
	 * @return
	 */
	public Integer setRowCellValueTermMethod(JSONObject object, String name, String phone, String cid, String result,
			String title, Integer rowIndex, HSSFSheet sheet) {
		rowIndex = rowIndex + 1;
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 450);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);

		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 350);
		cell = row.createCell(0);
		cell.setCellStyle(style3);
		cell.setCellValue("姓名");
		cell = row.createCell(1);
		cell.setCellStyle(style3);
		cell.setCellValue("身份证");
		cell = row.createCell(2);
		cell.setCellStyle(style3);
		cell.setCellValue("手机号");
		cell = row.createCell(3);
		cell.setCellStyle(style3);
		cell.setCellValue("核查结果");

		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 350);
		cell = row.createCell(0);
		cell.setCellStyle(style3);
		cell.setCellValue(name);
		cell = row.createCell(1);
		cell.setCellStyle(style3);
		cell.setCellValue(cid);
		cell = row.createCell(2);
		cell.setCellStyle(style3);
		cell.setCellValue(phone);
		cell = row.createCell(3);
		cell.setCellStyle(style3);
		cell.setCellValue(result);
		return rowIndex;
	}

	/**
	 * 横版表格 setValue
	 * 
	 * @param list
	 * @param arrayData
	 * @param title
	 * @param rowIndex
	 * @param sheet
	 * @return
	 */
	public Integer setRowCellValueMethod(JSONObject object, String[] arrayData, String title, Integer rowIndex,
			HSSFSheet sheet) {
		rowIndex = rowIndex + 1;
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, arrayData.length - 1);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 450);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);

		JSONObject object2 = null;
		if (title.equals("重要时间点")) {
			Map<String, JSONObject> map = EcomUtils.jsonMap(object);
			object2 = map.get("esTime");
		} else {
			Map<String, JSONObject> map2 = EcomUtils.jsonMap(object);
			object2 = map2.get("esOy");
		}
		if (!object2.isEmpty()) {

			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 350);
			if (null != arrayData) {
				for (int i = 0; i < arrayData.length; i++) {
					String titles = arrayData[i].split("#")[0];
					cell = row.createCell(i);
					cell.setCellStyle(style3);
					cell.setCellValue(titles);
				}
			}
			row.setHeight((short) 350);
			row = sheet.createRow(rowIndex++);
			for (int i = 0; i < arrayData.length; i++) {
				String fieldName = arrayData[i].split("#")[1];
				cell = row.createCell(i);
				cell.setCellStyle(style3);
				cell.setCellValue((object2.get(fieldName) == null || object2.get(fieldName).equals("")
						|| object2.get(fieldName).equals(null) || object2.get(fieldName).equals("\"null\"")) ? "--"
								: object2.get(fieldName) + "");
			}
		} else {

			if (arrayData != null) {

				cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
				sheet.addMergedRegion(cra);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 450);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(title);

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);
				cell = row.createCell(0);
				cell.setCellStyle(style3);
				cell.setCellValue("暂无信息");

			}

		}
		return rowIndex;
	}

	/**
	 * 竖版表格 年度统计 平台top3
	 * 
	 * @param list
	 *            当前分类内容
	 * @param arrayData
	 *            标题-字段对应
	 * @param title
	 *            分类标题
	 * @param rowIndex
	 *            行数
	 * @param sheet
	 *            大标题
	 * @return
	 */
	public Integer setCellValueMethod(JSONObject data, String[] arrayData, String title, Integer rowIndex,
			HSSFSheet sheet) {
		rowIndex = rowIndex + 1;
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra = null;
		Integer array = 0;
		Map<String, JSONArray> map = EcomUtils.jsonConvertMap(data);
		JSONArray object2 = null;
		if (title.equals("年度统计")) {
			object2 = map.get("yearTime");
		}
		if (title.equals("平台top3")) {
			object2 = map.get("avgSpent");
		}
		if (!object2.isEmpty()) {
			array++;
			if (arrayData.length == 5) {
				cra = new CellRangeAddress(rowIndex, rowIndex, 0, arrayData.length);
				sheet.addMergedRegion(cra);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 450);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(title);

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);
				cell = row.createCell(0);
				cell.setCellStyle(style3);
				cell.setCellValue("年份");

				Integer yearNum = 1;
				JSONArray yearObject = map.get("yearTime");
				cell = row.createCell(yearNum);
				cell.setCellStyle(style3);
				for (int i = 0; i < yearObject.size(); i++) {
					cell = row.createCell(yearNum);
					cell.setCellStyle(style3);
					cell.setCellValue(yearObject.getInt(i));
					yearNum = yearNum + 1;
				}
			} else if (arrayData.length == 3) {
				cra = new CellRangeAddress(rowIndex, rowIndex, 0, arrayData.length);
				sheet.addMergedRegion(cra);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 450);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(title);

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);

				cell = row.createCell(0);
				cell.setCellStyle(style3);
				cell.setCellValue("商家");

				Integer topNum = 1;
				JSONArray topObject = map.get("avgSpent");
				cell = row.createCell(topNum);
				cell.setCellStyle(style3);
				for (int i = 0; i < topObject.size(); i++) {
					cell = row.createCell(topNum);
					cell.setCellStyle(style3);
					cell.setCellValue(topObject.getInt(i));
					topNum = topNum + 1;
				}
			}
			for (int k = 0; k < arrayData.length; k++) {
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);
				String[] cellData = arrayData[k].split("#");
				int cellIndex = cellData.length;
				for (int m = 0; m < cellIndex; m++) {
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
						JSONArray tempObject = map.get(fieldName);
						Integer num = 1;
						if (fieldName.equals("yearSpentSum") || fieldName.equals("yearSpentCity")
								|| fieldName.equals("yearOrderSum") || fieldName.equals("yearOrderCity")
								|| fieldName.equals("yearCity")) {
							for (int i = 0; i < tempObject.size(); i++) {
								cell = row.createCell(num);
								cell.setCellStyle(style3);
								cell.setCellValue((tempObject.get(i).equals("null")|| tempObject.get(i).equals("\"null\"")|| tempObject.get(i).equals(null))? "--" : tempObject.get(i) + "");
								num = num + 1;
							}
						} else {

							for (int i = 0; i < tempObject.size(); i++) {

								cell = row.createCell(num);
								cell.setCellStyle(style3);
								cell.setCellValue((tempObject.get(i).equals("null")|| tempObject.get(i).equals("\"null\"")|| tempObject.get(i).equals(null))? "--" : tempObject.get(i) + "");
								num = num + 1;
							}
						}
					}
				}
			}
		} else {

			if (arrayData != null) {

				cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
				sheet.addMergedRegion(cra);
				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 450);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(title);

				row = sheet.createRow(rowIndex++);
				row.setHeight((short) 350);
				cell = row.createCell(0);
				cell.setCellStyle(style3);
				cell.setCellValue("暂无信息");

			} else {
				rowIndex--;
			}

		}

		return rowIndex;
	}

}