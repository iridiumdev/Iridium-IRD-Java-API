package cash.ird.walletd.model.request;

import cash.ird.walletd.rpc.method.RequestMethod;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor(staticName = "of")
@Data
public class WalletdRequest{

    private String jsonrpc = "2.0";

    private String id = UUID.randomUUID().toString();

    @NonNull
    private RequestMethod method;

    @NonNull
    private Map<String, Object> params;

}
