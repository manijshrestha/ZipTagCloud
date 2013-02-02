package ziptagcloud

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class ZipCode {
	
	//Represents the acutal zipcode/postal code it could be 5 to 9 char long
	String zipCode

	static belongsTo = [state: State]
	
    static constraints = {
		zipCode unique: true
		zipCode size: 5..9
    }
	
	@Override
	String toString(){
		return zipCode
	}
}
