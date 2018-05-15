package cash.ird.walletd.model.request;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@ToString
public class BlockIndexRange extends BlockRange<Long> {

    @Getter
    @Setter
    private Long start;

    @Getter
    @Setter
    private long count;

    @Getter
    private final Type type = Type.INDEX;

    private BlockIndexRange(){
        super();
    }

    public static BlockIndexRange of(long start, long count) {
        BlockIndexRange blockIndexRange = new BlockIndexRange();
        blockIndexRange.setStart(start);
        blockIndexRange.setCount(count);
        return blockIndexRange;
    }
}
