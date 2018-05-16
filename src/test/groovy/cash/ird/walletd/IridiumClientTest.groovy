package cash.ird.walletd

import cash.ird.walletd.model.body.*
import cash.ird.walletd.model.request.BlockHashRange
import cash.ird.walletd.model.request.BlockIndexRange
import cash.ird.walletd.model.request.PrivateKey
import cash.ird.walletd.model.request.PublicKey
import cash.ird.walletd.rpc.exception.IridiumWalletdException
import com.anotherchrisberry.spock.extensions.retry.RetryOnFailure
import groovy.util.logging.Slf4j
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

@Slf4j
class IridiumClientTest extends Specification {


    @Shared
    private IridiumAPI sut

    @Shared
    private IridiumAPI sut2

    @Shared
    private String wallet1

    @Shared
    private String wallet2

    void setupSpec() {
        sut = new IridiumClient("localhost", 14008)
        wallet1 = getClass().getResource("/iridium/wallet1/wallet.adr").readLines().first()

        sut2 = new IridiumClient("localhost", 14009)
        wallet2 = getClass().getResource("/iridium/wallet2/wallet.adr").readLines().first()

        waitForBalance(sut, wallet1, 1)
//        waitForBalance(sut2, wallet2, 0)
    }

    void cleanupSpec() {
        sut.reset()
        sut2.reset()
    }

    @Ignore
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

    @Ignore
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

    def "GetStatus for wallet1"() {
        when:
        Status status = sut.getStatus()

        then:
        status.blockCount != null
    }

    def "GetStatus for wallet2"() {
        when:
        Status status = sut2.getStatus()

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

    def "GetBalance for wallet2"() {
        when:
        Balance balance = sut2.getBalance(wallet2)

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
        given:
        Status status = sut.getStatus()
        when:
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockIndexRange.of(0, status.knownBlockCount))

        then:
        !hashBags.isEmpty()
    }

    def "GetTransactionHashes with BlockHashRange"() {
        given:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0, 1)).first()
        Status status = sut.getStatus()
        when:
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockHashRange.of(firstBlockHash, status.knownBlockCount))

        then:
        !hashBags.isEmpty()
        !hashBags.first().transactionHashes.isEmpty()
    }

    def "GetTransactionHashes with BlockHashRange and addresses"() {
        given:
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0, 1)).first()
        List<String> addresses = [sut.createAddress()]
        Status status = sut.getStatus()
        when:
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockHashRange.of(firstBlockHash, status.knownBlockCount), addresses)

        then:
        !hashBags.isEmpty()
        hashBags.first().transactionHashes.isEmpty()
    }

    def "GetTransactionHashes with BlockIndexRange and addresses and paymentId"() {
        given:
        List<String> addresses = [sut.createAddress()]
        String paymentId = generatePaymentId()
        Status status = sut.getStatus()
        when:
        List<TransactionHashBag> hashBags = sut.getTransactionHashes(BlockIndexRange.of(0, status.knownBlockCount), addresses, paymentId)

        then:
        !hashBags.isEmpty()
        hashBags.first().transactionHashes.isEmpty()
    }

    def "GetTransactions with index"() {
        given:
        Status status = sut.getStatus()
        when:
        List<TransactionItemBag> bagList = sut.getTransactions(BlockIndexRange.of(0, status.knownBlockCount))

        then:
        !bagList.empty

    }

    def "GetTransactions with hash"() {
        given:
        Status status = sut.getStatus()
        String firstBlockHash = sut.getBlockHashes(BlockIndexRange.of(0, 1)).first()
        when:
        List<TransactionItemBag> bagList = sut.getTransactions(BlockHashRange.of(firstBlockHash, status.knownBlockCount))

        then:
        !bagList.empty

    }

    def "GetUnconfirmedTransactionHashes"() {
        when:
        List<String> unconfirmedTransactionHashes = sut.getUnconfirmedTransactionHashes()

        then:
        unconfirmedTransactionHashes != null

        when:
        List<String> unconfirmedTransactionHashes2 = sut2.getUnconfirmedTransactionHashes()

        then:
        unconfirmedTransactionHashes2 != null
    }

    @RetryOnFailure(delaySeconds = 10, times = 29)
    def "SendTransaction to wallet2"() {
        setup:
        waitForBalance(sut, wallet1, 105000)

        when:
        String transactionHash = sut.sendTransaction(
                [Transfer.of(100000, wallet2)],
                5000,
                2,
                wallet1,
                null,
                0, //no confirmation at all, just next block has to be mined
                null
        )

        then:
        Transaction tx = sut.getTransaction(transactionHash)
        tx.amount == -105000

        waitForTransactionConfirmation(sut, transactionHash)

        when:
        Transaction tx2 = sut2.getTransaction(transactionHash)

        then:
        tx2.amount == 100000
    }

    @RetryOnFailure(delaySeconds = 10, times = 29)
    def "SendTransaction to multiple addresses on wallet2"() {
        setup:
        waitForBalance(sut, wallet1, 205000)
        def address1 = sut2.createAddress()
        def address2 = sut2.createAddress()
        def status = sut2.getStatus()

        when:
        String transactionHash = sut.sendTransaction(
                [
                        Transfer.of(100000, address1),
                        Transfer.of(100000, address2)
                ],
                5000,
                2,
                wallet1,
                [wallet1],
                null,
                0, //no confirmation at all, just next block has to be mined
                null
        )

        then:
        Transaction tx = sut.getTransaction(transactionHash)
        tx.amount == -205000

        waitForTransactionConfirmation(sut, transactionHash)
        waitForTransactionConfirmation(sut2, transactionHash)

        when:
        Transaction tx2 = sut2.getTransaction(transactionHash)
        def balance1 = sut2.getBalance(address1)
        def balance2 = sut2.getBalance(address2)

        then:
        tx2.amount == 200000
        balance1.availableBalance == 100000 || balance1.lockedAmount == 100000
        balance2.availableBalance == 100000 || balance2.lockedAmount == 100000

        when:
        def transactionHashList = sut2.getUnconfirmedTransactionHashes([address1, address2])

        then:
        transactionHashList.empty

        when:
        def txItemBagList = sut2.getTransactions(BlockHashRange.of(status.lastBlockHash, 10), [address1, address2])

        then:
        txItemBagList.find { it.transactions.find {it.transactionHash == transactionHash} } != null


    }

    @RetryOnFailure(delaySeconds = 10, times = 29)
    def "Delayed Transactions"() {
        given:
        waitForBalance(sut, wallet1, 205000)
        def address1 = sut2.createAddress()
        def address2 = sut2.createAddress()

        when:
        def dTxHash = sut.createDelayedTransaction(
                [
                        Transfer.of(100000, address1),
                        Transfer.of(100000, address2)
                ],
                5000,
                2,
                wallet1,
                [wallet1],
                null,
                0, //no confirmation at all, just next block has to be mined
                null
        )

        then:
        dTxHash != null

        when:
        def dTxHashes = sut.getDelayedTransactionHashes()

        then:
        dTxHashes.contains(dTxHash)

        when:
        def success = sut.deleteDelayedTransaction(dTxHash)

        then:
        success

        when:
        def dTxHash2 = sut.createDelayedTransaction(
                [
                        Transfer.of(100000, address1),
                        Transfer.of(100000, address2)
                ],
                5000,
                2,
                wallet1,
                null,
                0, //no confirmation at all, just next block has to be mined
                null
        )

        then:
        dTxHash != dTxHash2
        !sut.getUnconfirmedTransactionHashes().contains(dTxHash2)

        when:
        success = sut.sendDelayedTransaction(dTxHash2)

        then:
        success
        sut.getUnconfirmedTransactionHashes().contains(dTxHash2)


    }

    @RetryOnFailure(delaySeconds = 10, times = 29)
    def "Fusion.... HA!"() { //hehehe ;)
        given:
        waitForBalance(sut, wallet1, 505000)
        def address1 = sut2.createAddress()
        def address2 = sut2.createAddress()
        def address3 = sut2.createAddress()
        def address4 = sut2.createAddress()
        def address5 = sut2.createAddress()
        def addresses = [address1, address2, address3, address4, address5]

        String transactionHash = sut.sendTransaction(
                [
                        Transfer.of(100000, address1),
                        Transfer.of(100000, address2),
                        Transfer.of(100000, address3),
                        Transfer.of(100000, address4),
                        Transfer.of(100000, address5)
                ],
                5000,
                2,
                wallet1,
                [wallet1],
                null,
                0, //no confirmation at all, just next block has to be mined
                null
        )

        Transaction tx = sut.getTransaction(transactionHash)
        tx.amount == -505000

        waitForTransactionConfirmation(sut, transactionHash)
        waitForTransactionConfirmation(sut2, transactionHash)

        addresses.each {
            waitForBalance(sut2, it, 10000)
        }

        when:
        def estTotal = sut2.estimateFusion(5000000)

        and:
        waitForBlockHeightIncrement(sut2, 1)

        then:
        estTotal.fusionReadyCount >= addresses.size()

        when:
        def fTxHash = sut2.sendFusionTransaction(5000000, 2, wallet2)
        waitForTransactionConfirmation(sut2, fTxHash)

        then:
        addresses.each {
            sut2.getBalance(it).availableBalance == 0
        }


    }

    private static String generatePaymentId() {
        def r = new Random()
        def result = (0..<64).collect { r.nextInt(16) }
                .collect { Integer.toString(it, 16) }
                .join()
        return result
    }

    private static void waitForBalance(IridiumAPI api, String address, long threshold) {
        while (api.getBalance(address).availableBalance < threshold) {
            log.info("Waiting for valid balance >=$threshold on wallet $address...")
            sleep(1000)
        }
    }

    private static void waitForTransactionConfirmation(IridiumAPI api, String transactionHash) {
        while (api.getUnconfirmedTransactionHashes().contains(transactionHash)) {
            log.info("Waiting for tx $transactionHash to get confirmed...")
            sleep(1000)
        }
    }

    private static void waitForBlockHeightIncrement(IridiumAPI api, int increment) {
        def desiredHeight = api.getStatus().knownBlockCount + increment
        def currentHeight
        while ((currentHeight = api.getStatus().knownBlockCount) && currentHeight < desiredHeight) {
            log.info("Waiting for blockHeight>$desiredHeight, currently blockHeight=$currentHeight...")
            sleep(1000)
        }
    }

}
