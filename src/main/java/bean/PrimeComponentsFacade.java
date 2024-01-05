   /**
    * Class MainMenu and controls main menu navigation
    * links to mainMenu bean and .xhtml file
    */

package bean;

import javax.inject.Named;

@Named
public class PrimeComponentsFacade{
    
    private String linkedPage;
    private String valuedId;

    public PrimeComponentsFacade() {
        String value = "some text";
    }
    
    public String getValuedId() {
        String valuable = "extra text";
        return valuedId.concat(valuable);
    }

    public void setValuedId(String valuedId) {
        this.valuedId = valuedId;
    }
    
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