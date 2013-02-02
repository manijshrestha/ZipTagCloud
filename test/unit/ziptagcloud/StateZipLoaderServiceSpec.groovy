package ziptagcloud

import grails.plugin.spock.UnitSpec
import ziptagcloud.geonames.ZipSlurperService

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class StateZipLoaderServiceSpec extends UnitSpec{

	def "StateZipLoaderService returns full state list"(){
		setup:
		def stateZipLoaderService = new StateZipLoaderService()

		when:
		def stateList = stateZipLoaderService.getStates()

		then:
		stateList.size() == 51
	}

	def "ZipSlurperService is called for given state and data is populated"() {
		setup:
		mockDomain(State)
		def State minnesota = new State(stateCode: "MN", displayName: "Minnesota")

		//Mocking the Zip Slurper 
		ZipSlurperService mockZipSlurperService = Mock()
		1 * mockZipSlurperService.getZipsOfState("MN") >> ["55401", "55402"]

		//Creating the StateZipLoader Service and injecting in the mocked zip slurper
		def stateZipLoaderService = new StateZipLoaderService()
		stateZipLoaderService.zipSlurperService = mockZipSlurperService

		when:
		stateZipLoaderService.populate(minnesota)

		then:
		assert minnesota.zipCodes.size() == 2
		
		ZipCode expected = new ZipCode(zipCode: "55401", state: minnesota);
		assert minnesota.zipCodes.contains(expected)
	}
}
