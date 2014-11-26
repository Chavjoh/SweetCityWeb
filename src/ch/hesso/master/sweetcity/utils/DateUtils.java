package ch.hesso.master.sweetcity.utils;

import java.util.Calendar;
import java.util.Date;
import ch.hesso.master.sweetcity.Project;

public class DateUtils {

	public static final long ONE_MINUTE_IN_MILLIS = 60000;
	
	public static Date createDate(int year, int month, int day) throws Exception {
		if (day < 0 || day > 31 || month < 0 || month > 31 || year > 2100 || year < 2000) {
			throw new Exception("Invalid date");
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(Project.TIMEZONE);
		calendar.clear();
		calendar.set(year, month - 1, day, 0, 0);
		return calendar.getTime();
	}

	public static Date createDate(String year, String month, String day) throws Exception {
		return createDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
	}

	public static Date now() {
		return new Date();
	}

	public static boolean between(Date dateToCompare, Date dateMin, Date dateMax) {
		return dateToCompare.after(dateMin) && dateToCompare.before(dateMax);
	}

	// http://stackoverflow.com/questions/2517709/comparing-two-dates-to-see-if-they-are-in-the-same-day
	public static boolean sameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTimeZone(Project.TIMEZONE);
		cal2.setTimeZone(Project.TIMEZONE);
		cal1.setTime(date1);
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
			&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}
}
