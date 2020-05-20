package resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class fechasConverter {
	
	private static final String dateFormart = "yyyy-MM-dd";
	private static final String hourFormart = "HH:mm";
	
	public static String fechaToString(Date f) { //return Date into String in format year-month-day (sql format)
		Calendar cal = Calendar.getInstance();
        cal.setTime(f);
        int y = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        
        sb.append(y);
        sb.append("-");
        
        if (month < 10) sb.append(0).append(month);
        else sb.append(month);
        
        sb.append("-");
        
        if (day < 10) sb.append(0).append(day);
        else sb.append(day);
        
        return sb.toString();
	}
	public static String horaToString(Date h) { //return Date into String in format hour:minute (sql format)
		Calendar cal = Calendar.getInstance();
        cal.setTime(h);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        StringBuilder sb = new StringBuilder();
        
        if (hour < 10) sb.append(0).append(hour);
        else sb.append(hour);
        
        sb.append(":");
        
        if (min < 10) sb.append(0).append(min);
        else sb.append(min);

        return sb.toString();
	}
	public static Date StringFechaToDate(String f) { //convert string into date
		DateFormat format = new SimpleDateFormat(dateFormart);
		try {
			Date d= format.parse(f);
			
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public static Date StringHoraToDate(String h) { //convert string into date(hour)
		DateFormat formatter = new SimpleDateFormat(hourFormart);
        try {
			Date hour = formatter.parse(h);
			return hour;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

}
