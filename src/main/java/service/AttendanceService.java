   /**
    * Class AttendanceService 
    * creates random code
    */

package service;


//Class is used to show invokations to entities MS

public class AttendanceService {
	
	private String randomCode;

    public AttendanceService() {

    }

	
   public String getRandomCode() {
       return randomCode;
   }

   public String setrandomCode(String randomCode) {
       return this.randomCode = randomCode;
   }
}

//End of AttendanceService Class