package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.ccx.credit.util.HistoryTypeMap;
import com.ccx.credit.util.ResCodeMap;
import com.ccx.credit.util.StringUtil;

@SuppressWarnings("deprecation")
public class ExportExcel<T> {

	/*public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd",);
	}

	
	public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
	}*/
	
	public void exportExcel(String[] headers,String[] cellNames, Collection<T> dataset, OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", headers,cellNames, dataset, out, "yyyy-MM-dd");
	}
	
	public void exportIcInfoExcel(Collection<T> dataset, String jsonStr, OutputStream out) {
		exportIcInfoExcel(dataset, jsonStr, out, "yyyy-MM-dd");
	}
	
	
	
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

	@SuppressWarnings({ "unchecked", "resource", "rawtypes" })
	public byte[] exportExcel(String title, String[] headers,String[] cellNames, Collection<T> dataset, OutputStream out, String pattern) {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格

		HSSFSheet sheet = workbook.createSheet(title);

		// 设置表格默认列宽度为25个字节

		sheet.setDefaultColumnWidth((short) 30);
		
		// 生成一个样式

		HSSFCellStyle style = workbook.createCellStyle();

		// 设置这些样式

		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// 把字体应用到当前的样式
		style.setFont(font);

		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		HSSFFont font3 = workbook.createFont();
		font3.setColor(HSSFColor.BLUE.index);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 定义注释的大小和位置,详见文档
//		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));

		// 设置注释内容
//		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//		comment.setAuthor("leno");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);

		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行

		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			row.setHeight((short) 390);//
			
			T t = (T) it.next();
			
//			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
//			Field[] fields = t.getClass().getDeclaredFields();
//			for (short i = 0; i < fields.length; i++) {
//				Field field = fields[i];
//				String fieldName = field.getName();
//				if ( ! cellNames.contains(fieldName) ) {
//					continue;
//				}
			
			for (int i = 0; i < cellNames.length; i++) {
				String fieldName = cellNames[i];
				
				
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 查询服务项
					if("type".equals(fieldName)){
						if (StringUtil.isNotEmpty(value)) {
							value=HistoryTypeMap.historyTypeToMsg(Integer.parseInt(value.toString()));
						}
					}
					// 查询结果
					if("resCode".equals(fieldName)){
						if (StringUtil.isNotEmpty(value)) {
							value=ResCodeMap.resCodeToMsg(value.toString());
						}
					}
					if("cid".equals(fieldName)){
						if (StringUtil.isNotEmpty(value)) {
							if(value.toString().length()>18){
								//加密身份证号不展示
								value="";
							}
						}
					}
					if("m_type".equals(fieldName)){
						if (StringUtil.isNotEmpty(value)) {
							if(value.equals("1")){
								value= "处理成功";
							}else{
								value = "正在处理...";
							}
						}
					}
					// 判断值的类型后进行强制类型转换
					String textValue = null;

					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
						1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(
						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						if( value != null ){
							textValue = value.toString();
						} else {
							textValue = null;
						}
						
					}

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
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
	public byte[] exportIcInfoExcel(Collection<T> dataset,String jsonStr, OutputStream out, String pattern) {
		
		// 声明一个工作薄
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 生成一个样式
		
		style = workbook.createCellStyle();
		
		// 设置这些样式
		
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
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

		JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
		String resCode = (String)jsonArr.get("resCode");
		if(resCode.equals("0000")){
			
			String type = jsonArr.get("type")+"";
			JSONArray entities = (JSONArray)jsonArr.get("entities");
			
			Map<String, List<JSONObject>> jsonMap = jsonToMap(jsonStr);
			
			if(type.equals("1")||type.equals("10")){
				
				//股东信息
				String[] gd_t = { 
						"查询人姓名#pyname#企业(机构)名称#entname", 
						"注册号#regno#注册资本（万元）#regcap",
						"注册资本币种#regcapcur#企业状态#entstatus",
						"认缴出资（万元）#subconam#认缴出资币种#currency"
				};
				
				//高管信息
				String[] gg_t = { 
						"查询人姓名#pyname#企业(机构)名称#entname", 
						"注册号#regno#企业(机构)类型#enttype",
						"注册资本（万元）#regcap#注册资本币种#regcapcur",
						"企业状态#entstatus#职务#position"
				};
				
				//法人信息
				String[] fr_t = { 
						"查询人姓名#pyname#企业(机构)名称#entname", 
						"注册号#regno#企业机构类型#enttype",
						"注册资本（万元）#regcap#注册资本币种#regcapcur",
						"企业状态#entstatus"
				};
				
				//失信被执行信息
				String[] sxbzx_t = { 
						"案号#casecode#被执行人姓名/名称#inameclean",
						"失信人类型#type#性别#sexyclean",
						"身份证号码/工商注册号#cardnum#身份证原始发证地#ysfzd",
						"法定代表人/负责人姓名#businessentity#立案时间#regdateclean",
						"公布时间#publishdateclean#执行法院#courtname",
						"省份#areanameclean#执行依据文号#gistid",
						"做出执行依据单位#gistunit",
						"生效法律文书确定的义务#duty",
						"失信被执行人行为具体情形#disruptypename",
						"被执行人的履行情况#performance",
						"已履行#performedpart#未履行#unperformpart"
				};
				
				//被执行人信息
				String[] bzx_t = { 
						"被执行人姓名/名称#inameclean#立案时间#regdateclean",
						"执行法院#courtname#执行标的#execmoney"
				};
				
				//行政处罚历史信息
				String[] xzcfls_t = { 
						"执行类别#exesort#处罚决定书签发日期#pendecissdate",
						"作出行政处罚决定机关名称#penauth#处罚依据#penbasis",
						"处罚种类#pentype#处罚金额#penam",
						"处罚结果#penresult",
						"处罚执行情况#penexest",
						"处罚决定文书#pendecno"
				};

							
				Integer rowIndex = 0;
				
				setCellValueMethod(jsonMap.get("gd"),  gd_t, "股东信息", rowIndex, workbook);
				
				setCellValueMethod(jsonMap.get("gg"),  gg_t, "高管信息", rowIndex, workbook);
				
				setCellValueMethod(jsonMap.get("fr"),  fr_t, "法定代表人信息", rowIndex, workbook);
	    		
				setCellValueMethod(jsonMap.get("sxbzx"),  sxbzx_t, "失信被执行信息", rowIndex, workbook);
	    		
				setCellValueMethod(jsonMap.get("bzx"),  bzx_t, "被执行信息", rowIndex, workbook);
	    		
				setCellValueMethod(jsonMap.get("xzcfls"),  xzcfls_t, "行政处罚历史信息", rowIndex, workbook);
				
				
					
			}else if(type.equals("2")){
				//注册信息
				String[] org_t = { 
						"企业名称#entname#注册号#regno", 
						"原注册号#oriregno#法定代表人姓名#frname", 
						"注册资本(万元)#regcap#实收资本(万元)#reccap",
						"币种#regcapcur#经营状态#entstatus",
						"企业(机构)类型#enttype#开业日期#esdate",
						"经营期限自#opfrom#经营期限至#opto",
						"登记机关#regorg#最后年检年度#acyear",
						"注销日期#candate#吊销日期#revdate",
						"行业门类代码#industryphycode#行业门类名称#industryphyname",
						"国民经济行业代码#industrycocode#国民经济行业名称#industryconame",
						"最后年检日期#anchedate#变更日期#changedate",
						"注册部门编码#regorgcode#注册省份#regorgprovince",
						"住址#dom",
						"许可经营项目#abuitem",
						"一般经营项目#cbuitem",
						"经营(业务)范围#opscope",
						"经营(业务)范围及方式#opscoandform" 
				};
				
				//股东信息
				String[] gd_t = {
						"股东名称#shaname",
						"国别#country",
						"认缴出资额(万元)#subconam",
						"认缴出资币种#regcapcur",
						"出资方式#conform",
						"出资比例#fundedratio",
						"出资日期#condate",
						"股东总数量#invamount",
						"股东出资比例总和#invsumfundedratio"
				};
	
				//高管信息
				String[] gg_t = {
						"姓名#pername",
						"职位#position",
						"性别#sex"
				};
				
				
				//法定代表人对外投资信息
				String[] frdwtz_t = {
						"法定代表人姓名#name#企业(机构)名称#entname",
						"注册号#regno#企业(机构)类型#enttype",
						"注册资本(万元)#regcap#注册资本币种#regcapcur",
						"企业状态#entstatus#注销日期#regorg",
						"吊销日期#revdate#登记机关#regorg",
						"认缴出资额(万元)#subconam#认缴出资币种#congrocur",
						"出资方式#conform#出资比例#fundedratio",
						"开业日期#esdate"
				};
				
				//法定代表人其他任职信息
				String[] frqtrz_t = {
						"法定代表人姓名#name#企业(机构)名称#entname",
						"注册号#regno#企业(机构)类型#enttype",
						"注册资本(万元)#regcap#注册资本币种#regcapcur",
						"企业状态#entstatus#注销日期#regorg",
						"吊销日期#revdate#登记机关#regorg",
						"职务#position#是否法定代表人#lerepsign",
						"开业日期#esdate"
				};
				
				//企业对外投资信息
				String[] tz_t = {
						"企业(机构)名称#entname#注册号#regno",
						"企业(机构)类型#enttype#企业状态#entstatus",
						"注册资本(万元)#regcap#注册资本币种#regcapcur",
						"注销日期#candate#吊销日期#revdate",
						"认缴出资额(万元)#subconam#认缴出资币种#congrocur",
						"出资方式#conform#出资比例#fundedratio",
						"登记机关#regorg#开业日期#esdate",
						"法定代表人姓名#name"
				};
				
				//变更信息
				String[] bgxx_t = {
						"变更项目#altitem",
						"变更前内容#altbe",
						"变更后内容#altaf",
						"变更日期#altdate"
				};
				
				//分支结构信息
				String[] fzjg_t = {
						"分支机构名称#altitem",
						"分支机构企业注册号#altbe",
						"分支机构负责人#altaf",
						"一般经营项目#altdate",
						"分支机构地址#altdate"
				};
				
				/*//股权出质历史信息
				String[] gqcz_t = {
						"质权人姓名#imporg#出质人类别#imporgtype",
						"出质金额#impam#出质备案日期#imponrecdate",
						"出质批准日期#impsandate#出质截止日期#impto",
						"出质审批部门#impexaeep"
				};
				
				//动产抵押信息
				String[] dcdy_t = {
						"抵押ID#morregid#抵押人#mortgagor",
						"抵押权人#more#登记机关#regorg",
						"登记日期#regidate#状态标识#mortype",
						"被担保主债权种类#priclaseckind#被担保主债权数额(万元)#priclasecam",
						"履约起始日期#pefperform#履约截止日期#pefperto",
						"注销日期#candate#登记证号#morregcno",
						"申请抵押原因#appregrea"
				};
				
				//动产抵押物信息
				String[] dcdyw_t = {
						"抵押ID#morregid#抵押物名称#guaname",
						"数量#quan#价值(万元)#value"
				};
				
				//失信被执行人信息
				String[] sxbzxr_t = {
						"案号#casecode#被执行人姓名/名称#inameclean",
						"失信人类型#type#性别#sexyclean",
						"身份照号码/工商注册号#cardnum#身份证原始发征地#ysfzd",
						"法定代表人/负责人姓名#businessentity#立案时间#regdateclean",
						"公布时间#publishdateclean#执行法院#courtname",
						"省份#areanameclean#执行依据文号#gistid",
						"做出执行依据单位#gistunit#被执行人的履行情况#performance",
						"已履行#performedpart#未履行#unperformpart",
						"生效法律文书确定的义务#duty",
						"失信被执行人行为具体情形#disrupttypename"
				};
				
				//被执行人信息
				String[] bzx_t = {
						"被执行人姓名/名称#inameclean",
						"立案时间#regdateclean",
						"执行法院#courtname",
						"执行标的#execmoney"
				};
				
				//股权冻结历史信息
				String[] gqdjls_t = {
						"冻结文号#frodocon#冻结机关#froauth",
						"冻结起始日期#frofrom#冻结截止日期#froto",
						"冻结金额#froam#解冻机关#thawauth",
						"解冻文号#thawdocno#解冻日期#thawdate",
						"解冻说明#thawcomment"
				};
				
				//清算信息
				String[] qs_t = {
						"清算责任人#ligentity#清算负责人#ligprincipal",
						"起算组成员#liqmen#清算完结日期#ligenddate",
						"债务承接人#debttranee#债权承接人#claimtranee",
						"清算完结情况#ligst"
				};
				
				//行政处罚历史信息
				String[] xzcfls_t = {
						"案发时间#casetime#违法行为类型#casetype",
						"处罚决定书签发日期#pendecissdate#作出行政处罚决定机关名称#penauth",
						"处罚种类#pentype#处罚金额#penam",
						"案由#casereason",
						"案值#caseval",
						"执行类别#exesort",
						"案件结果#caseresult",
						"处罚决定文书#pendecno",
						"主要违法事实#illegfact",
						"处罚依据#penbasis",
						"处罚结果#penresult",
						"处罚执行情况#penexest"
				};
				
				//经营异常
				String[] jyyc_t = {
						"列入经营异常名录原因#reasonin",
						"列入时间#datein",
						"移除经营异常名录原因#reasonout",
						"移除时间#dateout",
						"做出决定机关#desorgan"
				};
				
				//专利
				String[] zl_t = {
						"申请号/专利号#applyID",
						"专利类型#patentType",
						"发明名称#applyName"
				};*/
				
				Integer rowIndex = 0;

				setCellValueMethod(jsonMap.get("zc"), org_t, "注册信息", rowIndex, workbook);
				
				setRowCellValueMethod(jsonMap.get("gd"), gd_t, "股东信息", rowIndex, workbook);
				
				setRowCellValueMethod(jsonMap.get("gg"), gg_t, "高管信息", rowIndex, workbook);
				
				setCellValueMethod(jsonMap.get("frdwtz"),  frdwtz_t, "法定代表人对外投资", rowIndex, workbook);
				
	    		setCellValueMethod(jsonMap.get("frqtrz"),  frqtrz_t, "法定代表人其他任职", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("tz"),  tz_t, "投资信息", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("bgjl"),  bgxx_t, "变更信息", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("fzjg"),  fzjg_t, "分支机构信息", rowIndex, workbook);
	    		
	    	/*	setCellValueMethod(jsonMap.get("gqcz"),  gqcz_t, "股权出质历史信息", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("dcdy"),  dcdy_t, "动产抵押信息", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("dcdyw"),  dcdyw_t, "动产抵押物信息", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("sxbzx"),  sxbzxr_t, "失信被执行信息", rowIndex, workbook);
	    		
	    		setRowCellValueMethod(jsonMap.get("bzx"),  bzx_t, "被执行信息", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("gqdjls"),  gqdjls_t, "股权冻结历史信息", rowIndex, workbook);
	    		
	    		setCellValueMethod(jsonMap.get("qs"),  qs_t, "清算信息", rowIndex, workbook);
	    		
		    	setCellValueMethod(jsonMap.get("xzcfls"),  xzcfls_t, "行政处罚历史信息", rowIndex, workbook);
		    	
		    	setRowCellValueMethod(jsonMap.get("jyyc"), jyyc_t, "经营异常", rowIndex, workbook);
		    	
		    	setRowCellValueMethod(jsonMap.get("zl"), zl_t, "专利", rowIndex, workbook);*/
				
			}
		}else{
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("万象信用-工商信息-详细信息");
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
	
	 public Map<String, List<JSONObject>> jsonToMap(String jsonStr){
	    	JSONObject  jsonArr = JSONObject .fromObject(jsonStr);//通过jsonstr字符串构造JSONArray对象
	    	Map<String, List<JSONObject>> jsonMap = new HashMap<String, List<JSONObject>>();
			
			String resCode = (String)jsonArr.get("resCode");
			if(resCode.equals("0000")){
				
				String type = jsonArr.get("type")+"";
				JSONArray entities = (JSONArray)jsonArr.get("entities");			
				List<JSONObject> list = new ArrayList<JSONObject>();
				
				if(type.equals("1")||type.equals("10")){
					for(int i =0 ; i<entities.size(); i++) {
	    				JSONObject obj = (JSONObject)entities.get(i);
						String relation = (String) obj.get("relation");
						JSONObject data =  (JSONObject)obj.get("data");
						if(relation.equals("信息主体")){
							jsonMap = setJsonMap(jsonMap, list, obj, "zc");
						} else if(relation.equals("股东")){
							jsonMap = setJsonMap(jsonMap, list, data, "gd");
						} else if(relation.equals("高管")){
							jsonMap = setJsonMap(jsonMap, list, data, "gg");
						} else if ( relation.equals("法人") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "fr");
				    	} else if ( relation.equals("失信被执行") ) {
				    		jsonMap = setJsonMap(jsonMap, list, data, "sxbzx");
				    	} else if ( relation.equals("被执行") ) {
				    		jsonMap = setJsonMap(jsonMap, list, data, "bzx");
				    	} else if (relation.equals("行政处罚历史") ) {
				    		jsonMap = setJsonMap(jsonMap, list, data, "xzcfls");
				    	}
					}
				}else if(type.equals("2")){
					for(int i =0 ; i<entities.size(); i++) {
						JSONObject obj = (JSONObject)entities.get(i);
						String relation = (String) obj.get("relation");
						JSONObject data =  (JSONObject)obj.get("data");
						if(relation.equals("信息主体")){
							jsonMap = setJsonMap(jsonMap, list, data, "zc");
						} else if(relation.equals("股东")){
							jsonMap = setJsonMap(jsonMap, list, data, "gd");
						} else if ( relation.equals("高管") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "gg");
						} else if ( relation.equals("法人对外投资") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "frdwtz");
						} else if ( relation.equals("法人其他任职") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "frqtrz");
						} else if ( relation.equals("投资信息") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "tz");
						} else if (relation.equals("变更记录") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "bgjl");
						} else if ( relation.equals("分支机构") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "fzjg");
						} else if ( relation.equals("股权出质") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "gqcz");
						} else if ( relation.equals("动产抵押") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "dcdy");
						} else if ( relation.equals("动产抵押物") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "dcdyw");
						} else if ( relation.equals("失信被执行") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "sxbzx");
						} else if ( relation.equals("被执行") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "bzx");
						} else if ( relation.equals("股权冻结历史") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "gqdjls");
						} else if ( relation.equals("清算信息") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "qs");
						} else if ( relation.equals("行政处罚历史") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "xzcfls");
						} else if ( relation.equals("经营异常") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "jyyc");
						} else if ( relation.equals("专利") ) {
							jsonMap = setJsonMap(jsonMap, list, data, "zl");
						}
					}
				}
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
	public Integer setCellValueMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex,HSSFWorkbook workbook){
		
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
	/**
	 * 横版表格 setValue
	 * @param list
	 * @param arrayData
	 * @param title
	 * @param rowIndex
	 * @param sheet
	 * @return
	 */	
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

	public static void main(String[] args) {

//		// 测试学生
//
//		ExportExcel<Student> ex = new ExportExcel<Student>();
//
////		String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
////		String[] cellNames = {"id","name","age","sex","birthday"};
//
//		List<Student> dataset = new ArrayList<Student>();
////
////		dataset.add(new Student(10000001, "张三", 20, true, new Date(),"test1"));
////		dataset.add(new Student(20000002, "李四", 24, false, new Date(),"test2"));
////		dataset.add(new Student(30000003, "王五", 22, true, new Date(),"test3"));
//
//		// 测试图书
////		ExportExcel<Book> ex2 = new ExportExcel<Book>();
////		String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN", "图书出版社", "封面图片" };
////		List<Book> dataset2 = new ArrayList<Book>();
//		try {
////
////			BufferedInputStream bis = new BufferedInputStream(
////			new FileInputStream("E://book.jpg"));
////			byte[] buf = new byte[bis.available()];
////			while ((bis.read(buf)) != -1) {
////
////			}
////			dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567", "清华出版社", buf));
////			dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567", "阳光出版社", buf));
////			dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567", "清华出版社", buf));
////			dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567", "清华出版社", buf));
////			dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567", "汤春秀出版社", buf));
//
//			OutputStream out = new FileOutputStream("E://工商信息详细信息.xls");
////			OutputStream out2 = new FileOutputStream("E://图书.xls");
////			ex.exportExcel(headers, cellNames, dataset, out);
////			ex2.exportExcel(headers2, dataset2, out2);
//			ex.exportIcInfoExcel(dataset, "" , out);
//			out.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}