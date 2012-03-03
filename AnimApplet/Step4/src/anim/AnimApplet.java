package anim;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: nate
 * Date: 28/10/11
 * Time: 09:51
 * To change this template use File | Settings | File Templates.
 */
public class AnimApplet extends JApplet {

    Random r = new Random();

    class Circle {
        int x, y, radius;
        Color color;

        public Circle() {
            x = r.nextInt(100);
            y = r.nextInt(100);
            radius = 10 + r.nextInt(30);
            color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }

        public void grow() {
            radius += 2;
        }

    }

    ArrayList<Circle> circleList;

    public void init() {
        circleList = new ArrayList<Circle>();
        circleList.add(new Circle());
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        }.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        circleList.add(new Circle());
        for (Circle circle : circleList) {
            circle.grow();
            g2d.setColor(circle.color);
            g2d.fillOval(circle.x - circle.radius, circle.y - circle.radius,
                    circle.radius * 2, circle.radius * 2);
            if (circle.radius > 100) {
                circleList.remove(circle);
            }
        }
    }

}
