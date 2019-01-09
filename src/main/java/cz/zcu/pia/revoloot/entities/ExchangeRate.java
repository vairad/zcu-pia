package cz.zcu.pia.revoloot.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Entitní objekt kurzu v systému
 *
 * @author Radek Vais
 */
@Entity
@Table(name = TableConfig.TABLE_EXCHANGES)
public class ExchangeRate extends BaseEntity {

    /**
     * Měna ze které se převádí
     */
    private Currency fromCur;

    /**
     * Měna do které se převádí
     */
    private Currency toCur;

    /**
     * Od jakého data je kurz platný
     */
    private Date validFrom;

    /**
     * Hodnota kurzu ... koeficient pro převod
     * from*rate = to
     */
    private Double rate;

    public ExchangeRate() {
        validFrom = new Date();
    }


    /**
     * Měna ze které se převádí
     *
     * @return zdrojová měna
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Currency getFromCur() {
        return fromCur;
    }

    /**
     * Nastaví zdrojovou měnu
     *
     * @param from zdrojová měna
     */
    public void setFromCur(Currency from) {
        this.fromCur = from;
    }

    /**
     * Měna do které se převádí
     *
     * @return cílová měna
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Currency getToCur() {
        return toCur;
    }

    /**
     * Nastaví cílovou měnu
     *
     * @param to cílová měna
     */
    public void setToCur(Currency to) {
        this.toCur = to;
    }

    /**
     * Začátek platnosti kurzu
     * výchozí hodnota je čas konstrukce objektu
     *
     * @return začátek platnosti kurzu
     */
    @Column(nullable = false)
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * Nastaví začátek platnosti kurzu
     *
     * @param validFrom začátek platnosti kurzu
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Převodní kurz ze zdrojové měny do cílové
     * from*rate = to
     *
     * @return směnný kurz
     */
    @Column(nullable = false)
    public Double getRate() {
        return rate;
    }

    /**
     * Nastav převodní kurz tak aby platilo
     * from*rate = to
     *
     * @param rate směnný kurz
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }


    /**
     * To string ve formátu
     * CZK->GBP = 0.0
     *
     * @return textová reprezentace kurzu
     */
    @Override
    public String toString() {
        return fromCur + "->" + toCur +
                " = " + rate;
    }
}
