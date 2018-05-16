package cash.ird.walletd

import groovy.util.logging.Slf4j
import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.meanbean.test.BeanTester
import spock.lang.Specification

import java.lang.reflect.ParameterizedType

@Slf4j
abstract class BeanSpec<T> extends Specification {
    Class<T> getBeanClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
    }

    def checkBean(){
        return testBeanSpec() && testEqualsContract()
    }

    def testBeanSpec() {
        log.info("Checking bean spec of $beanClass")
        new BeanTester().testBean(getBeanClass())

        return true
    }

    def testEqualsContract() {
        def equalsVerifier = EqualsVerifier
                .forClass(getBeanClass())
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)

        customizeEqualsVerifier(equalsVerifier)

        log.info("Checking equals contract of $beanClass")
        equalsVerifier.verify()

        return true
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    protected <T> EqualsVerifier<T> customizeEqualsVerifier(EqualsVerifier<T> equalsVerifier){
        return equalsVerifier
    }
}
