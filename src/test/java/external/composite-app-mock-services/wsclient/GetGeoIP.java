/**
 * Class GetGeoIP imported from WSDL with java tool 
 * replicates WSDL to invoke External Web service
 */

package external.services.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ipAddress"
})
@XmlRootElement(name = "GetGeoIP")
public class GetGeoIP {

    @XmlElement(name = "IPAddress")
    protected String ipAddress;

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *         possible object is
     *         {@link String }
     * 
     */
    public String getIPAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *              allowed object is
     *              {@link String }
     * 
     */
    public void setIPAddress(String value) {
        this.ipAddress = value;
    }
}
