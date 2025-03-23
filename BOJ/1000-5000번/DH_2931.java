import java.io.*;
import java.util.*;

/*
 * 가스관
 */

public class DH_2931 {
	static final int U = 0, L = 1, D = 2, R = 3;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1}; 
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Point start, end;
	static char[][] map;
	static Point point;
	static char[] blocks = {'|', '-', '+', '1', '2', '3', '4'};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		
		// setPoint[0][1]: 블록 설치해야 되는 위치 (r, c)
		// setPoint[2]: M에서 출발할 때 어느 방향으로 가야 되는지
		int[] setPoint = getPoint();
		
		char type = 0; // 무슨 타입 블록인지
		int d = setPoint[2]; // M이 처음 시작하는 방향
		
		for(int k = 0; k < 7; k++) { // 블록을 설치해야 되는 위치에 모든 타입의 블록을 놓아봄
			
			char block = blocks[k];
			
			map[setPoint[0]][setPoint[1]] = block;
			
			int nr = start.r + dr[d]; // M 직후 지점
			int nc = start.c + dc[d]; // M 직후 지점
			
			// 현재 방향이 d이고, 다음 블록이 map[nr][nc]일 때
			// map[nr][nc]에서 나아가는 방향 정보
			int dir = getDir(nr, nc, d); 
			dfs(nr, nc, dir);
			
			// dfs를 수행하며 블록을 따라 갔을 때, 끝점에 도달한다면 block타입 블록을 설치해야 됨
			if(point.r == end.r && point.c == end.c) {
				type = block;
				break;
			}
		}
		sb.append(setPoint[0] + 1).append(" " ).append(setPoint[1] + 1).append(" ").append(type);
		System.out.println(sb);
	}
	
	static void dfs(int r, int c, int cd) {
		
		// 없어진 블록이거나, 끝 지점이라면 dfs 끝내기
		if(map[r][c] == '.' || map[r][c] == 'Z') {
			point = new Point(r, c);
			return;
		}
		
		if(cd == -1) return; // 유효하지 않은 방향정보라면 끝내기
		
		int nr = r + dr[cd];
		int nc = c + dc[cd];
		
		if(!check(nr, nc)) return; // 범위를 벗어난다면 끝내기
		
		int nd = getDir(nr, nc, cd);
		dfs(nr, nc, nd);
	}
	
	// M에서 출발할 때, 어떤 방향을 바라보며 이동하는지와 없어진 블럭의 정보 찾기
	static int[] getPoint() {
		int dir = 0;
		
		for(int d = 0; d < 4; d++) {
			int nr = start.r + dr[d];
			int nc = start.c + dc[d];
			
			// 이 때, M 옆에 바로 Z가 있을 수도 있으니 map[nr][nc]가 Z인 경우도 제외해줘야 됨!!!
			if(!check(nr, nc) || map[nr][nc] == '.' || map[nr][nc] == 'Z') continue;
			
			dir = d;
			dfs(nr, nc, getDir(nr, nc, d));
		}
		
		return new int[] {point.r, point.c, dir};
	}
	
	// 현재 지점에서 나아가는 방향이 d이고, 다음 좌표가 (r, c)일 때
	// (r, c)에서의 방향
	static int getDir(int r, int c, int d) {
		char ch = map[r][c];
		
		if(ch =='.') return -1;
		int cd = -1;
		
		if(d == U) {
			if(ch == '|') cd = U;
			else if(ch == '+') cd = U;
			else if(ch == '1') cd = R;
			else if(ch == '4') cd = L;
		}
		
		if(d == L) {
			if(ch == '-') cd = L;
			else if(ch == '+') cd = L;
			else if(ch == '1') cd = D;
			else if(ch == '2') cd = U;
		}
		
		if(d == D) {
			if(ch == '|') cd = D;
			else if(ch == '+') cd = D;
			else if(ch == '2') cd = R;
			else if(ch == '3') cd = L;
		}
		
		if(d == R) {
			if(ch == '-') cd = R;
			else if(ch == '+') cd = R;
			else if(ch == '3') cd = U;
			else if(ch == '4') cd = D;
		}
		
		return cd;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
	static void initInput() throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				
				if(map[r][c] == 'M') start = new Point(r, c);
				if(map[r][c] == 'Z') end = new Point(r, c);
				
				if(map[r][c] == 'M' || map[r][c] == 'Z' || map[r][c] == '.') continue;
			}
		}
		
	}
}