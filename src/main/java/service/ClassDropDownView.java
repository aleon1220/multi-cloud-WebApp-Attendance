   /**
    * Class ClassDropDownView
    * creating UI in primeFaces a dropDownView
    */

package service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import jakarta.annotation.PostConstruct;
public class ClassDropDownView implements Serializable {
     
    /**
     * Populates values
     */
    private static final long serialVersionUID = 1L;
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String country; 
    private String city;  
    private Map<String,String> countries;
    private Map<String,String> cities;
     
    @PostConstruct
    public void init() {
        countries  = new HashMap<String, String>();
        countries.put("USA", "USA");
        countries.put("Germany", "Germany");
        countries.put("Brazil", "Brazil");
         
        Map<String,String> map = new HashMap<String, String>();
        map.put("New York", "New York");
        map.put("San Francisco", "San Francisco");
        map.put("Denver", "Denver");
        data.put("USA", map);
         
        map = new HashMap<String, String>();
        map.put("Berlin", "Berlin");
        map.put("Munich", "Munich");
        map.put("Frankfurt", "Frankfurt");
        data.put("Germany", map);
         
        map = new HashMap<String, String>();
        map.put("Sao Paolo", "Sao Paolo");
        map.put("Rio de Janerio", "Rio de Janerio");
        map.put("Salvador", "Salvador");
        data.put("Brazil", map);
    }
 
    public Map<String, Map<String, String>> getData() {
        return data;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public Map<String, String> getCountries() {
        return countries;
    }
 
    public Map<String, String> getCities() {
        return cities;
    }
 
    public void onCountryChange() {
        if(country !=null && !country.equals(""))
            cities = data.get(country);
        else
            cities = new HashMap<String, String>();
    }
     
    public void displayLocation() {
        System.out.println("Java Faces display message in the browser");
    }
}