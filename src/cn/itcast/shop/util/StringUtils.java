package cn.itcast.shop.util;

public class StringUtils {

	public static void main(String[] args) {
		String str1 = null;
		String str2 = "";
		String str3 = "   ";
		System.out.println(org.apache.commons.lang3.StringUtils.isEmpty(str1));// true
		System.out.println(org.apache.commons.lang3.StringUtils.isEmpty(str2));// true
		System.out.println(org.apache.commons.lang3.StringUtils.isEmpty(str3));// false
		
		System.out.println(org.apache.commons.lang3.StringUtils.isBlank(str1));// true
		System.out.println(org.apache.commons.lang3.StringUtils.isBlank(str2));// true
		System.out.println(org.apache.commons.lang3.StringUtils.isBlank(str3));// true
	}
}
