package cash.ird.walletd.model.request

import cash.ird.walletd.BeanSpec
import nl.jqno.equalsverifier.EqualsVerifier

class PublicKeyTest extends BeanSpec<PublicKey> {

    def "reqArgsConstructor"() {
        given:
        def value = "public123"
        when:
        def key = PublicKey.of(value)
        then:
        key.getValue() == value
        !key.isPrivate()
    }

    @Override
    protected <T> EqualsVerifier<T> customizeEqualsVerifier(EqualsVerifier<T> equalsVerifier) {
        return equalsVerifier
                .withRedefinedSuperclass()
    }
}
