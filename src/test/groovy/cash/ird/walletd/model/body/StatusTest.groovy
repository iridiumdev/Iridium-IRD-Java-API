package cash.ird.walletd.model.body

import cash.ird.walletd.BeanSpec

class StatusTest extends BeanSpec<Status> {

    def "testBean"() {
        expect: checkBean()
    }
}
