package cash.ird.walletd.rpc.method;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum RequestMethod {

    /**
     * reset	Re-synchronize your wallet
     * save	Save changes into your wallet file
     * getViewKey	Return the view-only address key.
     * getSpendKeys	Return address spend keys.
     * getStatus	Return informations about the current Iridium RPC Wallet state: block_count, known_block_count, last_block_hash and peer_count.
     * getAddresses	Return an array of the Iridium RPC wallet's addresses.
     * createAddress	Create an address.
     * deleteAddress	Delete a specified address.
     * getBalance	Return a balance for a specified address. If address is not specified, returns a cumulative balance of all wallet's addresses.
     * getBlockHashes	Return an array of block hashes for a specified block range.
     * getTransactionHashes	Return an array of block and transaction hashes
     * getTransactions	Return information about the transactions in specified block range or for specified addresses.
     * getUnconfirmedTransactionHashes	Return information about the current unconfirmed transaction pool or for a specified addresses.
     * getTransaction	Return information about the specified transaction.
     * sendTransaction	Creates and send a transaction.
     * createDelayedTransaction	Creates but not send a transaction.
     * getDelayedTransactionHashes	Return hashes of delayed transactions.
     * deleteDelayedTransaction	Delete a specified delayed transaction.
     * sendDelayedTransaction	Send a specified delayed transaction.
     * sendFusionTransaction	Create and send a fusion transaction.
     * estimateFusion	Estimate a number of outputs that can be optimized with fusion transactions
     */

    RESET("reset"),
    SAVE("save"),
    GET_VIEW_KEY("getViewKey"),
    GET_SPEND_KEYS("getSpendKeys"),
    GET_STATUS("getStatus"),
    GET_ADDRESSES("getAddresses"),
    CREATE_ADDRESS("createAddress"),
    DELETE_ADDRESS("deleteAddress"),
    GET_BALANCE("getBalance"),
    GET_BLOCK_HASHES("getBlockHashes"),
    GET_TRANSACTION_HASHES("getTransactionHashes"),
    GET_TRANSACTIONS("getTransactions"),
    GET_UNCONFIRMED_TRANSACTION_HASHES("getUnconfirmedTransactionHashes"),
    GET_TRANSACTION("getTransaction"),
    SEND_TRANSACTION("sendTransaction"),
    CREATE_DELAYED_TRANSACTION("createDelayedTransaction"),
    GET_DELAYED_TRANSACTION_HASHES("getDelayedTransactionHashes"),
    DELETE_DELAYED_TRANSACTION("deleteDelayedTransaction"),
    SEND_DELAYED_TRANSACTION("sendDelayedTransaction"),
    SEND_FUSION_TRANSACTION("sendFusionTransaction"),
    ESTIMATE_FUSION("estimateFusion");



    @JsonValue
    @Getter
    private final String name;


    RequestMethod(String name) {
        this.name = name;
    }
}
