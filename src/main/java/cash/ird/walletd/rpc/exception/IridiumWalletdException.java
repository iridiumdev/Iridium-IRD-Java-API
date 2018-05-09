package cash.ird.walletd.rpc.exception;

import cash.ird.walletd.rpc.RPCError;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class IridiumWalletdException extends Exception {

    private final long code;

    public IridiumWalletdException(RPCError error) {
        super(error.getMessage() + ", code:" + error.getCode());
        this.code = error.getCode();
    }

}
