package cash.ird.walletd.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlockIndexRange extends BlockRange<Long> {

    private BlockIndexRange(){
        super();
        this.setType(Type.INDEX);
    }

    public static BlockIndexRange of(long start, long count) {
        BlockIndexRange blockIndexRange = new BlockIndexRange();
        blockIndexRange.setStart(start);
        blockIndexRange.setCount(count);
        return blockIndexRange;
    }

    @Override
    public Long getStart() {
        return this.start;
    }

    @Override
    public void setStart(Long start) {
        this.start = start;
    }
}
