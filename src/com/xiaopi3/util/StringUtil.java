package com.xiaopi3.util;
	/**
	 * �ַ���������
	 * @author PP
	 *
	 */
public class StringUtil {
	/**
	 * �ж��ַ����Ƿ�Ϊ��
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
	 * �ж��ַ����Ƿ�ǿ�
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
