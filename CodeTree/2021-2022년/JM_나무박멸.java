import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_나무박멸 {
	static int N;
	static int M;	// 박멸이 진행되는 년 수
	static int K;	// 제초제의 확산 범위 K
	static int C;	// 제초제가 남아있는 년 수 C
	static int[][] map; // 0(빈칸), -1(벽)
	static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static int[][] herbicide;

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	private static void reduceHerbicide() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(herbicide[i][j] <= 0) continue;
				herbicide[i][j] -= 1;
			}
		}
	}

	private static int[] findMaxRemovedTreePos() {
		int[] pos = new int[3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] <= 0) continue;

				int removedTree = map[i][j];
				for (int d = 4; d < 8; d++) {
					int ny = i;
					int nx = j;
					int k = 0;
					while (k++ < K) {
						ny += DIR[d][0];
						nx += DIR[d][1];
						if(!inRange(ny, nx) || map[ny][nx] == 0 || map[ny][nx] == -1) break;
						removedTree += map[ny][nx];
					}
				}

				if(removedTree > pos[2]) {
					pos[0] = i;
					pos[1] = j;
					pos[2] = removedTree;
				}
			}
		}
		return pos;
	}

	private static int sprayHerbicide() {
		int[] pos = findMaxRemovedTreePos();
		int y = pos[0];
		int x = pos[1];
		int removedTree = pos[2];

		map[y][x] = 0;
		herbicide[y][x] = C + 1;
		for (int d = 4; d < 8; d++) {
			int ny = y;
			int nx = x;
			int k = 0;
			while (k++ < K) {
				ny += DIR[d][0];
				nx += DIR[d][1];
				if(!inRange(ny, nx)) break;
				herbicide[ny][nx] = C + 1;
				if(map[ny][nx] <= 0) break;
				map[ny][nx] = 0;
			}
		}

		return removedTree;
	}

	private static int countReproduction(int y, int x) {
		int cnt = 0;
		for (int k = 0; k < 4; k++) {
			int ny = y + DIR[k][0];
			int nx = x + DIR[k][1];
			if(!inRange(ny, nx) || map[ny][nx] != 0 || herbicide[ny][nx] > 0) continue;
			cnt++;
		}
		return cnt == 0 ? 0 : map[y][x] / cnt;
	}

	private static void reproduce() {
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] <= 0) continue;

				int reproduction = countReproduction(i, j);
				if(reproduction == 0) continue;
				for (int d = 0; d < 4; d++) {
					int ny = i + DIR[d][0];
					int nx = j + DIR[d][1];
					if(!inRange(ny, nx) || map[ny][nx] != 0 || herbicide[ny][nx] > 0) continue;
					tmp[ny][nx] += reproduction;
				}
			}
		}
		map = tmp;
	}

	private static void growUp() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] <= 0) continue;
				for (int d = 0; d < 4; d++) {
					int ny = i + DIR[d][0];
					int nx = j + DIR[d][1];
					if(!inRange(ny, nx) || map[ny][nx] <= 0) continue;
					map[i][j] += 1;
				}
			}
		}
	}

	private static int solve() {
		int removedTree = 0;

		while (M-- > 0) {
			reduceHerbicide();
			growUp();
			reproduce();
			removedTree += sprayHerbicide();
		}

		return removedTree;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		herbicide = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solve());
	}
}
