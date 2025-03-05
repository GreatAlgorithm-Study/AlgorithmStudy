import java.util.*;
import java.io.*;
public class JY_9944 {

	static final int INF = 1000001;
	static int N, M;
	static char[][] g;
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static int eCnt = 0;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;
		
		int t = 1;
		while((line = br.readLine()) != null && !line.isEmpty()) {
			st = new StringTokenizer(line);
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			g = new char[N][M];
			eCnt = 0;
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int j=0; j<M; j++) {
					g[i][j] = str.charAt(j);
					if(g[i][j] == '.') eCnt++;
				}
			}
			
			ans = INF;
			
			// 시작점
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(g[i][j] == '*') continue;
					visited = new boolean[N][M];
					for(int d=0; d<4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						// d방향으로 이동 가능하면 단계 1부터 시작
						if(canGo(nx, ny)) {
							dfs(i, j, d, 1, 1);													
						}
						// 불가능하면 단계 2부터 시작
						else {
							dfs(i, j, d, 1, 0);
						}
						
					}
				}
			}
			System.out.print("Case "+t+": ");
			if(ans == INF) System.out.println(-1);
			else System.out.println(ans);
			t++;
		}
		

	}
	public static void printG() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	public static boolean canGo(int x, int y) {
		return inRange(x, y) && !visited[x][y] && g[x][y] != '*';
	}
	public static void dfs(int x, int y, int dir, int mCnt, int cnt) {
		// 모든 칸 탐색함
		if(mCnt == eCnt) {
			ans = Math.min(ans, cnt);
			return;
		}
		// 현재 이동 경로의 수가 저장된 정답보다 크다면 진행X
		if(cnt >= ans || cnt >= INF) return;
		
		visited[x][y] = true;
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		// 현재 방향으로 갈 수 있음
		if(canGo(nx, ny)) {
			dfs(nx, ny, dir, mCnt+1, cnt);
		} 
		// 갈 수 없음 -> 방햔 전환 필요(단계 증가)
		else {
			boolean isMove = false;
			for(int i=0; i<4; i++) {
				if(i == dir) continue;
				nx = x + dx[i];
				ny = y + dy[i];
				if(!canGo(nx, ny)) continue;
				
				isMove = true;
				dfs(nx, ny, i, mCnt+1, cnt+1);
			}

		}
		visited[x][y] = false;
	}

}
