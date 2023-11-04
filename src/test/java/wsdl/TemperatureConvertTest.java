package wsdl;
import service.clients.wsdl.temperatureConvert.TempConvertSoap;
import service.clients.wsdl.temperatureConvert.TempConvert;
import org.junit.Assert;
import org.junit.Test;

public class TemperatureConvertTest {
    @Test
    public void testCelsiusToFahrenheit() {
        // Create an instance of the service
        TempConvert service = new TempConvert();
        TempConvertSoap port = service.getTempConvertSoap();

        // Test conversion from Celsius to Fahrenheit
        String celsius = "100";
        String expectedFahrenheit = "212";
        String actualFahrenheit = port.celsiusToFahrenheit(celsius);

        // Assert that the expected Fahrenheit value matches the actual Fahrenheit value
        Assert.assertEquals(expectedFahrenheit, actualFahrenheit);
    }
}
