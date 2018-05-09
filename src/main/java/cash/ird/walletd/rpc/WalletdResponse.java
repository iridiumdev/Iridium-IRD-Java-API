package cash.ird.walletd.rpc;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WalletdResponse<T> extends AbstractResponse<T,T>{

    @Override
    public T unwrap() {
        return this.getResult();
    }

}
