package ziptagcloud

import ziptagcloud.geonames.ZipSlurperService

class StateZipLoaderService {
	
	ZipSlurperService zipSlurperService

	//Returns the List of State
    Map<String, State> getStates() {
		def stateMap = [
		"AL":"Alabama",
		"AK":"Alaska",
		"AZ":"Arizona",
		"AR":"Arkansas",
		"CA":"California",
		"CO":"Colorado",
		"CT":"Connecticut",
		"DC":"District of Columbia",
		"DE":"Delaware",
		"FL":"Florida",
		"GA":"Georgia",
		"HI":"Hawaii",
		"ID":"Idaho",
		"IL":"Illinois",
		"IN":"Indiana",
		"IA":"Iowa",
		"KS":"Kansas",
		"KY":"Kentucky",
		"LA":"Louisiana",
		"ME":"Maine",
		"MD":"Maryland",
		"MA":"Massachusetts",
		"MI":"Michigan",
		"MN":"Minnesota",
		"MS":"Mississippi",
		"MO":"Missouri",
		"MT":"Montana",
		"NE":"Nebraska",
		"NV":"Nevada",
		"NH":"New Hampshire",
		"NJ":"New Jersey",
		"NM":"New Mexico",
		"NY":"New York",
		"NC":"North Carolina",
		"ND":"North Dakota",
		"OH":"Ohio",
		"OK":"Oklahoma",
		"OR":"Oregon",
		"PA":"Pennsylvania",
		"RI":"Rhode Island",
		"SC":"South Carolina",
		"SD":"South Dakota",
		"TN":"Tennessee",
		"TX":"Texas",
		"UT":"Utah",
		"VT":"Vermont",
		"VA":"Virginia",
		"WA":"Washington",
		"WI":"Wisconsin",
		"WV":"West Virginia",
		"WY":"Wyoming"]
		
		def stateList = [:]
		stateMap.each {
			State state = new State(stateCode: it.key, displayName: it.value)
			stateList.put(it.key, state)
		}
		
		return stateList
    }
	
	/**
	 * Populates the state with proper zip
	 * 
	 * @param state
	 */
	void populate(State state){
		def zipsOfState = zipSlurperService.getZipsOfState(state.stateCode)
		zipsOfState.each{
			ZipCode zipCode = new ZipCode([zipCode: it, state: state])
			state.addToZipCodes(zipCode)
		}
		state.save()
	}
	
	/**
	 * Populates all the state with zipcode information
	 */
	void populateAll() {
		def states = getStates()
		states.each{
			populate(it.value)
		}
	}
}
