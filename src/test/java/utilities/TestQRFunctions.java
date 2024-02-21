package utilities;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.google.zxing.WriterException;

import service.GenerateQRCode;

public class TestQRFunctions {
  @Test
  public void createQRCodeLocally() {
    GenerateQRCode qrCode = new GenerateQRCode();
    try {
      qrCode.createWebsiteQRCode();
    } catch (WriterException | IOException e) {
      e.printStackTrace();
    }
    assertTrue(qrCode.validateQR);
  } // End of createQRCodeLocally()
} // End of Class Declaration
