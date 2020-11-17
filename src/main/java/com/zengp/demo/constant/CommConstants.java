package com.zengp.demo.constant;
/**
  * 通过常量类
 * @author admin
 *
 */
public class CommConstants {
	// secuid长度
	public static int uidLength = 55;
	// 游标变量
	public static int cursor = 1;
	
	public static String secUid = "sec_uid";
	
	public static String secUidUrl = "?sec_uid=";
	// 中文正则
	public static String REGEX_CHINESE = "[\u4e00-\u9fa5]";
	// 匹配几乎所有符号
	public static String REGEX_SYMBOL = "[\\pP\\p{Punct}]";
	// 逗号
	public static String REGEX_COMMA = "，";
	// 感叹号
	public static String REGEX_EXCLAMATORY_MARK = "！";
	
}
