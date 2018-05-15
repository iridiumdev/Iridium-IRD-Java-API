package cash.ird.walletd.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public abstract class BlockRange<T> {

    public abstract T getStart();

    public abstract void setStart(T start);

    public abstract long getCount();

    public abstract void setCount(long count);

    public abstract Type getType();

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
