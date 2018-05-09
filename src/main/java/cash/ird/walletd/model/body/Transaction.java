package cash.ird.walletd.model.body;

import lombok.Data;

import java.util.List;

@Data
public class Transaction {

    private String transactionHash;

    private long blockIndex;

    private long timestamp;

    private boolean isBase;

    private long unlockTime;

    private long amount;

    private long fee;

    private String extra;

    private String paymentId;

    private List<Transfer> transfers;

}
