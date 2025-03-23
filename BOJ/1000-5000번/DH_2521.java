import java.io.*;
import java.util.*;

/*
 * 거울 설치
 */

public class DH_2521 {
	static final int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node> {
		int e, mirrorCnt;

		public Node(int e, int mirrorCnt) {
			this.e = e;
			this.mirrorCnt = mirrorCnt;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.mirrorCnt, o.mirrorCnt);
		}
	}
	static ArrayList<Integer> door;
	static ArrayList<Integer>[] adj;
	static char[][] map;
	static int N;
	
	public static void main(String[] args) throws Exception {
		initInput();
		setAdj();
		System.out.println(dijkstra());
	}
	
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	static void initInput() throws Exception {

		System.setIn(new FileInputStream("./input/BOJ2521.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		adj = new ArrayList[N * N];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();

		door = new ArrayList<Integer>(); // 문의 위치 저장
		
		for(int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
			
			for(int c = 0; c < N; c++) {
				if(map[r][c] != '!' && map[r][c] != '#') continue;
				
				int pos = r * N + c;
				if(map[r][c] == '#') door.add(pos);
			}
		}
	}
	
	// 인접리스트 만들기
	static void setAdj() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				
				// !이거나 #이면서 직선으로 이동할 때, 다른 !이나 #을 만날 수 있다면 인접리스트 만들어주기
				if(map[r][c] != '!' && map[r][c] != '#') continue;
				
				int pos = r * N + c;
				
				// 4방으로 직선으로 이동하기
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!check(nr, nc) || map[nr][nc] == '*') continue;
					q.add(new int[] {nr, nc, d});
				}
				
				while(!q.isEmpty()) {
					
					int[] poll = q.poll();
					int cr = poll[0], cc = poll[1];
					
					// 이동하다가 !이나 #을 만나면 인접리스트에 정보 저장해주기
					if(map[cr][cc] == '!' || map[cr][cc] == '#') {
						adj[pos].add(cr * N + cc);
					}
					
					int d = poll[2];
					int nr = poll[0] + dr[d];
					int nc = poll[1] + dc[d];
					
					if(!check(nr, nc) || map[nr][nc] == '*') continue;
					
					q.add(new int[] {nr, nc, d});
				}
			}
		}
	}
	
	// 다익스트라 실행
	static int dijkstra() {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		
		boolean[] v = new boolean[N * N];
		
		// 첫 번째 문에서 출발
		int start = door.get(0);
		
		q.add(new Node(start, 0));

		int result = 0;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			if(v[current.e]) continue;
			v[current.e] = true;
			
			// 두 번째 문에 도달했다면 끝내기
			int cr = current.e / N, cc = current.e % N;
			if((cr == door.get(1) / N) && (cc == door.get(1) % N)) {
				result = current.mirrorCnt;
				break;
			}
			
			// 거울의 개수가 작아질 수 있도록 해야 됨
			for(int next: adj[current.e]) {
				
				int installMirrorCnt = current.mirrorCnt;
				
				// 현재 위치의 좌표가 !이라면 거울의 개수를 늘려야 됨
				if(map[cr][cc] == '!') installMirrorCnt += 1;
				
				q.add(new Node(next, installMirrorCnt));
			}
		}
		
		return result;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
