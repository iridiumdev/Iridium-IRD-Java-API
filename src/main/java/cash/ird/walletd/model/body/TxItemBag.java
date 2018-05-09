package cash.ird.walletd.model.body;

import lombok.Data;

import java.util.List;

@Data
public class TxItemBag {

    private String blockHash;

    private List<Transaction> transactions;

}
