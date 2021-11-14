package com.booklibrary.demo.util;

import com.booklibrary.demo.constant.DateTimeEnum;
import com.google.common.base.Strings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	public static LocalDateTime getDateTime() {
		return LocalDateTime.now(getTimeZone());
	}

	public static LocalDate getDate() {
		return LocalDate.now(getTimeZone());
	}

	public static String getDateTime(LocalDateTime dt) {
		if (dt == null)
			return null;
		return dt.format(getDateTimeFormatter());
	}

	public static String getDate(LocalDate d) {
		return d.format(getDateFormatter());
	}

	public static String getTime(LocalTime t) {
		return t.format(getTimeFormatter());
	}

	public static LocalDateTime getDateTime(String dt) {
		return LocalDateTime.parse(dt,
				DateTimeFormatter.ofPattern(DateTimeEnum.DATE_TIME_PATTERN));
	}

	public static LocalDate getDate(String d) {
		if (Strings.isNullOrEmpty(d))
			return null;
		return LocalDate.parse(d, DateTimeFormatter.ofPattern(DateTimeEnum.DATE_PATTERN));
	}

	public static DateTimeFormatter getDateTimeFormatter() {
		return DateTimeFormatter.ofPattern(DateTimeEnum.DATE_TIME_PATTERN);
	}

	public static DateTimeFormatter getDateFormatter() {
		return DateTimeFormatter.ofPattern(DateTimeEnum.DATE_PATTERN);
	}

	public static DateTimeFormatter getTimeFormatter() {
		return DateTimeFormatter.ofPattern(DateTimeEnum.TIME_PATTERN);
	}

	public static ZoneOffset getTimeZone() {
		return ZoneOffset.of("+1");
	}

}
