import java.io.*;
import java.util.*;

/*
 * 꽃길
 */
public class DH_14620 {
	static int N, result;
	static int[][] map;
	static boolean[][] v;
	static int[] dr = {-1 ,1, 0, 0}, dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		initInput();
		func(0, 0, 0);
		System.out.println(result);
	}
	
	static void func(int depth, int idx, int cost) {
		if(depth == 3) {
			result = Math.min(result, cost);
			return;
		}
		
		for(int i = idx; i < N * N; i++) {
			int r = i / N, c = i % N;

			if(!check(r, c)) continue;

			v[r][c] = true;
			cost += map[r][c] + plant(r, c, true);

			func(depth + 1, i + 1, cost);
			v[r][c] = false;
			cost -= map[r][c] + plant(r, c, false);
		}
	}
	
	static int plant(int r, int c, boolean status) {
		int cost = 0;
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			v[nr][nc] = status;
			cost += map[nr][nc];
		}
		
		return cost;
	}
	
	static boolean check(int r, int c) {
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isValid(nr, nc) || v[nr][nc]) return false;
		}
		
		return true;
	}
	
	static boolean isValid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N][N];
		
		result = Integer.MAX_VALUE;
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
}
