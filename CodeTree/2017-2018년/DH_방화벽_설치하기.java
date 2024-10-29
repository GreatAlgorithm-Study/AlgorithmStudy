import java.io.*;
import java.util.*;

public class DH_방화벽_설치하기 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, emptyArea, result;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	static void solution() {
		// 방화벽 3개 추가 설치 (조합)
		func(0, 0, new boolean[N][M]);
		System.out.println(result);
	}
	
	static int bfs(boolean[][] wall) {
		Deque<Point> q = new ArrayDeque<Point>();
		
		int cnt = 0; // 불에 타는 영역 수
		boolean[][] v = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 이미 방문한 곳이거나, 불이 아니거나, 벽일 때
				if(v[r][c] || map[r][c] != 2 || wall[r][c]) continue;
				q.add(new Point(r, c));
				v[r][c] = true;
			}
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				// 범위에 벗어나거나, 이미 간 곳이거나, 벽이면 못감
				if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == 1 || wall[nr][nc]) continue;
				q.add(new Point(nr, nc));
				v[nr][nc]= true;
				cnt++;
			}
		}
		
		return cnt;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	static void func(int idx, int depth, boolean[][] wall) {
		if(depth == 3) {
			
			int fireArea = bfs(wall);
			result = Math.max(result, emptyArea - 3 - fireArea);
			
			return;
		}
	
		for(int i = idx; i < N * M; i++) {
			int r = i / M;
			int c = i % M;
			
			// 이미 방화벽이 있거나, 불이 있는 곳에는 방화벽을 세울 수 없음
			if(map[r][c] != 0) continue;
			
			wall[r][c] = true;
			func(i + 1, depth + 1, wall);
			wall[r][c] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/방화벽설치하기.txt"));
		initInput();
		solution();
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		result = Integer.MIN_VALUE;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 0) emptyArea++;
			}
		}
	}
}
