import java.io.*;
import java.util.*;

public class JY_1938 {

	static int N;
	static char[][] g;
	// 상, 좌, 하, 우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int ans;
	static class Step {
		int x, y;
		int dir;	// 세로: 0, 가로: 1
		int cnt;
		public Step(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Step [x=" + x + ", y=" + y + ", dir=" + dir + ", cnt=" + cnt + "]";
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		g =  new char[N][N];
		
		List<int[]> bList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				g[i][j] = line.charAt(j);
				
				if(g[i][j] == 'B') {
					g[i][j] = '0';
					bList.add(new int[] {i, j});
				}
			}
		}
		
		// 초기 나무 가로 or 세로인지 찾기
		int cDir = -1;
		int[] t = bList.get(0);
		int[] c = bList.get(1);
		// 세로인가? (cDir =0: 세로, cDir=1: 가로)
		if(c[0]+dx[0] == t[0] && c[1]+dy[0] == t[1]) {
			cDir = 0;
		} else {
			cDir = 1;
		}

		
		ans = 0;
		bfs(c[0], c[1], cDir);
		
		System.out.println(ans);
		
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static boolean isExit(int x, int y, int dir) {
		int ax = x + dx[dir];
		int ay = y + dy[dir];
		int bx = x + dx[dir+2];
		int by = y + dy[dir+2];
		
		return (g[ax][ay]=='E') && (g[x][y]=='E') && (g[bx][by] =='E');
		
	}
	public static void bfs(int sx, int sy, int sDir) {
		// 방문처리: (i,j)좌표에 중심점이 세로(0) or 가로(1)로 방문한적이 있는지 체크
		boolean[][][] visited = new boolean[N][N][2];
		Queue<Step> q = new LinkedList<>();
		
		q.add(new Step(sx, sy, sDir, 0));
		visited[sx][sy][sDir] = true;
		
		while(!q.isEmpty()) {
			Step now = q.poll();
			
			if(isExit(now.x, now.y, now.dir)) {
				ans = now.cnt;
				break;
			}
			
			// U, L, D, R
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				// 중심 통나무 체크
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny][now.dir]) continue;
				if(g[nx][ny] == '1') continue;
				
				
				// 나머지 통나무들 체크
				int ax = nx + dx[now.dir];
				int ay = ny + dy[now.dir];
				int bx = nx + dx[now.dir+2];
				int by = ny + dy[now.dir+2];
				if(!inRange(ax, ay) || !inRange(bx, by)) continue;
				if(g[ax][ay] == '1' || g[bx][by] == '1') continue;
								
				// 이동가능
				visited[nx][ny][now.dir] = true;
				q.add(new Step(nx, ny, now.dir, now.cnt+1));
			}
			
			if(!canTurn(now.x, now.y)) continue;
			// turn
			int nDir = Math.abs(now.dir-1);
			if(visited[now.x][now.y][nDir]) continue;	// 회전한 방향으로 회전한 적이 있음
			int ax = now.x + dx[nDir];
			int ay = now.y + dy[nDir];
			int bx = now.x + dx[nDir+2];
			int by = now.y + dy[nDir+2];
			if(!inRange(ax, ay) || !inRange(bx, by)) continue;
			if(g[ax][ay] == '1' || g[bx][by] == '1') continue;
			
			visited[now.x][now.y][nDir] = true;
			q.add(new Step(now.x, now.y, nDir, now.cnt+1));
		}
		
	}
	public static boolean canTurn(int x, int y) {
		for(int i=x-1; i<x+2; i++) {
			for(int j=y-1; j<y+2; j++) {
				if(!inRange(i, j)) return false;
				if(g[i][j] == '1') return false;
			}
		}
		return true;
	}

}
