import java.io.*;
import java.util.*;

public class DH_색깔폭탄 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, score;
	static int[][] map; 
	static final int INF = Integer.MAX_VALUE;
	static final int RED = -2, BLACK = -1, EMPTY = 0;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
		System.out.println(score);
	}

	static void solution() {
		Deque<Point> bomb;
		while((bomb = step1()).size() >= 2) { // 폭탄 묶음 찾기
			step2(bomb); // 폭탄 터지기
			step3(); // 중력 작용하기
			step4(); // 반시계 방향으로 90도 회전하기
			step3(); // 중력 작용하기
		}
	}
	
	// 폭탄 묶음 찾기
	static Deque<Point> step1() {
		Deque<Point> result = new ArrayDeque<Point>(); // 우선순위에 따라 폭탄이 터질 위치를 저장하는 큐
		Deque<Point> tmp = new ArrayDeque<Point>(); // bfs탐색했던 위치들을 모두 저장하는 큐
		Deque<Point> q = new ArrayDeque<Point>(); // bfs 탐색하는 큐
		
		// 폭탄묶음의 크기, 빨간색 폭탄의 개수, 
		int groupSize = -INF, redCnt = INF;
		
		// 빨간색 폭탄은 bfs를 할 때마다 또 다시 사용할 수 있으므로
		// 따로 빨간색을 제외한 색깔 폭탄을 확인했는지 구분해주기 위해 bfs에서 사용하는 방문 배열 외에 또 다른 2차원 boolean 배열을 만들어줌
		boolean[][] checkColor = new boolean[N][N];
		
		// bfs 탐색을 하는데, 우선순위에 따른 폭탄 묶음을 구하기 위해 
		// r은 N - 1부터 시작하고, c는 0부터 시작함
		// 그리고 빨간 색이 아닌 다른 색깔 폭탄일 때 bfs 탐색 시작
		for(int r = N - 1; r >= 0; r--) {
			for(int c = 0; c < N; c++) {
				if(!isColor(map[r][c]) || checkColor[r][c]) continue;
				
				boolean[][] v = new boolean[N][N];
				int currentRedCnt = 0; // 현재 위치에서 폭탄 묶음을 구성할 때, 빨간 폭탄의 개수
				int currentSize = 1; // 현재 위치에서 폭탄 묶음을 구성할 때, 폭탄 묶음의 크기
				
				tmp.clear();
				
				q.add(new Point(r, c));
				tmp.add(new Point(r, c));
				
				v[r][c] = true;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(int d = 0; d < 4; d++) {
						int nr = current.r + dr[d];
						int nc = current.c + dc[d];
						
						// 범위에 벗어났거나, 확인을 한 곳이거나, 돌이 있거나, 빈 공간이라면 continue
						if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == BLACK || map[nr][nc] == EMPTY) continue;
						
						// 빨간 폭탄도 아니고, 검정폭탄도 아닌데, 현재 자신의 색깔과 다른 경우
						if((map[nr][nc] > 0 && map[nr][nc] != map[r][c])) continue;
						
						// 빨간 폭탄일 경우
						if(map[nr][nc] == RED) currentRedCnt += 1;
						
						q.add(new Point(nr, nc));
						tmp.add(new Point(nr, nc));
						
						v[nr][nc] = true;
						currentSize += 1;
					}
				}
				
				// 폭탄 묶음 크기가 큰 폭탄부터
				if(groupSize < currentSize) {
					result.clear();
					result.addAll(tmp);
					
					groupSize = currentSize;
					redCnt = currentRedCnt;
				} 
				// 폭탄 묶음 크기가 같다면, 빨간 폭탄의 개수 확인하기
				else if(groupSize == currentSize) {
					if(currentRedCnt < redCnt) {
						result.clear();
						result.addAll(tmp);
						
						groupSize = currentSize;
						redCnt = currentRedCnt;
					} 
				}
			}
		}
		
		return result;
	}
	
	// 폭탄 터지기
	static void step2(Deque<Point> bomb) {
		score += bomb.size() * bomb.size();
		
		while(!bomb.isEmpty()) {
			Point current = bomb.poll();
			map[current.r][current.c] = EMPTY; // 빈 공간으로 바꿔주기
		}
	}
	
	// 중력 작용
	// 폭탄들이 있으면 큐에 넣어주고, 돌이 나온다면 gravity(기준선)을 기준으로 쌓아주기
	static void step3() {
		int[][] tmp = new int[N][N];
		Deque<Integer> q = new ArrayDeque<Integer>();
		
		for(int c = 0; c < N; c++) {
			int gravity = N;
			
			for(int r = N - 1; r >= 0; r--) {
				if(isBomb(map[r][c])) q.add(map[r][c]);
				if(map[r][c] == BLACK) {
					tmp[r][c] = BLACK;
					while(!q.isEmpty()) tmp[--gravity][c] = q.poll();
					gravity = r;
				}
			}
			
			while(!q.isEmpty()) tmp[--gravity][c] = q.poll();
		}
		
		map = tmp;
	}
	
	// 반시계 방향으로 회전
	// (r, c) -> (N - 1 - c, r) 로 바뀜
	static void step4() {
		int[][] tmp = new int[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				tmp[N - 1 - c][r] = map[r][c];
			}
		}
		
		map = tmp;
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	// 빨간색을 제외한 모든 색깔 폭탄
	static boolean isColor(int n) {
		return n > 0 && n < M + 1;
	}

	static boolean isBomb(int n) {
		return isColor(n) || n == RED;
	}
	
	static void printMapInfo() {
		System.out.println("printMapInfo ---------------------");
		for(int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/색깔폭탄.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 0) map[r][c] = RED;
			}
		}
	}
}
