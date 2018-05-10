package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.TxItemBag;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class TxItemBagListResponse extends WrappedWalletdResponse<TxItemBagListResponse,List<TxItemBag>> {

    @JsonSetter("items")
    @Override
    public void setWrapped(List<TxItemBag> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
