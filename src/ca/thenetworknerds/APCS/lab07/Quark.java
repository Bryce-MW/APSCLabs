package ca.thenetworknerds.APCS.lab07;

public class Quark extends Fermion {
//    double mass;
//    double charge;
//    double spin;
//    ColorCharge colorCharge;
//    boolean antiness;
//
//    double X;
//    double Y;
//    double VX;
//    double VY;

//    int generation;
//    boolean sidedness; // True for top, false for bottom

    Quark(double X, double Y, int generation, boolean sidedness, boolean antiness) {
        this.X = X;
        this.Y = Y;
        VX = 0;
        VY = 0;

        this.generation = generation;
        this.sidedness = sidedness;
        this.antiness = antiness;

        this.spin = (antiness) ? -0.5 : 0.5;

        this.charge = (sidedness) ? 2D / 3D : -1D / 3D;
        this.charge *= (antiness) ? -1 : 1;

        switch (generation) {
            case 1 -> this.mass = (sidedness) ? 2.3 : 4.8;
            case 2 -> this.mass = (sidedness) ? 1275D : 95D;
            case 3 -> this.mass = (sidedness) ? 173100D : 4180D;
        }

        this.colorCharge = Particle.colorCharges[(int) (Math.random() * Particle.colorCharges.length)];
    }

    public Quark copy() {
        Quark tmp = new Quark(this.X, this.Y, this.generation, this.sidedness, this.antiness);
        tmp.X = this.X;
        tmp.Y = this.Y;
        tmp.VX = this.VX;
        tmp.VY = this.VY;
        tmp.colorCharge = this.colorCharge;
        return tmp;
    }
}
