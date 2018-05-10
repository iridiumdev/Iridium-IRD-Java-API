package cash.ird.walletd.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleWalletdResponse<T> extends AbstractWalletdResponse<T,T> {

    @Override
    public T unwrap() {
        return this.getResult();
    }

}
