package cash.ird.walletd.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoopResponse extends SimpleWalletdResponse<Object> {
}
