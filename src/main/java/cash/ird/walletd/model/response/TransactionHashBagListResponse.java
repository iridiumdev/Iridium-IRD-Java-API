package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.TransactionHashBag;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class TransactionHashBagListResponse extends WrappedWalletdResponse<TransactionHashBagListResponse,List<TransactionHashBag>> {

    @JsonSetter("items")
    @Override
    public void setWrapped(List<TransactionHashBag> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
