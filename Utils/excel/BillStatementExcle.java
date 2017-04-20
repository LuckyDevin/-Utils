package com.ccx.credit.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class BillStatementExcle<T> {
	protected Logger LOG = Logger.getLogger( this.getClass().getName() );
	private  HSSFWorkbook workbook;
	private HSSFSheet sheet;
	public BillStatementExcle(HSSFWorkbook workbook, HSSFSheet sheet) {
		super();
		this.workbook = workbook;
		this.sheet = sheet;
		/*创建样式一*/
		HSSFCellStyle style = createStyle( workbook);
		/*创建样式二*/
		HSSFCellStyle style2 = createStyle2( workbook);
		/*创建样式二*/
		HSSFCellStyle style3 = createStyle3( workbook);
		sheet.setColumnWidth(0, 20 * 256); 
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------
		//创建样式一<表名>
		public  HSSFCellStyle  createStyle(HSSFWorkbook workbook){
			/*创建一个样式*/
			HSSFCellStyle style = workbook.createCellStyle();
			//设置背景色
			style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			/*设置边框*/
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  //  下边框
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);			//左边框
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);		//右边框
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);			//上边框
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);		//设置居中
			style.setWrapText(true);                                                //设置自动换行
			/*设置字体 */
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index);
			font.setFontName("宋体");											//设置字体类型
			font.setFontHeightInPoints((short) 14);                    //设置字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);          //粗体显示
			style.setFont(font);													//选择需要用到的字体样式
			return style;
		}
		
		//创建样式一<表名>
				public  HSSFCellStyle  createStyle3(HSSFWorkbook workbook){
					/*创建一个样式*/
					HSSFCellStyle style = workbook.createCellStyle();
					//设置背景色
					style.setFillForegroundColor(HSSFColor.WHITE.index);
					style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					/*设置边框*/
					style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  //  下边框
					style.setBorderLeft(HSSFCellStyle.BORDER_THIN);			//左边框
					style.setBorderRight(HSSFCellStyle.BORDER_THIN);		//右边框
					style.setBorderTop(HSSFCellStyle.BORDER_THIN);			//上边框
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);		//设置居中
					style.setWrapText(true);                                                //设置自动换行
					/*设置字体 */
					HSSFFont font = workbook.createFont();
					font.setColor(HSSFColor.BLACK.index);
					font.setFontName("宋体");											//设置字体类型
					font.setFontHeightInPoints((short) 12);                    //设置字体大小
					font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);          //粗体显示
					style.setFont(font);													//选择需要用到的字体样式
					return style;
				}
				
		
		//创建样式二<项目名>
			public   HSSFCellStyle  createStyle2(HSSFWorkbook workbook){
				HSSFCellStyle style2 = workbook.createCellStyle();
				
				style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
				style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				
				style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFFont font2 = workbook.createFont();
				font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
				style2.setFont(font2);
				
				return style2;
			}
		
		/**
		 * 向excle表中导入读取的数据。
		 * */
		public void  doExcuteData(HSSFSheet sheet,String[] headers, Collection<T> dataset,
				OutputStream out, String pattern,String[] propertys,HSSFWorkbook workbook,HSSFPatriarch patriarch,String title){
			/*创建样式一*/
			HSSFCellStyle style = createStyle( workbook);
			/*创建样式二*/
			HSSFCellStyle style2 = createStyle2( workbook);
			/*创建样式二*/
			HSSFCellStyle style3 = createStyle3( workbook);
			/*设置行（row）和单元格（cell）*/
			HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
			row.setHeight((short) 400);
			HSSFCell tcell = row.createCell(0);
			tcell.setCellStyle(style);//======*********
			setGB2312String(tcell,title);
			sheet.addMergedRegion(new Region(row.getRowNum(),(short)(0),row.getRowNum(),(short)(headers.length-1)));
			
			 row = sheet.createRow(row.getRowNum()+1);
			 row.setHeight((short) 300);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell1 = row.createCell(i);
				cell1.setCellStyle(style2);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell1.setCellValue(text);
				//如果内容为"空"，则合并单元格
				if(headers[i].equals("空")){
					sheet.addMergedRegion(new Region(row.getRowNum(),(short)(i-1),row.getRowNum(),(short)i));
				}
				//如果内容为"排序"
				if(headers[i].equals("排序")){
					sheet.addMergedRegion(new Region(row.getRowNum(),(short)(i-1),row.getRowNum(),(short)i));
				}
			}
			
			Iterator<T> it = dataset.iterator();
		while (it.hasNext()) {
				row = sheet.createRow(row.getRowNum()+1);
				T t = (T) it.next();
				Field[] fields = t.getClass().getDeclaredFields();
				int j= 0;
				for(int v =0;v<propertys.length;v++){
						for (short i = 0; i < fields.length; i++) {
							Field field = fields[i];
							String fieldName = field.getName();
							String getMethodName = "get"
									+ fieldName.substring(0, 1).toUpperCase()
									+ fieldName.substring(1);
						if(fieldName.equals(propertys[v])){
							HSSFCell cell = row.createCell(j);
							j++;
							cell.setCellStyle(style3);
							//如果内容为"doExcle"，则合并单元格
							if(fieldName.equals("doExcle")){
								sheet.addMergedRegion(new Region(row.getRowNum(),(short)(v-1),row.getRowNum(),(short)v));
							}
							
								try {
									Class tCls = t.getClass();
									Method getMethod = tCls.getMethod(getMethodName,
											new Class[] {});
									Object value = getMethod.invoke(t, new Object[] {});
									String textValue = null;
									if (value instanceof Boolean) {
										boolean bValue = (Boolean) value;
										textValue = "是";
										if (!bValue) {
											textValue = "否";
										}
									} else if (value instanceof Date) {
										Date date = (Date) value;
										SimpleDateFormat sdf = new SimpleDateFormat(pattern);
										textValue = sdf.format(date);
									} else if (value instanceof byte[]) {
										row.setHeightInPoints(60);
										sheet.setColumnWidth(i, (short) (35.7 * 80));
										// sheet.autoSizeColumn(i);
										byte[] bsValue = (byte[]) value;
										HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
												1023, 255, (short) 6, sheet.getLastRowNum(), (short) 6, sheet.getLastRowNum());
										anchor.setAnchorType(2);
										patriarch.createPicture(anchor, workbook.addPicture(
												bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
									} else {
										textValue = value==null ? "" : value.toString();
									}
									if (textValue != null) {
										Pattern p = Pattern.compile("^//d+(//.//d+)?$");
										Matcher matcher = p.matcher(textValue);
										if (matcher.matches()) {
											cell.setCellValue(Double.parseDouble(textValue));
										} else {
											HSSFRichTextString richString = new HSSFRichTextString(
													textValue);
											cell.setCellValue(richString);
										}
									}
								} catch (SecurityException e) {
									LOG.error("error:",e);
								} catch (NoSuchMethodException e) {
									LOG.error("error:",e);
								} catch (IllegalArgumentException e) {
									LOG.error("error:",e);
								} catch (IllegalAccessException e) {
									LOG.error("error:",e);
								} catch (InvocationTargetException e) {
									LOG.error("error:",e);
								} finally {
						
								}
							}	
						}
					}
			}
		 row = sheet.createRow(row.getRowNum()+1);
		 row.setHeight((short) 400);
		 sheet.addMergedRegion(new Region(row.getRowNum(),(short)(0),row.getRowNum(),(short)(headers.length-1)));
			try {
				out.flush();
			} catch (IOException e) {
				LOG.error("error:",e);
			}
		}
		
		//创建表头
			public int  createBill(String title) {
				int index =0;
				HSSFCellStyle style = createStyle3( workbook);
				style.getFont(workbook).setFontHeightInPoints((short) 18);
				
				HSSFRow row;
				HSSFCell tcell ;
				row=sheet.createRow(index++);
				row.setHeight((short)400);  
				tcell=row.createCell((short)0);  
				tcell.setCellStyle(style);  
				setGB2312String(tcell,title);  
				return index;  
			}
			//
			public  void  createTime(OutputStream out, String pattern,String starttime,String endtime) {
				HSSFCellStyle style = createStyle( workbook);
				HSSFRow row;
				HSSFCell tcell ;
				row=sheet.createRow(sheet.getLastRowNum()+1);
				tcell=row.createCell((short)0);  
				tcell.setCellStyle(style);  
				setGB2312String(tcell,"对账时间");  
				
				row=sheet.createRow(sheet.getLastRowNum()+1);
				tcell=row.createCell((short)0);  
				tcell.setCellStyle(style);  
				setGB2312String(tcell,"起始时间");  
				tcell=row.createCell((short)1);  
				tcell.setCellStyle(style);  
				setGB2312String(tcell,starttime);  
				
				tcell=row.createCell((short)2);  
				tcell.setCellStyle(style);  
				setGB2312String(tcell,"截止时间"); 
				tcell=row.createCell((short)3);  
				tcell.setCellStyle(style);  
				setGB2312String(tcell,endtime);  
			}
			
			private void setGB2312String(HSSFCell tcell, String string) {
				HSSFRichTextString text = new HSSFRichTextString(string);
				tcell.setCellValue(text);
			}
				
}
