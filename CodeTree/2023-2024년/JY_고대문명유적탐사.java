import java.io.*;
import java.util.*;

public class JY_고대문명유적탐사 {

	static final int N = 5;
	static final int S = 3;
	static int K, M;
	static int[][] g;
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Deque<Integer> q;
	static boolean[][] visited;
	static List<int[]> pList;
	static int fCnt;
	static boolean isOk;
	
	static class State implements Comparable<State> {
		 int x, y;
		 int degree, cnt;
		 int[][] sg;
		 
		public State(int x, int y, int degree, int score, int[][] sg) {
			super();
			this.x = x;
			this.y = y;
			this.degree = degree;
			this.cnt = score;
			this.sg = sg;
		}

		// 획득가치큰거 -> 각도작은거 -> 열작은거-> 행작은거
		@Override
		public int compareTo(State other) {
			if(this.cnt == other.cnt) {
				if(this.degree == other.degree) {
					if(this.y == other.y) {
						return this.x - other.x;
					}
					return this.y - other.y;
				}
				return this.degree - other.degree;
			}
			return other.cnt - this.cnt;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", degree=" + degree + ", score=" + cnt + ", sg="
					+ Arrays.toString(sg) + "]";
		}

		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ;j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			q.add(Integer.parseInt(st.nextToken()));
		}
		
		
		StringBuilder ans = new StringBuilder();
		// -- for문 반복 K번
		for(int k=0; k<K; k++) {
			int total = 0;
			// 1) 탐사 진행 3*3 선택
			total += findSpot();
			if(total == 0) break;
			
			// 2) 유물 채우기
			fillSpot();
			
			// 3) 유물 연쇄 획득
			isOk = true;
			while(isOk) {
				fCnt = 0;
				int[][] sg = calScore(g);
				total += fCnt;
				if(!isOk) break;
				g = sg;
				fillSpot();
			}
			
			ans.append(total+" ");
			
		}
		
		System.out.println(ans.toString());

	}
	public static void printG(int[][] a) {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static int[][] copyG(int[][] ori) {
		int[][] tmp = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = ori[i][j];
			}
		}
		return tmp;
	}
	// 획득가치큰거 -> 각도작은거 -> 열작은거-> 행작은거
	public static int findSpot() {
		PriorityQueue<State> pq = new PriorityQueue<>();
		
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) {
				// turn
				// copy g
				int[][] tmp = copyG(g);
				for(int t=0; t<3; t++) {
					tmp = turn(i-1, j-1, tmp);
					fCnt = 0;
					int[][] sg = calScore(tmp);
					pq.add(new State(i, j, t, fCnt, sg));
				}
			}
		}
		// 가장 우선순위 높은 State 출력
		State res = pq.poll();
		g = res.sg;
		return res.cnt;
	}
	public static int[][] turn(int sx, int sy, int[][] tg) {
		int[][] tmp = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = tg[i][j];
			}
		}
		
		for(int i=sx; i<sx+S; i++) {
			for(int j=sy; j<sy+S; j++) {
				int ox = i - sx;
				int oy = j - sy;
				int nx = oy;
				int ny = S - ox - 1;
				tmp[nx+sx][ny+sy] = tg[i][j];
			}
		}
		return tmp;
	}
	public static int[][] calScore(int[][] t) {
		visited = new boolean[N][N];
		pList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				bfs(i, j, t);
			}
		}
		
		int[][] ct = copyG(t);
		if(pList.size() == 0) {
			isOk = false;
			return ct;
		}
		for(int[] pos: pList) {
			fCnt++;
			ct[pos[0]][pos[1]] = 0;
		}
		
		return ct;
	}
	public static void bfs(int x, int y, int[][] t) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		List<int[]> tList = new ArrayList<>();
		tList.add(new int[] {x, y});
		int num = t[x][y];
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(num != t[nx][ny]) continue;
				
				visited[nx][ny] = true;
				tList.add(new int[] {nx, ny});
				q.add(new int[] {nx, ny});
			}
		}
		
		if(tList.size() >= 3) {
			pList.addAll(tList);
		}
		
	}
	public static void fillSpot() {
		// 작은 열 -> 큰 행
		for(int j=0; j<N; j++) {
			for(int i=N-1; i>=0; i--) {
				if(g[i][j] == 0) {
					g[i][j] = q.poll();
				}
			}
		}
	}

 
}
