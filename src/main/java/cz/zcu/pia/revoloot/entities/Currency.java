package cz.zcu.pia.revoloot.entities;

/**
 * Seznam měn použitelných v aplikaci
 *
 * @author Radek Vais
 */
public enum Currency {
    CZK,
    EUR,
    GBP;

    /**
     * Z textové reprezentace měny vytvoří objekt číselníku.
     * @param currency textová reprezentace měny například czk
     * @return bjekt číselníku
     * @throws IllegalArgumentException v případě neimplementované měny
     */
    public static Currency fromString(String currency) {
        if (currency.toLowerCase().equals("czk")) {
            return CZK;
        }
        if (currency.toLowerCase().equals("eur")) {
            return EUR;
        }
        if (currency.toLowerCase().equals("gbp")) {
            return GBP;
        }
        throw new IllegalArgumentException("Unsupported currency");
    }
}

