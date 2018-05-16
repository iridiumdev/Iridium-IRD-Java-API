package cash.ird.walletd.model.response

import cash.ird.walletd.ResponseBeanSpec

class EstimatedFusionResponseTest extends ResponseBeanSpec<EstimatedFusionResponse> {

    def "testBean"() {
        expect: checkBean()
    }
}
