import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeWoker {
	
	public static String getStartandEndtimeString(int week){
		week = week + 1;
		Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.WEEK_OF_YEAR,week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        String start = format.format(cal.getTime());
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = format.format(cal.getTime());
        return start + " 到 " + end;
	}
	
	public static String getLastDaybyYearandMonth(int year, int month){
		Calendar cal = Calendar.getInstance();
		  // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        String last = format.format(lastDate.getTime());

		return last;
	}
	
	public static void main(String[] args){
//		System.out.println(getFirstDaybyYearandMonth(2012,2));
	}

}
