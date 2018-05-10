package cash.ird.walletd.model.response;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class BlockHashListResponse extends WrappedWalletdResponse<BlockHashListResponse,List<String>> {

    @JsonSetter("blockHashes")
    @Override
    public void setWrapped(List<String> wrapped) {
        this.setInnerWrapped(wrapped);
    }
}
