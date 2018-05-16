package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class TransactionResponseTest extends ResponseBeanSpec<TransactionResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
