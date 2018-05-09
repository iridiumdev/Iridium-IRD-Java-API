package cash.ird.walletd;

import cash.ird.walletd.model.body.Status;

public interface IridiumAPI {

    boolean reset(String viewSecretKey);

    boolean save();

    String getViewKey();

    Status getStatus(String address);

    // TODO: 09.05.18 add missing api methods

}
