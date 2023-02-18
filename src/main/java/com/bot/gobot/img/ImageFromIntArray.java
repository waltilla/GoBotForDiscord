package com.bot.gobot.img;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFromIntArray {

    public static void generate(int[][] imgArray) {
        int width = imgArray.length - 1;
        int height = imgArray.length - 1;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        File f = null;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                img.setRGB(x, y, imgArray[y][x]);
            }
        }
        try {
            f = new File("src/main/resources/Output.png");
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
