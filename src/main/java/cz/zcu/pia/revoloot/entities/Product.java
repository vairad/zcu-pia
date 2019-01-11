package cz.zcu.pia.revoloot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


/**
 * Entita typu ůčtu v aplikaci
 *
 * @author Radek Vais
 */
@Entity
@Table(name = TableConfig.TABLE_PRODUCTS)
public class Product extends BaseEntity {
    private String name;
    private String shortText;
    private String terms;
    private boolean marketing;
    private boolean available;
    private List<Account> accountsByProduct;

    /**
     * Ve výchozím stavu je produkt dostupný a není marketingově zvýhodněn.
     */
    public Product() {
        available = true;
        marketing = false;
    }

    /**
     * Vrací název produktu
     *
     * @return název produktu
     */
    @Column
    public String getName() {
        return name;
    }

    /**
     * Nastavuje název produktu
     *
     * @param name název produktu
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Vrací krátký marketingový popis produktu
     *
     * @return krátký popis
     */
    @Column
    public String getShortText() {
        return shortText;
    }

    /**
     * Nastavuje krátký marketingový popis
     *
     * @param shortText krátký popis
     */
    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    /**
     * Vrací html značený text podmínek účtu
     *
     * @return podmínky účtu (html tagged)
     */
    @Column(columnDefinition="text")
    public String getTerms() {
        return terms;
    }

    /**
     * Nastavuje text.
     *
     * @param terms podmínky
     */
    public void setTerms(String terms) {
        this.terms = terms;
    }

    /**
     * Příznak, zda je účet marketingově zvýhodněn
     *
     * @return příznak vyšší reklamy
     */
    @Column
    public boolean isMarketing() {
        return marketing;
    }

    /**
     * Nastavuje marketingová příznak
     *
     * @param marketing příznak marketingu
     */
    public void setMarketing(boolean marketing) {
        this.marketing = marketing;
    }

    /**
     * Příznak možnosti sjednat účet.
     *
     * @return možnost vytvářet nové produkty tohoto typu
     */
    @Column
    public boolean isAvailable() {
        return available;
    }

    /**
     * Nastavuje příznak dostupnosti
     *
     * @param available příznak dostupnosti
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Seznam všech účtů daného typu
     *
     * @return seznam účtů daného typu
     */
    @OneToMany(mappedBy = "product")
    public List<Account> getAccountsByProduct() {
        return accountsByProduct;
    }

    /**
     * Metoda nastaví seznam účtů daného typu
     *
     * @param accountsByProduct seznam k nastavení
     */
    public void setAccountsByProduct(List<Account> accountsByProduct) {
        this.accountsByProduct = accountsByProduct;
    }
}
