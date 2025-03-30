import java.util.*;
import java.io.*;
public class JY_나무박멸 {
	
	static int N, M, K, C;
	static int[][] g;
	static int[][] crr;
	// 상 좌 하 우, 좌상 좌하 우하 우상
	static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
	static int[] dy = {0, -1, 0, 1, -1, -1, 1, 1};
	static int now;
	static int ans;
	// 제초제 뿌릴 때, 나무 상태
	static class State implements Comparable<State> {
		int x, y, tCnt;

		public State(int x, int y, int tCnt) {
			super();
			this.x = x;
			this.y = y;
			this.tCnt = tCnt;
		}
		@Override
		public int compareTo(State other) {
			if(this.tCnt == other.tCnt) {
				if(this.x == other.x) {
					return this.y - other.y;
				}
				return this.x - other.x;
			}
			return other.tCnt - this.tCnt;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", tCnt=" + tCnt + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("src/tree.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// g: 나무 정보
		g = new int[N][N];
		// crr: 제초제 유효년도 정보
		crr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		
		//---- for문
		now = 1;
		while(now <= M) {
			System.out.println(">> NOW: "+now);
			// 1) 나무 성장
			growTree();;
			
			// 2) 번식
			breed();
			
			// 3) 제초제 뿌리기
			ans += spread();
			
			now++;
		}
		
		System.out.println(ans);
		
		
	}
	public static void print(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 & x<N && y>=0 && y<N;
	}
	public static void growTree() {
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				if(g[x][y] <= 0) continue;
				// 나무
				// 인접 나무 수 찾기
				int cnt = 0;
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!inRange(nx, ny)) continue;
					if(g[nx][ny] <= 0) continue;
					cnt++;				
				}
				g[x][y] += cnt;
			}
		}
	}
	public static void breed() {
		// copy배열 생성
		int[][] tmp = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = g[i][j];
			}
		}
		
		// 나무 반복
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				if(g[x][y] <= 0) continue;
				// 나무
				// 인접한 가능한 4개의 칸 
				int cnt = 0;
				List<int[]> oList = new ArrayList<>();
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!inRange(nx, ny)) continue;
					// 벽 or 나무
					if(g[nx][ny] != 0) continue;
					// 제초제 남아 있음
					if(crr[nx][ny] >= now) continue;
					cnt++;
					oList.add(new int[] {nx, ny});
				}
				if(cnt == 0) continue;
				int bTree = g[x][y] / cnt;
				
				// 나무 심기
				for(int[] next : oList) {
					tmp[next[0]][next[1]] += bTree;
				}
				
			}
		}
		
		g = tmp;
		
	}
	public static int spread() {
		PriorityQueue<State> pq = new PriorityQueue<>();
		
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				if(g[x][y] <= 0) continue;
				// 나무
				// 박멸 시킬 수 있는 나무 수 찾기
				int total = g[x][y];
				for(int d=4; d<8; d++) {
					for(int k=1; k<K+1; k++) {
						int nx = x + dx[d] * k;
						int ny = y + dy[d] * k;
						if(!inRange(nx, ny)) continue;
						// 벽 or 0인 칸
						if(g[nx][ny] <= 0) break;
						total += g[nx][ny];
					}
				}
				pq.add(new State(x, y, total));
			}
		}
		
		System.out.println("> "+pq.peek());
		// 더이상 나무 없음
		if(pq.isEmpty()) {
			return 0;
		}
		// 제초제 뿌릴 나무 선택 및 뿌리기
		State tree = pq.poll();
		g[tree.x][tree.y] = 0;
		crr[tree.x][tree.y] = now + C;
		for(int d=4; d<8; d++) {
			for(int k=1; k<K+1; k++) {
				int nx = tree.x + dx[d] * k;
				int ny = tree.y + dy[d] * k;
				if(!inRange(nx, ny)) continue;
				// 벽 or 0인 칸
				if(g[nx][ny] <= 0) {
					crr[nx][ny] = now + C;
					break;
				}
				g[nx][ny] = 0;
				crr[nx][ny] = now+C;
			}
		}
		
		return tree.tCnt;
	}

}
