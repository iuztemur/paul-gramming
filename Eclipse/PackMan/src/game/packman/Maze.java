package game.packman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {

	ArrayList<String> lines;
//	int row, column;
	int rows, columns; 
	int width, height;
	public Position packmanPos;
	public Position ghostPos;
	public ArrayList<Position> pills, powerPills; 
	
	public Maze(int m) {
		// load the lines
		try {
			pills = new ArrayList<Position>();
			powerPills = new ArrayList<Position>();
			lines = new ArrayList<String>();
			Scanner s = new Scanner(new File("mazes/"+m));
			int r = 0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				lines.add(line);
				if (line.contains("4")) {
					ghostPos = new Position(r, line.indexOf('4'));
				}
				if (line.contains("5")) {
					packmanPos = new Position(r, line.indexOf('5'));
				}
				for (int i=0; i<line.length(); i++) {
					if (line.charAt(i) == '2') {
						pills.add(new Position(r, i));
					} else if (line.charAt(i) == '3') {
						powerPills.add(new Position(r, i));
					}
				}
				r++;
			}
			s.close();
			
			rows = lines.size();
			columns = lines.get(0).length();
			
			width = columns*2;
			height = rows*2;
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public char charAt(int r, int c) {
		return lines.get(r).charAt(c);
	}

	public char[][] getCells() {
		char[][] cells = new char[rows][columns];
		for (int r=0; r<rows; r++) {
			System.arraycopy(lines.get(r).toCharArray(), 0, cells[r], 0, columns);
//			for (int c=0; c<columns; c++) {
//			}
		}
		return cells;
	}

}
