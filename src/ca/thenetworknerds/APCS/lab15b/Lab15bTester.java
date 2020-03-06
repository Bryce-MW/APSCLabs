package ca.thenetworknerds.APCS.lab15b;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class
 * methods.  Uncomment the methods and the code
 * in the main to test.
 *
 * @author Barbara Ericson
 * <p>
 * 02-27-15 altered by Leon Schram
 * This class is a reduced version of the PictureTester class.
 * The file is reduced to concentrate of the Lab15b Assignment only.
 */


public class Lab15bTester {

    public static void start() {
        test80Points();
        test90Points();
        test100Points();
    }


    public static void test80Points() {
        Picture pix = new Picture("src/ca/thenetworknerds/APCS/lab15b/beach.jpg");
        pix.explore();
        pix.grayScale();
        pix.explore();
        pix.mirror();
        pix.explore();
        pix.upsideDown();
        pix.explore();
    }

    public static void test90Points() {
        Picture pix1 = new Picture("src/ca/thenetworknerds/APCS/lab15b/motorcycle.jpg");
        pix1.explore();
        pix1.mirrorHorizontal();
        pix1.explore();
        Picture pix2 = new Picture("src/ca/thenetworknerds/APCS/lab15b/motorcycle.jpg");
        pix2.mirrorVertical();
        pix2.explore();
        Picture pix3 = new Picture("src/ca/thenetworknerds/APCS/lab15b/motorcycle.jpg");
        pix3.mirrorDiagonal();
        pix3.explore();
    }

    public static void test100Points() {
        Picture pix = new Picture("src/ca/thenetworknerds/APCS/lab15b/temple.jpg");
        pix.explore();
        pix.mirrorTemple();
        pix.explore();
    }

}