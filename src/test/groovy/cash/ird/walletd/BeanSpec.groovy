package cash.ird.walletd

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.meanbean.test.BeanTester
import spock.lang.Specification

import java.lang.reflect.ParameterizedType

abstract class BeanSpec<T> extends Specification {

    Class<T> getBeanClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
    }

    def "Check bean spec"() {
        expect:
        new BeanTester().testBean(getBeanClass())
    }

    def "Check equals contract"() {
        given:
        def equalsVerifier = EqualsVerifier
                .forClass(getBeanClass())
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)

        customizeEqualsVerifier(equalsVerifier)

        expect:
        equalsVerifier.verify()
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    protected <T> EqualsVerifier<T> customizeEqualsVerifier(EqualsVerifier<T> equalsVerifier){
        return equalsVerifier
    }

}
