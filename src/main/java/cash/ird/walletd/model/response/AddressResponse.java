package cash.ird.walletd.model.response;

import com.fasterxml.jackson.annotation.JsonSetter;

public class AddressResponse extends WrappedWalletdResponse<AddressResponse,String> {

    @JsonSetter("address")
    @Override
    public void setWrapped(String wrapped) {
        this.setInnerWrapped(wrapped);
    }

}
