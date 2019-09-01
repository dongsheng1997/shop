package cn.itcast.shop.util;

import java.util.UUID;

// 生产随机字符串的工具类
public class UUIDUtils {
	
	public static String getUUID() {
		// replace代替掉uuid中间的横杠
		return UUID.randomUUID().toString().replace("-", "");
	}
}
