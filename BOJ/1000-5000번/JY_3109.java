import java.util.*;
import java.io.*;

public class JY_3109 {

	static int R, C;
	static char[][] g;
	// 우상, 우, 우하
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	static int ans, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		g = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				g[i][j] = line.charAt(j);
			}
		}
		
//		printG();
		
		ans = 0;
		for(int i=0; i<R; i++) {
			dfs(i, 0);
		}
		
		System.out.println(ans);
		
		
	}
	public static void printG() {
		for(int i=0; i<R; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	
	public static boolean inRange(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}
	public static boolean dfs(int x, int y) {
		g[x][y] = 'x';		// 갔던 곳 방문 처리
		
		if(y == C-1) {
			ans++;
			// 방향 탐색은 우상 -> 우 -> 우하 순으로 이루어지는데,
			// 우상 방향에서 갈 수 있는 모든 방향을 다 탐색 했다면 더 이상 우, 우하 방향은 탐색하지 않아도됨
			return true;
		}
		
		boolean res = false;
		for(int i=0; i<3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!inRange(nx, ny)) continue;
			if(g[nx][ny] == 'x') continue;
			
			res = dfs(nx, ny);
			
			if(res) break;
		}
		
		return res;
	}

}
