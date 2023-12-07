package service;

import java.io.IOException;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class GenerateQRCode {
  public boolean validateQR = false;

  public void createWebsiteQRCode() throws WriterException, IOException {
    String qrData = "www.andres.nz";
    String localPath = "/home/aleonrangel";
    BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, 500, 500, null);
    MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(localPath), null);
    System.out.println("QR code created");
    validateQR = true;
  }
}
