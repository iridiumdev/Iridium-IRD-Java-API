package cash.ird.walletd.model.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionHashListResponse extends WrappedWalletdResponse<TransactionHashListResponse,List<String>> {

    @JsonSetter("transactionHashes")
    @Override
    public void setWrapped(List<String> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
