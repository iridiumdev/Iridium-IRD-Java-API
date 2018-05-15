package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.TransactionHashBag;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionHashBagListResponse extends WrappedWalletdResponse<TransactionHashBagListResponse,List<TransactionHashBag>> {

    @JsonSetter("items")
    @Override
    public void setWrapped(List<TransactionHashBag> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
