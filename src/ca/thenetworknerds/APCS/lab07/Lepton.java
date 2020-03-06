package ca.thenetworknerds.APCS.lab07;

public class Lepton extends Fermion {
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
    int timeout;

    Lepton(double X, double Y, int generation, boolean sidedness, boolean antiness) {
        this.X = X;
        this.Y = Y;
        VX = 0;
        VY = 0;

        this.generation = generation;
        this.sidedness = sidedness;
        this.antiness = antiness;

        this.timeout = (sidedness) ? -1 : 60;

        this.spin = (antiness) ? -0.5 : 0.5;

        this.charge = (sidedness) ? -1 : 0;
        this.charge *= (antiness) ? -1 : 1;

        switch (generation) {
            case 1 -> this.mass = (sidedness) ? 0.511 : 0.000003;
            case 2 -> this.mass = (sidedness) ? 105.66 : 0.000003;
            case 3 -> this.mass = (sidedness) ? 1777D : 0.000003;
        }

        this.colorCharge = ColorCharge.WHITE;
    }

    public Lepton copy() {
        Lepton tmp = new Lepton(this.X, this.Y, this.generation, this.sidedness, this.antiness);
        tmp.X = this.X;
        tmp.Y = this.Y;
        tmp.VX = this.VX;
        tmp.VY = this.VY;
        tmp.timeout = this.timeout;
        return tmp;
    }
}
