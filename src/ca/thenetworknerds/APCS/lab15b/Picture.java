package ca.thenetworknerds.APCS.lab15b;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Picture extends SimplePicture {

    public Picture() {}
    public Picture(String fileName) {
        super(fileName);
    }
    public Picture(int height, int width) {
        super(width, height);
    }
    public Picture(Picture copyPicture) {
        super(copyPicture);
    }
    public Picture(BufferedImage image) {
        super(image);
    }
    public String toString() {
        return "Picture, filename " + getFileName() +
               " height " + getHeight()
               + " width " + getWidth();

    }

    
    
    public void grayScale() {
        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color startColor = new Color(bufferedImage.getRGB(i, j));
                int average = (startColor.getRed() + startColor.getGreen() + startColor.getBlue()) / 3;
                Color finalColor = new Color(average, average, average);
                bufferedImage.setRGB(i, j, finalColor.getRGB());
            }
        }
    }


    public void mirror() {
        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();
        for (int i = 0; i < width / 2; i++) {
            for (int j = 0; j < height; j++) {
                int left = bufferedImage.getRGB(i, j);
                int right = bufferedImage.getRGB(width - 1 - i, j);
                bufferedImage.setRGB(i, j, right);
                bufferedImage.setRGB(width - 1 - i, j, left);
            }
        }
    }


    public void upsideDown() {
        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height / 2; j++) {
                int top = bufferedImage.getRGB(i, j);
                int bottom = bufferedImage.getRGB(i, height - 1 - j);
                bufferedImage.setRGB(i, j, bottom);
                bufferedImage.setRGB(i, height - 1 - j, top);
            }
        }
    }


    public void mirrorVertical() {
        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();
        for (int i = 0; i < width / 2; i++) {
            for (int j = 0; j < height; j++) {
                int left = bufferedImage.getRGB(i, j);
                bufferedImage.setRGB(width - 1 - i, j, left);
            }
        }
    }


    public void mirrorHorizontal() {
        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height / 2; j++) {
                int top = bufferedImage.getRGB(i, j);
                bufferedImage.setRGB(i, height - 1 - j, top);
            }
        }
    }


    public void mirrorDiagonal() {
        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < i; j++) {
                int color = bufferedImage.getRGB(j, i);
                bufferedImage.setRGB(i, j, color);
            }
        }
    }


    public void mirrorTemple() {
        var width = bufferedImage.getWidth();
        for (int i = 0; i < width / 2; i++) {
            for (int j = 0; j < 100; j++) {
                int left = bufferedImage.getRGB(i, j);
                bufferedImage.setRGB(width - 1 - i, j, left);
            }
        }
    }

} 
