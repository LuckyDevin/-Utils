package com.ccx.credit.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class BaseAction {

	/**
	 * 将ajax查询的内容写回到页面
	 * 
	 * @param content
	 *            //ajax查询到的内容
	 * @throws IOException
	 */
	public static void writeContentToPage(String content,
			HttpServletResponse response) throws IOException {
		// 設定字符集
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(content);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
