package cash.ird.walletd.model.response;

import cash.ird.walletd.model.body.EstimatedFusion;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EstimatedFusionResponse extends SimpleWalletdResponse<EstimatedFusion> {
}
