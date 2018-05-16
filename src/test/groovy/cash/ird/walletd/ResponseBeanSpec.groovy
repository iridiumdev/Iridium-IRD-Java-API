package cash.ird.walletd

import nl.jqno.equalsverifier.EqualsVerifier

class ResponseBeanSpec<T> extends BeanSpec<T> {

    @Override
    <S> EqualsVerifier<S> customizeEqualsVerifier(EqualsVerifier<S> equalsVerifier) {
        return equalsVerifier.withRedefinedSuperclass()
    }
}
