package cash.ird.walletd

import cash.ird.walletd.model.body.SpendKeyPair
import cash.ird.walletd.model.body.Status
import spock.lang.Specification

class IridiumClientTest extends Specification {

    //todo change tests to run against a mocked http json api instead of the real walletd

    private IridiumClient sut

    void setup() {
        sut = new IridiumClient()
    }


    def "Reset"() {
        when:
        Boolean success = sut.reset()

        then:
        success
    }

    def "GetStatus"() {
        when:
        Status status = sut.getStatus("ir3qQc31Jjn5m2oqWgzFuY8E3UgcwbKVCj57oCseDBhff4PQWwyN84uXxEzYEZCxxbhBq1iddSvJQTZHU5RnwN2p2HCSLiZeh")

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
        SpendKeyPair keyPair = sut.getSpendKeys("ir3qQc31Jjn5m2oqWgzFuY8E3UgcwbKVCj57oCseDBhff4PQWwyN84uXxEzYEZCxxbhBq1iddSvJQTZHU5RnwN2p2HCSLiZeh")

        then:
        keyPair != null
        keyPair.getPublicKey() != null
        keyPair.getSecretKey() != null
    }
}
