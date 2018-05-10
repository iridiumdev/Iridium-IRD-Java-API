package cash.ird.walletd.model.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class BlockHashRange extends BlockRange<String> {

    private BlockHashRange(){
        super();
        this.setType(Type.HASH);
    }

    public static BlockHashRange of(String start, long count) {
        BlockHashRange blockHashRange = new BlockHashRange();
        blockHashRange.setStart(start);
        blockHashRange.setCount(count);
        return blockHashRange;
    }

    @Override
    public String getStart() {
        return this.start;
    }

    @Override
    public void setStart(String start) {
        this.start = start;
    }
}
