package cash.ird.walletd.model.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Transaction {

    private String transactionHash;

    private Long blockIndex;

    private Long timestamp;

    @JsonProperty("isBase")
    private Boolean isBase;

    private Long unlockTime;

    private Long amount;

    // TODO: 13.05.18 what is this? It's not in the docs :(
    private Long state;

    private Long fee;

    private String extra;

    private String paymentId;

    private List<Transfer> transfers;

}
