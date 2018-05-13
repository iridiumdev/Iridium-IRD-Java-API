package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.Transaction;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TransactionResponse extends WrappedWalletdResponse<TransactionResponse,Transaction> {

    @JsonSetter("transaction")
    @Override
    public void setWrapped(Transaction wrapped) {
        this.setInnerWrapped(wrapped);
    }

}
