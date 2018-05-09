package cash.ird.walletd.rpc.method;

import lombok.Getter;

public enum RequestMethod {

    RESET("reset"),
    GET_STATUS("getStatus");


    @Getter
    private final String name;


    RequestMethod(String name) {
        this.name = name;
    }
}
