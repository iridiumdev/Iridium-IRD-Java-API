package cash.ird.walletd.model.request;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class PublicKey extends Key {

    @NonNull
    private String value;

    @Override
    public boolean isPrivate() {
        return false;
    }
}
