import java.io.*;
import java.util.*;

/*
 * 무기 공학
 */

public class DH_18430 {
	
	static int N, M, result;
	static int[][] arr;
	static int[][][] dir = {
			{{0, 0}, {0, -1}, {1, 0}},
			{{0, 0}, {-1, 0}, {0, -1}},
			{{0, 0}, {-1, 0}, {0, 1}},
			{{0, 0}, {0 , 1}, {1, 0}}
	};
	
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		initInput();
		
		dfs(0, 0);
		
		System.out.println(result);
	}
	
	static void dfs(int pos, int sum) {
		
		if(pos == N * M) {
			result = Math.max(result, sum);
			return;
		}

		int r = pos / M;
		int c = pos % M;
		
		// 바로 그 다음 지점에 놓아주기
		dfs(pos + 1, sum);
		
		// 부메랑을 놓을 수 있다면 놓고 다음 지점으로 이동
		for(int d = 0; d < 4; d++) {
			
			boolean flag = true; // 부메랑을 놓을 수 있는지
			
			int tmp = 0;
			
			for(int k = 0; k < 3; k++) {
				int nr = r + dir[d][k][0];
				int nc = c + dir[d][k][1];
				
				// 범위를 벗어나거나, 이미 놓여져 있다면
				if(!check(nr, nc) || v[nr][nc]) {
					flag = false;
					break;
				}
			}
			
			if(!flag) continue;
			
			for(int k = 0; k < 3; k++) {
				int nr = r + dir[d][k][0];
				int nc = c + dir[d][k][1];
				
				v[nr][nc] = true;
				tmp += arr[nr][nc];
				if(k == 0) tmp += arr[nr][nc];
			}
			
			// dfs 진행
			dfs(pos + 1, sum + tmp);
			
			// 원상복구
			for(int k = 0; k < 3; k++) {
				int nr = r + dir[d][k][0];
				int nc = c + dir[d][k][1];
				
				v[nr][nc] = false;
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		v = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < M; c++) arr[r][c] = Integer.parseInt(st.nextToken());
		}
	}
}