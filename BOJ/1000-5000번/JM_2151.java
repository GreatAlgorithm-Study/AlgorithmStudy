import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JM_2151 {
	static class Point implements Comparable<Point> {
		int y, x;
		int dir;
		int dist;

		public Point(int y, int x, int dir, int dist) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			return this.dist - o.dist;
		}
	}
	static int N;
	static char[][] map;
	static Point start;
	static Point end;
	static final int[][] DIR = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

	private static boolean inRange(int y, int x) {
		return  0 <= y && y < N && 0 <= x && x < N;
	}

	private static int solve() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[][][] visited = new boolean[N][N][4];

		for (int i = 0; i < 4; i++) {
			pq.add(new Point(start.y, start.x, i, 0));
		}

		while (!pq.isEmpty()) {
			Point curr = pq.poll();

			visited[curr.y][curr.x][curr.dir] = true;

			if(curr.y == end.y && curr.x == end.x) return curr.dist;

			int ny = curr.y + DIR[curr.dir][0];
			int nx = curr.x + DIR[curr.dir][1];

			if(!inRange(ny, nx) || map[ny][nx] == '*' || visited[ny][nx][curr.dir]) continue;

			if(map[ny][nx] == '!') {
				pq.add(new Point(ny, nx, (curr.dir + 1) % 4, curr.dist + 1));
				pq.add(new Point(ny, nx, (curr.dir + 3) % 4, curr.dist + 1));
			}
			pq.add(new Point(ny, nx, curr.dir, curr.dist));

		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if(map[i][j] == '#') {
					if(start == null) start = new Point(i, j, -1, 0);
					else end = new Point(i, j, -1, 0);
				}
			}
		}

		System.out.println(solve());
	}
}
