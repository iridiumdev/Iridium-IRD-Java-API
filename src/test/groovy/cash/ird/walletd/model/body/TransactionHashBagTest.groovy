package cash.ird.walletd.model.body

import cash.ird.walletd.BeanSpec

class TransactionHashBagTest extends BeanSpec<TransactionHashBag> {

    def "testBean"() {
        expect: checkBean()
    }
}
