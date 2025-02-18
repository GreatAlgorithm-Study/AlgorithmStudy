import java.util.*;
import java.io.*;

public class JM_고대_문명_유적_탐사 {
	static int K; // 탐사 반복 횟수
	static int M; // 유물 조각의 개수
	static final int N = 5;
	static int[][] map;
	static int[] wall;
	static int wallIdx;
	static final int SIZE = 3;
	static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

    private static int fillWallPiece(int[][] resultMap) {
		int valueCount = 0;
		for(int j = 0; j < N; j++){
			for(int i = N - 1; i >= 0; i--) {
				if(resultMap[i][j] == 0) {
					valueCount++;
					resultMap[i][j] = wall[wallIdx];
					wallIdx = (wallIdx + 1) % M;
				}
			}
		}
		return valueCount;
	}

	private static void remove(Queue<int[]> pieces, int[][] currMap) {
		while(!pieces.isEmpty()) {
			int[] point = pieces.poll();
			currMap[point[0]][point[1]] = 0;
		}
	}

	private static Queue<int[]> bfs(int sy, int sx, int[][] currMap, boolean[][] visited) {
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> piece = new LinkedList<>();

		visited[sy][sx] = true;
		queue.add(new int[] {sy, sx});
		piece.add(new int[] {sy, sx});

		while(!queue.isEmpty()) {
			int y = queue.peek()[0];
			int x = queue.peek()[1];
			queue.poll();

			for(int i = 0; i < 4; i++) {
				int ny = y + DIR[i][0];
				int nx = x + DIR[i][1];
				if(!inRange(ny, nx)) continue;
				if(visited[ny][nx] || currMap[y][x] != currMap[ny][nx]) continue;

				visited[ny][nx] = true;
				queue.add(new int[] {ny, nx});
				piece.add(new int[] {ny, nx});
			}
		}
		return piece;
	}

	private static int calcValue(int[][] currMap) {
		boolean[][] visited = new boolean[N][N];
		int count = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(visited[y][x]) continue;

				Queue<int[]> pieces = bfs(y, x, currMap, visited);
				if(pieces.size() >= SIZE) {
					count += pieces.size();
					remove(pieces, currMap);
				}
			}
		}
		return count;
	}

    private static int[][] rotate(int y, int x, int r) {
		int[][] rotated = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(r == 1) rotated[j][SIZE - i - 1] = map[y + i][x + j]; // 90도
				else if(r == 2) rotated[SIZE - i - 1][SIZE - j - 1] = map[y + i][x + j]; // 180도
				else rotated[SIZE - j - 1][i] = map[y + i][x + j]; // 270도
			}
		}

		int[][] result = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(y <= i && i <= y + 2 && x <= j && j <= x + 2) {
					result[i][j] = rotated[i - y][j - x];
				}
				else result[i][j] = map[i][j];
			}
		}
		return result;
	}

	private static int[][] process() {
		int maxValue = 0;
		int[][] maxMap = new int[N][N];
		maxMap[0][0] = -1;

		for(int r = 1; r <= 3; r++) {
			for(int j = 0; j < SIZE; j++) {
			for(int i = 0; i < SIZE; i++) {
					int[][] currMap = rotate(i, j, r);
					int value = calcValue(currMap);
					if(maxValue < value) {
						maxValue = value;
						maxMap = currMap;
					}
				}
			}
		}
		return maxMap;
	}
    

	private static String solve() {
		StringBuilder sb = new StringBuilder();
		while(K-- > 0) {
			int[][] resultMap = process(); // 1. 탐사 진행
			if(resultMap[0][0] == -1) break; // 유물 획득 실패

			int value = fillWallPiece(resultMap); // 2. 유물 획득

			while(calcValue(resultMap) != 0) {	// 3. 연쇄 획득
				value += fillWallPiece(resultMap);
			}

			map = resultMap;
			sb.append(value).append(" ");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		wall = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			wall[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());
	}
}
