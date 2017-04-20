package com.ccx.credit.util;

import java.util.HashMap;
import java.util.Map;

public class HistoryWhereMap {
	public final static Map<String, String> HISTORY_WHERE_MAP = new HashMap<String, String>();
	
	// 修改这里参数 注意也需要修该HistoryQueryDaoImpl  导出全部历史记录sql: SEARCH_ALL_HISTORY_ADMIN_SQL
	static {
		HISTORY_WHERE_MAP.put("history_report_ent", " AND TYPE = 10 ");
		HISTORY_WHERE_MAP.put("history_report_batchper", " AND TYPE = 248 ");
		HISTORY_WHERE_MAP.put("history_report_per", " AND CATEGORY = 186 ");
		HISTORY_WHERE_MAP.put("history_score", " AND (TYPE = 1 OR (TYPE = 170 AND CATEGORY = 187 ))  ");
		HISTORY_WHERE_MAP.put("history_cloud", " AND (TYPE IN(4, 140, 141, 142, 143, 102, 108) OR  (TYPE = 75 AND MID IS NULL) )");
		HISTORY_WHERE_MAP.put("history_alliance", " AND TYPE = 229 "); //万象联盟历史记录只展示查询的历史记录。228：上报。229：查询。231：反馈。
		HISTORY_WHERE_MAP.put("history_wisdom_topo", " AND TYPE = 8 ");
		HISTORY_WHERE_MAP.put("history_wisdom_judicial", " AND TYPE IN(3, 196) ");
		HISTORY_WHERE_MAP.put("history_wisdom_business", " AND TYPE IN (14, 28, 29)");
		HISTORY_WHERE_MAP.put("history_wisdom_cert", " AND TYPE = 15 ");
		HISTORY_WHERE_MAP.put("history_wisdom_trans", " AND (TYPE = 13 or TYPE = 30) ");
		HISTORY_WHERE_MAP.put("history_wisdom_fixedPhone", " AND TYPE = 69  ");
		HISTORY_WHERE_MAP.put("history_wisdom_netloan", " AND TYPE = 195  ");
		HISTORY_WHERE_MAP.put("history_wisdom_edu", " AND  TYPE = 7 ");
		HISTORY_WHERE_MAP.put("history_wisdom_creditagency", " AND  TYPE = 166 ");
		HISTORY_WHERE_MAP.put("history_wisdom_badbehave", " AND  TYPE = 523 ");
		HISTORY_WHERE_MAP.put("history_wisdom_riskinfo", " AND  TYPE = 163 ");
		HISTORY_WHERE_MAP.put("history_wisdom_mobileop", " AND  TYPE = 320 ");

		HISTORY_WHERE_MAP.put("history_wisdom_billvalid", "");
		HISTORY_WHERE_MAP.put("history_wisdom_flight", "");
		HISTORY_WHERE_MAP.put("history_wisdom_financial", "");
		HISTORY_WHERE_MAP.put("history_wisdom_ecommerce", "");

	}

	public static String getWhereStr(String codeValue) {
		if (codeValue != null) {
			return HISTORY_WHERE_MAP.get(codeValue);
		} else {
			return "";
		}
	}
	
	
}
