package ziptagcloud

import ziptagcloud.geonames.ZipSlurperService
import groovy.time.*
import groovyx.gpars.GParsPool

class StateZipLoaderService {


	ZipSlurperService zipSlurperService

	/**
	 * Populates the state with proper zip
	 * 
	 * @param state
	 */
	 void populate(State state){
		def startTime = new Date()

		def zipsOfState = zipSlurperService.getZipsOfState(state.stateCode)
		State.withNewSession {
			zipsOfState.each{
				log.debug "Parsing ${state} : Zip: ${it}"
				def persistantZipCode = ZipCode.findByZipCode(it)
				if (!persistantZipCode){
					ZipCode zipCode = new ZipCode([zipCode: it, state: state])
					if (zipCode.validate()){
						state.addToZipCodes(zipCode)
						zipCode.save()
					}
					else
						zipCode.discard()
				}
				else
					log.debug "${it} already in database."
			}
		}
		log.debug("Processed ${state} in: " + TimeCategory.minus( new Date(), startTime) )
	}

	/**
	 * Populates all the state with zipcode information
	 */
	void populateAll() {
		def states = State.getAll()
		GParsPool.withPool {
			states.eachParallel{ populate(it) }
		}
	}


	/**
	 * Clears zip codes of a state
	 */
	void clearZipCodeForState(State state){
		def zipCodes = ZipCode.findAllByState(state)
			zipCodes.each{
				state.removeFromZipCodes(it)
				it.delete()
			}
	}

	/**
	 * Clears all the zipcode from State
	 */
	void clearAllZipCodes() {
		def states = State.getAll()
		states.each{ clearZipCodeForState(it) }
	}
}
