package cash.ird.walletd.rpc;


import cash.ird.walletd.rpc.method.RequestMethod;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Map;

public class WalletdClient {

    private final String url;

    public WalletdClient(String url) {
        this.url = url;


        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    @SneakyThrows(UnirestException.class)
    public <R,T extends WalletdResponse<R>> R doRequest(RequestMethod method, Map<String, Object> params, Class<T> responseType) {

        WalletdRequest walletdRequest = WalletdRequest.of(method, params);

        HttpResponse<T> response = Unirest.post(this.url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(walletdRequest)
                .asObject(responseType);

        return response.getBody().getResult();

    }


}
