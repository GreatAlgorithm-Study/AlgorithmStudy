import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_18430 {
	static int N;
	static int M;
	static int[][] tree;
	static int answer;
	static final int[][][] boomerang = {
		{{0, -1}, {1, 0}},
		{{-1, 0}, {0, -1}},
		{{-1, 0}, {0, 1}},
		{{0, 1}, {1, 0}}
	};

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	private static void solve(int index, int totalSum, boolean[][] check) {
		if(index == N * M) {
			answer = Math.max(answer, totalSum);
			return;
		}

		int y = index / M;
		int x = index % M;

		if(check[y][x]) {
			solve(index + 1, totalSum, check);
			return;
		}

		check[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int y1 = y + boomerang[i][0][0];
			int x1 = x + boomerang[i][0][1];
			int y2 = y + boomerang[i][1][0];
			int x2 = x + boomerang[i][1][1];

			if(!inRange(y1, x1) || !inRange(y2, x2)) continue;
			if(check[y1][x1] || check[y2][x2]) continue;

			check[y1][x1] = true;
			check[y2][x2] = true;
			int currSum = tree[y][x] * 2 + tree[y1][x1] + tree[y2][x2];
			solve(index + 1, totalSum + currSum, check);
			check[y1][x1] = false;
			check[y2][x2] = false;
		}
		check[y][x] = false;

		solve(index + 1, totalSum, check);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tree = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tree[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(0, 0, new boolean[N][M]);
		System.out.println(answer);
	}
}
