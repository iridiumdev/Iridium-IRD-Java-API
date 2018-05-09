package cash.ird.walletd.model.response;

import cash.ird.walletd.rpc.WrappedWalletdResponse;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class AddressListResponse extends WrappedWalletdResponse<AddressListResponse,List<String>> {

    @JsonSetter("addresses")
    @Override
    public void setWrapped(List<String> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
