package cash.ird.walletd.rpc;

import lombok.Data;

@Data
public abstract class AbstractResponse<T, S> {

    private String jsonrpc;

    private String id;

    private T result;

    private RPCError error;

    public abstract S unwrap();

}
