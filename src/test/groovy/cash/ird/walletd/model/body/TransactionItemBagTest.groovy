package cash.ird.walletd.model.body

import cash.ird.walletd.BeanSpec

class TransactionItemBagTest extends BeanSpec<TransactionItemBag> {

    def "testBean"() {
        expect: checkBean()
    }
}
