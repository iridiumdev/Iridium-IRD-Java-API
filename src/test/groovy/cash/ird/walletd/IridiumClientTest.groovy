package cash.ird.walletd

import cash.ird.walletd.model.body.Balance
import cash.ird.walletd.model.body.SpendKeyPair
import cash.ird.walletd.model.body.Status
import cash.ird.walletd.model.body.Transaction
import cash.ird.walletd.model.body.TransactionHashBag
import cash.ird.walletd.model.body.Transfer
import cash.ird.walletd.model.request.BlockHashRange
import cash.ird.walletd.model.request.BlockIndexRange
import cash.ird.walletd.model.request.PrivateKey
import cash.ird.walletd.model.request.PublicKey
import cash.ird.walletd.rpc.exception.IridiumWalletdException
import com.anotherchrisberry.spock.extensions.retry.RetryOnFailure
import spock.lang.Ignore
import spock.lang.Specification

class IridiumClientTest extends Specification {


    private IridiumAPI sut
    private String wallet1
    private String wallet2

    void setup() {
        sut = new IridiumClient("localhost", 14008)
        wallet1 = getClass().getResource("/iridium/wallet1/wallet.adr").readLines().first()
        sut.reset()

        def walletd2 = new IridiumClient("localhost", 14009)
        wallet2 = getClass().getResource("/iridium/wallet2/wallet.adr").readLines().first()
        walletd2.reset()
    }


    def "Reset"() {
        when:
        Boolean success = sut.reset()

        then:
        success
    }

    @Ignore
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
        SpendKeyPair keyPair = sut.getSpendKeys(wallet1)

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

    def "GetBalance for wallet1"() {
        when:
        Balance balance = sut.getBalance(wallet1)

        then:
        balance
        balance.availableBalance >= 0
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
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockIndexRange.of(0, 150))

        then:
        !hashBags.isEmpty()
    }

    def "GetTransactionHashes with BlockHashRange"() {
        setup:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0, 1)).first()
        when:
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockHashRange.of(firstBlockHash, 150))

        then:
        !hashBags.isEmpty()
        !hashBags.first().transactionHashes.isEmpty()
    }

    def "GetTransactionHashes with BlockHashRange and addresses"() {
        setup:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0, 1)).first()
        List<String> addresses = [sut.createAddress()]
        when:
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockHashRange.of(firstBlockHash, 150), addresses)

        then:
        !hashBags.isEmpty()
        hashBags.first().transactionHashes.isEmpty()
    }

    def "GetTransactionHashes with BlockIndexRange and addresses and paymentId"() {
        setup:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0, 1)).first()
        List<String> addresses = [sut.createAddress()]
        String paymentId = generatePaymentId()
        when:
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockIndexRange.of(0, 150), addresses, paymentId)

        then:
        !hashBags.isEmpty()
        hashBags.first().transactionHashes.isEmpty()
    }

    @RetryOnFailure(delaySeconds=2, times = 10)
    def "SendTransaction to wallet2"() {
        setup:

        when:
        String transactionHash = sut.sendTransaction(
                [Transfer.of(200000000, wallet2)],
                5000,
                2,
                wallet1,
                null,
                null,
                null
        )

        then:
        Transaction tx = sut.getTransaction(transactionHash)
        tx.amount == -200005000

    }


    private static String generatePaymentId() {
        def r = new Random()
        def result = (0..<64).collect { r.nextInt(16) }
                .collect { Integer.toString(it, 16) }
                .join()
        return result
    }

}
