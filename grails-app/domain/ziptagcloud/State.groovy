package ziptagcloud

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class State {
	//Represents the actual Name of the state i.e. "Minnesota"
	String displayName
	//Represents the 2 char state code i.e. "CA"
	String stateCode
	
	static hasMany = [zipCodes: ZipCode]

    static constraints = {
		displayName unique: true
		stateCode unique: true
		stateCode size: 2..2
    }
	
	@Override
	String toString() {
		return stateCode
	}
}
