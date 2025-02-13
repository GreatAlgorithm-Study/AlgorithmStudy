import java.io.*;
import java.util.*;

public class JY_18430 {
	
	static int N, M;
	static int[][] g;
	// 상 좌 하 우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][M];
		ans = 0;
		dfs(0, 0, 0);
		
		System.out.println(ans);
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	public static void dfs(int x, int y, int score) {
		// 다음 행 진행
		if(y == M) {
			x += 1;
			y = 0;
		}
		if(x == N) {
			ans = Math.max(ans, score);
			return;
		}
		// (x, y) 방문 O
		if(!visited[x][y]) {
			// 부메랑 반복
			for(int d=0; d<4; d++) {
				int ax = x + dx[d];
				int ay = y + dy[d];
				int bx = x + dx[(d+1)%4];
				int by = y + dy[(d+1)%4];
				if(!inRange(ax, ay) || !inRange(bx, by)) continue;
				if(visited[ax][ay] || visited[bx][by]) continue;
				visited[x][y] = true;
				visited[ax][ay] = true;
				visited[bx][by] = true;
				dfs(x,y+1, score + (g[x][y]*2 +g[ax][ay] + g[bx][by]));
				visited[x][y] = false;
				visited[ax][ay] = false;
				visited[bx][by] = false;
			}			
		}
		// (x, y) 방문 X
		dfs(x, y+1, score);

	}

}
