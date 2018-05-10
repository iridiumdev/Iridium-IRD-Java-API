package cash.ird.walletd.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
public class PrivateKey extends Key {

    public PrivateKey(String value){
        this.value = value;
    }

    public static PrivateKey of(String value){
        return new PrivateKey(value);
    }

    @Override
    public boolean isPrivate() {
        return true;
    }

}
