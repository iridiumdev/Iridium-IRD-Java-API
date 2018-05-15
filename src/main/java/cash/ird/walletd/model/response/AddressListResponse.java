package cash.ird.walletd.model.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressListResponse extends WrappedWalletdResponse<AddressListResponse,List<String>> {

    @JsonSetter("addresses")
    @Override
    public void setWrapped(List<String> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
