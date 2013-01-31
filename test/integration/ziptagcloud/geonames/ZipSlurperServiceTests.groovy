package ziptagcloud.geonames

import grails.test.mixin.*
import org.junit.*

import ziptagcloud.geonames.ZipSlurperService;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ZipSlurperService)
class ZipSlurperServiceTests {

	void testEndPointHealth() {
		List zipCodes = service.getZipsOfState("MN", 3)
		assert zipCodes.size == 3
		assert zipCodes.contains("55082")
	}
}
