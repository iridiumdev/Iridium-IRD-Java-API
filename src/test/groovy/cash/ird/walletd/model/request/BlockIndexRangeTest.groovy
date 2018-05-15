package cash.ird.walletd.model.request

import cash.ird.walletd.BeanSpec
import nl.jqno.equalsverifier.EqualsVerifier

class BlockIndexRangeTest extends BeanSpec<BlockIndexRange> {

    def "static constructor"() {
        when:
        def a = BlockIndexRange.of(0, 1)
        then:
        a.getStart() == 0
        a.getCount() == 1
        a.getType() == BlockRange.Type.INDEX
    }

    @Override
    protected <T> EqualsVerifier<T> customizeEqualsVerifier(EqualsVerifier<T> equalsVerifier) {
        equalsVerifier
                .withRedefinedSuperclass()

        return super.customizeEqualsVerifier(equalsVerifier)
    }

}
