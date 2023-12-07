package utilities;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

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
