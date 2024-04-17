package com.incallup.backend.utility;

import net.coobird.thumbnailator.filters.Caption;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


public class ImageUtility {

//    public static String LOGO_PATH = "logo.png";
//    public static String LOGO_LINK = "https://incallup.com/server/assets/img/logo.png";

    public static byte[] getBufferedImage(MultipartFile image1) throws IOException {
        BufferedImage originalImage = ImageIO.read(image1.getInputStream());

//        BufferedImage watermarkImage = null ;
//        try{

//         watermarkImage = ImageIO.read(new File(ImageUtility.LOGO_PATH));

//            URL url = new URL(ImageUtility.LOGO_LINK);
//            URLConnection conn = url.openConnection();
//         watermarkImage = ImageIO.read(conn.getInputStream());

//            HttpClient httpClient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(ImageUtility.LOGO_LINK);
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            if (httpEntity != null) {
//
//                byte[] imageBytes = IOUtils.toByteArray(httpEntity.getContent());
//                watermarkImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
//
//            }
            String caption = "Incallup";
            Font font = new Font("Monospaced", Font.PLAIN, 54);
            Color c = Color.white;
            Position position = Positions.CENTER;
            int insetPixels = 0;

//        }catch (IOException e){
//            throw new RuntimeException(e);
//        }

//        watermarkImage = ImageUtility.toBufferedImage(watermarkImage.getScaledInstance(200,200, BufferedImage.SCALE_SMOOTH));
        Caption filter = new Caption(caption, font, c, position, insetPixels);
//        Watermark filter = new Watermark(Positions.CENTER, watermarkImage, 0.5f);

      var watermarkedImage =  filter.apply(originalImage);
      return ImageUtility.toByteArray(watermarkedImage,"png");

    }
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
