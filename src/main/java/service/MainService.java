   /**
    * Class MainService
    * invokes two external Web Services
    */

package service;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class MainService {
    
    public String invokeWSDLForIP() {
        // GeoIPService service = new GeoIPService();
        // GeoIP ipService = service.getGeoIPServiceSoap().getGeoIPContext();
        //String result = ipService.getCountryName().concat(", IP is: ")+ipService.getIP().concat("code details are: ")+ipService.getReturnCodeDetails();
        // String wsdlResult = ipService.getCountryCode().concat(": ")+ipService.getCountryName().concat(", IP is: ")+ipService.getIP().concat(" the return code details are: ")+ipService.getReturnCodeDetails();
        return "GeoIP Web Service No longer functions";
    }
    
    public String invokeRestForBibleVerse() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource service = client.resource(UriBuilder.fromUri("http://labs.bible.org/api/?passage=random").build());
        return service.get(String.class);
    }

}
