package demo.orbitsys.com.fmgmdemo.util;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityOfActivity {


    public static String convertDate_MMddyyyy_to_ddMMMyyyy(TextView mPreferredDateTv, String date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        SimpleDateFormat formatDateTime = new SimpleDateFormat("dd-MMM-yyyy");
        Date targetDate = null;

        try {
            targetDate = simpleDateFormat.parse(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        String dateString = formatDateTime.format(targetDate);
        mPreferredDateTv.setText(dateString);
        return dateString;
    }

    public static String converRangedate(String date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

        SimpleDateFormat formatDateTime = new SimpleDateFormat("dd-MM-yyyy");
        Date targetDate = null;

        try {
            targetDate = simpleDateFormat.parse(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        String dateString = formatDateTime.format(targetDate);
       /* Date finaldate =null;
        try {
            finaldate = formatDateTime.parse(dateString);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }*/
        return dateString;
    }


    public static String convertDate_MMddyyyy_to_ddMMMyyyyy(String date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        SimpleDateFormat formatDateTime = new SimpleDateFormat("dd-MMM-yyyy");
        Date targetDate = null;

        try {
            targetDate = simpleDateFormat.parse(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        String dateString = formatDateTime.format(targetDate);

        return dateString;
    }

}
