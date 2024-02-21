/**
 * Class AttendanceService 
 * creates random code
 */

package service;

import java.util.Arrays;

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

    public String generateAttendanceCode() {
        var randomCode = "";
        // Generates 10 Random Numbers in the range 1 - 50
        int[] numbers = new int[49];
        
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 9);
        } // end for loop

        randomCode = Arrays.toString(numbers).replace(",", " -");
        System.out.println("Numbers Generated: " + randomCode);

        return randomCode;
    }
}

// End of AttendanceService Class