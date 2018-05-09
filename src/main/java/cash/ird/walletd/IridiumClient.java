package cash.ird.walletd;


import cash.ird.walletd.model.body.Status;
import cash.ird.walletd.model.response.StatusResponse;
import cash.ird.walletd.model.response.NoopResponse;
import cash.ird.walletd.rpc.WalletdClient;
import cash.ird.walletd.rpc.method.RequestMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IridiumClient implements IridiumAPI {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_PATH = "json_rpc";

    private final WalletdClient walletdClient;


    public IridiumClient() {
        this("localhost", 14007);
    }

    public IridiumClient(String host, int port) {
        this(String.format("%s://%s:%s/%s", DEFAULT_PROTOCOL, host, port, DEFAULT_PATH));
    }

    public IridiumClient(String url) {
        this.walletdClient = new WalletdClient(url);
    }

    @Override
    public boolean reset(String viewSecretKey) {
        Map<String, Object> params = buildParams();

        if (viewSecretKey != null) {
            params.put("viewSecretKey", viewSecretKey);
        }

        Object noop = this.walletdClient.doRequest(RequestMethod.RESET, Collections.unmodifiableMap(params), NoopResponse.class);

        return noop != null;
    }

    @Override
    public boolean save() {
        return false; // TODO: 09.05.18 implement   
    }

    @Override
    public String getViewKey() {
        return null; // TODO: 09.05.18 implement
    }

    @Override
    public Status getStatus(String address) {
        Map<String, Object> params = buildParams();
        params.put("address", address);

        return this.walletdClient.doRequest(RequestMethod.GET_STATUS, Collections.unmodifiableMap(params), StatusResponse.class);
    }

    private Map<String, Object> buildParams() {
        return new HashMap<>();
    }
}
