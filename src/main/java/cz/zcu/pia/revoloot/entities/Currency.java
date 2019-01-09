package cz.zcu.pia.revoloot.entities;

public enum Currency {
    CZK,
    EUR,
    GBP;

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

