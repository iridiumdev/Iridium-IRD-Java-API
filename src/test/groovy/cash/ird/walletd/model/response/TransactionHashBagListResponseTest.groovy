package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class TransactionHashBagListResponseTest extends ResponseBeanSpec<TransactionHashBagListResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
