package cash.ird.walletd.model.response;

import lombok.Data;

@Data
public abstract class AbstractWalletdResponse<T, S> {

    private String jsonrpc;

    private String id;

    private T result;

    private WalletdError error;

    public abstract S unwrap();

}
