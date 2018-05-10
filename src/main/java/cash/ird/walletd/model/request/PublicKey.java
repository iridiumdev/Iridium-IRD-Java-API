package cash.ird.walletd.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublicKey extends Key {

    public PublicKey(String value){
        this.value = value;
    }

    public static PublicKey of(String value){
        return new PublicKey(value);
    }

    @Override
    public boolean isPrivate() {
        return false;
    }
}
