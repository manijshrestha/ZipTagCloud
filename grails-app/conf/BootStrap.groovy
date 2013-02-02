import ziptagcloud.State
import ziptagcloud.ZipCode

class BootStrap {

    def init = { servletContext ->
		if(!State.count()) {
			State mn = new State(stateCode:"MN", displayName: "Minnesota")
			ZipCode mnZip1 = new ZipCode(zipCode: "55401", state: mn)
			ZipCode mnZip2 = new ZipCode(zipCode: "55123", state: mn)
			ZipCode mnZip3 = new ZipCode(zipCode: "55437", state: mn)
			mn.addToZipCodes(mnZip1)
			mn.addToZipCodes(mnZip2)
			mn.addToZipCodes(mnZip3)
			mn.save()
			
			State wi = new State(stateCode:"WI", displayName: "Wisconsin")
			ZipCode wiZip1 = new ZipCode(zipCode:"54301", state: wi)
			ZipCode wiZip2 = new ZipCode(zipCode:"54101", state: wi)
			wi.addToZipCodes(wiZip1)
			wi.addToZipCodes(wiZip2)
			wi.save()
			
			State ia = new State(stateCode:"IA", displayName: "Iowa")
			ZipCode iaZip1 = new ZipCode(zipCode: "52101", state: ia)
			ia.addToZipCodes(iaZip1)
			ia.save()
		}
    }
    def destroy = {
    }
}
