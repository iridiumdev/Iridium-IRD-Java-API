package cash.ird.walletd.model.body;

import lombok.Data;

@Data
public class Status {

    private long peerCount;

    private long blockCount;

    private String lastBlockHash;

    private long knownBlockCount;

}
