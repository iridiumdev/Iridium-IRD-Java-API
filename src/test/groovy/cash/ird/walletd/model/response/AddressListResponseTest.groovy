package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class AddressListResponseTest extends ResponseBeanSpec<AddressListResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
