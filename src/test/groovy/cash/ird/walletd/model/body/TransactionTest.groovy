package cash.ird.walletd.model.body

import cash.ird.walletd.BeanSpec

class TransactionTest extends BeanSpec<Transaction> {

    def "testBean"() {
        expect: checkBean()
    }
}
