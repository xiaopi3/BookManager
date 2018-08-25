package com.xiaopi3.util;
	/**
	 * ×Ö·û´®¹¤¾ßÀà
	 * @author PP
	 *
	 */
public class StringUtil {
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if(s==null || "".equals(s.trim())) {
			return true;
		}
		return false;
	}
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñ·Ç¿Õ
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		if(s!=null&&!"".equals(s.trim())) {
			return true;
		}
		return false;
	}
}
