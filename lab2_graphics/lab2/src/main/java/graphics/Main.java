package graphics;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
    Timer timer;

    private static int maxWidth = 1600;
    private static int maxHeight = 900;

    private static int paddingX = 700;
    private static int paddingY = 550;

    private double angle = 0;

    private double dx = 0;
    private double tx = 0;
    private double dy = 5;
    private double ty = 0;

    private final double center_x = 210;
    private final double center_y = 125;

    TriangleShape SmallTriangle = new TriangleShape(new Point2D.Double(400, 300),
                new Point2D.Double(440, 390), new Point2D.Double(360, 390));

    TriangleShape BigTriangle = new TriangleShape(new Point2D.Double(400, 300),
                new Point2D.Double(450, 400), new Point2D.Double(350, 400));
     Ellipse2D.Double redC =
            new Ellipse2D.Double(390, 325, 20, 20);
    Ellipse2D.Double redY =
            new Ellipse2D.Double(390, 345, 20, 20);
    Ellipse2D.Double redG =
            new Ellipse2D.Double(390, 365, 20, 20);
    public Main() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setBackground(new Color(121, 122, 127));
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        g2d.setColor(new Color(255, 0, 0));
        BasicStroke bs1 = new BasicStroke(16, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs1);
        g2d.drawRect(100, 100, 1400, 750);
//
        g2d.translate(tx, ty);

        g2d.rotate(angle, 400, 300);
        g2d.setColor(new Color(255, 0, 0));

        g2d.fill(BigTriangle);
        g2d.setColor(new Color(255, 255, 255));

        g2d.fill(SmallTriangle);
        g2d.setColor(new Color(255,0, 0));
        g2d.fill(redC);
        g2d.setColor(new Color(0,0, 255));
        g2d.fill(redG);
        g2d.setColor(new Color(255,0, 255));
        g2d.fill(redY);

        g2d.setColor(new Color(0,0, 0));

        double points[][] = {
                { 390, 400 },  { 390, 480 }, { 410, 480 },  { 410, 400 }
        };
        GeneralPath rect = new GeneralPath();
        rect.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            rect.lineTo(points[k][0], points[k][1]);
        rect.closePath();
        g2d.fill(rect);

    }

    public void actionPerformed(ActionEvent e) {
        if ( tx < 0 ) {
            dx = 0;
            dy = 5;
            tx = 0;
        } else if ( tx > maxWidth - paddingX ) {
            dx = 0;
            dy = -5;
            tx = maxWidth - paddingX;
        }

        if ( ty < 0 ) {
            dy = 0;
            dx = -5;
            ty = 0;
        } else if ( ty > maxHeight - paddingY ) {
            dy = 0;
            dx = 5;
            ty = maxHeight - paddingY;
        }

        tx += dx;
        ty += dy;
        angle -= 0.1;
        repaint();
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Kachanov lab 2");
        frame.add(new Main());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }
    class TriangleShape extends Path2D.Double {
        public TriangleShape(Point2D... points) {
            moveTo(points[0].getX(), points[0].getY());
            lineTo(points[1].getX(), points[1].getY());
            lineTo(points[2].getX(), points[2].getY());
            closePath();
        }
    }
}