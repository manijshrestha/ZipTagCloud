package ziptagcloud;

import spock.lang.Specification

class ConstraintValidationSpec extends Specification {
	
	/**
	 * Validates the #field for #error in given #obj
	 */
	void validateConstraints(obj, field, error) {
		def validated = obj.validate()
		if (error && error != 'valid') {
			assert !validated
			assert obj.errors[field]
			assert error == obj.errors[field]
		} else {
			assert !obj.errors[field]
		}
	}
	
}
