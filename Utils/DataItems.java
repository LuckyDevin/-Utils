package com.ccx.credit.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 常量保存类
 * @author wbh
 *
 */
public class DataItems {
	
	/*************************保存常量************************/
	public static final Integer CONSTANT_ZERO = 0;
	public static final Integer CONSTANT_ONE = 1;
	public static final Integer CONSTANT_TWO = 2;

	public static final List<Integer> PERSONALCREDITTYPE = new ArrayList<Integer>();
	
	static{
		//PERSONALCREDITTYPE.add(170);//信用报告-子项-头信息 
		//PERSONALCREDITTYPE.add(172);//信用报告-子项-分数信息
		PERSONALCREDITTYPE.add(173);//信用报告-子项-核验信息
		PERSONALCREDITTYPE.add(174);//信用报告-子项-户籍信息
		PERSONALCREDITTYPE.add(175);//信用报告-子项-教育信息"
		PERSONALCREDITTYPE.add(176);//信用报告-子项-职业资格证书信息"
		PERSONALCREDITTYPE.add(177);//信用报告-子项-工商信息"
		PERSONALCREDITTYPE.add(178);//信用报告-子项-资产信息"
		PERSONALCREDITTYPE.add(179);//信用报告-子项-银联交易信息"
		PERSONALCREDITTYPE.add(180);//信用报告-子项-信贷记录信息"
		PERSONALCREDITTYPE.add(181);//信用报告-子项-司法记录信息"
		PERSONALCREDITTYPE.add(182);//信用报告-子项-电信运营商信息"
		//PERSONALCREDITTYPE.add(183);//信用报告-子项-本人异议申告信息"
		PERSONALCREDITTYPE.add(184);//信用报告-子项-身份信息"
		//PERSONALCREDITTYPE.add(185);//信用报告-子项-查询历史信息
		PERSONALCREDITTYPE.add(207);//信用报告-子项-户籍信息、教育信息中学历信息是否一致
		PERSONALCREDITTYPE.add(216);//信用报告-子项-子项被查询次数
		PERSONALCREDITTYPE.add(300);//信用报告-子项-新2017银联交易接口
	}
	
	public static final Map<Integer, Integer> PERSONALCREDITTYPEMAP = new LinkedHashMap<Integer, Integer>();
	static {
		//PERSONALCREDITTYPEMAP.put(0, 170);
		PERSONALCREDITTYPEMAP.put(10, 172);
		PERSONALCREDITTYPEMAP.put(11,173);
		PERSONALCREDITTYPEMAP.put(1, 174);
		PERSONALCREDITTYPEMAP.put(2, 175);
		PERSONALCREDITTYPEMAP.put(21, 176);
		PERSONALCREDITTYPEMAP.put(3, 177);
		PERSONALCREDITTYPEMAP.put(4, 178);
		PERSONALCREDITTYPEMAP.put(5, 179);
		PERSONALCREDITTYPEMAP.put(6, 180);
		PERSONALCREDITTYPEMAP.put(7, 181);
		PERSONALCREDITTYPEMAP.put(8, 182);
//		PERSONALCREDITTYPEMAP.put(CONTRACT_NO_EXIST, 183);
		PERSONALCREDITTYPEMAP.put(12, 184);
		PERSONALCREDITTYPEMAP.put(26, 207);
		PERSONALCREDITTYPEMAP.put(27, 216);			//个人报告子项查询次数
		PERSONALCREDITTYPEMAP.put(30, 300);			//信用报告-子项-新2017银联交易接口
//		PERSONALCREDITTYPEMAP.put(APPLY_DEAD_LINE_ERROR,185);
	};
	
	
	
}
