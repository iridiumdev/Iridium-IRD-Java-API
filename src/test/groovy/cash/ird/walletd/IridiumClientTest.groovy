package cash.ird.walletd

import cash.ird.walletd.model.body.Status
import spock.lang.Specification


class IridiumClientTest extends Specification {

    //todo fix tests to run against a mock instead of a real walletd

    void setup() {
    }


    def "Reset"() {
        given:
        def sut = new IridiumClient()

        when:
        Boolean success = sut.reset()

        then:
        success
    }

    def "GetStatus"() {
        given:
        def sut = new IridiumClient()

        when:
        Status status = sut.getStatus("ir3qQc31Jjn5m2oqWgzFuY8E3UgcwbKVCj57oCseDBhff4PQWwyN84uXxEzYEZCxxbhBq1iddSvJQTZHU5RnwN2p2HCSLiZeh")

        then:
        //noinspection GroovyDoubleNegation
        !!status.blockCount
    }
}
