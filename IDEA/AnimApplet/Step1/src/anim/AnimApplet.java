package anim;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: nate
 * Date: 28/10/11
 * Time: 09:51
 * To change this template use File | Settings | File Templates.
 */
public class AnimApplet extends JApplet {

    public void init() {

    }

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(10, 10, 100, 200);
    }

}
