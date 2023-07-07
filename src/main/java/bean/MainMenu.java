   /**
    * Class MainMenu and controls main menu navigation
    * links to mainMenu bean and .xhtml file.
    */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "mainMenu")
@SessionScoped
public class MainMenu{
    
    private String linkedPage;
    
    public void setLinkedPage(String linkedPage) {
        this.linkedPage = linkedPage;
    }
    
    public String getlinkedPage() {
        if (linkedPage=="1") {
            linkedPage = "attendance";
            System.out.println("this page is-->"+linkedPage);
        } else {
            linkedPage = "registerAttendance";
            System.out.println("this else page is-->"+linkedPage);
        }
        System.out.println("Return final value:-->"+linkedPage);
        return linkedPage;
    }
}