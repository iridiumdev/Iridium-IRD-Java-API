package cash.ird.walletd.model.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpendKeyPair {

    @JsonProperty("spendSecretKey")
    private String secretKey;

    @JsonProperty("spendPublicKey")
    private String publicKey;

}
