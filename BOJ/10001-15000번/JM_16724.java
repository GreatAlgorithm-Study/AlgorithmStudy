import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_16724 {
	static int N;
	static int M;
	static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int cycle;

	private static void dfs(int y, int x, boolean[][] visited, boolean[][] finished) {
		visited[y][x] = true;

		int ny = y + DIR[map[y][x]][0];
		int nx = x + DIR[map[y][x]][1];

		if(!visited[ny][nx]) dfs(ny, nx, visited, finished);
		else if(!finished[ny][nx]) cycle++;

		finished[y][x] = true;
	}

	private static int solve() {
		cycle = 0;
		boolean[][] visited = new boolean[N][M];
		boolean[][] finished = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					continue;
				dfs(i, j, visited, finished);
			}
		}
		return cycle;
	}

	private static int getDir(char dir) {
		if(dir == 'U') return 0;
		else if(dir == 'D') return 1;
		else if(dir == 'L') return 2;
		return 3;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = getDir(tmp[j]);
			}
		}
		System.out.println(solve());
	}
}
