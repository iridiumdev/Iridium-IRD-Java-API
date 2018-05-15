package cash.ird.walletd;


import cash.ird.walletd.model.body.*;
import cash.ird.walletd.model.request.BlockIndexRange;
import cash.ird.walletd.model.request.BlockRange;
import cash.ird.walletd.model.request.Key;
import cash.ird.walletd.model.response.*;
import cash.ird.walletd.rpc.WalletdClient;
import cash.ird.walletd.rpc.exception.IridiumWalletdException;
import cash.ird.walletd.rpc.method.RequestMethod;
import lombok.NonNull;

import java.util.*;

public class IridiumClient implements IridiumAPI {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_PATH = "json_rpc";

    private final WalletdClient walletdClient;


    public IridiumClient() {
        this("localhost", 14007);
    }

    public IridiumClient(@NonNull String host, @NonNull int port) {
        this(String.format("%s://%s:%s/%s", DEFAULT_PROTOCOL, host, port, DEFAULT_PATH));
    }

    public IridiumClient(String url) {
        this.walletdClient = new WalletdClient(url);
    }

    @Override
    public boolean reset() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.RESET, NoopResponse.class) != null;
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
    public Status getStatus() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.GET_STATUS, StatusResponse.class);
    }

    @Override
    public List<String> getAddresses() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.GET_ADDRESSES, AddressListResponse.class);
    }

    @Override
    public String createAddress() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.CREATE_ADDRESS, AddressResponse.class);
    }

    @Override
    public String createAddress(Key key) throws IridiumWalletdException {
        if (key != null) {
            Map<String, Object> params = buildParams();
            if (key.isPrivate()) {
                params.put("secretSpendKey", key.getValue());
            } else {
                params.put("publicSpendKey", key.getValue());
            }

            return this.walletdClient.doRequest(RequestMethod.CREATE_ADDRESS, Collections.unmodifiableMap(params), AddressResponse.class);
        } else {
            return this.createAddress();
        }
    }

    @Override
    public boolean deleteAddress(String address) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();

        if (address != null) {
            params.put("address", address);
        } else {
            throw new IllegalArgumentException("address must not be null!");
        }

        return this.walletdClient.doRequest(RequestMethod.DELETE_ADDRESS, Collections.unmodifiableMap(params), NoopResponse.class) != null;
    }

    @Override
    public Balance getBalance() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.GET_BALANCE, BalanceResponse.class);
    }

    @Override
    public Balance getBalance(String address) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();

        if (address != null) {
            params.put("address", address);
        }

        return this.walletdClient.doRequest(RequestMethod.GET_BALANCE, Collections.unmodifiableMap(params), BalanceResponse.class);
    }

    @Override
    public List<String> getBlockHashes(BlockIndexRange blockIndexRange) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        params.put("firstBlockIndex", blockIndexRange.getStart());
        params.put("blockCount", blockIndexRange.getCount());

        return this.walletdClient.doRequest(RequestMethod.GET_BLOCK_HASHES, Collections.unmodifiableMap(params), BlockHashListResponse.class);
    }

    @Override
    public <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange) throws IridiumWalletdException {
        return this.getTransactionHashes(blockRange, null, null);
    }

    @Override
    public <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses) throws IridiumWalletdException {
        return this.getTransactionHashes(blockRange, addresses, null);
    }

    @Override
    public <T extends BlockRange> List<TransactionHashBag> getTransactionHashes(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();

        if (blockRange != null) {
            params.put(blockRange.getType().getParamName(), blockRange.getStart());
            params.put("blockCount", blockRange.getCount());
        } else {
            throw new IllegalArgumentException("blockRange must not be null!");
        }

        if (addresses != null && !addresses.isEmpty()) {
            params.put("addresses", addresses);
        }

        if (paymentId != null) {
            params.put("paymentId", paymentId);
        }

        return this.walletdClient.doRequest(RequestMethod.GET_TRANSACTION_HASHES, Collections.unmodifiableMap(params), TransactionHashBagListResponse.class);
    }


    @Override
    public <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange) throws IridiumWalletdException {
        return this.getTransactions(blockRange, null, null);
    }

    @Override
    public <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses) throws IridiumWalletdException {
        return this.getTransactions(blockRange, addresses, null);
    }

    @Override
    public <T extends BlockRange> List<TransactionItemBag> getTransactions(T blockRange, List<String> addresses, String paymentId) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();

        if (blockRange != null) {
            params.put(blockRange.getType().getParamName(), blockRange.getStart());
            params.put("blockCount", blockRange.getCount());
        } else {
            throw new IllegalArgumentException("blockRange must not be null!");
        }

        if (addresses != null && !addresses.isEmpty()) {
            params.put("addresses", addresses);
        }

        if (paymentId != null) {
            params.put("paymentId", paymentId);
        }

        return this.walletdClient.doRequest(RequestMethod.GET_TRANSACTIONS, Collections.unmodifiableMap(params), TransactionItemBagListResponse.class);
    }


    @Override
    public List<String> getUnconfirmedTransactionHashes() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.GET_UNCONFIRMED_TRANSACTION_HASHES, TransactionHashListResponse.class);
    }

    @Override
    public List<String> getUnconfirmedTransactionHashes(List<String> addresses) throws IridiumWalletdException {

        if (addresses != null && !addresses.isEmpty()) {
            Map<String, Object> params = buildParams();
            params.put("addresses", addresses);

            return this.walletdClient.doRequest(RequestMethod.GET_UNCONFIRMED_TRANSACTION_HASHES, Collections.unmodifiableMap(params), TransactionHashListResponse.class);
        } else {
            return this.getUnconfirmedTransactionHashes();
        }

    }

    @Override
    public Transaction getTransaction(String transactionHash) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        if (transactionHash != null) {

            params.put("transactionHash", transactionHash);
        } else {
            throw new IllegalArgumentException("transactionHash must not be null!");
        }

        return this.walletdClient.doRequest(RequestMethod.GET_TRANSACTION, Collections.unmodifiableMap(params), TransactionResponse.class);

    }

    @Override
    public String sendTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException {
        Map<String, Object> params = buildTxParams(transfers, fee, anonymity, changeAddress, addresses, extra, unlockTime, paymentId);
        return this.walletdClient.doRequest(RequestMethod.SEND_TRANSACTION, Collections.unmodifiableMap(params), TransactionHashResponse.class);
    }


    @Override
    public String sendTransaction(List<Transfer> transfers, long fee, int anonymity, String address, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException {
        return this.sendTransaction(transfers, fee, anonymity, null, Collections.singletonList(address), extra, unlockTime, paymentId);
    }

    @Override
    public String createDelayedTransaction(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException {
        Map<String, Object> params = buildTxParams(transfers, fee, anonymity, changeAddress, addresses, extra, unlockTime, paymentId);
        return this.walletdClient.doRequest(RequestMethod.CREATE_DELAYED_TRANSACTION, Collections.unmodifiableMap(params), TransactionHashResponse.class);
    }

    @Override
    public String createDelayedTransaction(List<Transfer> transfers, long fee, int anonymity, String address, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException {
        return this.createDelayedTransaction(transfers, fee, anonymity, null, Collections.singletonList(address), extra, unlockTime, paymentId);
    }


    @Override
    public List<String> getDelayedTransactionHashes() throws IridiumWalletdException {
        return this.walletdClient.doRequest(RequestMethod.GET_DELAYED_TRANSACTION_HASHES, TransactionHashListResponse.class);
    }

    @Override
    public boolean deleteDelayedTransaction(String transactionHash) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        if (transactionHash != null) {

            params.put("transactionHash", transactionHash);
        } else {
            throw new IllegalArgumentException("transactionHash must not be null!");
        }
        return this.walletdClient.doRequest(RequestMethod.DELETE_DELAYED_TRANSACTION, Collections.unmodifiableMap(params), NoopResponse.class) != null;
    }

    @Override
    public boolean sendDelayedTransaction(String transactionHash) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        if (transactionHash != null) {

            params.put("transactionHash", transactionHash);
        } else {
            throw new IllegalArgumentException("transactionHash must not be null!");
        }
        return this.walletdClient.doRequest(RequestMethod.SEND_DELAYED_TRANSACTION, Collections.unmodifiableMap(params), NoopResponse.class) != null;
    }

    @Override
    public String sendFusionTransaction(long threshold, int anonymity, List<String> addresses, String destinationAddress) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        params.put("threshold", threshold);
        params.put("anonymity", anonymity);

        List<String> containerAddresses = this.getAddresses();

        if (destinationAddress == null) {

            if (addresses == null || addresses.isEmpty()) {
                if (containerAddresses.size() == 1) {
                    params.put("destinationAddress", containerAddresses.get(0));
                } else {
                    throw new IllegalArgumentException("destinationAddress must not be null if no addresses specified and container has multiple addresses!");
                }
            } else {
                if (addresses.size() == 1) {
                    params.put("destinationAddress", addresses.get(0));
                } else {
                    throw new IllegalArgumentException("destinationAddress must not be null if multiple addresses specified!");
                }
            }

        } else {
            params.put("destinationAddress", destinationAddress);
        }

        return this.walletdClient.doRequest(RequestMethod.SEND_FUSION_TRANSACTION, Collections.unmodifiableMap(params), TransactionHashResponse.class);
    }

    @Override
    public String sendFusionTransaction(long threshold, int anonymity, String address) throws IridiumWalletdException {
        return this.sendFusionTransaction(threshold, anonymity, Collections.singletonList(address), null);
    }


    @Override
    public EstimatedFusion estimateFusion(long threshold, List<String> addresses) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();
        params.put("threshold", threshold);

        if (addresses != null && !addresses.isEmpty()) {
            params.put("addresses", addresses);
        }

        return this.walletdClient.doRequest(RequestMethod.ESTIMATE_FUSION, Collections.unmodifiableMap(params), EstimatedFusionResponse.class);
    }

    @Override
    public EstimatedFusion estimateFusion(long threshold) throws IridiumWalletdException {
        return this.estimateFusion(threshold, null);
    }



    private Map<String, Object> buildParams() {
        return new HashMap<>();
    }

    private Map<String, Object> buildTxParams(List<Transfer> transfers, long fee, int anonymity, String changeAddress, List<String> addresses, String extra, Long unlockTime, String paymentId) throws IridiumWalletdException {
        Map<String, Object> params = buildParams();

        if (transfers != null && !transfers.isEmpty()) {
            params.put("transfers", transfers);
        } else {
            throw new IllegalArgumentException("transfers must not be null or empty!");
        }

        params.put("fee", fee);
        params.put("anonymity", anonymity);

        if (changeAddress != null) {
            params.put("changeAddress", changeAddress);
        } else {

            List<String> containerAddresses = this.getAddresses();

            if (addresses == null || addresses.size() == 0) {
                if (containerAddresses.size() > 1) {
                    throw new IllegalArgumentException("changeAddress must not be null if no addresses specified and current container has more than 1 address!");
                } else if (containerAddresses.size() == 1) {
                    params.put("changeAddress", containerAddresses.get(0));
                } else {
                    throw new IllegalArgumentException("changeAddress must not be null, could not determine any container address!");
                }

            } else if (addresses.size() > 1 && containerAddresses.size() > 1){
                throw new IllegalArgumentException("changeAddress must not be null if multiple addresses specified and current container has more than 1 address!");
            } else {
                params.put("changeAddress", addresses.get(0));
            }

        }

        if (addresses != null && !addresses.isEmpty()) {
            params.put("addresses", addresses);
        }

        if (extra != null) {
            params.put("extra", extra);
        }

        if (unlockTime != null) {
            params.put("unlockTime", unlockTime);
        }

        if (paymentId != null) {
            params.put("paymentId", paymentId);
        }
        return params;
    }


}
