package cash.ird.walletd.model.body;

import lombok.Data;

@Data
public class Transfer {

    private long amount;

    private int type;

    private String address;

}
