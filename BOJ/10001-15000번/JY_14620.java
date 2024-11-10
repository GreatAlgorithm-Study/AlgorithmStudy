package day1108;

import java.io.*;
import java.util.*;

public class JY_14620 {
	
	static int N;
	static int[][] g;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean[][] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		g = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		visited = new boolean[N][N];
		findSpot(0, 0, 0, 0);
		
		System.out.println(ans);
		
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static boolean isOk(int x, int y) {
		if(visited[x][y]) return false;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(visited[nx][ny]) return false;
		}
		
		return true;
	}
	public static void findSpot(int x, int y, int depth, int cost) {
		if(depth == 3) {
			ans = Math.min(ans, cost);
			return;
		}
		
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) {
				if(x==i && y==j) continue;
				if(!isOk(i, j)) continue;
				
				int c = g[i][j];
				visited[i][j] = true;
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					visited[nx][ny] = true;
					c += g[nx][ny];
				}
				
				findSpot(i, j, depth+1, cost+c);
				
				visited[i][j] = false;
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					visited[nx][ny] = false;
				}
				
			}
		}
		
	}

}
