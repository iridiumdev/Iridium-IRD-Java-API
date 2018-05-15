package cash.ird.walletd;

import cash.ird.walletd.model.body.*;
import cash.ird.walletd.model.request.BlockIndexRange;
import cash.ird.walletd.model.request.BlockRange;
import cash.ird.walletd.model.request.Key;
import cash.ird.walletd.rpc.exception.IridiumWalletdException;

import java.util.List;

public interface IridiumAPI {

    String RPC_VERSION = "2.0";

    boolean reset() throws IridiumWalletdException;

    boolean reset(String viewSecretKey) throws IridiumWalletdException;


    boolean save() throws IridiumWalletdException;


    String getViewKey() throws IridiumWalletdException;


    SpendKeyPair getSpendKeys(String address) throws IridiumWalletdException;


    Status getStatus() throws IridiumWalletdException;


    List<String> getAddresses() throws IridiumWalletdException;


    String createAddress() throws IridiumWalletdException;

    String createAddress(Key key) throws IridiumWalletdException;


    boolean deleteAddress(String address) throws IridiumWalletdException;


    Balance getBalance() throws IridiumWalletdException;


    Balance getBalance(String address) throws IridiumWalletdException;


    List<String> getBlockHashes(BlockIndexRange blockIndexRange) throws IridiumWalletdException;


    <T extends  BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange) throws IridiumWalletdException;

    <T extends  BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses) throws IridiumWalletdException;

    <T extends  BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException;



    <T extends  BlockRange> List<TransactionItemBag> getTransactions(T blockRange) throws IridiumWalletdException;

    <T extends  BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses) throws IridiumWalletdException;

    <T extends  BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException;


    List<String> getUnconfirmedTransactionHashes() throws IridiumWalletdException;

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
