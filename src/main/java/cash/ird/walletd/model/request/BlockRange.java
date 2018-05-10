package cash.ird.walletd.model.request;

import lombok.Getter;
import lombok.Setter;

public abstract class BlockRange<T> {

    protected T start;

    @Getter
    @Setter
    protected long count;

    @Getter
    @Setter
    protected Type type;


    public abstract T getStart();

    public abstract void setStart(T start);

    public enum Type {
        HASH("blockHash"),
        INDEX("firstBlockIndex");

        @Getter
        private final String paramName;

        Type(String paramName) {
            this.paramName = paramName;
        }
    }

}
