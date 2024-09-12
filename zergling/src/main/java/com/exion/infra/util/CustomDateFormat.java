package com.exion.infra.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomDateFormat {
	public static String addNowTimeString(String date) {
		LocalDateTime localDateTime = LocalDateTime.now();
//		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern(Constants.TIME_FOROMAT_BASIC));
//		return date + " " + localDateTimeString;
		return "";
	}
	public static String add00TimeStarting(String date) {
		return date + "00:00:00";
	}
	public static String add59TimeStarting(String date) {
		return date + "23:59:59";
	}
	public static String dateToString(Date dateParam) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(dateParam);
		return date;
	}
	public static String dateTimeToString(Date dateTimeParam) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = simpleDateFormat.format(dateTimeParam);
		return dateTime;
	}
}
