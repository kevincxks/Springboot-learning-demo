package springboot.shirodemo.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static Date addHours(Date date,Integer hour){
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.HOUR,2);
        return new Date(date.getTime()+calendar.getTimeInMillis());
    }
}
