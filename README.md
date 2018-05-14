# iridium-java-api

[![Build Status](https://travis-ci.org/danielclasen/iridium-java-api.svg?branch=master)](https://travis-ci.org/danielclasen/iridium-java-api)

Java wrapper for the [Iridium Walletd JSON RPC API](https://wiki.ird.cash/iridium_walletd_rpc_call "Iridium Wiki").
Method names are 100% compatible but there are some small differences in the signatures (see: [IridiumAPI.java](src/main/java/cash/ird/walletd/IridiumAPI.java)) to avoid signature clashes.
All return types are unwrapped from any kind of envelope and can therefore be used straight away. 

## Usage

### Plain Java
//todoc - gradle dependency \
//todoc - maven dependency

Create a new Instance of the [IridiumClient.java](src/main/java/cash/ird/walletd/IridiumClient.java):

    IridiumAPI iridium = new IridiumClient("localhost", 14007);
    
Or if your `iridium_walletd` is running on `localhost` with the default port of `14007` just use the default constructor:

    IridiumAPI iridium = new IridiumClient();
    


### Spring Boot
//todoc - spring boot starter

## Tests

### Integration Tests

To run the integration tests first start the embedded testnet from the `docker-compose.yml` in the projects root:

    docker-compose up
    
This will generate a few docker containers running three `iridiumd` nodes, two `irididum_walletd` instances and generates two new wallets which are used in the walletd instances.
Additionally two `iridium_miner` instances will be launched to mine blocks during the tests. This is necessary to get some IRDs in our test wallets and approval on test transactions. Don't worry mining won't take long in a testnet setup ;)

After the docker-compose is started, leave it running for a few minutes and then execute the tests from [IridiumClientTest.groovy](src/test/groovy/cash/ird/walletd/IridiumClientTest.groovy).
To do so you can use your favorite IDE to just run the spock tests or run the gradle test task from the projects root with:

    ./gradlew test