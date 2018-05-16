package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class NoopResponseTest extends ResponseBeanSpec<NoopResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
