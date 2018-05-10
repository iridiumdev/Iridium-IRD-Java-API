package cash.ird.walletd.model.request;

import lombok.Data;

@Data
public abstract class Key {

    protected String value;

    public abstract boolean isPrivate();

}
