package cash.ird.walletd.rpc;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WalletdResponse<T> extends WalletdRPCModel {

    private T result;

}
