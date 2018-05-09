package cash.ird.walletd.rpc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public abstract class WrappedWalletdResponse<T extends WrappedWalletdResponse<T,S>,S> extends AbstractResponse<T, S>{

    private S wrapped;

    public abstract void setWrapped(S wrapped);

    protected void setInnerWrapped(S wrapped) {
        this.wrapped = wrapped;
    }

    @JsonIgnore
    public S getWrapped(){
        return this.wrapped;
    }

    @Override
    public S unwrap() {
        return this.getResult().getWrapped();
    }

}
