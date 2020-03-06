package ca.thenetworknerds.APCS.lab07;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lab07v110 {
    static final Object particleLock = new Object();
    private static ArrayList<Particle> particles;
    private static Boolean running = true;

    public static void main(String[] args) {
        Lab07v110.particles = Particle.createParticles(20);

        Runnable r = () -> {
            JFrame f = new JFrame("Lab 7: 110 Point Version");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            simPanel panel = new simPanel(Lab07v110.particles, Lab07v110.running);
            f.add(panel);
            f.pack();
            f.setLocationByPlatform(true);
            f.setSize(1600, 810);
            f.setVisible(true);
            Lab07v110.startSim(panel, Lab07v110.particles);
        };
        SwingUtilities.invokeLater(r);
    }

    private static void startSim(simPanel panel, ArrayList<Particle> particles) {
        new Thread(() -> {
            while (Lab07v110.running) {
                synchronized (Lab07v110.particleLock) {
                    ArrayList<Particle> toRemove = new ArrayList<>();
                    ArrayList<Particle> toAdd = new ArrayList<>();
                    ArrayList<Particle> tempParticles = new ArrayList<>();
                    for (Particle particle :
                            particles) {
                        tempParticles.add(particle.copy());
                    }
                    for (Particle particle :
                            particles) {
                        particle.processStrong(tempParticles);
                        particle.processElectroMagnetic(tempParticles);
                        particle.processVelocity();
                        particle.processCollisions(tempParticles);
                        if (particle instanceof Lepton) {
                            if (((Lepton) particle).timeout != -1) {
                                if (((Lepton) particle).timeout-- == 0) {
                                    toRemove.add(particle);
                                }
                            }
                        }
                        if (particle instanceof Quark) {
                            Quark tmp = (Quark) particle;
                            if (!tmp.sidedness && tmp.generation == 2) {
                                if ((int) (Math.random() * Math.pow(particles.size(), 2)) == 0) {
                                    for (int i = 0; i < 5; i++) {
                                        if ((int) (Math.random() * 2) == 1) {
                                            Quark newQuark = new Quark(particle.X + (Math.random() * 100 - 50), particle.Y + (Math.random() * 100 - 50),
                                                    (int) (Math.random() * 3) + 1, (int) (Math.random() * 2) == 1,
                                                    (int) (Math.random() * 2) == 1);
                                            newQuark.VX = Math.random() * 100 - 50;
                                            newQuark.VY = Math.random() * 100 - 50;
                                            toAdd.add(newQuark);
                                        } else {
                                            Lepton newLepton = new Lepton(particle.X + (Math.random() * 100 - 50), particle.Y + (Math.random() * 100 - 50),
                                                    (int) (Math.random() * 3) + 1, (int) (Math.random() * 2) == 1,
                                                    (int) (Math.random() * 2) == 1);
                                            newLepton.VX = Math.random() * 100 - 50;
                                            newLepton.VY = Math.random() * 100 - 50;
                                            toAdd.add(newLepton);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for (Particle particle :
                            toRemove) {
                        particles.remove(particle);
                    }
                    particles.addAll(toAdd);
                }
                panel.repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
    }
}

class simPanel extends JPanel {
    private static final char[][][][] particleCharacters =
            {{{{'u'}, {'c'}, {'t'}},
                    {{'d'}, {'s'}, {'b'}}},

                    {{{'e'}, {'μ'}, {'τ'}},
                            {{'γ', 'ₑ'}, {'γ', 'ᵨ'}, {'γ', 'ᵣ'}}}};

    private ArrayList<Particle> particles;
    private Boolean running;

    simPanel(ArrayList<Particle> particles, Boolean running) {
        this.particles = particles;
        this.running = running;
    }

    public void paintComponent(Graphics g) {
        int size = 20;

        synchronized (Lab07v110.particleLock) {
            for (Particle particle :
                    this.particles) {
                if (particle.colorCharge == ColorCharge.WHITE) {
                    g.setColor(Color.black);
                }
                if (!particle.antiness) {
                    switch (particle.colorCharge) {
                        case RED -> g.setColor(Color.RED);
                        case GREEN -> g.setColor(Color.GREEN);
                        case BLUE -> g.setColor(Color.BLUE);
                    }
                } else {
                    switch (particle.colorCharge) {
                        case RED -> g.setColor(Color.CYAN);
                        case GREEN -> g.setColor(new Color(0x800080));
                        case BLUE -> g.setColor(Color.YELLOW);
                    }
                }
                g.fillOval((int) (particle.X - size / 2D), (int) (particle.Y - size / 2D), size, size);
                if (particle.colorCharge == ColorCharge.BLUE && particle.antiness) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                if (particle instanceof Fermion) {
                    g.drawChars(simPanel.particleCharacters[(particle instanceof Quark) ? 0 : 1][(((Fermion) particle).sidedness) ? 0 : 1][((Fermion) particle).generation - 1],
                            0, ((particle instanceof Lepton) ? ((((Lepton) particle).sidedness) ? 1 : 2) : 1),
                            (int) (particle.X - size / 4D), (int) (particle.Y + size / 4D));
                }
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 1600, 800);
    }

    public void removeNotify() {
        this.running = false;
    }
}