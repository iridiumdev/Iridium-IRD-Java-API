package cash.ird.walletd.rpc;

import lombok.Data;

@Data
public class RPCError {
    private long code;
    private String message;
}
