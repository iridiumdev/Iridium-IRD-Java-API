package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.TxHashBag;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class TxHashBagListResponse extends WrappedWalletdResponse<TxHashBagListResponse,List<TxHashBag>> {

    @JsonSetter("items")
    @Override
    public void setWrapped(List<TxHashBag> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
