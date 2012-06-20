package game.packman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.game.engine.Game;
import org.game.engine.GameApplication;

public class PackMan extends Game {
	
	public static void main(String[] args) {
		GameApplication.start(new PackMan());
	}

	final int STEP = 2;
	
	BufferedImage packman;
	int frame;
	int reqDir, curDir;
	int column, row;
	
	int columns, rows;
	
	ArrayList<String> lines = new ArrayList<String>();
	BufferedImage[] mazeImages = new BufferedImage[4];
	int mazeNo = 0;
	Maze[] mazes = new Maze[4];
	char[][] cells; // copy of chars in current maze
	
	public PackMan() {
		// load mazes information
		for (int m=0; m<4; m++) {
			mazes[m] = new Maze(m);
		}
		
		// update the information from mazes[mazeNo]
		// get copy cells
		cells = mazes[mazeNo].getCells();
		rows = mazes[mazeNo].rows;
		columns = mazes[mazeNo].columns;
		// initial position for packman
		row = mazes[mazeNo].row;
		column = mazes[mazeNo].column;
		// size of the game screen
		width = mazes[mazeNo].width;
		height = mazes[mazeNo].height;
		
		title = "PackMan";
		frame = 0;
		curDir = reqDir = KeyEvent.VK_RIGHT;
		try {
			packman = ImageIO.read(new File("images/packman.png"));
			for (int m=0; m<4; m++) {
				mazeImages[m] = ImageIO.read(new File("images/"+m+m+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (37 <= key && key <= 40) {
			reqDir = key;
		}
	}
	
	@Override
	public void update() {
		frame++;
		if (frame > 5) {
			frame = 0;
		}
		
		if (move(reqDir) == SUCCESS) {
			curDir = reqDir;
		} else {
			move(curDir);
		}
		// updating the pills
		if (cells[row][column] == '2') {
			// eat the pill
			cells[row][column] = '1';
		} else if (cells[row][column] == '3') {
			// eat the power pill
			cells[row][column] = '1';
			delay = 15;
		}
	}
	
	static int SUCCESS = 1, FAIL = 0;
	
	private int move(int reqDir) {
		// current position of packman is (row, column)
		switch (reqDir) {
		case KeyEvent.VK_LEFT: // 37
			if (column > 0 && mazes[mazeNo].charAt(row, column-1) != '0') {
				column -= 1;
				return SUCCESS;
			} 
			if (column == 0 && cells[row][columns-1] == '1' ) {
//				row = row;
				column = columns-1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_UP:   // 38
			if (row > 0 && mazes[mazeNo].charAt(row-1, column) != '0') {
				row -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_RIGHT: // 39
			if (column < columns-1 && mazes[mazeNo].charAt(row, column+1) != '0') {
				column += 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_DOWN:  // 40
			if (row < rows-1 && mazes[mazeNo].charAt(row+1, column) != '0') {
				row += 1;
				return SUCCESS;
			}
			break;
		}
		return FAIL;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(mazeImages[mazeNo], 0, 0, null);
		g.setColor(Color.white);
		for (int r=0; r<mazes[mazeNo].rows; r++) {
			for (int c=0; c<mazes[mazeNo].columns; c++) {
				if (cells[r][c] == '2') {
					// draw pill
					g.fillOval(c*STEP-3, r*STEP-3, 6, 6);
				} else if (cells[r][c] == '3') {
					// draw power pill
					g.fillOval(c*STEP-6, r*STEP-6, 12, 12);
				}
			}
		}
		g.drawImage(packman.getSubimage((frame/2)*30, (curDir-37)*30, 28, 28), column*STEP-14, row*STEP-14, null);
	}

}
