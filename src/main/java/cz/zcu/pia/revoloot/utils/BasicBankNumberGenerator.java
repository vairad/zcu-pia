package cz.zcu.pia.revoloot.utils;

public class BasicBankNumberGenerator implements IBankNumbers {
    @Override
    public int getBankCode() {
        return 3666;
    }

    @Override
    public long getNewAccNum(Long maxAccNum) {
        return 0;
    }
}
