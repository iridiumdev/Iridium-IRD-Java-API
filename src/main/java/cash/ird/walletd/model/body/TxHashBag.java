package cash.ird.walletd.model.body;

import lombok.Data;

import java.util.List;

@Data
public class TxHashBag {

    private List<String> transactionHashes;

    private String blockHash;

}
