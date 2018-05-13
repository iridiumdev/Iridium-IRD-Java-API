package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.TransactionItemBag;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class TransactionItemBagListResponse extends WrappedWalletdResponse<TransactionItemBagListResponse,List<TransactionItemBag>> {

    @JsonSetter("items")
    @Override
    public void setWrapped(List<TransactionItemBag> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
