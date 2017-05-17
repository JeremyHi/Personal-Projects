package math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Dartboard extends JComponent {
    private static enum DartState {
        IN, OUT
    }

    private static class Plotter implements Runnable {
        private Dartboard dartboard;
        private double x;
        private double y;
        private DartState state;

        public Plotter(Dartboard dartboard, double x, double y, DartState state) {
            this.dartboard = dartboard;
            this.x = x;
            this.y = y;
            this.state = state;
        }

        @Override
        public void run() {
            dartboard.plot(x, y, state);
        }
    }

    private double left;
    private double top;
    private double right;
    private double bottom;

    public Dartboard(double left, double top, double right, double bottom) {
        super();
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public void plot(double x, double y, DartState state) {
        Graphics2D g2d = (Graphics2D)getGraphics();

        int xs = (int)((double)getWidth() * (x - left) / (right - left));
        int ys = (int)((double)getHeight() * (y - top) / (bottom - top));

        final int xSize = 2;

        g2d.setColor(state == DartState.IN ? Color.BLUE : Color.RED);
        g2d.drawLine(xs - xSize, ys - xSize, xs + xSize, ys + xSize);
        g2d.drawLine(xs - xSize, ys + xSize, xs + xSize, ys - xSize);
    }

    private static void printUsage() {
        System.out.println("Usage: java Dartboard window-width window-height left top right bottom");
    }

    private static enum InputState {
        PRELUDE, DATA, POSTLUDE
    }

    public static void main(String[] args) {
        int width = 512;
        int height = 512;
        double left = -1.0;
        double top = 1.0;
        double right = 1.0;
        double bottom = -1.0;

        if (args.length == 6) {
            try {
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[1]);
                left = Double.parseDouble(args[2]);
                top = Double.parseDouble(args[3]);
                right = Double.parseDouble(args[4]);
                bottom = Double.parseDouble(args[5]);
            } catch (NumberFormatException nfe) {
                printUsage();
                return;
            }
        } else if (args.length != 0) {
            printUsage();
            return;
        }

        JFrame dartFrame = new JFrame("Dartboard");
        dartFrame.setSize(width, height);
        dartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dartboard dartboard = new Dartboard(left, top, right, bottom);
        dartFrame.setContentPane(dartboard);
        dartFrame.setVisible(true);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
            InputState state = InputState.PRELUDE;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (state != InputState.DATA) {
                    if ("start".equals(line)) {
                        state = InputState.DATA;
                    } else {
                        System.out.println(line);
                    }
                } else {
                    if ("end".equals(line)) {
                        state = InputState.POSTLUDE;
                    } else {
                        String[] dartData = line.split(" ");
                        SwingUtilities.invokeLater(new Plotter(
                            dartboard,
                            Double.parseDouble(dartData[0]),
                            Double.parseDouble(dartData[1]),
                            DartState.valueOf(dartData[2].toUpperCase())
                        ));
                    }
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}