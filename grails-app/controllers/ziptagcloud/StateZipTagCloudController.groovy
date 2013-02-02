package ziptagcloud

class StateZipTagCloudController {
	//static scaffold = State
    def index() {
		int count = State.getAll().size()
		render "We have ${count} States in the system."
	}
}
