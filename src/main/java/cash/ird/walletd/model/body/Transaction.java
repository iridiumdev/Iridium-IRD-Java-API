package cash.ird.walletd.model.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Transaction {

    private String transactionHash;

    private long blockIndex;

    private long timestamp;

    @JsonProperty("isBase")
    private boolean isBase;

    private long unlockTime;

    private long amount;

    // TODO: 13.05.18 what is this? It's not in the docs :(
    private long state;

    private long fee;

    private String extra;

    private String paymentId;

    private List<Transfer> transfers;

}
