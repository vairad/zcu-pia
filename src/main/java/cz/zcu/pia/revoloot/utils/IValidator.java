package cz.zcu.pia.revoloot.utils;

import cz.zcu.pia.revoloot.entities.Gender;

import java.util.Date;

/**
 * Služba pro validaci polí objektů
 */
public interface IValidator {

    /**
     * Ověří, zda pole je prázdné (nebo null)
     *
     * @param field pole k ověření
     * @return true pokud obsahuje nenulovou hodnotu ve smyslu datového typu
     */
    boolean isEmptyField(String field);

    /**
     * Ověří, zda pole je prázdné (nebo null)
     *
     * @param field pole k ověření
     * @return true pokud obsahuje nenulovou hodnotu ve smyslu datového typu
     */
    boolean isEmptyField(Date field);

    /**
     * Ověří, zda pole je prázdné (nebo null)
     *
     * @param field pole k ověření
     * @return true pokud obsahuje nenulovou hodnotu ve smyslu datového typu
     */
    boolean isEmptyField(Long field);

    /**
     * Ověří, zda pole je prázdné (nebo null)
     *
     * @param field pole k ověření
     * @return true pokud obsahuje nenulovou hodnotu ve smyslu datového typu
     */
    boolean isEmptyField(Integer field);

    /**
     * Ověří, zda pole je prázdné (nebo null)
     *
     * @param field pole k ověření
     * @return true pokud obsahuje nenulovou hodnotu ve smyslu datového typu
     */
    boolean isEmptyField(Double field);

    /**
     * Metoda ověří, zda datum narození odpovídá rodnému číslu dle známých parametrů rodného čísla
     * <p>
     * ženám se přičítá 50 k měsíci narození a rodné číslo je delitelné 11
     *
     * @param birthDate datum narození
     * @param gender    pohlaví
     * @param personID  rodné číslo
     * @return true pokud číslo odpovídá datu narození
     */
    boolean checkBirthAgainstPersonID(Date birthDate, Gender gender, long personID);

}
