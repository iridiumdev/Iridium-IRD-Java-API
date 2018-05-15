package cash.ird.walletd.model.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionHashResponse extends WrappedWalletdResponse<TransactionHashResponse,String> {

    @JsonSetter("transactionHash")
    @Override
    public void setWrapped(String wrapped) {
        this.setInnerWrapped(wrapped);
    }

}
