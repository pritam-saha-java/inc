package com.incallup.backend.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ImageUtility {
//
//
//
//    /**
//     * Embeds a textual watermark over a source image to produce
//     * a watermarked one.
//     * @param text The text to be embedded as watermark.
//     * @param sourceImageFile The source image file.
//     * @param destImageFile The output image file.
//     */
//    static void addTextWatermark(String text, File sourceImageFile, File destImageFile) {
//        try {
//            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
//            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
//
//            // initializes necessary graphic properties
//            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
//            g2d.setComposite(alphaChannel);
//            g2d.setColor(Color.BLUE);
//            g2d.setFont(new Font("Arial", Font.BOLD, 64));
//            FontMetrics fontMetrics = g2d.getFontMetrics();
//            Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
//
//            // calculates the coordinate where the String is painted
//            int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
//            int centerY = sourceImage.getHeight() / 2;
//
//            // paints the textual watermark
//            g2d.drawString(text, centerX, centerY);
//
//            ImageIO.write(sourceImage, "png", destImageFile);
//            g2d.dispose();
//
//            System.out.println("The tex watermark is added to the image.");
//
//        } catch (IOException ex) {
//            System.err.println(ex);
//        }
//    }
public static byte[] toByteArray(BufferedImage bi, String format)
        throws IOException {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(bi, format, baos);
    byte[] bytes = baos.toByteArray();
    return bytes;

}

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}
