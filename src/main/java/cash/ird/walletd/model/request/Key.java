package cash.ird.walletd.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class Key {

    protected String value;

    public abstract boolean isPrivate();

}
