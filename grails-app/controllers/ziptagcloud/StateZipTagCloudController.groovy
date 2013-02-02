package ziptagcloud

class StateZipTagCloudController {
	StateZipLoaderService stateZipLoaderService
	
	//static scaffold = State
    def index() {
		//stateZipLoaderService.populateAll()
		def states = State.getAll()
		int count = states.size()
		[stateList: states]
	}
}
