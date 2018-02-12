package com.ckey.shijing.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 加密用的东西
 * @author shuiyu
 *
 */
public class Md5Utils {
	private static long previous;

	/**
	 * 用MD5加密密码
	 * 
	 * @param msg
	 *            要加密的字符串
	 * @return
	 */
	public synchronized static String EncodePass(String msg) {
		try {

			long current = System.currentTimeMillis();
			if (current == previous)
				current++;
			previous = current;
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msg.getBytes());
			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 用MD5+时间信息加密Token
	 * 
	 * @param msg
	 *            要加密的信息
	 * @return
	 */
	public synchronized static String EncodeToken(String msg) {
		try {

			long current = System.currentTimeMillis();
			if (current == previous)
				current++;
			previous = current;
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msg.getBytes());
			byte now[] = (new Long(current)).toString().getBytes();
			md.update(now);
			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 用MD5加密密码
	 * 
	 * @param msg
	 *            要加密的字符串
	 * @return
	 */
	public static String EncodePass(int msg) {
		return EncodePass(String.valueOf(msg));
	}

	/**
	 * 用MD5+时间信息加密Token
	 * 
	 * @param msg
	 *            要加密的信息
	 * @return
	 */
	public static String EncodeToken(int msg) {
		return EncodeToken(String.valueOf(msg));
	}

	private static String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}

}
