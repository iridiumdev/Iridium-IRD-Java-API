package cash.ird.walletd

import cash.ird.walletd.model.body.Balance
import cash.ird.walletd.model.body.SpendKeyPair
import cash.ird.walletd.model.body.Status
import cash.ird.walletd.model.body.TxHashBag
import cash.ird.walletd.model.request.BlockHashRange
import cash.ird.walletd.model.request.BlockIndexRange
import cash.ird.walletd.model.request.PrivateKey
import cash.ird.walletd.model.request.PublicKey
import cash.ird.walletd.rpc.exception.IridiumWalletdException
import spock.lang.Specification

class IridiumClientTest extends Specification {

    //todo change tests to run against a mocked http json api instead of the real walletd

    private IridiumAPI sut
    private String baseAddress = "ir3iQYmVZ843gcJdZ2xEPnfCyF5wAmP6wQU6W8QdE3ieGKujPFWy7D5PVnZ2YtQcvs8EBhPfy79SVL4ZPvHebcqQ1WAGsLaMV"
    private String testAddress1
    private String testAddress2
    private String testAddress3

    void setup() {
        sut = new IridiumClient()
        //testAddress1 = sut.createAddress()
        //testAddress2 = sut.createAddress()
        //testAddress3 = sut.createAddress()
    }


    def "Reset"() {
        when:
        Boolean success = sut.reset()

        then:
        success
    }

    def "Reset with viewSecretKey"() {
        given:
        String viewKey = sut.getViewKey()

        when:
        Boolean success = sut.reset(viewKey)

        then:
        success
    }

    def "Reset with address should fail"() {
        given:
        String address = sut.createAddress()

        when:
        Boolean success = sut.reset(address)

        then:
        IridiumWalletdException ex = thrown(IridiumWalletdException)
        ex.code == -32000
        ex.message.contains("Wrong key format")
    }

    def "Save"() {
        when:
        Boolean success = sut.save()

        then:
        success
    }

    def "GetStatus"() {
        when:
        Status status = sut.getStatus()

        then:
        status.blockCount != null
    }

    def "GetViewKey"() {
        when:
        String viewKey = sut.getViewKey()

        then:
        viewKey != null
    }

    def "GetSpendKeys"() {
        when:
        SpendKeyPair keyPair = sut.getSpendKeys(testAddress1)

        then:
        keyPair != null
        keyPair.getPublicKey() != null
        keyPair.getSecretKey() != null
    }

    def "GetAddresses"() {
        when:
        List<String> addresses = sut.getAddresses()

        then:
        !addresses.isEmpty()
    }

    def "CreateAddress without param"() {
        when:
        String address = sut.createAddress()

        then:
        address != null
    }

    def "CreateAddress with publicKey"() {
        when:
        String address = sut.createAddress(PublicKey.of("test123"))

        then:
        address != null
    }

    def "CreateAddress with privateKey"() {
        when:
        String address = sut.createAddress(PrivateKey.of("secr3t"))

        then:
        address != null
    }

    def "DeleteAddress"() {
        setup:
        String addressToBeDeleted = sut.createAddress()
        when:
        boolean success = sut.deleteAddress(addressToBeDeleted)

        then:
        success
    }

    def "GetBalance"() {
        when:
        Balance balance = sut.getBalance()

        then:
        balance
        balance.availableBalance >= 0
        balance.lockedAmount >= 0
    }

    def "GetBalance with address"() {
        setup:
        String address = sut.createAddress()
        when:
        Balance balance = sut.getBalance(address)

        then:
        balance
        balance.availableBalance == 0
        balance.lockedAmount == 0
    }

    def "GetBalance with baseAddress"() {
        when:
        Balance balance = sut.getBalance(baseAddress)

        then:
        balance
        balance.availableBalance > 0
        balance.lockedAmount >= 0
    }


    def "GetBlockHashes"() {
        when:
        List<String> blockHashes = sut.getBlockHashes(BlockIndexRange.of(0, 1))

        then:
        !blockHashes.isEmpty()
        blockHashes.size() == 1
    }

    def "GetTransactionHashes with BlockIndexRange"() {
        when:
        List<TxHashBag> hashBags = sut.getTransactionHashes(BlockIndexRange.of(0, 150))

        then:
        !hashBags.isEmpty()
    }

    def "GetTransactionHashes with BlockHashRange"() {
        setup:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0,1)).first()
        when:
        List<TxHashBag> hashBags = sut.getTransactionHashes(BlockHashRange.of(firstBlockHash, 150))

        then:
        !hashBags.isEmpty()
        !hashBags.first().transactionHashes.isEmpty()
    }

    def "GetTransactionHashes with BlockHashRange and addresses"() {
        setup:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0,1)).first()
        List<String> addresses = [sut.createAddress()]
        when:
        List<TxHashBag> hashBags = sut.getTransactionHashes(BlockHashRange.of(firstBlockHash, 150), addresses)

        then:
        !hashBags.isEmpty()
        hashBags.first().transactionHashes.isEmpty()
    }

    def "GetTransactionHashes with BlockIndexRange and addresses and paymentId"() {
        setup:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0,1)).first()
        List<String> addresses = [sut.createAddress()]
        String paymentId = generatePaymentId()
        when:
        List<TxHashBag> hashBags = sut.getTransactionHashes(BlockIndexRange.of(0,150), addresses, paymentId)

        then:
        !hashBags.isEmpty()
        hashBags.first().transactionHashes.isEmpty()
    }


    private static String generatePaymentId(){
        def r = new Random()
        def result = (0..<64).collect { r.nextInt(16) }
                .collect { Integer.toString(it, 16) }
                .join()
        return result
    }

}
