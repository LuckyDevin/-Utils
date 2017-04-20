package com.ccx.credit.util.excel;

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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.Region;

public class ExcelLineDown<T> {
	public void  doExcuteData(HSSFCellStyle style,HSSFCellStyle style2,HSSFCellStyle style3,HSSFSheet sheet,String[] headers, Collection<T> dataset,OutputStream out, String pattern,String[] propertys ,String title){
		/*设置行（row）和单元格（cell）*/
		HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
		row.setHeight((short) 450);
		HSSFCell tcell = row.createCell(0);
		tcell.setCellStyle(style);//======*********
		setGB2312String(tcell,title);
		sheet.addMergedRegion(new Region(row.getRowNum(),(short)(0),row.getRowNum(),(short)(headers.length-1)));
		
		 row = sheet.createRow(row.getRowNum()+1);
		 row.setHeight((short) 350);
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
								Method getMethod;
										getMethod = tCls.getMethod(getMethodName,new Class[] {});
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
								}  else {
									textValue = value==null ? "" : value.toString();
								}
								if (textValue != null) {
									Pattern p = Pattern.compile("^//d+(//.//d+)?$");
									Matcher matcher = p.matcher(textValue);
									if (matcher.matches()) {
										cell.setCellValue(Double.parseDouble(textValue));
									} else {
										HSSFRichTextString richString = new HSSFRichTextString(textValue);
										cell.setCellValue(richString);
									}
								}
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
							}catch (IllegalAccessException e) {
									e.printStackTrace();
							} catch (IllegalArgumentException e) {
									e.printStackTrace();
							} catch (InvocationTargetException e) {
									e.printStackTrace();
							} finally {
					
							}
						}	
					}
				}
		}
	 row = sheet.createRow(row.getRowNum()+1);
	 row.setHeight((short) 450);
	 sheet.addMergedRegion(new Region(row.getRowNum(),(short)(0),row.getRowNum(),(short)(headers.length-1)));
	}
		
	private void setGB2312String(HSSFCell tcell, String string) {
		HSSFRichTextString text = new HSSFRichTextString(string);
		tcell.setCellValue(text);
	}
		
}
