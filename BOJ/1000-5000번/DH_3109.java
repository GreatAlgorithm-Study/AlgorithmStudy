import java.io.*;
import java.util.*;

/*
 * 빵집
 */

public class DH_3109 {
	static int[] dr = {-1, 0, 1}, dc = {1, 1, 1};
	static int R, C, cnt;
	static char[][] map;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
		
		System.out.println(cnt);
	}

	static void solution() {
		for(int r = 0; r < R; r++) {
			flag = false;
			dfs(r, 0); // (r, 0)에서 dfs 시작
		}
	}
	
	static void dfs(int r, int c) {
		if(c == C - 1) {
			cnt += 1;
			flag = true;
			return;
		}
		
		for(int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc) || map[nr][nc] == 'x' || flag) continue;
			map[nr][nc] = 'x';
			dfs(nr, nc);
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R= Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			map[r] = str.toCharArray();
		}
	}
	
	static void printMap(char[][] map) {
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
		
		System.out.println();
	}
}
