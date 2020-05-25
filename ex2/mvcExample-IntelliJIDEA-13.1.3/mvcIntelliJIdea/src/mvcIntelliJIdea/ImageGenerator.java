package mvcIntelliJIdea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageGenerator {
    private int width;
    private int height;

    public ImageGenerator(int width, int height) throws IOException {
        this.height = height;
        this.width = width;
    }

    public String generateCaptchaString() {
        Random random = new Random();
        int length = 6; // static captcha length

        StringBuilder captchaStringBuffer = new StringBuilder(); // generate only alpha-numeric characters
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(random.nextInt()) % 62; // there are 62 [0-9] + [a-z] + [A-Z] characters
            int charNumber = 0;
            if (baseCharNumber < 26) { // we generate a capital letter
                charNumber = 65 + baseCharNumber;
            } else if (baseCharNumber < 52) { // we generate a letter
                charNumber = 97 + (baseCharNumber - 26);
            } else { // we generate a digit
                charNumber = 48 + (baseCharNumber - 52);
            }
            captchaStringBuffer.append((char) charNumber); // we generate the characters according to their ASCII codes
        }
        return captchaStringBuffer.toString();
    }

    public String getCaptcha(String captcha) throws IOException {
        if (captcha.length() != 6) {
            throw new IOException("Lungimea trebuie sa fie egala cu 6");
        } else {
            BufferedImage awtImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = awtImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height); // create the background
            g.setColor(Color.BLACK);
            g.setFont(new Font("Courier New", Font.BOLD, 60));
            g.drawChars(captcha.toCharArray(), 0, captcha.length(), 40, 90); // insert the text into the image
            Random rand = new Random();
            for (int i = 1; i <= 25; i++) { // draw random lines across the text, in order to produce noise
                int randomX1 = rand.nextInt(width);
                int randomX2 = rand.nextInt(width);
                int randomY1 = rand.nextInt(height);
                int randomY2 = rand.nextInt(height);
                g.drawLine(randomX1, randomY1, randomX2, randomY2);
            }
            for (int x = 0; x < width; x++) { // "barcode" filter to reduce letter visibility
                for (int y = 0; y < height; y++) {
                    int yy = (int) (y + 2 * Math.sin(x * 2 * Math.PI / 3)); // ad a sinusoidal aspect to the width of the bars
                    if (yy >= 0 && yy < height) {
                        awtImage.setRGB(x, y, awtImage.getRGB(x, yy));
                    }
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); // final image that will be shown to the user
            ImageIO.write(awtImage, "png", baos);
            baos.flush();
            String base64Encoded = Base64.getEncoder().encodeToString(baos.toByteArray()); // base64 encoding in order to ensure that the HTML recognises all the information
            baos.close();
            return base64Encoded;
        }
    }
}
