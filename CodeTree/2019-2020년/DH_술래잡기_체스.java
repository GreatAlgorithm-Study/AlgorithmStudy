import java.io.*;
import java.util.*;

public class DH_술래잡기_체스 {
	static class Node {
		int r, c, dir;
		boolean isDie;

		public Node(int r, int c, int dir, boolean isDie) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.isDie = isDie;
		}

		public Node(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		public Node copy() {
			return new Node(this.r, this.c, this.dir, this.isDie);
		}
	}

	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 }, dc = { 1, 0, -1, -1, -1, 0, 1, 1 }; // '우상' 시작 반시계
	static Node[] nodes;
	static int map[][];
	static int answer;

	// r, c: 술래말의 위치
	// d: 술래말이 향하는 방향
	// sum: 점수 합계
	static void dfs(int r, int c, int d, int sum) {
		answer = Math.max(answer, sum);

		// 잡힌 도둑말
		int target = map[r][c];
		nodes[target].isDie = true;

		// 도둑말 움직이기
		// nodes와 map에서 정보 update 해줘야 됨
		for (int i = 1; i < nodes.length; i++) {
			if (nodes[i].isDie)
				continue;
			move(r, c, i, nodes, map);
		}

		// 술래말 움직이기
		// 격자 크기: 4 × 4이므로 술래말이 움직일 수 있는 최대 거리: 4×
		for (int i = 1; i < 4; i++) {
			int nr = r + dr[d] * i;
			int nc = c + dc[d] * i;

			// 범위를 벗어나거나 해당 말이 이미 죽어 있는 경우
			if (!check(nr, nc) || nodes[map[nr][nc]].isDie)
				continue;

			// 원상복구를 위해 nodes와 map의 복사본 만듦
			Node[] copyNodes = copyNodes();
			int[][] copyMap = copyMap();

			dfs(nr, nc, nodes[map[nr][nc]].dir, sum + map[nr][nc]);

			// nodes랑 map 원래 값으로 돌려놓기
			nodes = copyNodes;
			map = copyMap;
		}

		// 말 죽었다고 표시했던거 돌려놓기
		nodes[map[r][c]].isDie = false;
	}

	// 도둑 말 이동 시키기
	// r, c: 술래말의 위치
	// i: 도둑말의 인덱스
	static void move(int r, int c, int i, Node[] nodes, int map[][]) {
		Node current = nodes[i];

		for (int k = 0; k < 8; k++) {
			int nr = current.r + dr[(current.dir + k) % 8];
			int nc = current.c + dc[(current.dir + k) % 8];

			if (!check(nr, nc) || (nr == r && nc == c))
				continue;

			current.dir = (current.dir + k) % 8;
			int targetIdx = map[nr][nc];
			Node target = nodes[map[nr][nc]];

			map[current.r][current.c] = targetIdx;
			map[nr][nc] = i;

			target.r = current.r;
			target.c = current.c;
			current.r = nr;
			current.c = nc;
			break;
		}
	}

	static int[][] copyMap() {
		int[][] copyMap = new int[map.length][map[0].length];
		for (int r = 0; r < copyMap.length; r++)
			copyMap[r] = map[r].clone();
		return copyMap;
	}

	static Node[] copyNodes() {
		Node[] copyNodes = new Node[nodes.length];

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == null)
				continue;
			copyNodes[i] = nodes[i].copy();
		}
		return copyNodes;
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/술래잡기체스.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		nodes = new Node[17];
		map = new int[4][4];

		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				int idx = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				nodes[idx] = new Node(r, c, dir);
				map[r][c] = idx;
			}
		}

		dfs(0, 0, nodes[map[0][0]].dir, map[0][0]);
		System.out.println(answer);
	}
}