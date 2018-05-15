package cash.ird.walletd.model.body;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Balance {

    @NonNull
    private Long lockedAmount;

    @NonNull
    private Long availableBalance;

}
