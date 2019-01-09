package cz.zcu.pia.revoloot.utils;

import java.util.Date;

public interface IDateFormatter {

    String dateTimeFormat(Date date);

    String dateFormat(Date date);

    String datetimeToForm(Date date);

    String dateToForm(Date date);
}
