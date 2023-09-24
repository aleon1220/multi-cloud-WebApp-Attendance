package utilities;

import org.junit.Test;

public class TestQRFunctions {
  @Test
  public void createQRCodeLocally() {
    GenerateQRCode qrCode;
    qrCode.createWebsiteQRCode();
    assertTrue(qrCode.validateQR = true);
  }

}
