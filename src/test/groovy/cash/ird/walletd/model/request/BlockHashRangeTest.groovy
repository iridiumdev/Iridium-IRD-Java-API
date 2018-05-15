package cash.ird.walletd.model.request

import cash.ird.walletd.BeanSpec
import nl.jqno.equalsverifier.EqualsVerifier

class BlockHashRangeTest extends BeanSpec<BlockHashRange> {

    def "static constructor"() {
        when:
        def a = BlockHashRange.of("a", 1)
        then:
        a.getStart() == "a"
        a.getCount() == 1
        a.getType() == BlockRange.Type.HASH
    }

    @Override
    protected <T> EqualsVerifier<T> customizeEqualsVerifier(EqualsVerifier<T> equalsVerifier) {
        equalsVerifier
                .withRedefinedSuperclass()

        return super.customizeEqualsVerifier(equalsVerifier)
    }
}
