import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 25% 틀림 -> 1000초 이상 아니고, 초과로
 */
public class JM_19237 {
	static class Smell {
		int sn;
		int k;

		public Smell(int sn, int k) {
			this.sn = sn;
			this.k = k;
		}

		public void set(int sn, int k) {
			this.sn = sn;
			this.k = k;
		}

		@Override
		public String toString() {
			return "(" + sn + ", " + k + ')';
		}
	}
	static class Shark implements Comparable<Shark> {
		int sn;
		int y, x;
		int d;

		public Shark(int sn, int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.sn = sn;
			this.d = d;
		}

		@Override
		public int compareTo(Shark o) {
			return this.sn - o.sn;
		}

		@Override
		public String toString() {
			return "Shark{" +
				"sn=" + sn +
				", y=" + y +
				", x=" + x +
				", d=" + d +
				'}';
		}
	}
	static int N; // 격자 NxN
	static int M; // 상어 개수
	static int K; // 냄새 사라지는 시간
	static Smell[][] map;
	static int[][][] sharkPriorityDir;
	static PriorityQueue<Shark> sharks;
	static PriorityQueue<Shark> nextShanks;
	static final int MAX_TIME = 1000;
	static final int[][] DIR = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상,하,좌,우

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	private static void reduceKAll() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j].sn == 0) continue;
				map[i][j].k--;
				if(map[i][j].k == 0) map[i][j].sn = 0;
			}
		}
	}

	private static void move() {
		while (!nextShanks.isEmpty()) {
			Shark shank = nextShanks.poll();
			if(map[shank.y][shank.x].sn == 0 || map[shank.y][shank.x].sn == shank.sn) {
				map[shank.y][shank.x].set(shank.sn, K + 1); // K + 1로 초기화 필요
				sharks.add(shank);
			}
		}
	}

	private static void addNextShark() {
		while (!sharks.isEmpty()) {
			Shark curr = sharks.poll();
			Shark prev = null; // 가능한 이동 경로가 없을 경우, 냄새가 있는 칸
			Shark next = null;

			for(int nd : sharkPriorityDir[curr.sn][curr.d]) {
				if(nd == 0) continue;
				int ny = curr.y + DIR[nd][0];
				int nx = curr.x + DIR[nd][1];
				if(!inRange(ny,nx)) continue;

				if(next == null && map[ny][nx].sn == 0) {
					next = new Shark(curr.sn, ny, nx, nd);
				}
				if(prev  == null && map[ny][nx].sn == curr.sn) {
					prev = new Shark(curr.sn, ny, nx, nd);
				}
			}
			if(next == null) nextShanks.add(prev);
			else nextShanks.add(next);
		}
	}

	private static int solve() {
		int time = 0;
		while (true) {
			addNextShark(); // 1. 우선순위 방향 결정
			move();	// 2. 이동
			reduceKAll(); // 3. 현재 k 시간 감소시키기

			time++;
			if(time > MAX_TIME) return -1;
			if(sharks.size() == 1) break;
		}
		return time;
	}

	public static void init(int[][] tmpMap, int[] startDir) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Smell(0, 0);
				if(tmpMap[i][j] != 0) {
					int n = tmpMap[i][j];
					map[i][j].set(n, K);
					sharks.add(new Shark(tmpMap[i][j], i, j, startDir[n]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Smell[N][N];
		sharks = new PriorityQueue<>();
		nextShanks = new PriorityQueue<>();

		int[][] tmpMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				tmpMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] startDir = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			startDir[i] = Integer.parseInt(st.nextToken());
		}

		sharkPriorityDir = new int[M + 1][5][5];
		for (int m = 1; m <= M; m++) {
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 4; j++) {
					sharkPriorityDir[m][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		init(tmpMap, startDir);
		System.out.println(solve());
	}
}
