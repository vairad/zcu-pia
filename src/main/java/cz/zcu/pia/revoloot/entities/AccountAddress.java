package cz.zcu.pia.revoloot.entities;

import javax.persistence.Embeddable;

@Embeddable
public class AccountAddress {

    long prepend;
    long number;
    int bankCode;

    public long getPrepend() {
        return prepend;
    }

    public void setPrepend(long prepend) {
        this.prepend = prepend;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String toString() {
        return prepend + "-" + number + "/" + bankCode;
    }
}
