package ziptagcloud

class StateZipTagCloudController {

    def index() { 
		def state = new State()
		render(view: "/state-zip-tag-cloud/index", model: state)
	}
}
