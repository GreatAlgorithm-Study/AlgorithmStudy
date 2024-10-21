package day1021;

import java.io.*;
import java.util.*;

public class JY_시공의_돌풍 {
	
	static int N, M, T;
	static int[][] g;
	// 상우하좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static List<int[]> tList;

	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("src/day1021/tornado.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		g = new int[N][M];
		tList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
				if(g[i][j] == -1) {
					tList.add(new int[] {i, j});
				}
			}
		}
		
//		print();
//		System.out.println(Arrays.toString(tList.get(0)));
//		System.out.println(Arrays.toString(tList.get(1)));
		
		for(int t=0; t<T; t++) {
			// 1. 먼지 확산
			spread();
			
			// 2. 돌풍 청소
			// 위쪽 : 상우하좌
			int[] upDir = {0, 1, 2, 3};
			clean(tList.get(0)[0], tList.get(0)[1], upDir, true);

			// 아래쪽 : 하우상좌
			int[] downDir = {2, 1, 0, 3};
			clean(tList.get(1)[0], tList.get(1)[1], downDir, false);

		}
		// 3. 총 먼지의 양 구하기
		int ans = countDust();
		
		System.out.println(ans);
		
	}
	public static void print() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	public static void spread() {
		int[][] tmp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(g[i][j] == -1) {
					tmp[i][j] = -1;
					continue;
				}
				int amount = g[i][j] / 5;
				List<int[]> nList = new ArrayList<>();
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(!inRange(nx, ny)) continue;
					if(g[nx][ny] == -1) continue;
					
					nList.add(new int[] {nx, ny});
				}
				
				tmp[i][j] += g[i][j] - amount*nList.size();
				for(int[] next: nList) {
					int nx = next[0];
					int ny = next[1];
					tmp[nx][ny] += amount;
				}
			}
		}
		
		// copy
		g = tmp;
	}
	public static void clean(int ox, int oy, int[] dir, boolean up) {
		int x = ox;
		int y = oy;
		int dCnt = 0;
		int tmp = g[x][y];
		while(true) {
			int nx = x + dx[dir[dCnt]];
			int ny = y + dy[dir[dCnt]];
			if(!inRange(nx, ny) || (up && nx > ox) || (!up && nx < ox)) {
				// 모든 방향 전환 완료
				if(dCnt >= 3) break;
				// 방향전환 후, 다시 탐색
				dCnt = (dCnt+1) % 4;
				continue; 	
			}
			
			g[x][y] = g[nx][ny];
			x = nx;
			y = ny;
		}
		
		g[ox][oy] = tmp;
		g[ox][oy+1] = 0;
		
	}
	public static int countDust() {
		int total = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(g[i][j] > 0) {
					total += g[i][j];
				}
			}
		}
		return total;
	}

}
