import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_9944 {
	static int N;
	static int M;
	static char[][] map;
	private static final int[][] DIR = {{0, 1}, {-1, 0}, {1, 0}, {0, -1}};

	private static boolean isRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	private static int moveBack(int y, int x, int ny, int nx, int d) {
		int moveCnt = 0;
		while (!(y == ny && x == nx)) {
			map[ny][nx] = '.';
			moveCnt += 1;
			ny -= DIR[d][0];
			nx -= DIR[d][1];
		}
		return moveCnt;
	}

	private static int[] move(int y, int x, int d) {
		int moveCnt = 0;
		while (true) {
			int ny = y + DIR[d][0];
			int nx = x + DIR[d][1];
			if(!isRange(ny, nx) || map[ny][nx] != '.') break;
			map[ny][nx] = '#';
			moveCnt += 1;
			y = ny;
			x = nx;
		}
		return new int[]{y, x, moveCnt};
	}

	private static int dfs(int y, int x, int blankCnt) {
		if(blankCnt == 0) {
			return 0;
		}

		int res = -1;
		for (int d = 0; d < 4; d++) {
			int[] next = move(y, x, d);
			int ny = next[0];
			int nx = next[1];
			blankCnt -= next[2];

			if(!(y == ny && x == nx)) {
				int tmp = dfs(ny, nx, blankCnt);
				if(tmp != -1 && (res == -1 || res > tmp + 1)) {
					res = tmp + 1;
				}
			}

			blankCnt += moveBack(y, x, ny, nx, d);
		}

		return res;
	}

	private static int solve(int blankCnt) {
		int res = -1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == '*')  continue;
				map[i][j] = '#';
				int tmp = dfs(i, j, blankCnt - 1);
				if(tmp != -1 && (res == -1 || res > tmp)) {
					res = tmp;
				}
				map[i][j] = '.';
			}
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;

		int T = 1;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine()) != null && line.length() > 0) {
			st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int blankCnt = 0;
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if(map[i][j] == '.') blankCnt++;
				}
			}
			sb.append("Case ").append(T).append(": ").append(solve(blankCnt)).append("\n");
			T++;
		}
		System.out.println(sb);
	}
}
