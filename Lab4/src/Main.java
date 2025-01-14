//Изобразить в окне приложения отрезок, вращающийся в плоскости фрейма вокруг точки, движущейся по отрезку.
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

class RotatingLine {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Вращающийся отрезок");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        RotatingPanel panel = new RotatingPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}

class RotatingPanel extends JPanel {
    private double angle = 0;
    private double t = 0;

    public RotatingPanel() {
        Timer timer = new Timer(50, e -> {
            angle += 0.1;
            t += 0.01;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();


        int centerX = width / 2;
        int centerY = height / 2;


        int lineLength = 100;


        int x1 = centerX - lineLength / 2;
        int y1 = centerY;
        int x2 = centerX + lineLength / 2;
        int y2 = centerY;


        double movingX = x1 + t * (x2 - x1);
        double movingY = y1 + t * (y2 - y1);


        if (t > 1) {
            t = 0;
        }


        AffineTransform oldTransform = g2d.getTransform();
        g2d.translate(movingX, movingY);
        g2d.rotate(angle);


        g2d.setColor(Color.BLUE);
        g2d.drawLine(-lineLength / 2, 0, lineLength / 2, 0);


        g2d.setTransform(oldTransform);


        g2d.setColor(Color.RED);
        g2d.fillOval((int) movingX - 5, (int) movingY - 5, 10, 10);
    }
}
