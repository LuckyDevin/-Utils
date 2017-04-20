package com.ccx.credit.util;

import java.util.HashMap;
import java.util.Map;

public class HistoryTypeMap {
	private static final Map<Integer, String> historyTypeMap = new HashMap<Integer, String>();
	static {
		historyTypeMap.put(1, "万象评分信息");//信用评分--》万象评分信息
		historyTypeMap.put(2, "信用报告");
		historyTypeMap.put(3, "风险信息");
		historyTypeMap.put(4, "万象云盾信息");//反欺诈信息--》万象云盾信息
		historyTypeMap.put(5, "万象联盟信息");//信用联盟信息--》万象联盟
		historyTypeMap.put(6, "信用报告-信用评分");
		historyTypeMap.put(7, "学历信息");
		historyTypeMap.put(8, "工商信息");
		historyTypeMap.put(9, "信用联盟信息--其他相关查询");
		historyTypeMap.put(10, "企业信用报告");

		historyTypeMap.put(11, "电信信用评分");
		historyTypeMap.put(12, "电信信用报告");
		historyTypeMap.put(13, "银联交易报告");
		historyTypeMap.put(14, "商户交易信息");
		historyTypeMap.put(15, "职业资格证书");
		historyTypeMap.put(16, "保险-定制报告");
		historyTypeMap.put(17, "企业信用信息");
		historyTypeMap.put(18, "报告-太平保险定制");
		historyTypeMap.put(19, "身份核验照片--API服务");
		historyTypeMap.put(20, "保险-定制报告-亲信");

		historyTypeMap.put(21, "个人用户注册");

		historyTypeMap.put(25, "标准数据包");
		historyTypeMap.put(26, "标准数据包-验证失败");// 计费使用
		historyTypeMap.put(27, "标准数据包-无信息校验逻辑");

		historyTypeMap.put(31, "短信服务--通用");

		historyTypeMap.put(41, "计费-预付费扣费");

		historyTypeMap.put(50, "航空出行统计报告-身份核验");// 返回name+cid 认证结果：一致、不一致等信息
		historyTypeMap.put(51, "身份信息--API服务");// 返回name+cid 认证分数(gzt-不允许直接输出结果)
		historyTypeMap.put(52, "信用评分,风险信息标签--API服务");
		historyTypeMap.put(53, "信用评分--API服务");
		historyTypeMap.put(54, "信用评分-5维度评分--API服务");
		historyTypeMap.put(55, "有效认证--API服务");
		historyTypeMap.put(56, "银行卡有效性校验--API服务");
		historyTypeMap.put(57, "银联交易报告--API服务");
		historyTypeMap.put(58, "信用评分-5维度评分-数据标签--API服务");
		historyTypeMap.put(59, "信用报告-信用评分--API服务");

		historyTypeMap.put(60, "银联交易报告(用户画像)--API服务");
		historyTypeMap.put(61, "工商信息-企业信息--API服务");
		historyTypeMap.put(62, "工商信息-自然人信息--API服务");
		historyTypeMap.put(63, "司法信息-企业信息--API服务");
		historyTypeMap.put(64, "司法信息-自然人信息--API服务");
		historyTypeMap.put(65, "工商信息-企业信息--API服务--B1");
		historyTypeMap.put(66, "工商信息-自然人信息--API服务--B1");
		historyTypeMap.put(67, "手机实名认证-API服务");
		historyTypeMap.put(68, "工商信息-企业名称--API服务");
		historyTypeMap.put(69, "政企固话查询");//固话查询-API服务--》政企固话查询

		historyTypeMap.put(70, "手机在网时长-API服务");
		historyTypeMap.put(71, "微信公众号--查询注册状态");
		historyTypeMap.put(72, "微信公众号--发送短信验证码");
		historyTypeMap.put(73, "微信公众号--对应关系认证");
		historyTypeMap.put(74, "微信公众号--查询信用评分");
		historyTypeMap.put(75, "身份核验");//手机实名认证(3大运营商)-API服务-->身份核验
		historyTypeMap.put(76, "手机在网时长(3大运营商)-API服务");

		historyTypeMap.put(80, "数据量查询");
		historyTypeMap.put(81, "工商信息-资产相关类信息");
		historyTypeMap.put(82, "工商信息-资产相关类信息");
		historyTypeMap.put(83, "工商信息-风险类信息");
		historyTypeMap.put(84, "工商信息-变更处罚");
		historyTypeMap.put(85, "工商信息-专利首页");
		historyTypeMap.put(86, "工商信息-专利下页");
		historyTypeMap.put(87, "工商信息-专利商标信息");
		historyTypeMap.put(88, "工商信息-数据分析");
		historyTypeMap.put(89, "工商信息-待定1");

		/* 信用监控类型-begin */
		historyTypeMap.put(90, "信用监控");
		// 旧信用监控计费类型
		historyTypeMap.put(91, "信息监控--司法信息");
		historyTypeMap.put(92, "信息监控--网贷黑名单");
		historyTypeMap.put(93, "信息监控--工商信息变更");
		historyTypeMap.put(94, "信用监控--知识产权信息--企业");
		historyTypeMap.put(95, "信用监控--个人监控--司法信息");
		historyTypeMap.put(96, "信用监控--个人监控--工商信息");
		/* 信用监控类型-end */

		historyTypeMap.put(801, "工商信息-企业照面信息");
		historyTypeMap.put(802, "工商信息-股东信息");
		historyTypeMap.put(803, "工商信息-高管信息");
		historyTypeMap.put(804, "工商信息-法人对外投资信息");
		historyTypeMap.put(805, "工商信息-法人其他任职信息");
		historyTypeMap.put(806, "工商信息-企业对外投资信息");
		historyTypeMap.put(807, "工商信息-变更信息");
		historyTypeMap.put(808, "工商信息-分支机构信息");
		historyTypeMap.put(809, "工商信息-股权出质历史信息");

		historyTypeMap.put(810, "工商信息-动产抵押信息");
		historyTypeMap.put(811, "工商信息-动产抵押物信息");
		historyTypeMap.put(812, "工商信息-失信被执行人信息");
		historyTypeMap.put(813, "工商信息-被执行人");
		historyTypeMap.put(814, "工商信息-股权冻结历史信息");
		historyTypeMap.put(815, "工商信息-清算信息");
		historyTypeMap.put(816, "工商信息-行政处罚历史");
		historyTypeMap.put(817, "工商信息-经营异常");
		historyTypeMap.put(818, "工商信息-专利信息");
		historyTypeMap.put(819, "工商信息-商标信息");

		historyTypeMap.put(821, "工商信息-为企业法人信息");
		historyTypeMap.put(822, "工商信息-为企业股东信息");
		historyTypeMap.put(823, "工商信息-为企业主要管理人员信息");
		historyTypeMap.put(824, "工商信息-失信被执行人信息");
		historyTypeMap.put(825, "工商信息-被执行人");
		historyTypeMap.put(826, "工商信息-行政处罚历史");
		historyTypeMap.put(827, "工商信息-专利信息");
		historyTypeMap.put(828, "工商信息-商标信息");

		historyTypeMap.put(841, "工商信息-排名");

		historyTypeMap.put(901, "工商信息-企业照面信息-API服务");
		historyTypeMap.put(902, "工商信息-股东信息-API服务");
		historyTypeMap.put(903, "工商信息-高管信息-API服务");
		historyTypeMap.put(904, "工商信息-法人对外投资信息-API服务");
		historyTypeMap.put(905, "工商信息-法人其他任职信息-API服务");
		historyTypeMap.put(906, "工商信息-企业对外投资信息-API服务");
		historyTypeMap.put(907, "工商信息-变更信息-API服务");
		historyTypeMap.put(908, "工商信息-分支机构信息-API服务");
		historyTypeMap.put(909, "工商信息-股权出质历史信息-API服务");

		historyTypeMap.put(910, "工商信息-动产抵押信息-API服务");
		historyTypeMap.put(911, "工商信息-动产抵押物信息-API服务");
		historyTypeMap.put(912, "工商信息-失信被执行人信息-API服务");
		historyTypeMap.put(913, "工商信息-被执行人-API服务");
		historyTypeMap.put(914, "工商信息-股权冻结历史信息-API服务");
		historyTypeMap.put(915, "工商信息-清算信息-API服务");
		historyTypeMap.put(916, "工商信息-行政处罚历史-API服务");
		historyTypeMap.put(917, "工商信息-经营异常-API服务");
		historyTypeMap.put(918, "工商信息-专利信-API服务息");// new add by miaogh
		historyTypeMap.put(919, "工商信息-商标信息-API服务");// new add by miaogh

		historyTypeMap.put(920, "工商信息-排名-API服务");// new add by miaogh
		historyTypeMap.put(921, "工商信息-为企业法人信息-API服务");
		historyTypeMap.put(922, "工商信息-为企业股东信息-API服务");
		historyTypeMap.put(923, "工商信息-为企业主要管理人员信息-API服务");
		historyTypeMap.put(924, "工商信息-失信被执行人信息-API服务");
		historyTypeMap.put(925, "工商信息-被执行人-API服务");
		historyTypeMap.put(926, "工商信息-行政处罚历史-API服务");

		/* 信用监控类型-begin */

		// 新信用监控计费类型
		// 个人信用监控
		historyTypeMap.put(301, "信息监控--个人司法信息--天--半年");
		historyTypeMap.put(302, "信息监控--个人司法信息--天--一年");
		historyTypeMap.put(303, "信息监控--个人网贷黑名单--天--半年");
		historyTypeMap.put(304, "信息监控--个人网贷黑名单--天--一年");
		historyTypeMap.put(305, "信息监控--个人网络负面信息--天--半年");
		historyTypeMap.put(306, "信息监控--个人网络负面信息--天--一年");

		// 企业信用监控
		historyTypeMap.put(401, "信息监控--企业工商信息--周--一年");
		historyTypeMap.put(402, "信息监控--企业工商信息--周--两年");
		historyTypeMap.put(403, "信息监控--企业工商信息--月--一年");
		historyTypeMap.put(404, "信息监控--企业工商信息--月--两年");
		historyTypeMap.put(405, "信息监控--企业司法信息--天--一年");
		historyTypeMap.put(406, "信息监控--企业司法信息--天--两年");
		historyTypeMap.put(407, "信用监控--企业知识产权信息--周--一年");
		historyTypeMap.put(408, "信用监控--企业知识产权信息--周--两年");
		historyTypeMap.put(409, "信用监控--企业知识产权信息--月--一年");
		historyTypeMap.put(410, "信用监控--企业知识产权信息--月--两年");

		/* 信用监控类型-end */

		historyTypeMap.put(101, "卡号-姓名认证--API服务");
		historyTypeMap.put(102, "卡号-姓名-身份证-手机号认证--API服务");
		historyTypeMap.put(103, "卡号-手机号认证--API服务");
		historyTypeMap.put(104, "卡号-手机号-姓名认证--API服务");
		historyTypeMap.put(105, "银联账单核查-核验");//卡号-姓名-身份证号认证--API服务-->银联账单核查-核验
		historyTypeMap.put(106, "卡号-姓名-身份证-手机号认证-短信验证码--API服务");
		historyTypeMap.put(107, "卡号-姓名-身份证-手机号认证(WX)--API服务");
		historyTypeMap.put(108, "卡号-姓名-身份证-手机号认证(T2)--API服务");
		historyTypeMap.put(109, "卡号-姓名-身份证-手机号认证(T3)--API服务");
		historyTypeMap.put(110, "卡号-姓名-身份证认证(T2)--API服务");

		historyTypeMap.put(111, "爬虫-注册");
		historyTypeMap.put(112, "爬虫-注册");

		historyTypeMap.put(121, "专利列表");
		historyTypeMap.put(122, "专利详情");
		historyTypeMap.put(123, "商标列表");
		historyTypeMap.put(124, "商标详情");

		historyTypeMap.put(131, "信息监控-风险信息-企业");
		historyTypeMap.put(132, "信息监控-风险信息-个人");
		historyTypeMap.put(133, "信息监控-工商信息-企业");
		historyTypeMap.put(134, "信息监控-工商信息-个人");
		historyTypeMap.put(135, "信息监控-法海-企业");
		historyTypeMap.put(136, "信息监控-网贷信息-个人");
		historyTypeMap.put(137, "信息监控-司法信息-个人");
		historyTypeMap.put(138, "信息监控-诈骗信息-个人");

		historyTypeMap.put(140, "万象云盾-卡号-姓名认证");
		historyTypeMap.put(141, "万象云盾-卡号-手机号认证");
		historyTypeMap.put(142, "万象云盾-姓名-身份证认证");
		historyTypeMap.put(143, "万象云盾-卡号-姓名-身份证号-手机号认证");
		historyTypeMap.put(144, "万象云盾-卡号有效性认证");

		historyTypeMap.put(151, "证书-医师-API服务");
		historyTypeMap.put(152, "证书-护士-API服务");
		historyTypeMap.put(153, "证书-资格证书-API服务");

		
		

		historyTypeMap.put(2000, "信用提升-银行卡信息");
		historyTypeMap.put(2001, "信用提升-学历信息");
		historyTypeMap.put(2002, "信用提升-邮箱信息");
		historyTypeMap.put(2003, "信用提升-邮箱认证");

		historyTypeMap.put(162, "失信执行信息-企业信息--API服务");
		historyTypeMap.put(163, "失信执行信息-自然人信息--API服务");
		historyTypeMap.put(164, "企业信用报告--API服务");
		historyTypeMap.put(165, "学历信息(照片)--API服务");
		
		
		
		//信用报告-子项(171~187) -->个人信用报告
		historyTypeMap.put(170, "个人信用报告");
		historyTypeMap.put(171, "信用报告-子项-头信息");
		historyTypeMap.put(172, "信用报告-子项-分数信息");
		historyTypeMap.put(173, "信用报告-子项-核验信息");
		historyTypeMap.put(174, "信用报告-子项-户籍信息");
		historyTypeMap.put(175, "信用报告-子项-教育信息");
		historyTypeMap.put(176, "信用报告-子项-职业资格证书信息");
		historyTypeMap.put(177, "信用报告-子项-工商信息");
		historyTypeMap.put(178, "信用报告-子项-资产信息");
		historyTypeMap.put(179, "信用报告-子项-银联交易信息");
		historyTypeMap.put(180, "信用报告-子项-信贷记录信息");
		historyTypeMap.put(181, "信用报告-子项-司法记录信息");
		historyTypeMap.put(182, "信用报告-子项-电信运营商信息");
		historyTypeMap.put(183, "信用报告-子项-本人异议申告信息");
		historyTypeMap.put(184, "信用报告-子项-身份信息");
		historyTypeMap.put(185, "信用报告-子项-查询历史信息");
		// 用于检查分项报告余额是否充足 - 万象平台特殊需求 - begin
		historyTypeMap.put(186, "信用报告-子项-全部信息信息");
		
		historyTypeMap.put(187, "万象评分");//信用报告-子项-分数信息-身份信息-->万象评分
		
		
		
		
		
		// 用于检查分项报告余额是否充足 - 万象平台特殊需求 - end
		historyTypeMap.put(188, "企业信用-工商信息");
		historyTypeMap.put(189, "企业信用-风险信息");
		historyTypeMap.put(190, "企业信用-法人信息");
		historyTypeMap.put(191, "企业信用-企业年报");
		historyTypeMap.put(192, "企业信用-知识产权");
		historyTypeMap.put(193, "企业信用-数据分析");
		historyTypeMap.put(194, "企业信用-资产信息");

		historyTypeMap.put(195, "网贷黑名单");
		historyTypeMap.put(196, "司法信息");

		historyTypeMap.put(197, "企业信用报告-免费");
		historyTypeMap.put(198, "企业信用-图谱");

		historyTypeMap.put(199, "失信执行网贷信息-自然人信息--API服务");

		historyTypeMap.put(200, "手机实名认证-移动-API服务");
		historyTypeMap.put(201, "手机在网时长-移动-API服务");
		historyTypeMap.put(202, "业务网站-企业-专项工商(801~808)");
		historyTypeMap.put(203, "业务网站-自然人-专项工商(821~826)");
		historyTypeMap.put(204, "工商信息-企业信息T2--API服务");
		historyTypeMap.put(205, "工商信息--个人探查T2--API服务");
		historyTypeMap.put(206, "企业信用-风险信息-附加");
		historyTypeMap.put(207, "信用报告-子项--学历比对信息");

		historyTypeMap.put(210, "信用评分-3维度");
		historyTypeMap.put(211, "3维度授信额度报告");
		historyTypeMap.put(212, "个人交易账单认证");
		
		// 敬久-乘机人统计报告
		historyTypeMap.put(213, "航空出行统计报告");//
		historyTypeMap.put(214, "银联账单核查");//个人交易账单认证-卡信息--API服务--》银联账单核查
		historyTypeMap.put(215, "金融画像");//金融画像--API服务-->金融画像
		historyTypeMap.put(241, "金融画像1");//金融画像--API服务-->金融画像1
		historyTypeMap.put(242, "金融画像2");//金融画像--API服务-->金融画像2
		historyTypeMap.put(243, "金融画像3");//金融画像--API服务-->金融画像3
		historyTypeMap.put(244, "金融画像4");//金融画像--API服务-->金融画像4
		historyTypeMap.put(216, "信用报告-子项-次数查询");
		historyTypeMap.put(218, "运营商标签-电信-API服务");
		historyTypeMap.put(219, "敬众-乘机人缓存报告");
		historyTypeMap.put(221, "航旅报告(大客户01)");
		historyTypeMap.put(248, "个人报告批量查询");
		historyTypeMap.put(252, "电商消费画像");
		
		historyTypeMap.put(229, "万象联盟信息");
		historyTypeMap.put(230, "万象联盟信息");

		historyTypeMap.put(52, "3f信用评分");

		// 反欺诈 start
		historyTypeMap.put(601, "万象风云-黑名单查询");
		historyTypeMap.put(602, "万象风云-手机号码-入网城市");
		historyTypeMap.put(603, "万象风云-企业邮箱-有效性");
		historyTypeMap.put(604, "万象风云-IP地址");
		historyTypeMap.put(605, "万象风云-银行卡");
		historyTypeMap.put(606, "万象风云-类型次数");
		// 反欺诈 end

		// 法人信息校验
		historyTypeMap.put(220, "法人信息校验--API服务");

		historyTypeMap.put(8000, "缓存数据");
		historyTypeMap.put(9995, "未知类型-预留");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		historyTypeMap.put(161, "用户画像-单卡号");
		/**********************************/
		historyTypeMap.put(1000, "用户画像-AA0483");
		historyTypeMap.put(1001, "用户画像-AA0491");
		historyTypeMap.put(1002, "用户画像-AA0493");
		historyTypeMap.put(1003, "用户画像-AA0474");
		historyTypeMap.put(1004, "用户画像-AB0464");
		historyTypeMap.put(1005, "用户画像-AB0467");
		historyTypeMap.put(1006, "用户画像-AB0462");
		historyTypeMap.put(1007, "用户画像-AB0465");
		historyTypeMap.put(1008, "用户画像-AB0466");
		historyTypeMap.put(1009, "用户画像-AB0468");
		historyTypeMap.put(1010, "用户画像-AB0469");
		historyTypeMap.put(1011, "用户画像-AC0135");
		historyTypeMap.put(1012, "用户画像-AC0136");
		historyTypeMap.put(1013, "用户画像-AC0134");
		historyTypeMap.put(1014, "用户画像-AC0138");
		historyTypeMap.put(1015, "用户画像-BA0075");
		historyTypeMap.put(1016, "用户画像-BA0128");
		historyTypeMap.put(1017, "用户画像-BB0078");
		historyTypeMap.put(1018, "用户画像-BB0131");
		historyTypeMap.put(1019, "用户画像-BB0053");
		historyTypeMap.put(1020, "用户画像-BB0054");
		historyTypeMap.put(1021, "用户画像-BD0083");
		historyTypeMap.put(1022, "用户画像-BD0086");
		historyTypeMap.put(1023, "用户画像-BD0084");
		historyTypeMap.put(1024, "用户画像-BD0087");
		historyTypeMap.put(1025, "用户画像-BD0300");
		historyTypeMap.put(1026, "用户画像-BD0301");
		historyTypeMap.put(1027, "用户画像-BD0302");
		historyTypeMap.put(1028, "用户画像-BD0303");
		historyTypeMap.put(1029, "用户画像-BD0304");
		historyTypeMap.put(1030, "用户画像-BD0305");
		historyTypeMap.put(1031, "用户画像-BE0495");
		historyTypeMap.put(1032, "用户画像-BF0094");
		historyTypeMap.put(1033, "用户画像-BF0095");
		historyTypeMap.put(1034, "用户画像-BF0096");
		historyTypeMap.put(1035, "用户画像-BF0091");
		historyTypeMap.put(1036, "用户画像-BF0092");
		historyTypeMap.put(1037, "用户画像-BF0093");
		historyTypeMap.put(1038, "用户画像-BF0099");
		historyTypeMap.put(1039, "用户画像-BF0106");
		historyTypeMap.put(1040, "用户画像-BF0102");
		historyTypeMap.put(1041, "用户画像-BF0107");
		historyTypeMap.put(1042, "用户画像-CA0332");
		historyTypeMap.put(1043, "用户画像-CA0335");
		historyTypeMap.put(1044, "用户画像-CA0334");
		historyTypeMap.put(1045, "用户画像-CA0331");
		historyTypeMap.put(1046, "用户画像-CA0325");
		historyTypeMap.put(1047, "用户画像-CA0328");
		historyTypeMap.put(1048, "用户画像-CA0326");
		historyTypeMap.put(1049, "用户画像-CA0329");
		historyTypeMap.put(1050, "用户画像-CB0282");
		historyTypeMap.put(1051, "用户画像-CB0283");
		historyTypeMap.put(1052, "用户画像-CB0284");
		historyTypeMap.put(1053, "用户画像-CB0279");
		historyTypeMap.put(1054, "用户画像-CB0280");
		historyTypeMap.put(1055, "用户画像-CB0281");
		historyTypeMap.put(1056, "用户画像-CC0336");
		historyTypeMap.put(1057, "用户画像-CC0337");
		historyTypeMap.put(1058, "用户画像-CC0338");
		historyTypeMap.put(1059, "用户画像-CC0339");
		historyTypeMap.put(1060, "用户画像-CC0340");
		historyTypeMap.put(1061, "用户画像-CC0341");
		historyTypeMap.put(1062, "用户画像-CC0342");
		historyTypeMap.put(1063, "用户画像-CC0343");
		historyTypeMap.put(1064, "用户画像-CC0344");
		historyTypeMap.put(1065, "用户画像-CC0345");
		historyTypeMap.put(1066, "用户画像-CC0346");
		historyTypeMap.put(1067, "用户画像-CC0347");
		historyTypeMap.put(1068, "用户画像-CD0307");
		historyTypeMap.put(1069, "用户画像-CD0308");
		historyTypeMap.put(1070, "用户画像-CD0313");
		historyTypeMap.put(1071, "用户画像-CD0314");
		historyTypeMap.put(1072, "用户画像-CD0316");
		historyTypeMap.put(1073, "用户画像-CD0317");
		historyTypeMap.put(1074, "用户画像-CD0319");
		historyTypeMap.put(1075, "用户画像-CD0320");
		historyTypeMap.put(1076, "用户画像-DA0110");
		historyTypeMap.put(1077, "用户画像-DA0018");
		historyTypeMap.put(1078, "用户画像-DA0021");
		historyTypeMap.put(1079, "用户画像-DA0024");
		historyTypeMap.put(1080, "用户画像-DB0212");
		historyTypeMap.put(1081, "用户画像-DB0215");
		historyTypeMap.put(1082, "用户画像-DC0276");
		historyTypeMap.put(1083, "用户画像-DC0277");
		historyTypeMap.put(1084, "用户画像-DC0278");
		historyTypeMap.put(1085, "用户画像-DD0294");
		historyTypeMap.put(1086, "用户画像-DD0295");
		historyTypeMap.put(1087, "用户画像-DD0296");
		historyTypeMap.put(1088, "用户画像-DD0291");
		historyTypeMap.put(1089, "用户画像-DD0292");
		historyTypeMap.put(1090, "用户画像-DD0293");
		historyTypeMap.put(1091, "用户画像-DE0051");
		historyTypeMap.put(1092, "用户画像-DE0013");
		historyTypeMap.put(1093, "用户画像-DE0014");
		historyTypeMap.put(1094, "用户画像-DE0015");
		historyTypeMap.put(1095, "用户画像-DE0016");
		historyTypeMap.put(1096, "用户画像-DE0114");
		historyTypeMap.put(1097, "用户画像-DE0115");
		historyTypeMap.put(1098, "用户画像-DE0116");
		historyTypeMap.put(1099, "用户画像-DE0117");
		historyTypeMap.put(1101, "用户画像-DE0118");
		historyTypeMap.put(1102, "用户画像-DE0119");
		historyTypeMap.put(1103, "用户画像-DF0145");
		historyTypeMap.put(1104, "用户画像-DF0146");
		historyTypeMap.put(1105, "用户画像-DF0147");
		historyTypeMap.put(1106, "用户画像-DF0148");
		historyTypeMap.put(1107, "用户画像-DF0149");
		historyTypeMap.put(1108, "用户画像-DF0150");
		historyTypeMap.put(1109, "用户画像-DF0372");
		historyTypeMap.put(1110, "用户画像-DF0373");
		historyTypeMap.put(1111, "用户画像-DF0374");
		historyTypeMap.put(1112, "用户画像-EA0178");
		historyTypeMap.put(1113, "用户画像-EA0179");
		historyTypeMap.put(1114, "用户画像-EB0056");
		historyTypeMap.put(1115, "用户画像-EB0057");
		historyTypeMap.put(1116, "用户画像-EB0059");
		historyTypeMap.put(1117, "用户画像-EB0060");
		historyTypeMap.put(1118, "用户画像-EB0348");
		historyTypeMap.put(1119, "用户画像-EB0349");
		historyTypeMap.put(1120, "用户画像-EB0350");
		historyTypeMap.put(1121, "用户画像-EB0351");
		historyTypeMap.put(1122, "用户画像-EB0352");
		historyTypeMap.put(1123, "用户画像-EB0353");
		historyTypeMap.put(1124, "用户画像-EB0052");
		historyTypeMap.put(1125, "用户画像-EB0445");
		historyTypeMap.put(1126, "用户画像-EB0446");
		historyTypeMap.put(1127, "用户画像-EB0448");
		historyTypeMap.put(1128, "用户画像-EB0449");
		historyTypeMap.put(1129, "用户画像-EB0253");
		historyTypeMap.put(1130, "用户画像-EB0254");
		historyTypeMap.put(1131, "用户画像-EB0256");
		historyTypeMap.put(1132, "用户画像-EB0257");
		historyTypeMap.put(1133, "用户画像-EB0259");
		historyTypeMap.put(1134, "用户画像-EB0260");
		historyTypeMap.put(1135, "用户画像-EB0262");
		historyTypeMap.put(1136, "用户画像-EB0263");
		historyTypeMap.put(1137, "用户画像-EB0265");
		historyTypeMap.put(1138, "用户画像-EB0266");
		historyTypeMap.put(1139, "用户画像-EB0268");
		historyTypeMap.put(1140, "用户画像-EB0269");
		historyTypeMap.put(1141, "用户画像-EB0241");
		historyTypeMap.put(1142, "用户画像-EB0242");
		historyTypeMap.put(1143, "用户画像-EB0244");
		historyTypeMap.put(1144, "用户画像-EB0245");
		historyTypeMap.put(1145, "用户画像-EB0235");
		historyTypeMap.put(1146, "用户画像-EB0236");
		historyTypeMap.put(1147, "用户画像-EB0238");
		historyTypeMap.put(1148, "用户画像-EB0239");
		historyTypeMap.put(1149, "用户画像-EB0223");
		historyTypeMap.put(1150, "用户画像-EB0224");
		historyTypeMap.put(1151, "用户画像-EB0226");
		historyTypeMap.put(1152, "用户画像-EB0227");
		historyTypeMap.put(1153, "用户画像-EB0229");
		historyTypeMap.put(1154, "用户画像-EB0230");
		historyTypeMap.put(1155, "用户画像-EB0232");
		historyTypeMap.put(1156, "用户画像-EB0233");
		historyTypeMap.put(1157, "用户画像-EB0199");
		historyTypeMap.put(1158, "用户画像-EB0200");
		historyTypeMap.put(1159, "用户画像-EB0193");
		historyTypeMap.put(1160, "用户画像-EB0194");
		historyTypeMap.put(1161, "用户画像-EB0196");
		historyTypeMap.put(1162, "用户画像-EB0197");
		historyTypeMap.put(1163, "用户画像-EB0187");
		historyTypeMap.put(1164, "用户画像-EB0188");
		historyTypeMap.put(1165, "用户画像-EB0190");
		historyTypeMap.put(1166, "用户画像-EB0191");
		historyTypeMap.put(1167, "用户画像-EB0181");
		historyTypeMap.put(1168, "用户画像-EB0182");
		historyTypeMap.put(1169, "用户画像-EB0184");
		historyTypeMap.put(1170, "用户画像-EB0185");
		historyTypeMap.put(1171, "用户画像-EB0391");
		historyTypeMap.put(1172, "用户画像-EB0392");
		historyTypeMap.put(1173, "用户画像-EB0394");
		historyTypeMap.put(1174, "用户画像-EB0395");
		historyTypeMap.put(1175, "用户画像-EB0397");
		historyTypeMap.put(1176, "用户画像-EB0398");
		historyTypeMap.put(1177, "用户画像-EB0400");
		historyTypeMap.put(1178, "用户画像-EB0401");
		historyTypeMap.put(1179, "用户画像-EB0403");
		historyTypeMap.put(1180, "用户画像-EB0404");
		historyTypeMap.put(1181, "用户画像-EB0406");
		historyTypeMap.put(1182, "用户画像-EB0407");
		historyTypeMap.put(1183, "用户画像-EB0409");
		historyTypeMap.put(1184, "用户画像-EB0410");
		historyTypeMap.put(1185, "用户画像-EB0412");
		historyTypeMap.put(1186, "用户画像-EB0413");
		historyTypeMap.put(1187, "用户画像-EB0415");
		historyTypeMap.put(1188, "用户画像-EB0416");
		historyTypeMap.put(1189, "用户画像-EB0418");
		historyTypeMap.put(1190, "用户画像-EB0419");
		historyTypeMap.put(1191, "用户画像-EB0421");
		historyTypeMap.put(1192, "用户画像-EB0422");
		historyTypeMap.put(1193, "用户画像-EB0424");
		historyTypeMap.put(1194, "用户画像-EB0425");
		historyTypeMap.put(1195, "用户画像-EB0427");
		historyTypeMap.put(1196, "用户画像-EB0428");
		historyTypeMap.put(1197, "用户画像-EB0430");
		historyTypeMap.put(1198, "用户画像-EB0431");
		historyTypeMap.put(1199, "用户画像-EB0433");
		historyTypeMap.put(1200, "用户画像-EB0434");
		historyTypeMap.put(1201, "用户画像-EB0436");
		historyTypeMap.put(1202, "用户画像-EB0437");
		historyTypeMap.put(1203, "用户画像-EB0439");
		historyTypeMap.put(1204, "用户画像-EB0440");
		historyTypeMap.put(1205, "用户画像-EB0442");
		historyTypeMap.put(1206, "用户画像-EB0443");
		historyTypeMap.put(1207, "用户画像-EB0451");
		historyTypeMap.put(1208, "用户画像-EB0452");
		historyTypeMap.put(1209, "用户画像-EB0454");
		historyTypeMap.put(1210, "用户画像-EB0455");
		historyTypeMap.put(1211, "用户画像-EB0457");
		historyTypeMap.put(1212, "用户画像-EB0458");
		historyTypeMap.put(1213, "用户画像-EB0460");
		historyTypeMap.put(1214, "用户画像-EB0461");
		historyTypeMap.put(1215, "用户画像-FA0470");
		historyTypeMap.put(1216, "用户画像-FA0471");
		historyTypeMap.put(1217, "用户画像-FB0472");
		historyTypeMap.put(1218, "用户画像-FB0473");

		// 二期新增
		historyTypeMap.put(1219, "用户画像-AA0502");
		historyTypeMap.put(1220, "用户画像-AA0503");
		historyTypeMap.put(1221, "用户画像-AA0504");
		historyTypeMap.put(1222, "用户画像-AA0656");
		historyTypeMap.put(1223, "用户画像-AA0657");
		historyTypeMap.put(1224, "用户画像-AA0658");
		historyTypeMap.put(1225, "用户画像-AA0674");
		historyTypeMap.put(1226, "用户画像-AA0659");
		historyTypeMap.put(1227, "用户画像-AA0477");
		historyTypeMap.put(1228, "用户画像-AB0660");
		historyTypeMap.put(1229, "用户画像-AB0661");
		historyTypeMap.put(1230, "用户画像-AB0517");
		historyTypeMap.put(1231, "用户画像-AC0505");
		historyTypeMap.put(1232, "用户画像-AC0506");
		historyTypeMap.put(1233, "用户画像-AC0507");
		historyTypeMap.put(1234, "用户画像-AC0508");
		historyTypeMap.put(1235, "用户画像-AC0509");
		historyTypeMap.put(1236, "用户画像-AC0510");
		historyTypeMap.put(1237, "用户画像-AC0511");
		historyTypeMap.put(1238, "用户画像-AC0512");
		historyTypeMap.put(1239, "用户画像-AC0513");
		historyTypeMap.put(1240, "用户画像-BA0534");
		historyTypeMap.put(1241, "用户画像-BA0535");
		historyTypeMap.put(1242, "用户画像-BA0536");
		historyTypeMap.put(1243, "用户画像-BA0560");
		historyTypeMap.put(1244, "用户画像-BA0549");
		historyTypeMap.put(1245, "用户画像-BA0550");
		historyTypeMap.put(1246, "用户画像-BA0551");
		historyTypeMap.put(1247, "用户画像-BA0563");
		historyTypeMap.put(1248, "用户画像-BA0544");
		historyTypeMap.put(1249, "用户画像-BA0567");
		historyTypeMap.put(1250, "用户画像-BA0547");
		historyTypeMap.put(1251, "用户画像-BA0548");
		historyTypeMap.put(1252, "用户画像-BA0568");
		historyTypeMap.put(1253, "用户画像-BA0569");
		historyTypeMap.put(1254, "用户画像-BA0570");
		historyTypeMap.put(1255, "用户画像-BB0537");
		historyTypeMap.put(1256, "用户画像-BB0538");
		historyTypeMap.put(1257, "用户画像-BB0539");
		historyTypeMap.put(1258, "用户画像-BB0561");
		historyTypeMap.put(1259, "用户画像-BB0552");
		historyTypeMap.put(1260, "用户画像-BB0553");
		historyTypeMap.put(1261, "用户画像-BB0554");
		historyTypeMap.put(1262, "用户画像-BB0564");
		historyTypeMap.put(1263, "用户画像-BB0541");
		historyTypeMap.put(1264, "用户画像-BB0542");
		historyTypeMap.put(1265, "用户画像-BB0543");
		historyTypeMap.put(1266, "用户画像-BB0566");
		historyTypeMap.put(1267, "用户画像-BC0540");
		historyTypeMap.put(1268, "用户画像-BC0562");
		historyTypeMap.put(1269, "用户画像-BC0545");
		historyTypeMap.put(1270, "用户画像-BC0546");
		historyTypeMap.put(1271, "用户画像-BC0565");
		historyTypeMap.put(1272, "用户画像-BD0573");
		historyTypeMap.put(1273, "用户画像-BD0574");
		historyTypeMap.put(1274, "用户画像-BD0575");
		historyTypeMap.put(1275, "用户画像-BD0576");
		historyTypeMap.put(1276, "用户画像-BD0577");
		historyTypeMap.put(1277, "用户画像-BD0578");
		historyTypeMap.put(1278, "用户画像-BD0579");
		historyTypeMap.put(1279, "用户画像-BD0580");
		historyTypeMap.put(1280, "用户画像-BD0581");
		historyTypeMap.put(1281, "用户画像-BD0582");
		historyTypeMap.put(1282, "用户画像-BD0583");
		historyTypeMap.put(1283, "用户画像-BD0584");
		historyTypeMap.put(1284, "用户画像-BD0585");
		historyTypeMap.put(1285, "用户画像-BE0518");
		historyTypeMap.put(1286, "用户画像-BE0519");
		historyTypeMap.put(1287, "用户画像-BE0520");
		historyTypeMap.put(1288, "用户画像-BE0521");
		historyTypeMap.put(1289, "用户画像-BE0522");
		historyTypeMap.put(1290, "用户画像-BE0523");
		historyTypeMap.put(1291, "用户画像-BE0524");
		historyTypeMap.put(1292, "用户画像-BE0525");
		historyTypeMap.put(1293, "用户画像-BE0526");
		historyTypeMap.put(1294, "用户画像-BE0527");
		historyTypeMap.put(1295, "用户画像-BE0528");
		historyTypeMap.put(1296, "用户画像-BE0529");
		historyTypeMap.put(1297, "用户画像-BE0530");
		historyTypeMap.put(1298, "用户画像-BE0531");
		historyTypeMap.put(1299, "用户画像-BE0532");
		historyTypeMap.put(1300, "用户画像-BE0533");
		historyTypeMap.put(1301, "用户画像-BF0683");
		historyTypeMap.put(1302, "用户画像-BG0586");
		historyTypeMap.put(1303, "用户画像-BG0587");
		historyTypeMap.put(1304, "用户画像-BG0588");
		historyTypeMap.put(1305, "用户画像-BG0589");
		historyTypeMap.put(1306, "用户画像-BG0590");
		historyTypeMap.put(1307, "用户画像-BG0591");
		historyTypeMap.put(1308, "用户画像-BG0592");
		historyTypeMap.put(1309, "用户画像-BG0593");
		historyTypeMap.put(1310, "用户画像-BG0594");
		historyTypeMap.put(1311, "用户画像-BG0595");
		historyTypeMap.put(1312, "用户画像-BG0596");
		historyTypeMap.put(1313, "用户画像-BG0597");
		historyTypeMap.put(1314, "用户画像-BG0598");
		historyTypeMap.put(1315, "用户画像-BG0599");
		historyTypeMap.put(1316, "用户画像-BG0600");
		historyTypeMap.put(1317, "用户画像-CB0629");
		historyTypeMap.put(1318, "用户画像-CB0630");
		historyTypeMap.put(1319, "用户画像-CB0631");
		historyTypeMap.put(1320, "用户画像-CB0632");
		historyTypeMap.put(1321, "用户画像-CB0633");
		historyTypeMap.put(1322, "用户画像-CB0634");
		historyTypeMap.put(1323, "用户画像-CB0635");
		historyTypeMap.put(1324, "用户画像-DA0555");
		historyTypeMap.put(1325, "用户画像-DA0556");
		historyTypeMap.put(1326, "用户画像-DA0557");
		historyTypeMap.put(1327, "用户画像-DA0558");
		historyTypeMap.put(1328, "用户画像-DA0515");
		historyTypeMap.put(1329, "用户画像-DA0559");
		historyTypeMap.put(1330, "用户画像-DA0648");
		historyTypeMap.put(1331, "用户画像-DA0646");
		historyTypeMap.put(1332, "用户画像-DA0615");
		historyTypeMap.put(1333, "用户画像-DA0616");
		historyTypeMap.put(1334, "用户画像-DA0617");
		historyTypeMap.put(1335, "用户画像-DB0677");
		historyTypeMap.put(1336, "用户画像-DB0618");
		historyTypeMap.put(1337, "用户画像-DB0622");
		historyTypeMap.put(1338, "用户画像-DB0623");
		historyTypeMap.put(1339, "用户画像-DB0624");
		historyTypeMap.put(1340, "用户画像-DB0625");
		historyTypeMap.put(1341, "用户画像-DB0636");
		historyTypeMap.put(1342, "用户画像-DB0637");
		historyTypeMap.put(1343, "用户画像-DB0640");
		historyTypeMap.put(1344, "用户画像-DB0641");
		historyTypeMap.put(1345, "用户画像-DB0642");
		historyTypeMap.put(1346, "用户画像-DB0643");
		historyTypeMap.put(1347, "用户画像-DC0626");
		historyTypeMap.put(1348, "用户画像-DC0627");
		historyTypeMap.put(1349, "用户画像-DC0628");
		historyTypeMap.put(1350, "用户画像-DC0620");
		historyTypeMap.put(1351, "用户画像-DC0621");
		historyTypeMap.put(1352, "用户画像-DE0572");
		historyTypeMap.put(1353, "用户画像-DE0571");
		historyTypeMap.put(1354, "用户画像-DE0601");
		historyTypeMap.put(1355, "用户画像-DE0602");
		historyTypeMap.put(1356, "用户画像-DE0603");
		historyTypeMap.put(1357, "用户画像-DE0604");
		historyTypeMap.put(1358, "用户画像-DE0514");
		historyTypeMap.put(1359, "用户画像-DF0605");
		historyTypeMap.put(1360, "用户画像-DF0606");
		historyTypeMap.put(1361, "用户画像-DF0607");
		historyTypeMap.put(1362, "用户画像-DF0608");
		historyTypeMap.put(1363, "用户画像-DF0609");
		historyTypeMap.put(1364, "用户画像-DF0610");
		historyTypeMap.put(1365, "用户画像-DF0611");
		historyTypeMap.put(1366, "用户画像-DF0612");
		historyTypeMap.put(1367, "用户画像-DF0613");
		historyTypeMap.put(1368, "用户画像-DF0614");
		historyTypeMap.put(1369, "用户画像-EA0668");
		historyTypeMap.put(1370, "用户画像-EA0666");
		historyTypeMap.put(1371, "用户画像-EA0669");
		historyTypeMap.put(1372, "用户画像-EA0667");
		historyTypeMap.put(1373, "用户画像-EA0645");
		historyTypeMap.put(1374, "用户画像-EA0650");
		historyTypeMap.put(1375, "用户画像-EB0682");
		historyTypeMap.put(1376, "用户画像-EB0662");
		historyTypeMap.put(1377, "用户画像-EB0672");
		historyTypeMap.put(1378, "用户画像-EB0673");
		historyTypeMap.put(1379, "用户画像-EB0678");
		historyTypeMap.put(1380, "用户画像-EB0679");
		historyTypeMap.put(1381, "用户画像-EB0680");
		historyTypeMap.put(1382, "用户画像-EB0681");
		historyTypeMap.put(1383, "用户画像-EB0644");
		historyTypeMap.put(1384, "用户画像-EB0651");
		historyTypeMap.put(1385, "用户画像-EB0675");
		historyTypeMap.put(1386, "用户画像-EB0676");
		historyTypeMap.put(1387, "用户画像-FA0663");
		historyTypeMap.put(1388, "用户画像-FA0516");
		historyTypeMap.put(1389, "用户画像-FB0664");
		historyTypeMap.put(1390, "用户画像-FB0652");
		historyTypeMap.put(1391, "用户画像-FB0665");
		historyTypeMap.put(1392, "用户画像-FB0653");
		historyTypeMap.put(1393, "用户画像-FB0654");
		historyTypeMap.put(1394, "用户画像-FB0655");

		/**************** 用户画像-end ******************/

	}

	public static String historyTypeToMsg(Integer falg) {
		if (falg != null) {
			return historyTypeMap.get(falg);
		} else {
			return "";
		}
	}
}
