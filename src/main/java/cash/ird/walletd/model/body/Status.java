package cash.ird.walletd.model.body;

import lombok.Data;

@Data
public class Status {

    private int peerCount;

    private int blockCount;

    private String lastBlockHash;

    private int knownBlockCount;

}
