package cash.ird.walletd.rpc;


import cash.ird.walletd.model.response.AbstractWalletdResponse;
import cash.ird.walletd.model.request.WalletdRequest;
import cash.ird.walletd.rpc.exception.IridiumWalletdException;
import cash.ird.walletd.rpc.method.RequestMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class WalletdClient {

    private final String url;
    private final HttpClient httpClient;

    public WalletdClient(String url) {
        this(url, new HttpClient());
    }

    public WalletdClient(String url, HttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;

    }

    public <R, S, T extends AbstractWalletdResponse<S, R>> R doRequest(RequestMethod method, Class<T> responseType) throws IridiumWalletdException {
        return doRequest(method, Collections.emptyMap(), responseType);
    }

    @SneakyThrows(UnirestException.class)
    public <R, S, T extends AbstractWalletdResponse<S, R>> R doRequest(RequestMethod method, Map<String, Object> params, Class<T> responseType) throws IridiumWalletdException {

        WalletdRequest walletdRequest = WalletdRequest.of(method, params);

        HttpResponse<T> response = httpClient.post(this.url, walletdRequest, responseType);

        T body = response.getBody();

        if (body.getError() != null) {
            log.warn("Whoopsie, iridium-walletd threw an error: {}", body.getError());
            throw new IridiumWalletdException(body.getError());
        }

        return body.unwrap();

    }


}
