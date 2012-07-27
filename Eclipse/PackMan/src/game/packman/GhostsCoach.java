package game.packman;

import java.util.List;
import java.util.Random;

public class GhostsCoach {

	Random random = new Random();
	
	public int[] decide(GameData data) {
		// TODO Auto-generated method stub
		int[] dirs = new int[4];
		for (int i=0; i<4; i++) {
			List<Integer> list = data.getPossibleDirs(data.ghostInfos[i].pos);
//			System.out.println(list.size());
			list.remove(new Integer(MoverInfo.REV[data.ghostInfos[i].curDir]));
			dirs[i] = list.get(random.nextInt(list.size()));
		}
		return dirs;
	}
	
}
