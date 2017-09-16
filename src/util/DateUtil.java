package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String format(Date date) {
		return format.format(date);
	}
	public static Date parse(String dateTime) {
		try {
			return format.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
