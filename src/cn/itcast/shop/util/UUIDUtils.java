package cn.itcast.shop.util;

import java.util.UUID;

// ��������ַ����Ĺ�����
public class UUIDUtils {
	
	public static String getUUID() {
		// replace�����uuid�м�ĺ��
		return UUID.randomUUID().toString().replace("-", "");
	}
}
