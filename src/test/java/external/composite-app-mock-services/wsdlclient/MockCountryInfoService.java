import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.example.countryinfo.CountryInfoService;

public class MockCountryInfoService implements CountryInfoService {
    // Implement the methods from the CountryInfoService interface here

    public static void main(String[] args) {
        MockCountryInfoService mockService = new MockCountryInfoService();

        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(CountryInfoService.class);
        factory.setAddress("http://localhost:8080/");
        factory.setServiceBean(mockService);
        factory.create();
    }
}
