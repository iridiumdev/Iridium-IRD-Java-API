package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class TransactionHashResponseTest extends ResponseBeanSpec<TransactionHashResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
