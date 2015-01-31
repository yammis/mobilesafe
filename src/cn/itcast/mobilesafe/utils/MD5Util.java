package cn.itcast.mobilesafe.utils;

import java.security.MessageDigest;

public class MD5Util {

	public static String encode(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer sb = new StringBuffer();
			for (byte b : result) {
				int number = b & 0xff; // - 1; //╪сян
				String hexCode = Integer.toHexString(number);
				if (hexCode.length() == 1) {
					sb.append("0");
				}
				sb.append(hexCode);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			// can't reach
			return "";
		}
	}
}
