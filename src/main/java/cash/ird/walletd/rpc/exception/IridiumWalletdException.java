package cash.ird.walletd.rpc.exception;

import cash.ird.walletd.model.response.WalletdError;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
public class IridiumWalletdException extends Exception {

    @Getter
    private final long code;

    @Getter
    private final Object data;

    public IridiumWalletdException(WalletdError error) {
        super(error.getMessage() + ", code:" + error.getCode());
        this.code = error.getCode();
        this.data = error.getData();
    }

}
