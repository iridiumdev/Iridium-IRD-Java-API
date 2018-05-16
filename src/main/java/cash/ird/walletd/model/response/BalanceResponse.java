package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.Balance;
import cash.ird.walletd.model.body.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BalanceResponse extends SimpleWalletdResponse<Balance> {
}
