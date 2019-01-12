package cz.zcu.pia.revoloot.utils;

import org.springframework.stereotype.Component;

@Component
public class BasicBankNumberGenerator implements IBankNumbers {

    private long accNumSeed = 30606060L;
    private final int modularitySeed = 11;

    private long excludeOrder(Long accNumber){
        if(accNumber == 0L){
            return 0L;
        }
        long order = 0L;
        order+= accNumber%10;
        accNumber /= 100;

        order+= 10* (accNumber%10);
        accNumber /= 100;

        order+= 100 * (accNumber%10);
        accNumber /= 100;

        order+= 1000 * (accNumber%10);
        accNumber /= 100;

        order+= 10000* accNumber;
        return  order;
    }

    @Override
    public int getBankCode() {
        return 3666;
    }

    @Override
    public long getNewAccNum(Long maxAccNum) {
        long nextOrder = excludeOrder(maxAccNum) + modularitySeed;

        long newNumber = accNumSeed + nextOrder % 10;
        nextOrder /= 10;

        newNumber += 100 * (nextOrder % 10);
        nextOrder /= 10;

        newNumber += 10000 * (nextOrder%10);
        nextOrder /= 10;

        newNumber += 1000000 * (nextOrder%10);
        nextOrder /= 10;

        newNumber += 100000000 * nextOrder;

        return newNumber;
    }

    @Override
    public boolean validateNumber(Long accNum){
        return excludeOrder(accNum) % modularitySeed == 0;
    }
}
