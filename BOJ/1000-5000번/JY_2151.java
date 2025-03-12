import java.io.*;
import java.util.*;
public class JY_2151 {

	static final int INF = Integer.MAX_VALUE;
	static int N;
	static char[][] g;
	static int[][] cnt;
	static int[][] doors;
	// 상 좌 하 우
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
	static int ans;
	static class State {
		int x, y, dir, cnt;

		public State(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", dir=" + dir + ", cnt=" + cnt + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		g = new char[N][N];
		
		doors = new int[2][2];
		int d = 0;
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				g[i][j] = line.charAt(j);
				if(g[i][j] == '#') {
					doors[d][0] = i;
					doors[d][1] = j;
					d++;
				}
			}
		}
		cnt = new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(cnt[i], INF);
		}
		
		ans = -1;
		bfs(doors[0][0], doors[0][1]);
		
		System.out.println(ans);
	}

	
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static void bfs(int sx, int sy) {
		PriorityQueue<State> pq = new PriorityQueue<>((o1, o2)->o1.cnt-o2.cnt);
		
		cnt[sx][sy] = 0;
		g[sx][sy] = '*';
		
		// 초기에 갈 수 있는 방향 탐색
		for(int i=0; i<4; i++) {
			int nx = sx + dx[i];
			int ny = sy + dy[i];
			if(!inRange(nx, ny)) continue;
			if(g[nx][ny] == '*') continue;
			pq.add(new State(sx, sy, i, 0));
		}
		
		while(!pq.isEmpty()) {
			State now = pq.poll();
			
			if(g[now.x][now.y] == '#') {
				ans = now.cnt;
				return;
			}
			int nx = now.x + dx[now.dir];
			int ny = now.y + dy[now.dir];
			
			if(!inRange(nx, ny)) continue;
			if(g[nx][ny] == '*') continue;
			// 한 번이상 방문했고, 현재 저장된 회수보다 크다면 진행 X
			if(cnt[nx][ny] != INF && cnt[nx][ny] >= now.cnt) continue;
			
			cnt[nx][ny] = now.cnt;
			pq.add(new State(nx, ny, now.dir, now.cnt));
			
			// 다음 칸이 거울
			if(g[nx][ny] == '!') {
				pq.add(new State(nx, ny, (now.dir+1)%4, now.cnt+1));
				pq.add(new State(nx, ny, (now.dir+3)%4, now.cnt+1));
			}
		}
		
	}

}
