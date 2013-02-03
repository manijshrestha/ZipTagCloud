package ziptagcloud

import grails.plugin.spock.UnitSpec
import ziptagcloud.geonames.ZipSlurperService

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class StateZipLoaderServiceSpec extends UnitSpec{

	def "ZipSlurperService is called for given state and data is populated"() {
		setup:
		mockDomain(State)
		mockDomain(ZipCode)
		
		State.metaClass.static.withNewSession = {Closure c -> c.call() }
		
		State minnesota = new State(stateCode: "MN", displayName: "Minnesota")

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
	
	def "All Zip codes are removed from a given state"() {
		setup:
		mockDomain(State)
		mockDomain(ZipCode)
		
		State iowa = new State(stateCode: "IA", displayName: "Iowa")
		ZipCode zipCode = new ZipCode(zipCode: "52101", state: iowa)
		iowa.addToZipCodes(zipCode)
		
		ZipCode.metaClass.static.findAllByState = { State state -> state.zipCodes}
		
		def stateZipLoaderService = new StateZipLoaderService()
		
		when:
		stateZipLoaderService.clearZipCodeForState(iowa)
		
		then:
		assert iowa.zipCodes.size() == 0
	}
}
