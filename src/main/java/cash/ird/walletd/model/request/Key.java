package cash.ird.walletd.model.request;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Key {

    public abstract String getValue();
    public abstract void setValue(String value);

    public abstract boolean isPrivate();

}
