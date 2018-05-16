package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatusResponse extends SimpleWalletdResponse<Status> {
}
