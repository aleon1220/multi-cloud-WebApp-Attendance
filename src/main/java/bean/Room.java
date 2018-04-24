   /**
    * Class Room reflects paper management UI for Lectures Rooms
    * class template
    */

package bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.faces.bean.ManagedBean;

@ManagedBean
// (name = "room")
public class Room {

	public String obtainSessionNumber() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}
}
