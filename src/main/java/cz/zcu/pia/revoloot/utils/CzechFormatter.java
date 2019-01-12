package cz.zcu.pia.revoloot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.OneToMany;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("czechFormatter")
public class CzechFormatter implements IDateFormatter {

    private Logger logger = LoggerFactory.getLogger(CzechFormatter.class.getName());

    private final String formDateTimeFormat = "yyyy-MM-dd'T'HH:mm";
    private final String formDateFormat = "yyyy-MM-dd";

    private final String userDateTimeFormat = "dd. MM. yyyy HH:mm:ss";
    private final String userDateFormat = "dd. MM. yyyy";

    @Override
    public String dateTimeFormat(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(userDateTimeFormat);
        return dateFormat.format(date);
    }

    @Override
    public String dateFormat(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(userDateFormat);
        return dateFormat.format(date);
    }

    @Override
    public String datetimeToForm(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(formDateTimeFormat);
        return dateFormat.format(date);
    }

    @Override
    public String dateToForm(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(formDateFormat);
        return dateFormat.format(date);
    }

    @Override
    public Date formToDate(String dateRepresentation){
        try {
            Date date = new SimpleDateFormat(formDateFormat).parse(dateRepresentation);
            return date;
        } catch (ParseException | NullPointerException e) {
            logger.warn("date could not be parsed");
        }
        return null;
    }

    @Override
    public Date formToDateTime(String dateRepresentation){
        try {
            Date date = new SimpleDateFormat(formDateTimeFormat).parse(dateRepresentation);
            return date;
        } catch (ParseException | NullPointerException e) {
            logger.warn("date could not be parsed");
        }
        return null;
    }


}
