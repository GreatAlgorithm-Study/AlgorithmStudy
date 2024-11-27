import java.io.*;
import java.util.*;

public class DH_생명과학부_랩_인턴 {
	static class Node {
		int r, c, s, d, b;
		boolean isDie;

		public Node(int r, int c, int s, int d, int b) {
			this.r = r;
			this.c = c;
			this.s = s; // 속력
			this.d = d; // 이동 방향
			this.b = b; // 곰팡이의 크기
		}
		
		public void update(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int[] dr = {0, -1, 1, 0}, dc = {-1, 0, 0, 1}; // 좌, 상, 하, 우
	static int[][] map; // 곰팡이의 idx 저장
	static int N, M, K, totalCnt;
	static ArrayList<Node> list;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		for(int c = 0; c < map[0].length; c++) { // 1. 첫 번쨰 열부터 탐색
			step1(c); // 곰팡이 채취
			step2(); // 곰팡이 이동
		}
		
		System.out.println(totalCnt);
	}
	
	static void printMap() {
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
		
		System.out.println();
	}
	
	static void step2() {
		map = new int[N][M];

		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).isDie) continue;
			
			int r = list.get(i).r, c = list.get(i).c;
			int s = list.get(i).s, d = list.get(i).d;
						
			int mod = 0;
			if(d == 1 || d == 2) mod = N * 2 - 2; // 상, 하로 움직이는 경우 행의 크기 * 2 - 2
			else mod = M * 2 - 2;
			s %= mod;
			
			for(int dis = 0; dis < s; dis++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(!check(nr, nc)) {
					dis -= 1;
					d = changeDir(d);
					continue;
				}
				
				r = nr;
				c = nc;
			}
			 
			list.get(i).update(r, c, d);

			if(map[r][c] == 0) {
				map[r][c] = i + 1;
			} else if(map[r][c] < i + 1) {
				int nextIdx = map[r][c];
				if(list.get(nextIdx - 1).b < list.get(i).b) {
					list.get(nextIdx - 1).isDie = true;
					map[r][c] = i + 1;
				} else {
					list.get(i).isDie = true;
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	static int changeDir(int d) {
		if(d == 0) return 3;
		if(d == 3) return 0;
		if(d == 1) return 2;
		return 1;
	}
	// 곰팡이 채취
	static void step1(int c) {
		for(int r = 0; r < map.length; r++) {
			if(map[r][c] == 0) continue;
			int idx = map[r][c] - 1;
			totalCnt += list.get(idx).b;
			list.get(idx).isDie = true;
			map[r][c] = 0;
			return;
		}
	}
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/생명과학부 랩 인턴.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 격자의 크기
		K = Integer.parseInt(st.nextToken()); // 곰팡이의 개수
		
		map = new int[N][M];
		list = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) % 4;
			int b = Integer.parseInt(st.nextToken());
			
			list.add(new Node(r, c, s, d, b));
			map[r][c] = i + 1; // 곰팡이의 idx에 + 1을 해주고 map에 넣어줌
		}
	}
}
