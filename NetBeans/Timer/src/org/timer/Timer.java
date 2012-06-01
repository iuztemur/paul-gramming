/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.timer;

import com.sun.awt.AWTUtilities;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author paul
 */
public class Timer implements MouseListener {

	protected boolean over;
	protected String title = "Timer";
	protected int width=214, height=27;
	protected int delay = 30;

	int minute, second, millisecond;
	long lastUpdate, remain;
	boolean pause;
	BufferedImage colon;
	BufferedImage digits;

	public void init() {
		pause = true;
		minute = 0;
		second = 0;
		millisecond = 0;
		try {
		colon = ImageIO.read(ImageIO.class.getResource("/images/colon.png"));
		digits = ImageIO.read(ImageIO.class.getResource("/images/digits.png"));
		} catch (Exception ex) {}
	}

	public void reset() {
		minute = 0;
		second = 0;
		millisecond = 0;
	}

	public void update() {
		long current = System.currentTimeMillis();
		if (!pause) {
			remain += (current-lastUpdate);
			int a = ((int)remain/10);
			if (a > 0) {
				millisecondsPassed(a);
				remain -= a*10;
				lastUpdate = current;
			}
		} else {
			lastUpdate = current;
		}
	}

	public void draw(Graphics2D g) {
		int i = minute/10;
		int j = minute%10;
		g.drawImage(digits.getSubimage(0, i*(27+2), 30, 27),0,0,null);
		g.drawImage(digits.getSubimage(0, j*(27+2), 30, 27),30,0,null);
		g.drawImage(colon,61,0,null);
		i = second/10;
		j = second%10;
		g.drawImage(digits.getSubimage(0, i*(27+2), 30, 27),77,0,null);
		g.drawImage(digits.getSubimage(0, j*(27+2), 30, 27),107,0,null);
		g.drawImage(colon,138,0,null);
		i = millisecond/10;
		j = millisecond%10;
		g.drawImage(digits.getSubimage(0, i*(27+2), 30, 27),154,0,null);
		g.drawImage(digits.getSubimage(0, j*(27+2), 30, 27),184,0,null);
	}

	public boolean isOver() { return over; }
	public String getTitle() { return title; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDelay() { return delay; }
	public void resize(int width, int height) {}

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() > 1) {
			System.exit(0);
		}
		if (me.getButton() == MouseEvent.BUTTON3) {
			pause = true;
			reset();
		}
		if (me.getButton() == MouseEvent.BUTTON1) {
			pause = !pause;
			if (!pause) {
			}
		}
	}
	
	private void millisecondsPassed(int milliseconds) {
		millisecond+=milliseconds;
		if (millisecond >= 100) {
			millisecond = millisecond - 100;
			second++;
			if (second >= 60) {
				second = second - 60;
				minute++;
				if (minute >= 60) {
					minute = 0;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
	}

	@Override
	public void mouseReleased(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Area a = new Area();
				a.add(new Area(new RoundRectangle2D.Float(1, 0, 28, 27, 5, 5)));
				a.add(new Area(new RoundRectangle2D.Float(31, 0, 28, 27, 5, 5)));
				a.add(new Area(new RoundRectangle2D.Float(61, 0, 15, 27, 4, 4)));
				a.add(new Area(new RoundRectangle2D.Float(79, 0, 28, 27, 5, 5)));
				a.add(new Area(new RoundRectangle2D.Float(108, 0, 28, 27, 5, 5)));
				a.add(new Area(new RoundRectangle2D.Float(138, 0, 15, 27, 4, 4)));
				a.add(new Area(new RoundRectangle2D.Float(155, 0, 28, 27, 5, 5)));
				a.add(new Area(new RoundRectangle2D.Float(185, 0, 28, 27, 5, 5)));

				Timer timer = new Timer();
				JFrame frame = new JFrame(timer.getTitle());
				frame.setUndecorated(true);
				frame.setLocation(1000, 80);
				frame.setSize(timer.getWidth(), timer.getHeight());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				GameCanvas canvas = new GameCanvas(timer);
				frame.add(canvas);
				frame.setVisible(true);
				AWTUtilities.setWindowShape(frame, a);
				canvas.requestFocus();
				new GameLoop(timer, canvas).start();
			}
		});
	}
}
