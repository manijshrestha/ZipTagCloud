package ziptagcloud

class StateZipTagCloudController {
	
	def stateZipLoaderService
	
    def index() {
		def states = State.list(sort: 'displayName', order: "asc")
		int count = states.size()
		[stateList: states]
	}
	
	/**
	 * action that loads the zip code data
	 */
	def load() {
		String stateCode = params.stateCode

		if (stateCode.equalsIgnoreCase("ALL"))
			stateZipLoaderService.populateAll()
		else {
			def state = State.findByStateCode(stateCode)
			stateZipLoaderService.populate(state)
		}
	 
	    [stateCode: stateCode]
	}
	
	/**
	 * action to clear the zip code data
	 */
	def clear() {
		String stateCode = params.stateCode
		def state
		if(stateCode.equalsIgnoreCase("ALL"))
			stateZipLoaderService.clearAllZipCodes()
		else {
			state = State.findByStateCode(stateCode)
			stateZipLoaderService.clearZipCodeForState(state)
		}
		
		[stateCode: stateCode]
	}
}
