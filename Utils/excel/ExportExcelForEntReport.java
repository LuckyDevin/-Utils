package com.ccx.credit.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class ExportExcelForEntReport<T> {

	public void exportIcInfoExcel(Collection<T> dataset, Map<String,List<JSONObject>> resultMap, OutputStream out) {
		exportIcInfoExcel(dataset, resultMap, out, "yyyy-MM-dd");
	}

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] exportExcel(String title, String[] headers, String[] cellNames, Collection<T> dataset, OutputStream out, String pattern) {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格

		HSSFSheet sheet = workbook.createSheet(title);

		// 设置表格默认列宽度为25个字节

		sheet.setDefaultColumnWidth((short) 25);

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

		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 定义注释的大小和位置,详见文档
		// HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
		// 0, 0, 0, (short) 4, 2, (short) 6, 5));

		// 设置注释内容
		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		// comment.setAuthor("leno");

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

			// // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			// Field[] fields = t.getClass().getDeclaredFields();
			// for (short i = 0; i < fields.length; i++) {
			// Field field = fields[i];
			// String fieldName = field.getName();
			// if ( ! cellNames.contains(fieldName) ) {
			// continue;
			// }

			for (int i = 0; i < cellNames.length; i++) {
				String fieldName = cellNames[i];

				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);

				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});

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
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						if (value != null) {
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
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
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
			// workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteOut.toByteArray();
	}

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


	/**
	 * @param title
	 * @param headers
	 * @param cellNames
	 * @param dataset
	 * @param out
	 * @param pattern
	 * @return
	 */
	public byte[] exportIcInfoExcel(Collection<T> dataset, Map<String,List<JSONObject>> resultMap, OutputStream out, String pattern) {

		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个样式

		style = workbook.createCellStyle();

		// 
		//大标题 设置样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		
		

		//居左显示--黄色
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
         
	    
	    //居左显示--蓝色
		style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style3.setWrapText(true);     //自动换行
         
		 
		//文本居中 黄色
	    style4 = workbook.createCellStyle();
		style4.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style4.setWrapText(true);
		
		 //文本居中 蓝色
		style5 = workbook.createCellStyle();
		style5.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style5.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style5.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style5.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style5.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style5.setWrapText(true);
		
		
		
		 //文本居左 黄色
		style6 = workbook.createCellStyle();
		style6.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style6.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style6.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style6.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style6.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style6.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style6.setWrapText(true);
		
		 //文本居左 蓝色
		style7 = workbook.createCellStyle();
		style7.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style7.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style7.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style7.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style7.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style7.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style7.setWrapText(true);
		
		
		
		//文本居右 YELLOW
		style8 = workbook.createCellStyle();
		style8.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style8.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style8.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style8.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style8.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style8.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style8.setWrapText(true);
		
		//文本居右 TURQUOISE
		style9 = workbook.createCellStyle();
		style9.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style9.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style9.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style9.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style9.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style9.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		style9.setWrapText(true);
	
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式
		style2.setFont(font2);

		
		/**
		 * 工商信息
		 **/

		// 注册信息-普通
		String[] org_t = { "企业名称#entname", "法定代表人#frname", "注册号#regno","统一社会信用代码#", "原注册号#oriregno", "注册资本(万元)#regcap", "实收资本(万元)#reccap", "币种#regcapcur",  "开业日期#esdate","经营期限自#opfrom","经营期限至#opto","经营状态#entstatus", "企业（机构）类型#enttype",
				 "登记机关#regorg", "最后年检年度#acyear", "注销日期#candate", "吊销日期#revdate", "行业门类代码#industryphycode", "行业门类名称#industryphyname",
				"国民经济行业代码#industrycocode", "国民经济行业名称#industryconame", "最后年检日期#anchedate", "变更日期#changedate", "注册部门编码#regorgcode", "注册省份#regorgprovince", "住址#dom", "许可经营项目#abuitem", "一般经营项目#cbuitem",
				"经营(业务)范围#opscope", "经营(业务)范围及方式#opscoandform" };
		// 注册信息-个体		
		String[] org_t_gt = { "企业名称#entname", "经营者#frname", "注册号#regno","统一社会信用代码#", "原注册号#oriregno", "注册资本(万元)#regcap", "实收资本(万元)#reccap", "币种#regcapcur",  "开业日期#esdate","经营期限自#opfrom","经营期限至#opto","经营状态#entstatus", "个体工商户类型#enttype",
				"登记机关#regorg", "最后年检年度#acyear", "注销日期#candate", "吊销日期#revdate", "行业门类代码#industryphycode", "行业门类名称#industryphyname",
				"国民经济行业代码#industrycocode", "国民经济行业名称#industryconame", "最后年检日期#anchedate", "变更日期#changedate", "注册部门编码#regorgcode", "注册省份#regorgprovince", "住址#dom", "许可经营项目#abuitem", "一般经营项目#cbuitem",
				"经营(业务)范围#opscope", "经营(业务)范围及方式#opscoandform" };

		// 股东信息
		String[] gd_t = { "股东名称#shaname", "国别#country", "认缴出资额(万元)#subconam", "认缴出资币种#regcapcur", "出资方式#conform", "出资比例#fundedratio", "出资日期#condate", 
				"股东出资比例总和#invsumfundedratio" };

		// 高管信息
		String[] gg_t = { "姓名#pername", "职位#position", "性别#sex" };

		// 对外投资
		String[] tz_t = { "企业(机构)名称#entname", "法定代表人#name", "注册号#regno", "企业(机构)类型#enttype", "企业状态#entstatus", "注册资本(万元)#regcap", "注册资本币种#regcapcur", "注销日期#candate", "吊销日期#revdate", "认缴出资额(万元)#subconam", "认缴出资币种#congrocur",
				"出资方式#conform", "出资比例#fundedratio", "登记机关#regorg"};
		// 变更信息
		String[] bgxx_t = { "变更项目#altitem", "变更前内容#altbe", "变更后内容#altaf", "变更日期#altdate" };

		// 分支结构信息
		String[] fzjg_t = { "分支机构名称#brname", "分支机构企业注册号#brregno", "分支机构负责人#brprincipal", "一般经营项目#cbuitem", "分支机构地址#braddr" };

		/**
		 * 风险信息
		 */

		// 行政处罚历史信息
		String[] xzcfls_t = { "案发时间#casetime", "违法行为类型#casetype", "处罚决定书签发日期#pendecissdate", "作出行政处罚决定机关名称#penauth", "处罚种类#pentype", "处罚金额#penam", "案由#casereason", "案值#caseval", "执行类别#exesort",
				"案件结果#caseresult", "处罚决定文书#pendecno", "主要违法事实#illegfact", "处罚依据#penbasis", "处罚结果#penresult", "处罚执行情况#penexest" };

		// 失信被执行人信息
		String[] sxbzxr_t = { "案号#casecode", "被执行人姓名/名称#inameclean", "失信人类型#type", "性别#sexyclean", "身份照号码/工商注册号#cardnum", "身份证原始发征地#ysfzd", "法定代表人/负责人姓名#businessentity", "立案时间#regdateclean",
				"公布时间#publishdateclean", "执行法院#courtname", "省份#areanameclean", "执行依据文号#gistid", "做出执行依据单位#gistunit", "被执行人的履行情况#performance", "已履行#performedpart", "未履行#unperformpart", "生效法律文书确定的义务#duty",
				"失信被执行人行为具体情形#disrupttypename" };

		// 被执行人信息
		String[] bzx_t = { "被执行人姓名/名称#name", "执行案号#casenum","立案时间#time", "执行法院#court", "执行标的（元）#money" ,"案件状态#statute"};

		// 经营异常
		String[] jyyc_t = { "列入经营异常名录原因#reasonin", "列入时间#datein", "移除经营异常名录原因#reasonout", "移除时间#dateout", "做出决定机关#desorgan" };
		
		//法院涉诉信息
		//判决文书
		String[] fx101_t = { "法院判决详情      #datatime#发布", "pj#title#（#trialprocedure#）", "pj#court", "pj#casetype","pj#casenum", "pj#content","审判长#",
				"代理审判员#", "代理审判员#",  "pj#datatime", "书记员#"  };
		
		/*//失信被执行
		String[] fx102_t = { "被执行人名称/姓名#name", "身份证号/组织机构代码#cid", "法定代表人/负责人#leader", "住所地#address", "执行法院#court", "立案时间#time","执行案号#casenum", "执行标的#money",
				"执行依据文号#base", "做出执行依据单位#basecompany", "生效法律文书确定的义务#obligation", "生效法律文书确定的最后履行义务截止时间#lasttime", "被执行人的履行情况#performance", "失信被执行人行为具体情形#concretesituation", 
				"认定失信时间#breaktime", "发布时间#posttime", "异议内容#objection", "异议时间#objectiontime"  };*/
		//最高法执行
//		String[] fx103_t = { "被执行人名称/姓名#name", "身份证号/组织机构代码#cid", "执行法院#court", "立案时间#time","执行案号#casenum", "执行标的（元）#money","案件状态#statute",
//				"执行依据#basic", "做出执行依据的机构#basiccourt",  "异议内容#objection", "异议时间#objectiontime"  };
		
		//限制高消费被执行人
		String[] fx104_t = { "被限制人#name", "身份证号/组织机构代码#cid", "法定代表人/负责人#leader", "住所地#address","执行法院号#court", "案号#casenum","案由#anyou",
				"标的#money", "立案时间#time",  "发布时间#posttime", "具体内容#content", "执行依据#basic", "异议内容#objection", "异议时间#objectiontime"  };
		
		//限制出境被执行人
		String[] fx105_t = { "被限制人#name", "身份证号/组织机构代码#cid", "被限制人地址#address", "边控措施#control","边控日期#controltime", "承办法院#court","案号#casenum",
				"执行依据编号#basic", "立案时间#time",  "执行标的#money", "具体内容#content", "异议内容#objection", "异议时间#objectiontime"  };
		
		//老赖信息
		String[] fx106_t = { "姓名/公司名称#name", "身份证号/组织机构代码#cid", "法定代表人#leader", "头像/照片#imgurl","住址#address", "执行标的/未履行标的#money","执行法院#court",
				"执行案号#casenum", "立案时间#time",  "公布时间#posttime", "执行依据文号#basic", "失信情形#situation", "生效文书确定的义务#obligation", "异议内容#objection", "异议时间#objectiontime"  };
		
		//立案信息
		String[] fx107_t = { "当事人身份类型/当事人名称#plaintiff", "第三人#tdirdpeople", "原审原告#plaintiff2","原审被告#defendant2", "原审第三人#tdirdpeople2","受理法院#court",
				"立案时间#time", "案由#anyou",  "案号#casenum", "异议内容#objection", "异议时间#objectiontime"  };
		
		//开庭信息
		String[] fx108_t = { "身份证号/组织机构代码#cid", "当事人身份类型/当事人名称#plaintiff", "原审被告#defendant2", "原审第三人#tdirdpeople","审理法院#court",
				"开庭时间#time", "案由#anyou",  "案号#casenum", "异议内容#objection", "异议时间#objectiontime"  };
		
		//送达公告
		String[] fx109_t = { "当事人名称#name", "身份证号/组织机构代码#cid", "原告（上诉人）#plaintiff", "被告（被上诉人）#defendant","原审被告#defendant2", "原审第三人#tdirdpeople","审理法院#court",
				"开庭时间#time", "案由#anyou",  "案号#casenum", "异议内容#objection", "异议时间#objectiontime"  };
		
		//其他
		String[] fx110_t = { "当事人名称#name", "身份证号/组织机构代码#cid", "法定代表人/负责人#leader", "住所地#address", "执行申请人#applyname", "异议申请人#applyname2","执行法院#court", "执行案号#casenum",
				"执行依据文号#basic", "立案时间#time", "执行标的#money", "未履行标的（万元）#money2", "执行状态#statute", "执行依据制作单位#unit", "公开日期#opentime", "生效文书确定的义务#obligation", "异议内容#objection", "异议时间#objectiontime"  };
		
		
		//税务不良信息
		//欠税公告
		String[] fx201_t = { "纳税人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "纳税人识别号#taxnum", "经营地址#address", "所欠税种#taxtype","期初陈欠#money", "当期发生欠税余额（元）#money2",
				"欠税余额#money3", "应征发生日期#time", "认定日期#time2", "主管税务机关#unit", "分管领导#leadership", "主管税务人员#taxpeople", "所属市县区#region", "认定字号#casenum", "公告期次#period", "异议内容#objection","异议时间#objectiontime" };
		
		//失踪纳税人
		String[] fx202_t = { "失踪纳税人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "纳税人识别号#taxnum", "经营地址#address", "主管税务机关#unit","认定失踪日期#misstime", "失踪通知书编号#num",
				"偷逃欠税税额#money", "公告时间#time", "异议内容#objection","异议时间#objectiontime" };
		
		//注销信息
		String[] fx203_t = { "当事人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "纳税人识别号#taxnum", "纳税户类型#peopletype", "经营地址#address","主管税务机关#unit","注销日期#cancletime", "注销类型#cancletype",
				"注销原因#canclereason", "公告时间#time", "异议内容#objection","异议时间#objectiontime" };
		
		//失信纳税人
		String[] fx204_t = { "纳税人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "纳税人识别号#taxnum","主管税务机关#unit","是否评定为D级#isd", "评定时间#time", "异议内容#objection","异议时间#objectiontime" };
		
		//税务违法信息
		String[] fx205_t = { "纳税人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "纳税人识别号#taxnum", "主管税务机关#unit", "检查/稽查年度#year","稽查文书编号#num","违法违章事实#fact", "违法违章手段#means",
				"处理处罚决定日期#punishtime", "处理处罚限定履行日期#decisiontime", "罚款金额#money","处罚处理实际履行时间#performtime","实缴税款/入库金额(税、罚、滞合计)#money2","未缴税款/未入库金额(税、罚、滞合计)#money3",
				"限改状态#statute","纳税人当前状态#statute2","公告时间#time","异议内容#objection","异议时间#objectiontime" };
		
		//税务逾期信息
		String[] fx206_t = { "纳税人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "经营地址#address", "纳税人识别号#taxnum", "海关代码#code","主管税务机关#unit","申报期限#timelimit", "未申报项目#project",
				"未申报税种#taxtype", "欠缴税额#money","处罚金额#money2","处罚时间#time","异议内容#objection","异议时间#objectiontime" };
		
		
		//行政处罚信息
		//税务行政处罚决定书
		String[] fx301_t = { "纳税人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "生产经营地址#address", "纳税人识别号#taxnum", "主管税务机关#unit","违法事实#situation", "处罚类别#punishmenttype",
				"处罚结果#punishmentresult", "处罚时间#time","公告时间#opentime","异议内容#objection","异议时间#objectiontime" };
		
		//税务行政处罚信息
		String[] fx302_t = { "当事人名称#name", "身份证号/组织机构代码#cid", "法定代表人（业主）#leader", "生产经营地址#address", "纳税人识别号#taxnum", "主管税务机关#unit","责令限改通知书号#num", "责令限改状态#statute",
				"公告时间#opentime","异议内容#objection","异议时间#objectiontime" };
		
		
		/**
		 * 法定代表人信息
		 */
		// 法定代表人对外投资
		String[] frdwtz_t = { "企业(机构)名称#entname", "注册号#regno", "企业(机构)类型#enttype", "注册资本(万元)#regcap", "注册资本币种#regcapcur", "企业状态#entstatus", "注销日期#candate", "吊销日期#revdate", "登记机关#regorg",
				"认缴出资额(万元)#subconam", "认缴出资币种#currency", "出资方式#conform", "出资比例#fundedratio", "开业日期#esdate"  };
		// 法定代表人其他任职
		String[] frqtrz_t = { "企业(机构)名称#entname", "注册号#regno", "企业(机构)类型#enttype", "注册资本(万元)#regcap", "注册资本币种#regcapcur", "企业状态#entstatus", "注销日期#candate", "吊销日期#revdate", "登记机关#regorg",
				"职务#position", "是否法定代表人#lerepsign", "开业日期#esdate" };

		/**
		 * 知识产权
		 */

		// 商标信息
		String[] sb_t = {  "申请号/注册号#applyNum", "类号#classId", "商标名称#brandName", "申请人名称#applicant" };

		// 专利
		String[] zl_t = { "申请号/专利号#applyID", "专利类型#patentType", "发明名称#applyName" };

		/**
		 * 资产信息
		 */

		// 股权出质历史信息
		String[] gqcz_t = { "质权人姓名#imporg","出质人类别#imporgtype", "出质金额（万元）#impam","出质备案日期#imponrecdate", "出质批准日期#impsandate","出质截止日期#impto", "出质审批部门#impexaeep" };

		// 股权冻结历史信息
		String[] gqdjls_t = { "冻结文号#frodocno","冻结机关#froauth", "冻结起始日期#frofrom","冻结截止日期#froto", "冻结金额（万元）#froam","解冻机关#thawauth", "解冻文号#thawdocno","解冻日期#thawdate", "解冻说明#thawcomment"};

		// 动产抵押信息
		String[] dcdy_t = { "抵押ID#morregid","抵押人#mortgagor", "抵押权人#more","登记机关#regorg", "登记日期#regidate","状态标识#mortype", "被担保主债权种类#priclaseckind","被担保主债权数额(万元)#priclasecam",
				"履约起始日期#pefperform","履约截止日期#pefperto", "注销日期#candate","登记证号#morregcno", "申请抵押原因#appregrea" };

		// 动产抵押物信息
		String[] dcdyw_t = { "抵押ID#morregid","抵押物名称#guaname", "数量#quan","价值(万元)#value" };

		// 清算信息
		String[] qs_t = { "清算责任人#ligentity","清算负责人#ligprincipal", "起算组成员#liqmen","清算完结日期#ligenddate", "债务承接人#debttranee","债权承接人#claimtranee", "清算完结情况#ligst" };

		
//			Map<String, List<JSONObject>> jsonMap = EntReportUtils.entJsonToMap(jsonStr);
		
		
		Integer rowIndex = -1;
		HSSFSheet sheet = workbook.createSheet("工商信息");
		JSONObject xxzt = (JSONObject) resultMap.get("zc").get(0).get("data");
		String enttype = xxzt.getString("enttype");
		
		if (!enttype.isEmpty() && enttype.equals("个体")) {
			rowIndex = setCellValueMethod(resultMap.get("zc"), org_t_gt, "注册信息", rowIndex, sheet);
		}else{
			rowIndex = setCellValueMethod(resultMap.get("zc"), org_t, "注册信息", rowIndex, sheet);
			rowIndex = setRowCellValueMethod(resultMap.get("gd"), gd_t, "股东信息", rowIndex, sheet);
			rowIndex = setRowCellValueMethod(resultMap.get("gg"), gg_t, "高管信息", rowIndex, sheet);
		}

		rowIndex = setCellValueMethod(resultMap.get("tz"), tz_t, "对外投资", rowIndex, sheet);
		rowIndex = setCellValueMethod(resultMap.get("bgjl"), bgxx_t, "变更信息", rowIndex, sheet);
		rowIndex = setCellValueMethod(resultMap.get("fzjg"), fzjg_t, "分支机构", rowIndex, sheet);

		
		rowIndex = -1;
		HSSFSheet sheet1 = workbook.createSheet("风险信息");
		rowIndex = setCellValueMethod(resultMap.get("xzcfls"), xzcfls_t, "行政处罚历史", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("sxbzx"), sxbzxr_t, "失信被执行", rowIndex, sheet1);
		rowIndex = setRowCellValueMethod(resultMap.get("bzx"), bzx_t, "被执行人", rowIndex, sheet1);
		rowIndex = setRowCellValueMethod(resultMap.get("jyyc"), jyyc_t, "经营异常", rowIndex, sheet1);

		rowIndex = setCellValueMethod(null, null, "法院涉诉信息", rowIndex, sheet1);
		rowIndex = setCellValueCourtMethod(resultMap.get("fx101"), fx101_t, "裁决文书", rowIndex, sheet1);			
		rowIndex = setCellValueMethod(resultMap.get("fx104"), fx104_t, "限制高消费被执行人", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx105"), fx105_t, "限制出境被执行人", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx106"), fx106_t, "老赖信息", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx107"), fx107_t, "立案信息", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx108"), fx108_t, "开庭信息", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx109"), fx109_t, "送达公告", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx110"), fx110_t, "其他", rowIndex, sheet1);
										
		rowIndex = setCellValueMethod(null, null, "税务不良信息", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx201"), fx201_t, "欠税公告", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx202"), fx202_t, "失踪纳税人", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx203"), fx203_t, "注销信息", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx204"), fx204_t, "失信纳税人", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx205"), fx205_t, "税务违法信息", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx206"), fx206_t, "税务逾期信息", rowIndex, sheet1);				
		
		rowIndex = setCellValueMethod(null, null, "行政处罚信息", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx301"), fx301_t, "税务行政处罚决定书", rowIndex, sheet1);
		rowIndex = setCellValueMethod(resultMap.get("fx302"), fx302_t, "税务行政处罚信息", rowIndex, sheet1);
		
		
		rowIndex = -1;
		HSSFSheet sheet2 = workbook.createSheet("法定代表人信息");
		if (!enttype.isEmpty() && enttype.equals("个体")) {
			rowIndex = setCellValueMethod(resultMap.get("frdwtz"), frdwtz_t, "经营者对外投资", rowIndex, sheet2);
			rowIndex = setCellValueMethod(resultMap.get("frdwtz"), frdwtz_t, "经营者其他任职", rowIndex, sheet2);
		}else {
			rowIndex = setCellValueMethod(resultMap.get("frdwtz"), frdwtz_t, "法定代表人对外投资", rowIndex, sheet2);
			rowIndex = setCellValueMethod(resultMap.get("frqtrz"), frqtrz_t, "法定代表人其他任职", rowIndex, sheet2);
		}
		// rowIndex = -1;

		rowIndex = -1;
		HSSFSheet sheet4 = workbook.createSheet("知识产权");
		rowIndex = setRowCellValueMethod(resultMap.get("sb"), sb_t, "商标", rowIndex, sheet4);
		rowIndex = setRowCellValueMethod(resultMap.get("zl"), zl_t, "专利", rowIndex, sheet4);

		HSSFSheet sheet5 = workbook.createSheet("数据分析");
		sheet5.setDefaultColumnWidth((short) 40);
		HSSFRow row = null;
		HSSFCell cell = null;
		rowIndex = 0;
		
		if (resultMap.get("pmxx") != null) {
			//System.out.println("pmxx"+resultMap.get("pmxx").toString());
			JSONObject rankData = resultMap.get("pmxx").get(0);
			//System.out.println(rankData.toString());
			if(null != rankData && rankData.size() > 0 ){
				JSONObject data = rankData.getJSONObject("data");
				//System.out.println(data.toString());
				if(data != null){
					//注册数据分析
					String[] zc_t = {"注册资本","实收资本","对外投资笔数","对外投资认缴","成立时间","工商信息变更频次"};
					//String[] all_t = {"altFreqAll","esDateAll","subConAmAll","invNumAll","recCapAll","regCapAll"};
					String[] all_t = {"regCapAll","recCapAll","invNumAll","subConAmAll","esDateAll","altFreqAll"};
					//String[] pro_t = {"altFreqProvince","esDateProvince","subConAmProvince","invNumProvince","recCapProvince","regCapProvince"};
					String[] pro_t = {"regCapProvince","recCapProvince","invNumProvince","subConAmProvince","esDateProvince","altFreqProvince"};
					//String[] ind_t = {"altFreqIndustry","esDateIndustry","subConAmIndustry","invNumIndustry","recCapIndustry","regCapIndustry"};
					String[] ind_t = {"regCapIndustry","recCapIndustry","invNumIndustry","subConAmIndustry","esDateIndustry","altFreqIndustry"};
					CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, 4);
					sheet5.addMergedRegion(cra);
					row = sheet5.createRow(rowIndex++);
					row.setHeight((short) 450);
					cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue("注册数据分析");	
					JSONObject entDBRank = data.getJSONObject("entDBRank");
					for(int i = 0 ; i < zc_t.length;i++){
						row = sheet5.createRow(rowIndex++);
						row.setHeight((short) 350);
						cell = row.createCell(0);
						cell.setCellStyle(style3);
						cell.setCellValue(zc_t[i]);					
						cell = row.createCell(1);
						cell.setCellStyle(style2);
						
						String all_pm = entDBRank.getString(all_t[i]);
						if(all_pm != null && (!all_pm.equals("0") || !all_pm.equals("0.0"))){
							cell.setCellValue("在全国中排名：前" + all_pm + "%");
						}else{
							cell.setCellValue("在全国中排名：未知");
						}
						cell = row.createCell(2);
						cell.setCellStyle(style2);
						String pro_pm = entDBRank.getString(pro_t[i]);
						if(pro_pm != null && (!pro_pm.equals("0") || !pro_pm.equals("0.0"))){
							cell.setCellValue("在注册地所在省份中排名：前" + pro_pm + "%");
						}else{
							cell.setCellValue("在注册地所在省份中排名：未知");
						}
						cell = row.createCell(3);
						cell.setCellStyle(style2);
						String ind_pm = entDBRank.getString(ind_t[i]);
						if(ind_pm != null && (!ind_pm.equals("0") || !ind_pm.equals("0.0"))){
							cell.setCellValue("在同行业中排名：前" + ind_pm + "%");
						}else{
							cell.setCellValue("在同行业中排名：未知");
						}
					}
					JSONObject comRankInfo = (JSONObject) data.get("comRankInfo");
					if(null != comRankInfo){
						//	行业数据分析excel		
					    cra = new CellRangeAddress(rowIndex, rowIndex, 0, 6);
						sheet5.addMergedRegion(cra);
						row = sheet5.createRow(rowIndex++);
						row.setHeight((short) 450);
						cell = row.createCell(0);
						cell.setCellStyle(style);
						cell.setCellValue("行业数据分析");
						JSONArray comRanks = comRankInfo.getJSONArray("comRanks");
						for (int i = 0; i < comRanks.size(); i++) {
							JSONObject obj = (JSONObject) comRanks.get(i);
							String year = obj.get("year") + "";
							JSONArray comRankModels = (JSONArray) obj.get("comRankModels");
							row = sheet5.createRow(rowIndex++);
							row.setHeight((short) 350);
		
							cell = row.createCell(0);
							cell.setCellStyle(style3);
							cell.setCellValue(year);
							cell = row.createCell(1);
							cell.setCellStyle(style3);
							cell.setCellValue("指标值");
							cell = row.createCell(2);
							cell.setCellStyle(style3);
							cell.setCellValue("总排名");
							cell = row.createCell(3);
							cell.setCellStyle(style3);
							cell.setCellValue("区排名");
							cell = row.createCell(4);
							cell.setCellStyle(style3);
							cell.setCellValue("大行业排名");
							cell = row.createCell(5);
							cell.setCellStyle(style3);
							cell.setCellValue("一级分行业排名");
							cell = row.createCell(6);
							cell.setCellStyle(style3);
							cell.setCellValue("二级分行业排名");
							for (int j = 0; j < comRankModels.size(); j++) {
								JSONObject comrank = (JSONObject) comRankModels.get(j);
								row = sheet5.createRow(rowIndex++);
								row.setHeight((short) 350);
								cell = row.createCell(0);
								cell.setCellStyle(style2);
								cell.setCellStyle(style3);
								cell.setCellValue(comrank.getString("fieldName"));
								cell = row.createCell(1);
								cell.setCellStyle(style2);
								if(j<2){
									cell.setCellValue(comrank.getString("fieldValue")+"(千元)");
								}else{
									cell.setCellValue(comrank.getString("fieldValue"));
								}
								cell = row.createCell(2);
								cell.setCellStyle(style2);
								cell.setCellValue(comrank.getString("rank"));
								cell = row.createCell(3);
								cell.setCellStyle(style2);
								cell.setCellValue(comrank.getString("areaRank"));
								cell = row.createCell(4);
								cell.setCellStyle(style2);
								cell.setCellValue(comrank.getString("bigIndilRank"));
								cell = row.createCell(5);
								cell.setCellStyle(style2);
								cell.setCellValue(comrank.getString("secIndilRank"));
								cell = row.createCell(6);
								cell.setCellStyle(style2);
								cell.setCellValue(comrank.getString("indilRank"));
							}
							rowIndex++;
						}
						JSONArray comRatios = (JSONArray) comRankInfo.get("comRatios");
						rowIndex++;
						CellRangeAddress cra1 = new CellRangeAddress(rowIndex, rowIndex, 0, 7);
						sheet5.addMergedRegion(cra1);
						row = sheet5.createRow(rowIndex++);
						row.setHeight((short) 450);
						cell = row.createCell(0);
						cell.setCellStyle(style);
						cell.setCellValue("财务数据分析");
						row = sheet5.createRow(rowIndex++);
						row.setHeight((short) 350);
						cell = row.createCell(0);
						cell.setCellStyle(style3);
						cell.setCellValue("年份");
						cell = row.createCell(1);
						cell.setCellStyle(style3);
						cell.setCellValue("资产负债率（%）");
						cell = row.createCell(2);
						cell.setCellStyle(style3);
						cell.setCellValue("营业利润率（%）");
						cell = row.createCell(3);
						cell.setCellStyle(style3);
						cell.setCellValue("净值报酬率（%）");
						cell = row.createCell(4);
						cell.setCellStyle(style3);
						cell.setCellValue("总资产周转率（%）");
						cell = row.createCell(5);
						cell.setCellStyle(style3);
						cell.setCellValue("销售收入成长率（%）");
						cell = row.createCell(6);
						cell.setCellStyle(style3);
						cell.setCellValue("净利润成长率（%）");
						cell = row.createCell(7);
						cell.setCellStyle(style3);
						cell.setCellValue("总资产成长率（%）");
						for (int j = 0; j < comRatios.size(); j++) {
							JSONObject comrank = (JSONObject) comRatios.get(j);
							row = sheet5.createRow(rowIndex++);
							row.setHeight((short) 350);
							cell = row.createCell(0);
							cell.setCellStyle(style3);
							cell.setCellValue(comrank.getString("year"));
							cell = row.createCell(1);
							cell.setCellStyle(style2);
							cell.setCellValue(comrank.getString("ratio1"));
							cell = row.createCell(2);
							cell.setCellStyle(style2);
							cell.setCellValue(comrank.getString("ratio4"));
							cell = row.createCell(3);
							cell.setCellStyle(style2);
							cell.setCellValue(comrank.getString("ratio7"));
							cell = row.createCell(4);
							cell.setCellStyle(style2);
							cell.setCellValue(comrank.getString("ratio11"));
							cell = row.createCell(5);
							cell.setCellStyle(style2);
							cell.setCellValue(comrank.getString("ratio13"));
							cell = row.createCell(6);
							cell.setCellStyle(style2);
							cell.setCellValue(comrank.getString("ratio14"));
							cell = row.createCell(7);
							cell.setCellStyle(style2);
							cell.setCellValue(comrank.getString("ratio16"));
						}
					}
				}
			}
		}

		rowIndex = -1;
		HSSFSheet sheet6 = workbook.createSheet("资产信息");
		rowIndex = setCellValueMethod(resultMap.get("gqcz"), gqcz_t, "股权出质历史", rowIndex, sheet6);

		rowIndex = setCellValueMethod(resultMap.get("gqdjls"), gqdjls_t, "股权冻结历史", rowIndex, sheet6);

		rowIndex = setCellValueMethod(resultMap.get("dcdy"), dcdy_t, "动产抵押", rowIndex, sheet6);

		rowIndex = setCellValueMethod(resultMap.get("dcdyw"), dcdyw_t, "动产抵押物", rowIndex, sheet6);

		rowIndex = setCellValueMethod(resultMap.get("qs"), qs_t, "清算信息", rowIndex, sheet6);


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
	public Integer setCellValueMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex, HSSFSheet sheet) {
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

		if (list != null) {

			for (JSONObject datas : list) {
				if (datas != null) {
					JSONObject data = datas.getJSONObject("data");
					if(null == data){
						data = datas;
					}
					array++;
					for (int i = 0; i < arrayData.length; i++) {
						//设置单元格列合并
						sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 3));
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
									if(cellData[m-1].equals("注册资本币种")||cellData[m-1].equals("币种")){
										cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"人民币":data.get(fieldName)+"");
									}else{
										cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");
									}
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
									// cra = new CellRangeAddress(rowIndex-1,
									// rowIndex-1, 1, 3);
									// sheet.addMergedRegion(cra);
									String fieldName = cellData[m];
									if(cellData[m-1].equals("注册资本币种")||cellData[m-1].equals("币种")){
										cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"人民币":data.get(fieldName)+"");
									}else{
										cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");
									}
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
	public Integer setRowCellValueMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex, HSSFSheet sheet) {
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

		if (list != null) {
			
			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 350);
			if(null != arrayData){
				for (int i = 0; i < arrayData.length; i++) {
					String titles = arrayData[i].split("#")[0];
					cell = row.createCell(i);
					cell.setCellStyle(style3);
					cell.setCellValue(titles);
				}
			} 
			
			for (JSONObject datas : list) {
				JSONObject data = datas.getJSONObject("data");
				row.setHeight((short) 350);
				row = sheet.createRow(rowIndex++);
				for (int i = 0; i < arrayData.length; i++) {
					String fieldName = arrayData[i].split("#")[1];
					cell = row.createCell(i);
					cell.setCellStyle(style3);	
				    cell.setCellValue((data.get(fieldName)==null||data.get(fieldName).equals(""))?"--":data.get(fieldName)+"");	
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
	
	//背景黄色居中
	private static HSSFCellStyle style4;
	//背景浅蓝居中
	private static HSSFCellStyle style5;
	//背景黄色居左
	private static HSSFCellStyle style6;
	//背景浅蓝居左
	private static HSSFCellStyle style7;
	//背景黄色居右
	private static HSSFCellStyle style8;
	//背景浅蓝居右
	private static HSSFCellStyle style9;
	
	/**
	 * 法院判决书 竖版表格 setValue
	 * 
	 * @param list			当前分类内容
	 * @param arrayData		标题-字段对应
	 * @param title			分类标题
	 * @param rowIndex		行数
	 * @param sheet			大标题
	 * @return
	 */
	public Integer setCellValueCourtMethod(List<JSONObject> list, String[] arrayData, String title, Integer rowIndex, HSSFSheet sheet) {
	
		rowIndex = rowIndex + 1;
		// 设置表格默认列宽度为25个字节
		sheet.setDefaultColumnWidth((short) 40);
		HSSFRow row;
		HSSFCell cell;
		CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
		sheet.addMergedRegion(cra);
		//sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 400);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(title);
		Integer array = 0;

		if (list != null) {

			for (JSONObject datas : list) {
				if (datas != null) {
					JSONObject data = datas.getJSONObject("data");
					if(null == data){
						data = datas;
					}
					array++;
					for (int i = 0; i < arrayData.length; i++) {
						//设置单元格列合并
						sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
						row = sheet.createRow(rowIndex++);
						row.setHeight((short) 600);
						String[] cellData = arrayData[i].split("#");
						int cellIndex = cellData.length;
						if(i==0){
							//通过StringBuilder追加在一行显示
							cell = row.createCell(0);
							StringBuilder builder = new StringBuilder();
							for (int m = 0; m < cellIndex; m++) {
								if (array % 2 == 0) {
									cell.setCellStyle(style6);
								} else {
									cell.setCellStyle(style7);
								}
								if (m % 2 == 0) {
									//"pj"指代判决书
									if(!"pj".equals(cellData[m])){
										builder.append(cellData[m]);							
									}
								}
								else {

									String fieldName = cellData[m];
									builder.append((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "");
								}
						    }
							cell.setCellValue(builder.toString());
						}else if(i==1){
							//StringBuilder类追加在一行显示
							cell = row.createCell(0);
							StringBuilder builder = new StringBuilder();
							for (int m = 0; m < cellIndex; m++) {
								if (array % 2 == 0) {
									cell.setCellStyle(style4);
								} else {
									cell.setCellStyle(style5);
								}
								if (m % 2 == 0) {
									if(!"pj".equals(cellData[m])){
										builder.append(cellData[m]);								
									}
								}
								else {
									String fieldName = cellData[m];
									builder.append((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "");
								}
						    }
							cell.setCellValue(builder.toString());
						}else if(i>=6&&i<=arrayData.length){
							for (int m = 0; m < cellIndex; m++) {
								cell = row.createCell(0);
								if (array % 2 == 0) {
									
									cell.setCellStyle(style8);
								} else {
									
									cell.setCellStyle(style9);
								}
								if (m % 2 == 0) {
									if(!"pj".equals(cellData[m])){
										String da = cellData[m];
										cell.setCellValue(da+"----");										
									}
								} else {
									String fieldName = cellData[m];
									if("datatime".equals(fieldName)){
										cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "----");
									}
									cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "----");
								}
							}
						}else if(i==5){
							for (int m = 0; m < cellIndex; m++) {
								if (m % 2 == 0) {
									cell = row.createCell(0);
									if (array % 2 == 0) {
										cell.setCellStyle(style6);
									} else {
										cell.setCellStyle(style7);
									}
									if(!"pj".equals(cellData[m])){
										cell.setCellValue(cellData[m]);										
									}
								} else {
									String fieldName = cellData[m];
									if("content".equals(fieldName)){
										String cont= (data.get(fieldName) == null || data.get(fieldName).equals("")) ? "  " : data.get(fieldName) + "";
										//判段内容换行显示
										String newcont = cont.replaceAll("<p>", "").replaceAll("\r", "").replaceAll("\n", "");
										String[] contentArr = newcont.split("</p>");
										if(contentArr.length>0){
											for (int j = 0; j < contentArr.length; j++) {
												int length = contentArr[j].length();
												int rowHeight = (length/90+1)*260;
												sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
												row = sheet.createRow(rowIndex++);
												row.setHeight((short) rowHeight);
												cell = row.createCell(0);
												if (array % 2 == 0) {
													cell.setCellStyle(style6);
												} else {
													cell.setCellStyle(style7);
												}
												cell.setCellValue(contentArr[j]);
											}
										}
									}
								}
							}
						}
						else{
							for (int m = 0; m < cellIndex; m++) {
								row.setHeight((short) 300);
								cell = row.createCell(0);
								if (array % 2 == 0) {
									cell.setCellStyle(style4);
								} else {
									cell.setCellStyle(style5);
								}
								if (m % 2 == 0) {
									if(!"pj".equals(cellData[m])){
										cell.setCellValue(cellData[m]);										
									}
								} else {
									String fieldName = cellData[m];									
									cell.setCellValue((data.get(fieldName) == null || data.get(fieldName).equals("")) ? "--" : data.get(fieldName) + "");
								}
							}
							
						}
					}
				}
			}
		}else{
			
			cra = new CellRangeAddress(rowIndex, rowIndex, 0, 3);
			sheet.addMergedRegion(cra);
			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 350);
			cell = row.createCell(0);
			cell.setCellStyle(style3);
			cell.setCellValue("暂无信息");
			
		}
		return rowIndex;
	}
	
}