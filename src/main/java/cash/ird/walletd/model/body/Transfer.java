package cash.ird.walletd.model.body;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Transfer {

    @NonNull
    private Long amount;

    private Integer type;

    @NonNull
    private String address;

}
