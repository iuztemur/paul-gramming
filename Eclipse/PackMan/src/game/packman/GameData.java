package game.packman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameData {

	int mazeNo;
	CopyOnWriteArrayList<Position> pills;
	CopyOnWriteArrayList<Position> powerPills;
	public MoverInfo packman;
	public GhostInfo[] ghostInfos = new GhostInfo[4];
	public int score;
	
	Maze[] mazes;
	boolean dead = false;
	
	public GameData() {
		mazes = new Maze[4];
		// load mazes information
		for (int m=0; m<4; m++) {
			mazes[m] = new Maze(m);
		}
//		mazeNo = 0;
		setMaze(mazeNo);
	}
	
	private void setMaze(int m) {
		packman = new MoverInfo(mazes[m].packmanPos);
		for (int g=0; g<4; g++) {
			ghostInfos[g] = new GhostInfo(mazes[m].ghostPos);
		}
		pills = new CopyOnWriteArrayList((List<Position>)(mazes[m].pills.clone()));
		powerPills = new CopyOnWriteArrayList((List<Position>)(mazes[m].powerPills.clone()));
	}

	public void movePackMan(int reqDir) {
		if (move(reqDir, packman)) {
			packman.curDir = reqDir;
		} else {
			move(packman.curDir, packman);
		}
		
	}
	
	private int wrap(int value, int incre, int max) {
		return (value+max+incre)%max;
	}
	private boolean move(int reqDir, MoverInfo info) {
		// current position of packman is (row, column)
		int row = info.pos.row;
		int column = info.pos.column;
		int rows = mazes[mazeNo].rows;
		int columns = mazes[mazeNo].columns;
		int nrow = wrap(row, MoverInfo.DROW[reqDir], rows);
		int ncol = wrap(column, MoverInfo.DCOL[reqDir], columns);
		if (mazes[mazeNo].charAt(nrow, ncol) != '0') {
			info.pos.row = nrow;
			info.pos.column = ncol;
			return true;
		}
		return false;
	}
	public void update() {
		if (pills.contains(packman.pos)) {
			pills.remove(packman.pos);
			score += 5;
		} else if (powerPills.contains(packman.pos)) {
			powerPills.remove(packman.pos);
			score += 50;
			for (GhostInfo g:ghostInfos) {
				g.edibleCountDown = 500;
			}
		}
		for (GhostInfo g:ghostInfos) {
			if (g.edibleCountDown > 0) {
				if (touching(g.pos, packman.pos)) {
					// eat the ghost and reset
					score += 100;
					g.curDir = g.reqDir = MoverInfo.LEFT;
					g.pos.row = mazes[mazeNo].ghostPos.row;
					g.pos.column = mazes[mazeNo].ghostPos.column;
					g.edibleCountDown = 0;
				} 
				g.edibleCountDown--;
			} else {
				if (touching(g.pos, packman.pos)) {
					dead = true;
				}
			}
		}
		// level is cleared
		if (pills.isEmpty() && powerPills.isEmpty()) {
			mazeNo++;
			if (mazeNo < 4) {
				setMaze(mazeNo);
			} else {
				// game over
				dead = true;
			}
		}
	}
	
	private boolean touching(Position a, Position b) {
		return Math.abs(a.row-b.row) + Math.abs(a.column-b.column) < 3;
	}

	public void moveGhosts(int[] reqDirs) {
//		for (GhostInfo info:ghostInfos) {
		for(int i=0; i<4; i++) {
			GhostInfo info = ghostInfos[i];
			info.reqDir = reqDirs[i];
			if (move(info.reqDir, info)) {
				info.curDir = info.reqDir;
			} else {
				move(info.curDir, info);
			}
		}
	}
	public int getWidth() {
		return mazes[mazeNo].width;
	}
	public int getHeight() {
		return mazes[mazeNo].height;
	}

	public List<Integer> getPossibleDirs(Position pos) {
		List<Integer> list = new ArrayList<Integer>();
		for (int d=0; d<4; d++) {
			Position npos = getNextPositionInDir(pos, d);
			if (mazes[mazeNo].charAt(npos.row, npos.column) != '0') {
				list.add(d);
			}
		}
		return list;
	}

	private Position getNextPositionInDir(Position pos, int d) {
		int nrow = wrap(pos.row, MoverInfo.DROW[d], mazes[mazeNo].rows);
		int ncol = wrap(pos.column, MoverInfo.DCOL[d], mazes[mazeNo].columns);
		return new Position(nrow, ncol);
	}

}
