package cz.zcu.pia.revoloot.utils;

import java.util.Date;

/**
 * Komponenta pro jednotné formátování data v aplikaci
 *
 * @author Radek Vais
 */
public interface IDateFormatter {

    /**
     * Vrací lokalizzovaný datum+čas
     *
     * @param date datum k formátu
     * @return datum+čas
     */
    String dateTimeFormat(Date date);

    /**
     * Vrací lokalizzovaný datum
     *
     * @param date datum k formátu
     * @return datum
     */
    String dateFormat(Date date);

    /**
     * Vrací formaát pro form value objektu datetime
     *
     * @param date datum k formátu
     * @return datetime
     */
    String datetimeToForm(Date date);

    /**
     * Vrací formaát pro form value objektu date
     *
     * @param date datum k formátu
     * @return datum
     */
    String dateToForm(Date date);

    /**
     * Metoda parsuje datum te vstupu formulářového elementu date
     *
     * @param dateRepresentation vstupní hodnota datumu
     * @return objekt data
     */
    Date formToDate(String dateRepresentation);

    /**
     * Metoda parsuje datum te vstupu formulářového elementu datetime
     *
     * @param dateRepresentation vstupní hodnota datumu
     * @return objekt data
     */
    Date formToDateTime(String dateRepresentation);
}
