package cash.ird.walletd.model.body;

import lombok.Data;

@Data
public class Balance {

    private long lockedAmount;

    private long availableBalance;

}
