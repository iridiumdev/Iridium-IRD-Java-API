package cash.ird.walletd.rpc;

import cash.ird.walletd.rpc.method.RequestMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor(staticName = "of")
@Data
@EqualsAndHashCode(callSuper = true)
public class WalletdRequest extends WalletdRPCModel{

    @NonNull
    @JsonIgnore
    private RequestMethod requestMethod;

    @NonNull
    private Map<String, Object> params;

    @JsonProperty("method")
    public String getMethod() {
        return this.requestMethod.getName();
    }

}
