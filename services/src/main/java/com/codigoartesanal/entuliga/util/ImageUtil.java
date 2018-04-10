package com.codigoartesanal.entuliga.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
    private static final int IMG_WIDTH = 125;
    private static final int IMG_HEIGHT = 125;

    public static byte[] resizeImageDefault(byte[] originalImage) throws IOException {
        byte[] resultImage = null;
        InputStream in = new ByteArrayInputStream(originalImage);
        BufferedImage bImageFromConvert = ImageIO.read(in);

        bImageFromConvert = resizeImage(bImageFromConvert, bImageFromConvert.getType());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bImageFromConvert, "jpg", baos);
        baos.flush();
        resultImage = baos.toByteArray();
        baos.close();
        return resultImage;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
}
