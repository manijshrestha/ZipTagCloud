package ziptagcloud.geonames

import wslite.rest.*

class ZipSlurperService {

	/**
	 * Returns a list of zip codes for given state
	 * 
	 * @param state abbreviation to search for 
	 * @param size number of zips to pull (max of 500)
	 * @return list of zip codes as returned by the configured endpoint
	 */
	List getZipsOfState(String state, int size) {
		def zips = []

		def client = new RESTClient("http://api.geonames.org/postalCodeSearchJSON?username=manijshrestha")
		def response = client.get(query:[placename: state, maxRows: size])
		response.json.postalCodes.each(){ zips << it.postalCode }

		return zips
	}

	/**
	 * Returns a list of zip codes for given state with max results set to 500
	 * currently geonames supports up to 500 zips on free account
	 *  
	 * {@link ZipSlurperService#getZipsForState(String, int)}
	 */
	List getZipsOfState(String state) {
		this.getZipsForState(state, 500)
	}
}
