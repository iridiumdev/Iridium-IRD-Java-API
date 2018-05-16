package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class BalanceResponseTest extends ResponseBeanSpec<BalanceResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
