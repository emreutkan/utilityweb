package com.utility.web;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class QRCodeController {

    // This method displays the QR code generator page with the input form.
    @GetMapping("/qr")
    public String qrPage(@RequestParam(value = "text", required = false) String text, Model model) {
        model.addAttribute("text", text);
        return "qr";  // Thymeleaf template name (qr.html)
    }

    // This method generates the QR code image.
    @GetMapping(value = "/generateQR", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] generateQRCode(@RequestParam("text") String text) throws WriterException, IOException {
        int width = 250;
        int height = 250;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        // Convert the BitMatrix to a BufferedImage using ZXing utility.
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Write the BufferedImage to a byte array in PNG format.
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}
