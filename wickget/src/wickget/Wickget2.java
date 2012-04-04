package wickget;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.ShapeElement;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: nate
 * Date: 22/10/11
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class Wickget2 extends JFrame {

    int localx, localy;

    public Wickget2() {
        setUndecorated(true);
        setSize(225, 225);
        add(new JLabel(new ImageIcon("index.jpeg")));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        localx = localy = -1;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (localx == -1) {
                    localx = e.getX();
                    localy = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                localx = localy = -1;
            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (localx != -1) {
                    setLocation(e.getXOnScreen()-localx, e.getYOnScreen()-localy);
                }
            }
        });

        Shape shape = new Ellipse2D.Float(0, 0, 200, 200);
        shape = getShape("pumpkin.svg");
        AWTUtilities.setWindowShape(this, shape);
    }

    public Shape getShape(String svgFile) {
        try {
            URI uri = new File(svgFile).toURI();
            SVGDiagram diagram = SVGCache.getSVGUniverse().getDiagram(uri);
            SVGElement element = diagram.getElement("path3818");
            java.util.List list = (java.util.List) (element.getPath(null));
            return ((ShapeElement) (list.get(0))).getShape();
        } catch (Exception ex) {
        }

        return null;
    }

    public static void main(String[] args) {
        new Wickget2();
    }
}
