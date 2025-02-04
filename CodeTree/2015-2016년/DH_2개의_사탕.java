import java.io.*;
import java.util.*;

/*
 * 2개의 사탕
 */

public class DH_2개의_사탕 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void update(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, result = Integer.MAX_VALUE;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static Point RED, BLUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/2개의사탕.txt"));
		
		initInput();
		
		dfs(RED, BLUE, 0, -1);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	static void dfs(Point red, Point blue, int depth, int prevDir) {
		
		// 기울이는 횟수가 10번이 초과하는 경우 그만!!
		if(depth == 10) return;
		
		// 네가지 방향으로 기울여보기
		for(int d = 0; d < 4; d++) {
			// 이전에 있던 위치를 향해서 못가게 하기
			if(prevDir == (d + 2) % 4) continue;
			
			// 빨간색, 파란색 사탕 각각 기울였을 때, 도달하는 위치 구하기
			Point nextRed = getNextPoint(red, d);
			Point nextBlue = getNextPoint(blue, d);

			// 파란색 사탕이 빠져나가면 안됨
			if(map[nextBlue.r][nextBlue.c] == 'O') continue;

			// 기울였을 때, 파란색 사탕과 빨간색 사탕이 도달하는 위치가 같으면
			// 이전 위치를 비교해서 각 사탕이 있을 수 있는 위치 정해주기
			if(nextRed.r == nextBlue.r && nextRed.c == nextBlue.c) {
				// 상
				if(d == 0) {
					if(red.r > blue.r) nextRed.r += 1;
					else nextBlue.r += 1;
				}
				
				// 좌
				if(d == 1) {
					if(red.c > blue.c) nextRed.c += 1;
					else nextBlue.c += 1;
				}
				
				// 하
				if(d == 2) {
					if(red.r < blue.r) nextRed.r -= 1;
					else nextBlue.r -= 1;
				}
				
				// 우
				if(d == 3) {
					if(red.c < blue.c) nextRed.c -= 1;
					else nextBlue.c -= 1;
				}
			}
			
			// 두 사탕 모두 움직이지 못하는 경우 그만
			if(red.r == nextRed.r && red.c == nextRed.c && blue.r == nextBlue.r && blue.c == nextBlue.c) continue;
			if(map[nextRed.r][nextRed.c] == 'O' && map[nextBlue.r][nextBlue.c] == 'O') return;
			
			// 빨간색 사탕을 움직일 수 있는 경우
			if(map[nextRed.r][nextRed.c] == 'O') {
				result = Math.min(result, depth + 1);
				return;
			}

			dfs(nextRed, nextBlue, depth + 1, d);
		}
		
	}
	
	// 기울였을 때, 사탕이 도달하는 위치 구하기
	static Point getNextPoint(Point p, int dir) {
		Point next = new Point(p.r, p.c);
		
		while(true) {
			int nr = next.r + dr[dir];
			int nc = next.c + dc[dir];
			
			if(!isValid(nr, nc) || map[nr][nc] == '#') break;
			next.update(nr, nc);
			
			if(map[next.r][next.c] == 'O') break;
		}
		
		return next;
	}
	
	static boolean isValid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int r = 0; r < map.length; r++) {
			map[r] = br.readLine().toCharArray();
			
			for(int c = 0; c < map[r].length; c++) {
				if(map[r][c] != 'R' && map[r][c] != 'B') continue;
				if(map[r][c] == 'R') RED = new Point(r, c);
				if(map[r][c] == 'B') BLUE = new Point(r, c);
				map[r][c] = '.';
			}
		}
	}
}
