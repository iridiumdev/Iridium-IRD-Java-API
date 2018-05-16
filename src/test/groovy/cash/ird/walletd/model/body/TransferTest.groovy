package cash.ird.walletd.model.body

import cash.ird.walletd.BeanSpec

class TransferTest extends BeanSpec<Transfer> {

    def "testBean"() {
        expect: checkBean()
    }

    def "reqArgsConstructor"() {
        def address = "ir3abc..."
        when:
        def sut = Transfer.of(1000, address)
        then:
        sut.getAmount() == 1000
        sut.getAddress() == address
    }
}
