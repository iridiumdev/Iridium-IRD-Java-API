package cash.ird.walletd;

import cash.ird.walletd.model.body.*;
import cash.ird.walletd.model.request.BlockHashRange;
import cash.ird.walletd.model.request.BlockIndexRange;
import cash.ird.walletd.model.request.BlockRange;
import cash.ird.walletd.model.request.Key;
import cash.ird.walletd.rpc.exception.IridiumWalletdException;

import java.util.List;

public interface IridiumAPI {

    String RPC_VERSION = "2.0";

    /**
     * The reset method allows you to re-sync your wallet.
     *
     * @return true in case of success and
     * @throws IridiumWalletdException in case of an error
     */
    boolean reset() throws IridiumWalletdException;

    /**
     * The reset method allows you to re-sync your wallet.
     *
     * @param viewSecretKey -  If the view_secret_key argument is specified, the reset method substitutes the existing wallet with a new one with the specified view_secret_key and creates an address for it.
     * @return true in case of success and
     * @throws IridiumWalletdException in case of an error
     */
    boolean reset(String viewSecretKey) throws IridiumWalletdException;

    /**
     * Save changes into your wallet file.
     *
     * @return true in case of success and
     * @throws IridiumWalletdException in case of an error
     */
    boolean save() throws IridiumWalletdException;

    /**
     * Get the view-only address key.
     *
     * @return the view-only address key
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String getViewKey() throws IridiumWalletdException;

    /**
     * Return address spend keys.
     *
     * @param address Valid and existing address in container
     * @return {@link SpendKeyPair} of the given address
     * @throws IridiumWalletdException in case of an RPC Error
     */
    SpendKeyPair getSpendKeys(String address) throws IridiumWalletdException;

    /**
     * Get information about the current RPC Wallet state
     *
     * @return {@link Status} informations about the current Iridium RPC Wallet state: block_count, known_block_count, last_block_hash and peer_count.
     * @throws IridiumWalletdException in case of an RPC Error
     */
    Status getStatus() throws IridiumWalletdException;


    /**
     * Get all addresses of the current wallet container.
     *
     * @return an array of the Iridium RPC wallet's addresses of the current container
     * @throws IridiumWalletdException in case of an RPC Error
     */
    List<String> getAddresses() throws IridiumWalletdException;

    /**
     * Create a new address on the current wallet container.
     *
     * @return the new address
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String createAddress() throws IridiumWalletdException;

    /**
     * Create a new address on the current wallet container with either a {@link cash.ird.walletd.model.request.PrivateKey} or {@link cash.ird.walletd.model.request.PublicKey}
     *
     * @return the new address
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String createAddress(Key key) throws IridiumWalletdException;


    /**
     * Delete a specified address.
     *
     * @param address the address to be deleted
     * @return true in case of success and
     * @throws IridiumWalletdException in case of an error
     */
    boolean deleteAddress(String address) throws IridiumWalletdException;


    /**
     * Get the cumulative balance of all addresses in the current wallet container.
     *
     * @return Sum of {@link Balance} for all addresses
     * @throws IridiumWalletdException in case of an RPC Error
     */
    Balance getBalance() throws IridiumWalletdException;

    /**
     * Get the balance of a given addresses in the current wallet container.
     *
     * @param address the address to get the balance of
     * @return the {@link Balance}
     * @throws IridiumWalletdException in case of an RPC Error
     */
    Balance getBalance(String address) throws IridiumWalletdException;

    /**
     * Get an array of block hashes for a specified block range.
     *
     * @param blockIndexRange the given range
     * @return a list of blockHashes
     * @throws IridiumWalletdException in case of an RPC Error
     */
    List<String> getBlockHashes(BlockIndexRange blockIndexRange) throws IridiumWalletdException;

    /**
     * Get an array of bags of block and transaction hashes
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @return an array of bags of block and transaction hashes
     * @throws IridiumWalletdException in case of an RPC Error
     */
    <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange) throws IridiumWalletdException;

    /**
     * Get an array of bags of block and transaction hashes of transactions that contain transfer from at least one of specified addresses.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses  the given list of addresses
     * @return an array of bags of block and transaction hashes
     * @throws IridiumWalletdException in case of an RPC Error
     */
    <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses) throws IridiumWalletdException;

    /**
     * Get an array of bags of block and transaction hashes of transactions that contain transfer from at least one of specified addresses and the paymentId.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses  the given list of addresses
     * @param paymentId  the given paymentId
     * @return an array of bags of block and transaction hashes
     * @throws IridiumWalletdException in case of an RPC Error
     */
    <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException;

    /**
     * Get information about the transactions for a given blockRange
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @return an array of bags of blockHashes and transactions
     * @throws IridiumWalletdException in case of an RPC Error
     */
    <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange) throws IridiumWalletdException;

    /**
     * Get information about the transactions for a given blockRange that contain transfer from at least one of specified addresses.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses  the given list of addresses
     * @return an array of bags of blockHashes and transactions
     * @throws IridiumWalletdException in case of an RPC Error
     */
    <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses) throws IridiumWalletdException;

    /**
     * Get information about the transactions for a given blockRange that contain transfer from at least one of specified addresses and the paymentId.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses  the given list of addresses
     * @param paymentId  the given paymentId
     * @return an array of bags of blockHashes and transactions
     * @throws IridiumWalletdException in case of an RPC Error
     */
    <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException;

    /**
     * Get the currently yet unconfirmed transaction hashes.
     *
     * @return a list of transaction hashes
     * @throws IridiumWalletdException in case of an RPC Error
     */
    List<String> getUnconfirmedTransactionHashes() throws IridiumWalletdException;

    /**
     * Get the currently yet unconfirmed transaction hashes for transactions that contain transfer from at least one of specified addresses.
     *
     * @param addresses the given list of addresses
     * @return a list of transaction hashes
     * @throws IridiumWalletdException in case of an RPC Error
     */
    List<String> getUnconfirmedTransactionHashes(List<String> addresses) throws IridiumWalletdException;

    /**
     * Get the specified transaction.
     *
     * @param transactionHash the hash of the desired transaction
     * @return a {@link Transaction}
     * @throws IridiumWalletdException in case of an RPC Error
     */
    Transaction getTransaction(String transactionHash) throws IridiumWalletdException;

    /**
     * Creates and send a transaction to one or several addresses while withdrawing funds from multiple addresses. It also allow you to use a payment_id for a transaction to a single address.
     *
     * @param transfers a list of target addresses with the corresponding amount of IRDs
     * @param fee the transaction fee - Minimal fee in Iridium is 0.0005 IRD. This parameter should be specified in minimal available IRD units. For example, if your fee is 0.0005 IRD, you should pass it as 5000
     * @param anonymity privacy level (mixin). Level 6 is the highest
     * @param changeAddress valid and existing address in this wallet container
     * @param addresses list of addresses to take the funds from, has to be present in the current wallet container
     * @param extra String of variable length. Can contain A-Z, 0-9 characters.
     * @param unlockTime Height of the block until which transaction is locked.
     * @param paymentId a string used to identify the payment - 64 characters HEX
     * @return the transactionHash of this new transaction
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String sendTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;

    /**
     * Creates and send a transaction to one or several addresses  while withdrawing funds from only one address. It also allow you to use a payment_id for a transaction to a single address.
     *
     * @param transfers a list of target addresses with the corresponding amount of IRDs
     * @param fee the transaction fee - Minimal fee in Iridium is 0.0005 IRD. This parameter should be specified in minimal available IRD units. For example, if your fee is 0.0005 IRD, you should pass it as 5000
     * @param anonymity privacy level (mixin). Level 6 is the highest
     * @param address a single address to take the funds from, has to be present in the current wallet container
     * @param extra String of variable length. Can contain A-Z, 0-9 characters.
     * @param unlockTime Height of the block until which transaction is locked.
     * @param paymentId a string used to identify the payment - 64 characters HEX
     * @return the transactionHash of this new transaction
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String sendTransaction(List<Transfer> transfers, long fee, int anonymity, String address, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;

    /**
     * Creates but not send a transaction.
     *
     * @param transfers a list of target addresses with the corresponding amount of IRDs
     * @param fee the transaction fee - Minimal fee in Iridium is 0.0005 IRD. This parameter should be specified in minimal available IRD units. For example, if your fee is 0.0005 IRD, you should pass it as 5000
     * @param anonymity privacy level (mixin). Level 6 is the highest
     * @param changeAddress valid and existing address in this wallet container
     * @param addresses list of addresses to take the funds from, has to be present in the current wallet container
     * @param extra String of variable length. Can contain A-Z, 0-9 characters.
     * @param unlockTime Height of the block until which transaction is locked.
     * @param paymentId a string used to identify the payment - 64 characters HEX
     * @return the transactionHash of this new transaction
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String createDelayedTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;

    /**
     * @param transfers a list of target addresses with the corresponding amount of IRDs
     * @param fee the transaction fee - Minimal fee in Iridium is 0.0005 IRD. This parameter should be specified in minimal available IRD units. For example, if your fee is 0.0005 IRD, you should pass it as 5000
     * @param anonymity privacy level (mixin). Level 6 is the highest
     * @param address a single address to take the funds from, has to be present in the current wallet container
     * @param extra String of variable length. Can contain A-Z, 0-9 characters.
     * @param unlockTime Height of the block until which transaction is locked.
     * @param paymentId a string used to identify the payment - 64 characters HEX
     * @return the transactionHash of this new transaction
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String createDelayedTransaction(List<Transfer> transfers, long fee, int anonymity, String address, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;


    /**
     * Get th hashes of all delayed transactions (which were created with one of the createDelayedTransaction methods).
     *
     * @return a list of delayed transactionHashes
     * @throws IridiumWalletdException in case of an RPC Error
     */
    List<String> getDelayedTransactionHashes() throws IridiumWalletdException;

    /**
     * Delete a specified delayed transaction.
     *
     * @param transactionHash the transaction to be deleted
     * @return true in case of success and
     * @throws IridiumWalletdException in case of an error
     */
    boolean deleteDelayedTransaction(String transactionHash) throws IridiumWalletdException;

    /**
     * Send a specified delayed transaction.
     *
     * @param transactionHash the transaction to be send
     * @return true in case of success and
     * @throws IridiumWalletdException in case of an error
     */
    boolean sendDelayedTransaction(String transactionHash) throws IridiumWalletdException;

    /**
     * Create and send a fusion transaction. A fusion transaction is a fee-free transaction from yourself to yourself to merge (or fuse) funds from various addresses in the current wallet container on one address.
     * If there aren't any outputs that can be optimized, sendFusionTransaction will return an error. You can use the estimateFusion method to check the outputs, available for the optimization.
     *
     * @param threshold Value that determines which outputs will be optimized. Only the outputs, lesser than the threshold value, will be included into a fusion transaction.
     * @param anonymity Privacy level (mixin). Level 6 is the highest.
     * @param addresses List of addresses which should be considered to merge the funds from
     * @param destinationAddress address to receive the merged funds
     * @return the transactionHash of this fusion transaction
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String sendFusionTransaction(long threshold, int anonymity, List<String> addresses, String destinationAddress) throws IridiumWalletdException;


    /**
     * Create and send a fusion transaction considering all available addresses in the current wallet container. A fusion transaction is a fee-free transaction from yourself to yourself to merge (or fuse) funds from various addresses in the current wallet container on one address.
     * If there aren't any outputs that can be optimized, sendFusionTransaction will return an error. You can use the estimateFusion method to check the outputs, available for the optimization.
     *
     * @param threshold Value that determines which outputs will be optimized. Only the outputs, lesser than the threshold value, will be included into a fusion transaction.
     * @param anonymity Privacy level (mixin). Level 6 is the highest.
     * @param address address to receive the merged funds
     * @return the transactionHash of this fusion transaction
     * @throws IridiumWalletdException in case of an RPC Error
     */
    String sendFusionTransaction(long threshold, int anonymity, String address) throws IridiumWalletdException;

    /**
     * Estimate a number of outputs that can be optimized with fusion transactions
     *
     * @param threshold Value that determines which outputs will be optimized. Only the outputs, lesser than the threshold value, will be included into a fusion transaction.
     * @param addresses List of addresses which should be considered to estimate a fusion from
     * @return an {@link EstimatedFusion} to understand if a fusion transaction can be created. If fusionReadyCount returns a value = 0, then a fusion transaction cannot be created.
     * @throws IridiumWalletdException in case of an RPC Error
     */
    EstimatedFusion estimateFusion(long threshold, List<String> addresses) throws IridiumWalletdException;

    /**
     * Estimate a number of outputs that can be optimized with fusion transactions considering all addresses in the current wallet container
     *
     * @param threshold Value that determines which outputs will be optimized. Only the outputs, lesser than the threshold value, will be included into a fusion transaction.
     * @return an {@link EstimatedFusion} to understand if a fusion transaction can be created. If fusionReadyCount returns a value = 0, then a fusion transaction cannot be created.
     * @throws IridiumWalletdException in case of an RPC Error
     */
    EstimatedFusion estimateFusion(long threshold) throws IridiumWalletdException;


}
