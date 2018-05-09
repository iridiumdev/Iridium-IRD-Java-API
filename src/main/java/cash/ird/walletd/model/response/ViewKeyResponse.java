package cash.ird.walletd.model.response;

import cash.ird.walletd.rpc.WrappedWalletdResponse;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ViewKeyResponse extends WrappedWalletdResponse<ViewKeyResponse,String> {

    @JsonSetter("viewSecretKey")
    @Override
    public void setWrapped(String wrapped) {
        this.setInnerWrapped(wrapped);
    }

}
