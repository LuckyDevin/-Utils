package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSONObject;
import com.ccx.credit.entreport.model.EntReportUtils;
import com.ccx.credit.model.HistoryResultBean;
import com.ccx.credit.util.FinancialUtils;


public class ExportExcelForFinancialReport {

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
	public byte[] exportIcInfoExcel(List<HistoryResultBean> bean,String name, OutputStream out, String pattern,Integer cate) {

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

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);
		String historydata ="";
		JSONObject info  =null;
		HistoryResultBean hisbean = null;
		if(bean != null && bean.size()>0 && bean.get(0).getType().equals("215")){
			hisbean = bean.get(0);
			historydata = bean.get(0).getHistory_data();
			info = FinancialUtils.financialSetInfo(historydata);
		}else if(bean != null && bean.size()>0){
			info = FinancialUtils.finDataProcess(bean);
			//jsonstr = hisdata.toString();
		}
		//String resCode = (String)jsonObj.get("resCode");
           //基本信息
		//String[]  base_info ={"姓名","身份证号","手机号","核验结果"};
			//金融画像1—近1年的借记卡信息
		String[] info1 = {
				"最近卡余额 (元)#cardRemaSum",
				"卡龄#cardAge", 
				"借记卡数量(个)#cardCount",
				"卡的出账金额(元)#chargeOffSum", 
				"卡的出账笔数(笔)#chargeOffCount",
				"卡的消费金额(元)#payOfSum",
				"卡的线上消费金额(元)#olPayOfSum",
				"卡的入账金额(元)#postSum",
				"卡的入账笔数(笔)#postCount",
				"卡的投资金额(元)#inVestSum"
		};

		//金融画像2—近3个月的借记卡信息
		String[] info2 = {
				"最近卡余额 (元)#cardRemaSum",
				"资产短期净增长率等级#rateOfIncrease", 
				"卡的出账金额(元)#chargeOffSum",
				"卡的出账笔数(笔)#chargeOffCount", 
				"卡的消费金额(元)#payOfSum",
				"卡的线上消费金额(元)#olPayOfSum",
				"卡的入账金额(元)#postSum",
				"卡的入账笔数(笔)#postCount",
				"卡的入账等级#postLevel",
				"卡的投资金额(元)#inVestSum"
		};
		//金融画像3—近1年内的借记卡信息
		String[] info3 = {
				"卡最高等级#maxLevel",
				"卡龄(年)#blueCardAge", 
				"信用卡数量(个)#blueCardCount",
				"卡分期最长期数#longestNumber", 
				"近3个月消费金额(元)#payOfMoney",
				"近3个月消费笔数(个)#payOfCount",
				"近3个月还款金额(元)#refundMoney",
				"近3个月应还金额(元)#currentBalance",
				"近1年消费金额(元)#payOfMoneyTwelve",
				"近1年消费笔数(笔)#payOfCountTwelve",
				"近1年还款金额(元)#refundMoneyTwelve",
				"近1年应还金额(元)#currentBalanceTwelve",
				"近1年线上消费金额(元)#paySumMoney"
		};
		//金融画像4—银行卡相关信息
		String[] info4 = {
				"开卡银行个数(个)#bankNumber",
				"借记卡数量(个)#debitCardNumber", 
				"最近借记卡余额(元)#debitCardBalance",
				"信用卡数量(个)#blueCardNumber", 
				"历史借贷总金额(元)#loanFundMoney",
				"历史借贷总笔数(笔)#loanFundCount",
				"历史结贷总金额(元)#jdSumMoney",
				"信用卡最高等级#blueCardMaxLevel",
				"当前未结清贷款总金额(元)#nLoanFunMoney",
				"当前未结清贷款总笔数(笔)#nLoanFundCount"
		};
			if(info != null && !info.equals("") && !info.isEmpty()){
				//JSONObject json = JSONObject.parseObject(jsonstr);
				Integer [] cateArr = null;
				if(hisbean != null && hisbean.getType().equals("215")){
					cateArr = EntReportUtils.cateToArrayCate(cate, 4);
					Integer rowIndex =0;
					if(cateArr[0]==1 ){
						HSSFSheet sheet = workbook.createSheet("金融画像1");
						System.out.println(info.getJSONObject("info1"));
						JSONObject info_1 = info.getJSONObject("info1");
						rowIndex = setCellValueMethod(info_1, info1, "近1年的借记卡信息", rowIndex, sheet);
					}
					
					if( cateArr[1]==1 ){
						rowIndex =0;
						HSSFSheet sheet1 = workbook.createSheet("金融画像2");
						System.out.println(info.getJSONObject("info2"));
						JSONObject info_2 = info.getJSONObject("info2");
						rowIndex = setCellValueMethod(info_2, info2, "近3个月的借记卡信息", rowIndex, sheet1);
					}
					if(cateArr[2]==1 ){
						rowIndex =0;
						HSSFSheet sheet2 = workbook.createSheet("金融画像3");
						JSONObject info_3 = info.getJSONObject("info3");
						System.out.println(info.getJSONObject("info3"));
						rowIndex = setCellValueMethod(info_3, info3, "近1年内的借记卡信息", rowIndex, sheet2);
					}
					if(cateArr[3]==1 ){
						rowIndex = 0;
						HSSFSheet sheet3 = workbook.createSheet("金融画像4");
						System.out.println(info.getJSONObject("info4"));
						JSONObject info_4 = info.getJSONObject("info4");
						rowIndex = setCellValueMethod( info_4, info4, "银行卡相关信息", rowIndex, sheet3);
					}
				}else{
					Integer rowIndex =0;
					if(info.get("info1")!=null && !info.get("info1").equals("") ){
						HSSFSheet sheet = workbook.createSheet("金融画像1");
						System.out.println(info.getJSONObject("info1"));
						JSONObject info_1 = info.getJSONObject("info1");
						rowIndex = setCellValueMethod(info_1, info1, "近1年的借记卡信息", rowIndex, sheet);
					}
					
					if(info.get("info2")!=null && !info.get("info2").equals("")){
						rowIndex =0;
						HSSFSheet sheet1 = workbook.createSheet("金融画像2");
						System.out.println(info.getJSONObject("info2"));
						JSONObject info_2 = info.getJSONObject("info2");
						rowIndex = setCellValueMethod(info_2, info2, "近3个月的借记卡信息", rowIndex, sheet1);
					}
					if(info.get("info3")!=null && !info.get("info3").equals("")){
						rowIndex =0;
						HSSFSheet sheet2 = workbook.createSheet("金融画像3");
						System.out.println(info.getJSONObject("info2"));
						JSONObject info_3 = info.getJSONObject("info3");
						rowIndex = setCellValueMethod(info_3, info3, "近1年内的借记卡信息", rowIndex, sheet2);
					}
					if(info.get("info4")!=null && !info.get("info4").equals("")){
						rowIndex = 0;
						HSSFSheet sheet3 = workbook.createSheet("金融画像4");
						System.out.println(info.getJSONObject("info4"));
						JSONObject info_4 = info.getJSONObject("info4");
						rowIndex = setCellValueMethod( info_4, info4, "银行卡相关信息", rowIndex, sheet3);
					}
				}
		}else{
		//	String[]  base_info ={"姓名","身份证号","手机号","核验结果"};
			HSSFSheet sheet = workbook.createSheet("金融交易画像");
			HSSFRow row = null;
			HSSFCell cell = null;
			Integer rowIndex = 0;
			sheet.setDefaultColumnWidth(25);
			CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
			sheet.addMergedRegion(cra);
			row = sheet.createRow(rowIndex++);
			row = sheet.createRow(0);
			cell = row.createCell(0);
			row.setHeight((short) 450);
			cell.setCellStyle(style);
			cell.setCellValue("基本信息");
			hisbean = bean.get(0);
			if(hisbean !=null){
//				String[] data =new String[4];
//				data[0] = hisbean.getName();
//				data[1] = hisbean.getCid();
//				data[2] = hisbean.getMobile();
//				data[3] =(String) JSONObject.parseObject(hisbean.getHistory_data()).get("resMsg");
				row = sheet.createRow(rowIndex++);
				cell = row.createCell(0);
				cell.setCellValue("姓名");
				cell = row.createCell(1);
				cell.setCellValue(hisbean.getName());
				cell = row.createCell(2);
				cell.setCellValue("身份证号");
				cell = row.createCell(3);
				cell.setCellValue(hisbean.getCid());
				row = sheet.createRow(rowIndex++);
				cell = row.createCell(0);
				cell.setCellValue("手机号");
				cell = row.createCell(1);
				cell.setCellValue(hisbean.getMobile());
				cell = row.createCell(2);
				cell.setCellValue("核验结果");
				cell = row.createCell(3);
				cell.setCellValue((String) JSONObject.parseObject(hisbean.getHistory_data()).get("resMsg"));
//				for(int i=0;i<4;i++){
//					HSSFCell cell1 = row.createCell(2*i);
//					cell1.setCellValue(base_info[i]);
//					HSSFCell cell2 = row.createCell(2*i+1);
//					cell2.setCellValue(data[i]);
//					if(i==2){
//						row = sheet.createRow(rowIndex++);
//					}
//				}
			}
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
/**
 * 设置横板表格
 */
	public Integer setCellValueMethod(JSONObject info, String[] arrayData, String title, Integer rowIndex,HSSFSheet sheet){

		HSSFRow row ;
		HSSFCell cell;
		CellRangeAddress  cra;
		//rowIndex = 6;
		sheet.setDefaultColumnWidth(25);
		cra = new CellRangeAddress(rowIndex, rowIndex, 0, 1);
		sheet.addMergedRegion(cra);
		row = sheet.createRow(rowIndex++); 
		row.setHeight((short) 450);
		cell = row.createCell(0); 
		cell.setCellStyle(style);
		cell.setCellValue(title);

		if(info!=null){
					for(int i = 0;i<arrayData.length;i++){
						row = sheet.createRow(rowIndex++);
						row.setHeight((short) 350);
						String [] cellData = arrayData[i].split("#"); 
						int cellIndex = cellData.length;
						for(int m = 0 ; m < cellIndex ; m++){
							if(cellIndex == 2){
								cell = row.createCell(m);
								if(m%2==0){
									cell.setCellValue(cellData[m]);
									cell.setCellStyle(style2);
								}else{
									String fieldName =  cellData[m];
									cell.setCellValue((info.get(fieldName) == null || info.get(fieldName).equals("")) ? "--" : info.get(fieldName) + "");
									cell.setCellStyle(style2);
								}
							}
						}
					}
		
		}else{
			cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
			sheet.addMergedRegion(cra);
			row = sheet.createRow(rowIndex); 
			row.setHeight((short) 350);
			cell = row.createCell(0);
			cell.setCellStyle(style3);
			cell.setCellValue("暂未查到该项信息");
		}
		return rowIndex;
	}


	
}
