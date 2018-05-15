package cash.ird.walletd.model.request

import cash.ird.walletd.BeanSpec
import cash.ird.walletd.rpc.method.RequestMethod
import nl.jqno.equalsverifier.EqualsVerifier

class WalletdRequestTest extends BeanSpec<WalletdRequest> {

    def "reqArgsConstructor"() {
        def params = [foo: "bar"]
        def method = RequestMethod.GET_STATUS
        given:

        when:
        def request = WalletdRequest.of(method, params)

        then:
        request.method == method
        request.params == params
    }

    @Override
    protected <T> EqualsVerifier<T> customizeEqualsVerifier(EqualsVerifier<T> equalsVerifier) {
        return equalsVerifier
                .withIgnoredFields("jsonrpc")
    }
}
