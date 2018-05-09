package cash.ird.walletd;


import cash.ird.walletd.model.body.*;
import cash.ird.walletd.model.response.*;
import cash.ird.walletd.rpc.WalletdClient;
import cash.ird.walletd.rpc.exception.IridiumWalletdException;
import cash.ird.walletd.rpc.method.RequestMethod;

import java.util.*;

public class IridiumClient implements IridiumAPI {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_PATH = "json_rpc";

    private final WalletdClient walletdClient;


    public IridiumClient() {
        this("localhost", 14007);
    }

    public IridiumClient(String host, int port) {
        this(String.format("%s://%s:%s/%s", DEFAULT_PROTOCOL, host, port, DEFAULT_PATH));
    }

    public IridiumClient(String url) {
        this.walletdClient = new WalletdClient(url);
    }

    @Override
    public boolean reset(String viewSecretKey) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();

        if (viewSecretKey != null) {
            params.put("viewSecretKey", viewSecretKey);
        }

        return this.walletdClient.doRequest(RequestMethod.RESET, Collections.unmodifiableMap(params), NoopResponse.class) != null;
    }

    @Override
    public boolean save() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.SAVE, NoopResponse.class) != null;
    }

    @Override
    public String getViewKey() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.GET_VIEW_KEY, ViewKeyResponse.class);
    }

    @Override
    public SpendKeyPair getSpendKeys(String address) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        params.put("address", address);

        return this.walletdClient.doRequest(RequestMethod.GET_SPEND_KEYS, Collections.unmodifiableMap(params), SpendKeyResponse.class);
    }

    @Override
    public Status getStatus(String address) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        params.put("address", address);

        return this.walletdClient.doRequest(RequestMethod.GET_STATUS, Collections.unmodifiableMap(params), StatusResponse.class);
    }

    @Override
    public List<String> getAddresses() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.GET_ADDRESSES, AddressListResponse.class);
    }

    @Override
    public String createAddress(String secretSpendKey, String publicSpendKey) throws IridiumWalletdException {
        return null;
    }

    @Override
    public boolean deleteAddress(String address) throws IridiumWalletdException {
        return false;
    }

    @Override
    public Balance getBalance(String address) throws IridiumWalletdException {
        return null;
    }

    @Override
    public List<String> getBlockHashes(long firstBlockIndex, long blockCount) throws IridiumWalletdException {
        return null;
    }

    @Override
    public List<TxHashBag> getTransactionHashes(String blockHash, long blockCount, List<String> addresses, String paymentId) throws IridiumWalletdException {
        return null;
    }

    @Override
    public List<TxHashBag> getTransactionHashes(long firstBlockIndex, long blockCount, List<String> addresses, String paymentId) throws IridiumWalletdException {
        return null;
    }

    @Override
    public List<TxItemBag> getTransactions(String blockHash, long blockCount, List<String> addresses, String paymentId) throws IridiumWalletdException {
        return null;
    }

    @Override
    public List<TxItemBag> getTransactions(long firstBlockIndex, long blockCount, List<String> addresses, String paymentId) throws IridiumWalletdException {
        return null;
    }

    @Override
    public List<String> getUnconfirmedTransactionHashes(List<String> addresses) throws IridiumWalletdException {
        return null;
    }

    @Override
    public Transaction getTransaction(String transactionHash) throws IridiumWalletdException {
        return null;
    }

    @Override
    public String sendTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException {
        return null;
    }

    @Override
    public String createDelayedTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException {
        return null;
    }

    @Override
    public List<String> getDelayedTransactionHashes() throws IridiumWalletdException {
        return null;
    }

    @Override
    public boolean deleteDelayedTransaction(String transactionHash) throws IridiumWalletdException {
        return false;
    }

    @Override
    public boolean sendDelayedTransaction(String transactionHash) throws IridiumWalletdException {
        return false;
    }

    @Override
    public String sendFusionTransaction(long threshold, int anonymity, String destinationAddress, List<String> addresses) throws IridiumWalletdException {
        return null;
    }

    @Override
    public EstimatedFusion estimateFusion(long threshold, List<String> addresses) throws IridiumWalletdException {
        return null;
    }

    private Map<String, Object> buildParams() throws IridiumWalletdException {
        return new HashMap<>();
    }
}
