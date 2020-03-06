package ca.thenetworknerds.APCS.lab07;

import java.util.ArrayList;

// This is based somewhat on particle physics but I did many things wrong then gave up on things and changed how things
// work so that they look better

public abstract class Particle {
    static ColorCharge[] colorCharges = {ColorCharge.BLUE, ColorCharge.GREEN, ColorCharge.RED};

    double mass;
    double charge;
    double spin;
    ColorCharge colorCharge;
    boolean antiness;

    double X;
    double Y;
    double VX;
    double VY;

    static ArrayList<Particle> createParticles(int size) {
        ArrayList<Particle> particles = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            if ((int) (Math.random() * 2) == 1) {
                particles.add(new Quark(Math.random() * 1600, Math.random() * 800,
                        (int) (Math.random() * 3) + 1, (int) (Math.random() * 2) == 1,
                        (int) (Math.random() * 2) == 1));
            } else {
                particles.add(new Lepton(Math.random() * 1600, Math.random() * 800,
                        (int) (Math.random() * 3) + 1, true,
                        (int) (Math.random() * 2) == 1));
            }
        }
        particles.add(new Quark(Math.random() * 1600, Math.random() * 800,
                2, false,
                (int) (Math.random() * 2) == 1));
        return particles;
    }

    public abstract Particle copy();

    void processStrong(ArrayList<Particle> particles) {
        for (Particle particle :
                particles) {
            if (particle.X != this.X && particle.Y != this.Y) {
                if ((this.colorCharge != ColorCharge.WHITE) && (particle.colorCharge != ColorCharge.WHITE)
                    && ((colorCharge != particle.colorCharge) == (this.antiness == particle.antiness))) {
                    double distance = Math.hypot(Math.abs(this.X - particle.X), Math.abs(this.Y - particle.Y));
                    double magnitude = (((8987551787.3681764 / Math.pow(distance, 2))
                                               * 0.0166666666) / (this.mass)) * ((distance > 200) ? 1 / Math.pow((distance - 199), 2) : 0);
                    this.applyForce(particle, magnitude);
//                    if (magnitude != 0 && magnitude != -0 && (int) (Math.random() * Math.pow(particles.size(), 2)) == 0) {
//                        ColorCharge tmp = colorCharge;
//                        colorCharge = particle.colorCharge;
//                        particle.colorCharge = tmp;
//                    }
                } else if ((this.colorCharge != ColorCharge.WHITE) && (particle.colorCharge == this.colorCharge)) {
                    double distance = Math.hypot(Math.abs(this.X - particle.X), Math.abs(this.Y - particle.Y));
                    double magnitude = -(((8987551787.3681764 / Math.pow(distance, 2))
                                                * 0.0166666666) / (this.mass)) * ((distance > 200) ? 1 / Math.pow((distance - 199), 2) : 0);
                    this.applyForce(particle, magnitude);
                }
            }
        }
    }

    void processElectroMagnetic(ArrayList<Particle> particles) {
        for (Particle particle :
                particles) {
            if (particle.X != this.X && particle.Y != this.Y) {
                double magnitude = (((8987551787.3681764 * this.charge * particle.charge) / Math.pow(Math.hypot(Math.abs(this.X - particle.X), Math.abs(this.Y - particle.Y)), 2))
                                          * 0.0166666666) / (60 * this.mass);
                this.applyForce(particle, magnitude);
            }
        }
    }

    private void applyForce(Particle particle, double magnitude) {
        double direction = Math.atan2(Math.abs(this.X - particle.X), Math.abs(this.Y - particle.Y));
        this.VX += magnitude * Math.cos(direction);
        particle.VX += magnitude * Math.cos(direction);
        this.VY += magnitude * Math.sin(direction);
        particle.VY += magnitude * Math.sin(direction);
    }

    void processVelocity() {
        if (this.X < 0 || this.X > 1600) {
            this.VX *= -1;
        }
        if (this.Y < 0 || this.Y > 800) {
            this.VY *= -1;
        }
        this.X += this.VX * 0.0166666666;
        this.Y += this.VY * 0.0166666666;
        if (this.X < -50 || this.X > 1650 || this.Y < -50 || this.Y > 850) {
            this.X = Math.random() * 1600;
            this.Y = Math.random() * 800;
            this.VX = 0;
            this.VY = 0;
        }
    }

    void processCollisions(ArrayList<Particle> particles) {
        for (Particle particle :
                particles) {
            if (Math.hypot(Math.abs(this.X - particle.X), Math.abs(this.Y - particle.Y)) < 50) {
                this.VX = 0;
                this.VY = 0;
            }
        }
    }
}

