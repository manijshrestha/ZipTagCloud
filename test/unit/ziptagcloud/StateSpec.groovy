package ziptagcloud;

import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(State)
class StateSpec extends ConstraintValidationSpec {

	@Unroll("State valiation Test for #field using #val results in #error validation")
	def "state constraint test"() {
		setup:
		mockForConstraintsTests(State, [new State(stateCode: "MN", displayName: "Minnesota")])
		
		when:
		def state = new State("$field": val)

		then:
		validateConstraints(state, field, error)

		where:
		error	| field	|val
		'size'	| 'stateCode'	| 'M'
		'unique'| 'stateCode'	| 'MN'
		'unique'| 'displayName'	| 'Minnesota'
		'valid'	| 'displayName'	| 'California'
		'valid'	| 'stateCode'	| 'TX'
	}
}
