// Lab03v100.java
// This is the 100 point version.
// Bryce Wilson
// April 17, 2019
// APCS, Mr. Robinson


// Don't open this in the applet viewer, use the normal Java. I ended up switching it to a JFrame based thing rather
// than an applet so that I could have a double buffered component. I could have done just a JApplet but by the time
// I had done that, it is really easy to switch it to a full application that uses a JFrame. Then I can set the size
// myself rather than hoping that you will do it.
// Try changing angle (in radians) inside sinCubeHeadOn! It's not perfect so it looks better with smaller angles
// I could make it better than it is now but that would take way too much work. If I had just used 3d points
// to start with and then used a projection, it would be easier than the static transformations I used here.
// I would have also made a Vector class and installed JavaOO (Java Operator Overload) so that I could add common vector
// operations, probably using quaternions to allow for easier 3D stuff

package ca.thenetworknerds.APCS;

import javax.swing.*;
import java.awt.*;


public class Lab04bv110 {
    public static void main(String[] args) {
        Runnable r = () -> {
            JFrame f = new JFrame("Lab 4: 110 Point Version");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Lab04bv110Panel panel = new Lab04bv110Panel();
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1679, 995);
            f.setVisible(true);
            panel.init();
        };
        SwingUtilities.invokeLater(r);
    }
}


@SuppressWarnings("FieldCanBeLocal")
class Lab04bv110Panel extends JPanel {
    private final double sinCubeHeadOn = Math.sin(Math.PI / 12);
    private final int cubeStart = 200;
    private final int cubeSize = 300;
    private final int inscribedStartX = 800;
    private final int inscribedStartY = 50;
    private final int inscribedSize = 150;
    private final int inscribedIterations = 10;
    private final int APCSStartX = 800;
    private final int APCSStartY = 250;
    private final int APCSUnit = 20;
    private final int APCSHalf = (int) (0.5 * this.APCSUnit);
    private final int[][] APCSGrid = {
            {5, 1, 6, 0, 5, 1, 6, 0, 5, 1, 4, 0, 5, 1, 4},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 8, 0, 1, 0, 0, 0, 7, 1, 6},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {2, 0, 2, 0, 2, 0, 0, 0, 7, 1, 4, 0, 3, 1, 8}
    };
    private final int packStartX = 800;
    private final int packStartY = 400;
    private final int packStartSize = 200;
    private final int packLimit = 5;
    //  0: Nothing
    //  1: Filled
    //  2: Half Bottom
    //  3: Half Left
    //  4: Half Right
    //  5: Quarter Top Left
    //  6: Quarter Top Right
    //  7: Quarter Bottom Left
    //  8: Quarter Bottom Right
    private final double SQRT_3 = Math.sqrt(3);
    private Thread animation;
    private double cubeAngle = Math.PI / 6;
    private double inscribedIterationsInternal;
    private int inscribedIterationsTemp;
    private int packAngle = 90;
    private double packAngleInternal;
    private int sleepCount;

    public void paint(Graphics g) {
        // DRAW CUBE
        double sinCubeAngle = Math.sin(this.cubeAngle);
        double cosCubeAngle = Math.cos(this.cubeAngle);

        double cubeBackTopLeftCornerX = this.cubeStart;
        double cubeBackTopLeftCornerY = this.cubeStart;
        //g.drawLine(0,0,(int) cubeBackTopLeftCornerX,(int) cubeBackTopLeftCornerY);

        double cubeBackTopRightCornerX = this.cubeStart + this.cubeSize;
        double cubeBackTopRightCornerY = this.cubeStart;
        //g.drawLine(0,0,(int) cubeBackTopRightCornerX,(int) cubeBackTopRightCornerY);

        double cubeBackBottomLeftCornerX = this.cubeStart;
        double cubeBackBottomLeftCornerY = this.cubeStart + this.cubeSize;
        //g.drawLine(0,0,(int) cubeBackBottomLeftCornerX,(int) cubeBackBottomLeftCornerY);

        double cubeBackBottomRightCornerX = this.cubeStart + this.cubeSize;
        double cubeBackBottomRightCornerY = this.cubeStart + this.cubeSize;
        //g.drawLine(0,0,(int) cubeBackBottomRightCornerX,(int) cubeBackBottomRightCornerY);

        double cubeFrontTopLeftCornerX = this.cubeStart + cosCubeAngle * this.cubeSize * this.sinCubeHeadOn;
        double cubeFrontTopLeftCornerY = this.cubeStart + sinCubeAngle * this.cubeSize * this.sinCubeHeadOn;
        //g.drawLine(0,0,(int) cubeFrontTopLeftCornerX,(int) cubeFrontTopLeftCornerY);

        double cubeFrontTopRightCornerX = this.cubeStart + cosCubeAngle * this.cubeSize * this.sinCubeHeadOn + this.cubeSize;
        double cubeFrontTopRightCornerY = this.cubeStart + sinCubeAngle * this.cubeSize * this.sinCubeHeadOn;
        //g.drawLine(0,0,(int) cubeFrontTopRightCornerX,(int) cubeFrontTopRightCornerY);

        double cubeFrontBottomLeftCornerX = this.cubeStart + cosCubeAngle * this.cubeSize * this.sinCubeHeadOn;
        double cubeFrontBottomLeftCornerY = this.cubeStart + sinCubeAngle * this.cubeSize * this.sinCubeHeadOn + this.cubeSize;
        //g.drawLine(0,0,(int) cubeFrontBottomLeftCornerX,(int) cubeFrontBottomLeftCornerY);

        double cubeFrontBottomRightCornerX = this.cubeStart + cosCubeAngle * this.cubeSize * this.sinCubeHeadOn + this.cubeSize;
        double cubeFrontBottomRightCornerY = this.cubeStart + sinCubeAngle * this.cubeSize * this.sinCubeHeadOn + this.cubeSize;
        //g.drawLine(0,0,(int) cubeFrontBottomRightCornerX,(int) cubeFrontBottomRightCornerY);

        g.drawRect(this.cubeStart, this.cubeStart, this.cubeSize, this.cubeSize);
        g.drawRect((int) cubeFrontTopLeftCornerX, (int) cubeFrontTopLeftCornerY, this.cubeSize, this.cubeSize);

        g.drawLine((int) cubeBackTopLeftCornerX, (int) cubeBackTopLeftCornerY, (int) cubeFrontTopLeftCornerX, (int) cubeFrontTopLeftCornerY);
        g.drawLine((int) cubeBackTopRightCornerX, (int) cubeBackTopRightCornerY, (int) cubeFrontTopRightCornerX, (int) cubeFrontTopRightCornerY);
        g.drawLine((int) cubeBackBottomLeftCornerX, (int) cubeBackBottomLeftCornerY, (int) cubeFrontBottomLeftCornerX, (int) cubeFrontBottomLeftCornerY);
        g.drawLine((int) cubeBackBottomRightCornerX, (int) cubeBackBottomRightCornerY, (int) cubeFrontBottomRightCornerX, (int) cubeFrontBottomRightCornerY);


        // DRAW SPHERE

        g.drawOval((int) ((cubeBackTopLeftCornerX + cubeFrontTopLeftCornerX) / 2),
                (int) ((cubeBackTopLeftCornerY + cubeBackBottomLeftCornerY) / 2),
                this.cubeSize,
                (int) ((cubeFrontTopLeftCornerY + cubeFrontBottomLeftCornerY) / 2
                        - (cubeBackTopLeftCornerY + cubeBackBottomLeftCornerY) / 2));

        g.drawOval((int) ((cubeBackBottomLeftCornerX + cubeFrontBottomLeftCornerX) / 2),
                (int) ((cubeFrontTopLeftCornerY + cubeFrontBottomLeftCornerY) / 2),
                this.cubeSize,
                (int) (-(cubeFrontTopLeftCornerY + cubeFrontBottomLeftCornerY) / 2
                        + (cubeBackTopLeftCornerY + cubeBackBottomLeftCornerY) / 2));


        g.drawOval((int) ((cubeBackTopLeftCornerX + cubeBackTopRightCornerX) / 2),
                (int) ((cubeBackTopLeftCornerY + cubeFrontTopLeftCornerY) / 2),
                (int) ((cubeFrontTopLeftCornerX + cubeFrontTopRightCornerX) / 2
                        - (cubeBackTopLeftCornerX + cubeBackTopRightCornerX) / 2),
                this.cubeSize);

        g.drawOval((int) ((cubeFrontTopLeftCornerX + cubeFrontTopRightCornerX) / 2),
                (int) ((cubeBackTopRightCornerY + cubeFrontTopRightCornerY) / 2),
                (int) (-(cubeFrontTopLeftCornerX + cubeFrontTopRightCornerX) / 2
                        + (cubeBackTopLeftCornerX + cubeBackTopRightCornerX) / 2),
                this.cubeSize);


        g.drawOval((int) ((cubeBackTopLeftCornerX + cubeFrontTopLeftCornerX) / 2),
                (int) ((cubeBackTopLeftCornerY + cubeFrontTopLeftCornerY) / 2),
                this.cubeSize,
                this.cubeSize);


        // DRAW INSCRIBED/CIRCUMSCRIBED TRIANGLE

        double inscribedAX = this.inscribedStartX + this.inscribedSize / 2;
        double inscribedAY = this.inscribedStartY;

        double inscribedBX = inscribedAX - this.inscribedSize * this.SQRT_3 * 0.25;
        double inscribedBY = inscribedAY + this.inscribedSize * 0.75;

        double inscribedCX = inscribedAX + this.inscribedSize * this.SQRT_3 * 0.25;
        double inscribedCY = inscribedAY + this.inscribedSize * 0.75;

        g.drawOval(this.inscribedStartX, this.inscribedStartY, this.inscribedSize, this.inscribedSize);
        g.drawLine((int) inscribedAX, (int) inscribedAY, (int) inscribedBX, (int) inscribedBY);
        g.drawLine((int) inscribedBX, (int) inscribedBY, (int) inscribedCX, (int) inscribedCY);
        g.drawLine((int) inscribedCX, (int) inscribedCY, (int) inscribedAX, (int) inscribedAY);

        for (int i = 0; i < this.inscribedIterationsTemp; i++) {
            double inscribedAXn = (inscribedAX + inscribedBX) / 2;
            double inscribedBXn = (inscribedBX + inscribedCX) / 2;
            double inscribedCXn = (inscribedCX + inscribedAX) / 2;
            double inscribedAYn = (inscribedAY + inscribedBY) / 2;
            double inscribedBYn = (inscribedBY + inscribedCY) / 2;
            double inscribedCYn = (inscribedCY + inscribedAY) / 2;

            double littleHight = Math.abs((inscribedAYn - inscribedBY) / 2) * 0.65;
            double inscribedAverageX = (inscribedAXn + inscribedBX + inscribedBXn) / 3 - littleHight;
            double inscribedAverageY = (inscribedAYn + inscribedBY + inscribedBYn) / 3 - littleHight;
            g.drawOval((int) inscribedAverageX, (int) inscribedAverageY, (int) (2 * littleHight), (int) (2 * littleHight));
            inscribedAverageX = (inscribedAX + inscribedAXn + inscribedCXn) / 3 - littleHight;
            inscribedAverageY = (inscribedAY + inscribedAYn + inscribedCYn) / 3 - littleHight;
            g.drawOval((int) inscribedAverageX, (int) inscribedAverageY, (int) (2 * littleHight), (int) (2 * littleHight));
            inscribedAverageX = (inscribedCXn + inscribedBXn + inscribedCX) / 3 - littleHight;
            inscribedAverageY = (inscribedCYn + inscribedBYn + inscribedCY) / 3 - littleHight;
            g.drawOval((int) inscribedAverageX, (int) inscribedAverageY, (int) (2 * littleHight), (int) (2 * littleHight));

            inscribedAX = inscribedAXn;
            inscribedBX = inscribedBXn;
            inscribedCX = inscribedCXn;
            inscribedAY = inscribedAYn;
            inscribedBY = inscribedBYn;
            inscribedCY = inscribedCYn;

            g.drawLine((int) inscribedAX, (int) inscribedAY, (int) inscribedBX, (int) inscribedBY);
            g.drawLine((int) inscribedBX, (int) inscribedBY, (int) inscribedCX, (int) inscribedCY);
            g.drawLine((int) inscribedCX, (int) inscribedCY, (int) inscribedAX, (int) inscribedAY);
        }


        // DRAW APCS
        //g.drawRect(APCSStartX, APCSStartY, APCSUnit*15, APCSUnit*5);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                //g.drawRect(APCSStartX+i*APCSUnit, APCSStartY+j*APCSUnit, APCSUnit, APCSUnit);
                switch (this.APCSGrid[j][i]) {
                    //  0: Nothing

                    //  1: Filled
                    case 1:
                        g.fillRect(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, this.APCSUnit, this.APCSUnit);
                        break;

                    //  2: Half Bottom
                    case 2:
                        g.fillRect(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, this.APCSUnit, this.APCSHalf);
                        g.fillArc(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, this.APCSUnit, this.APCSUnit, 180, 180);
                        break;

                    //  3: Half Left
                    case 3:
                        g.fillRect(this.APCSStartX + i * this.APCSUnit + this.APCSHalf, this.APCSStartY + j * this.APCSUnit, this.APCSHalf, this.APCSUnit);
                        g.fillArc(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, this.APCSUnit, this.APCSUnit, 90, 180);
                        break;

                    //  4: Half Right
                    case 4:
                        g.fillRect(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, this.APCSHalf, this.APCSUnit);
                        g.fillArc(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, this.APCSUnit, this.APCSUnit, 270, 180);
                        break;

                    //  5: Quarter Top Left
                    case 5:
                        g.fillArc(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, 2 * this.APCSUnit, 2 * this.APCSUnit, 90, 90);
                        break;

                    //  6: Quarter Top Right
                    case 6:
                        g.fillArc(this.APCSStartX + (i - 1) * this.APCSUnit, this.APCSStartY + j * this.APCSUnit, 2 * this.APCSUnit, 2 * this.APCSUnit, 0, 90);
                        break;

                    //  7: Quarter Bottom Left
                    case 7:
                        g.fillArc(this.APCSStartX + i * this.APCSUnit, this.APCSStartY + (j - 1) * this.APCSUnit, 2 * this.APCSUnit, 2 * this.APCSUnit, 180, 90);
                        break;

                    //  8: Quarter Bottom Right
                    case 8:
                        g.fillArc(this.APCSStartX + (i - 1) * this.APCSUnit, this.APCSStartY + (j - 1) * this.APCSUnit, 2 * this.APCSUnit, 2 * this.APCSUnit, 270, 90);
                        break;
                }
            }
        }


        // DRAW PACMEN FLOWER
        this.drawMan(g, this.packStartSize, this.packAngleInternal, this.packStartX, this.packStartY, this.packLimit, 0, Math.sin(0.5 * Math.toRadians(this.packAngleInternal)), Math.cos(0.5 * Math.toRadians(this.packAngleInternal)));

    }

    private void drawMan(Graphics g, double size, double angle, double X, double Y, int limit, int current, double sinAngle, double cosAngle) {
        if (current > limit) {
            return;
        }
        g.fillArc((int) (X - 0.5 * size), (int) Y, (int) size, (int) size, (int) (270 + 0.5 * angle), (int) (360 - angle));
        int nextSize = (int) (size * sinAngle);
        int nextY = (int) (Y + 0.5 * size * cosAngle + 0.5 * size);
        int nextX = (int) (0.5 * size * sinAngle);
        this.drawMan(g, nextSize, angle, X - nextX, nextY, limit, current + 1, sinAngle, cosAngle);
        this.drawMan(g, nextSize, angle, X + nextX, nextY, limit, current + 1, sinAngle, cosAngle);
    }

    void init() {
        Lab04bv110Panel current = this;
        this.animation = new Thread(() -> {
            while (true) {
                current.repaint();
                try {
                    Thread.sleep(current.sleepCount % 3 == 0 ? 16 : 17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                current.sleepCount += 1;
                current.cubeAngle += 0.01;
                current.inscribedIterationsInternal += 0.01;
                current.inscribedIterationsTemp = (int) ((Math.sin(this.inscribedIterationsInternal) + 1) * this.inscribedIterations / 2);
                current.packAngleInternal = (Math.sin(this.inscribedIterationsInternal) + 1) * current.packAngle;
            }
        });
        this.animation.start();
    }

    public void removeNotify() {
        super.removeNotify();
        // I know this is really bad to do but it is ok in this very specific situation.
        //noinspection deprecation
        this.animation.stop();
    }
}
