package ziptagcloud

import grails.plugin.spock.UnitSpec
import ziptagcloud.ZipCode

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
class ZipCodeSpec extends UnitSpec{

	def "find ZipCode"() {
		setup:
		//mockDomain(State)
		mockDomain(ZipCode)

		when:
		//def mockState = new State(displayName: "California", stateCode: "CA")
		def mockZipCode = new ZipCode(zipCode: "90210")

		then:
		ZipCode.findByZipCode("90210") != null

	}
}
