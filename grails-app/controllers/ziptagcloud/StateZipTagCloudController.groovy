package ziptagcloud

class StateZipTagCloudController {
	def stateZipLoaderService
	
	//static scaffold = State
    def index() {
		//stateZipLoaderService.populateAll()
		def states = State.getAll()
		int count = states.size()
		[stateList: states]
	}
	
	/**
	 * action that loads the data for given state
	 * @return
	 */
	def load() {
		String stateCode = params.stateCode
		
		def stateToLoad = State.findByStateCode(stateCode)
		
		stateZipLoaderService.populate(stateToLoad)
		render "State ${stateCode} Loaded."
	}
	
	/**
	 * action to clear the state data
	 * @return
	 */
	def clear() {
		String stateCode = params.stateCode
		
		def state = State.findByStateCode(stateCode)
		
		state?.
		render "Will clear ${stateCode}"
	}
}
