package cz.zcu.pia.revoloot.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CzechFormatter implements IDateFormatter {

    @Override
    public String dateTimeFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    @Override
    public String dateFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy");
        return dateFormat.format(date);
    }
}
