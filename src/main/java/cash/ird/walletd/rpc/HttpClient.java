package cash.ird.walletd.rpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class HttpClient {


    public HttpClient(){
        this(new com.fasterxml.jackson.databind.ObjectMapper());
    }

    public HttpClient(com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper) {
        Unirest.setObjectMapper(new ObjectMapper() {
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

    public <T> HttpResponse<T> post(String url, Object body, Class<? extends T> responseClass) throws UnirestException {
        return Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .asObject(responseClass);
    }

}
