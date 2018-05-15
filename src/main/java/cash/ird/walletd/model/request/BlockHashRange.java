package cash.ird.walletd.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class BlockHashRange extends BlockRange<String> {

    @Getter
    @Setter
    private String start;

    @Getter
    @Setter
    private long count;

    @Getter
    private final Type type = Type.HASH;

    private BlockHashRange(){
        super();
    }

    public static BlockHashRange of(String start, long count) {
        BlockHashRange blockHashRange = new BlockHashRange();
        blockHashRange.setStart(start);
        blockHashRange.setCount(count);
        return blockHashRange;
    }



}
