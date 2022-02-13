package utils;

/**
 * Created by Leo.
 * User: KBS
 * Date: 10/5/2021
 * Time: 오전 9:26
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public String transform(Date dbdate) {

        SimpleDateFormat format1 = null;

        format1 = new SimpleDateFormat("yyyy-MM-dd");



        return format1.format(dbdate);
    }
}
