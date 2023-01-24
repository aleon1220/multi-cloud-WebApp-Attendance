   /**
    * Class Main connects to main.xhtml
    * calls Web services to obtain data from random Free services online
    * obtains IP and Random Bible Verse
    */

package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.MainService;
 
@ManagedBean(name = "main")
@SessionScoped

public class Main{
    
    private String bibleVerse;
    private String linkedPage;
    private String geoIpWS;
    
    @PostConstruct
    public void init() {
        MainService invoker = new MainService(); 
        //invoke services
        setGeoIpWS(invoker.invokeWSDLForIP());
        setBibleVerse(invoker.invokeRestForBibleVerse());
    }
    
    public String getGeoIpWS() {
        return geoIpWS;
    }

    public void setGeoIpWS(String geoIpWS) {
        this.geoIpWS = geoIpWS;
    }

    public String getBibleVerse() {
        return bibleVerse;
    }

    public void setBibleVerse(String bibleVerse) {
        this.bibleVerse = bibleVerse;
    }

    public void setLinkedPage(String linkedPage) {
        this.linkedPage = linkedPage;
    }
    
    public String getlinkedPage() {
        if (linkedPage=="1") {
            
            linkedPage = "attendance";
            System.out.println("this block page is-->"+linkedPage);
        } else {
            linkedPage = "registerAttendance";
            System.out.println("this else block page is-->"+linkedPage);
        }
        return linkedPage;
    }
}