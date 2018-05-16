package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.SpendKeyPair;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpendKeyResponse extends SimpleWalletdResponse<SpendKeyPair> {
}
