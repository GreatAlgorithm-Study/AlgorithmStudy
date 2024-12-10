import java.io.*;
import java.util.*;

/*
 * 달이 차오른다, 가자.
 */

public class DH_1194 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Node {
		Point p;
		int status, cnt;
		public Node(Point p, int status, int cnt) {
			this.p = p;
			this.status = status;
			this.cnt = cnt;
		}
	}
	static int N, M;
	static char[][] map;
	static boolean[][][] v;
	static Queue<Node> q;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		bfs();
	}
	
	static void bfs() {
		int result = -1;
		
		while(!q.isEmpty()) {
			Node current = q.poll();

			if(map[current.p.r][current.p.c] == '1') {
				result = current.cnt;
				break;
			}

			for(int d = 0; d < 4; d++) {
				int nr = current.p.r + dr[d];
				int nc = current.p.c + dc[d];
				int nstatus = current.status;
				
				if(!check(nr, nc) || v[nstatus][nr][nc] || map[nr][nc] == '#') continue;
				// 다음 지점이 문인 경우: 키가 없다면 continue
				if(isDoor(map[nr][nc]) && ((current.status & (1 << map[nr][nc] - 'A')) == 0)) continue;
				// 다음 지점에 키가 있는 경우: status에 or 연산 해주기
				if(isKey(map[nr][nc])) nstatus |= (1 << map[nr][nc] - 'a');
				
				q.add(new Node(new Point(nr, nc), nstatus, current.cnt + 1));
				v[nstatus][nr][nc] = true;
			}
		}
		
		System.out.println(result);
	}
	
	static boolean isDoor(char c) {
		return c >= 'A' && c <= 'F';
	}
	
	static boolean isKey(char c) {
		return c >= 'a' && c <= 'f';
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	static void printMap() {
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/BOJ1194.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		v = new boolean[1 << 6][N][M];
		
		q = new ArrayDeque<>();
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c);
				if(map[r][c] == '0') {
					q.add(new Node(new Point(r, c), 0, 0));
					v[0][r][c] = true;
				}
			}
		}
	}
}
