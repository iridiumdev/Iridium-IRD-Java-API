package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.TransactionItemBag;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionItemBagListResponse extends WrappedWalletdResponse<TransactionItemBagListResponse,List<TransactionItemBag>> {

    @JsonSetter("items")
    @Override
    public void setWrapped(List<TransactionItemBag> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
