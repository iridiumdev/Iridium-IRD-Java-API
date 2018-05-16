package cash.ird.walletd.model.body;

import lombok.Data;

@Data
public class Status {

    private Long peerCount;

    private Long blockCount;

    private String lastBlockHash;

    private Long knownBlockCount;

}
