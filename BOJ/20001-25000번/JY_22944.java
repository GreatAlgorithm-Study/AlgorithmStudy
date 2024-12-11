import java.io.*;
import java.util.*;

public class JY_22944 {
	
	static final int INF = Integer.MAX_VALUE;
	static int N, H, D;
	static char[][] g;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static List<int[]> uList;
	static int K;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		g = new char[N][N];
		int sx = -1; 
		int sy = -1;
		int ex = -1;
		int ey = -1;
		K = 0;
		uList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				g[i][j] = line.charAt(j);
				if(g[i][j] == 'S') {
					sx = i;
					sy = j;
				}
				if(g[i][j] == 'U') {
					uList.add(new int[] {i, j});
					K++;
				}
				if(g[i][j] == 'E') {
					ex = i;
					ey = j;
				}
			}
		}
		uList.add(new int[] {ex, ey});
		
		ans = INF;
		
		// DFS 탐색 진행
		visited = new boolean[N][N];
		visited[sx][sy] = true;
		dfs(sx, sy, 0, H, 0);
		
		if(ans == INF) System.out.println(-1);
		else System.out.println(ans);

		
	}
	public static int calDist(int sx, int sy, int ex, int ey) {
		return Math.abs(sx-ex) + Math.abs(sy-ey);
	}
	public static void dfs(int x, int y, int depth, int hp, int up) {
		if(g[x][y] == 'E') {
			ans = Math.min(ans, depth);
			return;
		}
		if(depth >= ans) return;
		
		for(int i=0; i<K+1; i++) {
			int[] u = uList.get(i);
			if(visited[u[0]][u[1]]) continue;
			
			// 현재 위치 -> 다음 우산 위치의 거리 
			int nDist = calDist(x, y, u[0], u[1]);
			if(nDist > hp+up) continue;
			
			int nhp = hp;
			// 체력이 소모 되는 경우 : 남은 우산 내구성 - 이동할 거리 + 1(다음위치도 우산이므로)
			int tmp = up - nDist + 1;
			if(tmp < 0) {
				nhp += tmp;
			}
			visited[u[0]][u[1]] = true;
			dfs(u[0], u[1], depth+nDist, nhp, D-1);
			visited[u[0]][u[1]] = false;
		}
	}

}
