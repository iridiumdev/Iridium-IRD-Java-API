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
     * @throws IridiumWalletdException
     */
    String getViewKey() throws IridiumWalletdException;

    /**
     * Return address spend keys.
     *
     * @param address Valid and existing address in container
     * @return {@link SpendKeyPair} of the given address
     * @throws IridiumWalletdException
     */
    SpendKeyPair getSpendKeys(String address) throws IridiumWalletdException;

    /**
     * Get information about the current RPC Wallet state
     *
     * @return {@link Status} informations about the current Iridium RPC Wallet state: block_count, known_block_count, last_block_hash and peer_count.
     * @throws IridiumWalletdException
     */
    Status getStatus() throws IridiumWalletdException;


    /**
     * Get all addresses of the current wallet container.
     *
     * @return an array of the Iridium RPC wallet's addresses of the current container
     * @throws IridiumWalletdException
     */
    List<String> getAddresses() throws IridiumWalletdException;

    /**
     * Create a new address on the current wallet container.
     *
     * @return the new address
     * @throws IridiumWalletdException
     */
    String createAddress() throws IridiumWalletdException;

    /**
     * Create a new address on the current wallet container with either a {@link cash.ird.walletd.model.request.PrivateKey} or {@link cash.ird.walletd.model.request.PublicKey}
     *
     * @return the new address
     * @throws IridiumWalletdException
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
     * @throws IridiumWalletdException
     */
    Balance getBalance() throws IridiumWalletdException;

    /**
     * Get the balance of a given addresses in the current wallet container.
     *
     * @param address
     * @return the {@link Balance}
     * @throws IridiumWalletdException
     */
    Balance getBalance(String address) throws IridiumWalletdException;

    /**
     * Get an array of block hashes for a specified block range.
     *
     * @param blockIndexRange the given range
     * @return a list of blockHashes
     * @throws IridiumWalletdException
     */
    List<String> getBlockHashes(BlockIndexRange blockIndexRange) throws IridiumWalletdException;

    /**
     * Get an array of bags of block and transaction hashes
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @return an array of bags of block and transaction hashes
     * @throws IridiumWalletdException
     */
    <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange) throws IridiumWalletdException;

    /**
     * Get an array of bags of block and transaction hashes of transactions that contain transfer from at least one of specified addresses.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses the given list of addresses
     * @return an array of bags of block and transaction hashes
     * @throws IridiumWalletdException
     */
    <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses) throws IridiumWalletdException;

    /**
     * Get an array of bags of block and transaction hashes of transactions that contain transfer from at least one of specified addresses and the paymentId.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses the given list of addresses
     * @param paymentId the given paymentId
     * @return an array of bags of block and transaction hashes
     * @throws IridiumWalletdException
     */
    <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException;

    /**
     * Get information about the transactions for a given blockRange
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @return an array of bags of blockHashes and transactions
     * @throws IridiumWalletdException
     */
    <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange) throws IridiumWalletdException;

    /**
     * Get information about the transactions for a given blockRange that contain transfer from at least one of specified addresses.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses the given list of addresses
     * @return an array of bags of blockHashes and transactions
     * @throws IridiumWalletdException
     */
    <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses) throws IridiumWalletdException;

    /**
     * Get information about the transactions for a given blockRange that contain transfer from at least one of specified addresses and the paymentId.
     *
     * @param blockRange can either be a {@link BlockIndexRange} or {@link BlockHashRange}
     * @param addresses the given list of addresses
     * @param paymentId the given paymentId
     * @return an array of bags of blockHashes and transactions
     * @throws IridiumWalletdException
     */
    <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException;

    /**
     * Get the currently yet unconfirmed transaction hashes.
     * @return a list of transaction hashes
     * @throws IridiumWalletdException
     */
    List<String> getUnconfirmedTransactionHashes() throws IridiumWalletdException;

    /**
     * Get the currently yet unconfirmed transaction hashes for transactions that contain transfer from at least one of specified addresses.
     * @param addresses the given list of addresses
     * @return a list of transaction hashes
     * @throws IridiumWalletdException
     */
    List<String> getUnconfirmedTransactionHashes(List<String> addresses) throws IridiumWalletdException;


    Transaction getTransaction(String transactionHash) throws IridiumWalletdException;


    String sendTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;

    String sendTransaction(List<Transfer> transfers, long fee, int anonymity, String address, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;


    String createDelayedTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;

    String createDelayedTransaction(List<Transfer> transfers, long fee, int anonymity, String address, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException;


    List<String> getDelayedTransactionHashes() throws IridiumWalletdException;


    boolean deleteDelayedTransaction(String transactionHash) throws IridiumWalletdException;


    boolean sendDelayedTransaction(String transactionHash) throws IridiumWalletdException;


    String sendFusionTransaction(long threshold, int anonymity, List<String> addresses, String destinationAddress) throws IridiumWalletdException;

    String sendFusionTransaction(long threshold, int anonymity, String address) throws IridiumWalletdException;


    EstimatedFusion estimateFusion(long threshold, List<String> addresses) throws IridiumWalletdException;

    EstimatedFusion estimateFusion(long threshold) throws IridiumWalletdException;


}
