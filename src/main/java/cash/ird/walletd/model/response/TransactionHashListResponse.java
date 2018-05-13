package cash.ird.walletd.model.response;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class TransactionHashListResponse extends WrappedWalletdResponse<TransactionHashListResponse,List<String>> {

    @JsonSetter("transactionHashes")
    @Override
    public void setWrapped(List<String> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
