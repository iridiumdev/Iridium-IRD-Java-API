package cash.ird.walletd.model.body;

import lombok.Data;

import java.util.List;

@Data
public class TransactionItemBag {

    private String blockHash;

    private List<Transaction> transactions;

}
