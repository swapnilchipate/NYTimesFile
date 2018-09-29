package sat.heady.com.nytimes.Utils;

/**
 * Created by Admin: Yang on 29-09-2018.
 */

public class Utils {

    public static String covertToRelativeTime(String timestamp){
        try {
           /* Timestamp times = Timestamp.valueOf( timestamp );
            Date d = new Date(times.getTime());
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'");
            String v=f.format(d);
            Timestamp timestamp1 = Timestamp.valueOf( v );
            Date date = new Date(timestamp1.getTime());
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date2=s.format(date);
            Timestamp timestamp2 =  Timestamp.valueOf(date2 );
            Long giventimeStamp = timestamp2.getTime();
            Long currentTimeStamp = System.currentTimeMillis() / 1000;
            return DateUtils.getRelativeTimeSpanString(giventimeStamp,currentTimeStamp,DateUtils.MINUTE_IN_MILLIS)+"";*/
           return timestamp;
        }catch (Exception e){
            return "check"+e.getMessage();
        }
    }
}
