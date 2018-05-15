package cash.ird.walletd.model.request

import cash.ird.walletd.BeanSpec
import nl.jqno.equalsverifier.EqualsVerifier

class PrivateKeyTest extends BeanSpec<PrivateKey> {

    def "reqArgsConstructor"() {
        given:
        def value = "a1b2c3"

        when:
        def key = PrivateKey.of(value)

        then:
        key.getValue() == value
        key.isPrivate()
    }

    @Override
    protected <T> EqualsVerifier<T> customizeEqualsVerifier(EqualsVerifier<T> equalsVerifier) {
        return equalsVerifier
                .withRedefinedSuperclass()
    }

}
