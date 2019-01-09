package cz.zcu.pia.revoloot.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("czechFormatter")
public class CzechFormatter implements IDateFormatter {

    @Override
    public String dateTimeFormat(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    @Override
    public String dateFormat(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy");
        return dateFormat.format(date);
    }

    @Override
    public String datetimeToForm(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return dateFormat.format(date);
    }

    @Override
    public String dateToForm(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

}
