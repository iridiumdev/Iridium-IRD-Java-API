package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class ViewKeyResponseTest extends ResponseBeanSpec<ViewKeyResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
