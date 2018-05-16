package cash.ird.walletd.model.request;

import cash.ird.walletd.IridiumAPI;
import cash.ird.walletd.rpc.method.RequestMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
public class WalletdRequest {

    private final String jsonrpc = IridiumAPI.RPC_VERSION;

    private final String id = UUID.randomUUID().toString();

    @NonNull
    private RequestMethod method;

    @NonNull
    private Map<String, Object> params;

}
