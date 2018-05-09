package cash.ird.walletd.rpc;


import cash.ird.walletd.rpc.exception.IridiumWalletdException;
import cash.ird.walletd.rpc.method.RequestMethod;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class WalletdClient {

    private final String url;

    public WalletdClient(String url) {
        this.url = url;


        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    log.debug("RPC Response: {}", value);
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {

                try {
                    String jsonString = jacksonObjectMapper.writeValueAsString(value);
                    log.debug("RPC Request: {}", jsonString);
                    return jsonString;
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public <R, S, T extends AbstractResponse<S, R>> R doRequest(RequestMethod method, Class<T> responseType) throws IridiumWalletdException {
        return doRequest(method, Collections.emptyMap(), responseType);
    }

    @SneakyThrows(UnirestException.class)
    public <R, S, T extends AbstractResponse<S, R>> R doRequest(RequestMethod method, Map<String, Object> params, Class<T> responseType) throws IridiumWalletdException {

        WalletdRequest walletdRequest = WalletdRequest.of(method, params);

        RequestBodyEntity respRaw = Unirest.post(this.url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(walletdRequest);

        HttpResponse<T> response = respRaw
                .asObject(responseType);

        T body = response.getBody();



        if (body.getError() != null) {
            log.warn("Whoopsie, we got an error: {}", body.getError());
            throw new IridiumWalletdException(body.getError());
        }

        return body.unwrap();

    }


}
