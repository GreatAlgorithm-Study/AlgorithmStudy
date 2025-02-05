import java.io.*;
import java.util.*;

public class DH_윷놀이_사기단 {
	static final int TURN = 10;
	static int[] arr, sel; // 중복 순열
	static int[][] map;
	static class Horse {
		int r, c;
		public Horse() {}
	}
	static int maxResult;
	static boolean[] v;
	static Horse[] horses;
	static Set<Integer> moreOne;
	
	public static void main(String[] args) throws Exception {
		horses = new Horse[5];
		v = new boolean[5];
		for(int i = 0; i < horses.length; i++) horses[i] = new Horse();

		map = new int[][]{
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 41},
			{10, 13, 16, 19, 25, 30, 35, 40, 41},
			{20, 22, 24, 25, 30, 35, 40, 41},
			{30, 28, 27, 26, 25, 30, 35, 40, 41}};
			
		moreOne = new HashSet<Integer>();
		moreOne.add(16);
		moreOne.add(22);
		moreOne.add(24);
		moreOne.add(26);
		moreOne.add(28);
		moreOne.add(30);

		initInput();
		solution();
		System.out.println(maxResult);
	}
	
	
	static void solution() {
		sel = new int[10];
		func(0, 0);
	}
	
	static void func(int depth, int result) {
		if(depth == TURN) {
			maxResult = Math.max(maxResult, result);
			return;
		}
		
		for(int i = 1; i < 5; i++) {
			if(v[i]) continue;
			sel[depth] = i;
			
			int currentR = horses[i].r;
			int currentC = horses[i].c;
			
			int nr = currentR;
			int nc = currentC + arr[depth];
			
			boolean canGo = true;
			if(nc >= map[nr].length) {
				nc = map[nr].length - 1;
				v[i] = true;
			} else {
				if(map[nr][nc] == 10 || map[nr][nc] == 20 ||
						(map[nr][nc] == 30 && map[nr][nc - 1] == 28)) {
					nr = map[nr][nc] / 10;
					nc = 0;
				}
				
				
				for(int j = 1; j < 5; j++) {
					if(j == i) continue;
					if(map[nr][nc] == 0 || map[horses[j].r][horses[j].c] == 0) continue;
					if(map[nr][nc] == 41 || map[horses[j].r][horses[j].c] == 41) continue;

					if(moreOne.contains(map[nr][nc])) {
						if(horses[j].r == nr && horses[j].c == nc) {
							canGo = false;
							break;
						}
					}  else {
						if(map[nr][nc] == map[horses[j].r][horses[j].c]) {
							canGo = false;
							break;
						}
					}
				}
				
			}
			
			if(!canGo) continue;
			
			horses[i].r = nr;
			horses[i].c = nc;
			
			func(depth + 1, result + (map[nr][nc] % 41));
			
			horses[i].r = currentR;
			horses[i].c = currentC;
			
			if(currentC < map[currentR].length) {
				v[i] = false;
			}
		}
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/윶놀이사기단.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[10];
		for(int i = 0; i < 10; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
}
