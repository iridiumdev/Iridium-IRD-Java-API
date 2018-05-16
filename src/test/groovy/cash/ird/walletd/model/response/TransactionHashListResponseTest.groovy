package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class TransactionHashListResponseTest extends ResponseBeanSpec<TransactionHashListResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
