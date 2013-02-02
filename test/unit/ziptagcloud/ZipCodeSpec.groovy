package ziptagcloud;

import grails.test.mixin.TestFor
import spock.lang.Unroll

@TestFor(ZipCode)
class ZipCodeSpec extends ConstraintValidationSpec{
	
	def setup() {
		mockForConstraintsTests(ZipCode, [new ZipCode(zipCode: "55401")])
	}
	
	@Unroll("ZipCode valiation Test for #field using #val results in #error validation")
	def "zipcode constraint test"() {
		when:
		def zipCode = new ZipCode("$field": val)
		
		then:
		validateConstraints(zipCode, field, error)
		
		where:
		error	| field	|val
		'size'	| 'zipCode'	| '123'
		'size'	| 'zipCode'	| '1234567890'
		'unique'| 'zipCode'	| '55401'
		'valid'	| 'zipCode'	| '55404'
		
	}

}
