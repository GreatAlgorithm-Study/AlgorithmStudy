import java.io.*;
import java.util.*;

public class JY_1405 {

	static int N, M;
	static double[] arr;
	static boolean[][] visited;
	// 동 서 남 북
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static double ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new double[4];
		for(int i=0; i<4; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) * 0.01;
		}
		
		// N <= 14 이므로 상하좌우 방향으로 최대 14까지 갈 수 있으므로 보드판 크기: 30
		M = 30;
		visited = new boolean[M][M];
		visited[M/2][M/2] = true;
		ans = 0;
		dfs(M/2, M/2, 0, 1);
		
		System.out.println(ans);
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<M && y>=0 && y<M;
	}
	public static void dfs(int x, int y, int cnt, double total) {
		if(cnt == N) {
			ans += total;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!inRange(nx, ny)) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1, total*arr[i]);
			visited[nx][ny] = false;
		}
	}

}
