package wickget;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.ShapeElement;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
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
public class Wickget extends JFrame {

    public Wickget() {
        setUndecorated(true);
        setSize(225, 225);
        add(new JLabel(new ImageIcon("index.jpeg")));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

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
        new Wickget();
    }
}
