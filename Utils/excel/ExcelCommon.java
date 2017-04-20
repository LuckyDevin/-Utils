package com.ccx.credit.util.excel;


public class ExcelCommon {

	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2007_POSTFIX = "xlsx";

//	public static void main(String[] args) throws IOException {
//		ExcelCommon excelCommon = new ExcelCommon();
//		String relativelyPath = System.getProperty("user.dir");
//		String fileName = relativelyPath + "\\WebContent\\resources\\download\\test1.xlsx";
//		InputStream is = new FileInputStream(fileName);
//		excelCommon.readExcel(fileName,is);
//	}

	public static String getPostfix(String path) {
		if (path == null || "".equals(path.trim())) {
			return "";
		}
		if (path.contains(".")) {
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		}
		return "";
	}
}
