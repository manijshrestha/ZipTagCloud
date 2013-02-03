package ziptagcloud

import grails.test.mixin.*
import org.junit.*
import grails.plugin.spock.ControllerSpec

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock(State)
class StateZipTagCloudControllerSpec extends ControllerSpec{

	def "index action lists all the States"() {
		setup:
		mockDomain(State, [stateOfMN])

		expect:
		controller.index() == [stateList: [stateOfMN]]

		where:
		stateOfMN = new State(stateCode: "MN", displayName: "Minnesota")
	}
	
	def "states are sorted in asc"() {
		setup:
		mockDomain(State, [stateOfWA, stateOfMN])

		expect:
		controller.index() == [stateList: [stateOfMN, stateOfWA]]

		where:
		stateOfMN = new State(stateCode: "MN", displayName: "Minnesota")
		stateOfWA = new State(stateCode: "WA", displayName: "Washington")
	}
	
	def "load calls populateAll when All is passed"() {
		setup:
		StateZipLoaderService mockStateZipLoaderService = Mock()
		1 * mockStateZipLoaderService.populateAll()
		
		controller.stateZipLoaderService = mockStateZipLoaderService
		controller.params.stateCode = "ALL"
		
		expect:
		controller.load() == [stateCode: "ALL"]
	}
	
	def "load calls populate on given state when specific state passed"() {
		setup:
		mockDomain(State, [stateOfMN])
		StateZipLoaderService mockStateZipLoaderService = Mock()
		1 * mockStateZipLoaderService.populate(stateOfMN)
		
		controller.stateZipLoaderService = mockStateZipLoaderService
		controller.params.stateCode = "MN"
		
		expect:
		controller.load() == [stateCode: "MN"]
		
		where:
		stateOfMN = new State(stateCode: "MN", displayName: "Minnesota")
	}
	
	def "clear calls clearAllZipCodes when All is passed"() {
		setup:
		StateZipLoaderService mockStateZipLoaderService = Mock()
		1 * mockStateZipLoaderService.clearAllZipCodes()
		
		controller.stateZipLoaderService = mockStateZipLoaderService
		controller.params.stateCode = "ALL"
		
		expect:
		controller.clear() == [stateCode: "ALL"]
	}
	
	def "clear calls clearZipCode on given state when specific state passed"() {
		setup:
		mockDomain(State, [stateOfMN])
		StateZipLoaderService mockStateZipLoaderService = Mock()
		1 * mockStateZipLoaderService.clearZipCodeForState(stateOfMN)
		
		controller.stateZipLoaderService = mockStateZipLoaderService
		controller.params.stateCode = "MN"
		
		expect:
		controller.clear() == [stateCode: "MN"]
		
		where:
		stateOfMN = new State(stateCode: "MN", displayName: "Minnesota")
	}
}
