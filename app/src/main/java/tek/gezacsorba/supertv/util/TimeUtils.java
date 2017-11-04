package tek.gezacsorba.supertv.util;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by geza on 11/4/17.
 */

public class TimeUtils {

    private static final String TAG = TimeUtils.class.getSimpleName();

    public static String getHoursAndMinutes(String time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD kk:mm:ss ", Locale.getDefault());
        try {
            Date date = dateFormat.parse(time.replace("T", " ").replace("Z", " "));
            SimpleDateFormat outFormat = new SimpleDateFormat("kk:mm", Locale.getDefault());
            return outFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "getHoursAndMinutes received a bad formatted time");
            return time;
        }

    }
}
