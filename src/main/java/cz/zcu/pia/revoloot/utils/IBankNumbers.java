package cz.zcu.pia.revoloot.utils;

public interface IBankNumbers {

    int getBankCode();
    long getNewAccNum(Long maxAccNum);

    boolean validateNumber(Long accNum);
}
