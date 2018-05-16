package cash.ird.walletd.model.response;

import lombok.Data;

@Data
public class WalletdError {
    private long code;
    private String message;
    private Object data;
}
