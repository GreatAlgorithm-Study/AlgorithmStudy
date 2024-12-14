import java.io.*;
import java.util.*;

/*
 * 죽음의 비
 */

public class DH_22944 {
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static class Point {
		int k, r, c, d, p, u;
		public Point(int k, int r, int c, int d, int p, int u) {
			this.k = k;
			this.r = r;
			this.c = c;
			this.d = d;
			this.p = p;
			this.u = u;
		}
	}

	static int N, H, D;
	static char[][] map;
	static boolean[][][] v;
	static Deque<Point> q;
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution());
	}
	
	static int solution() {
		int result = -1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(map[current.r][current.c] == 'E') {
				result = current.d;
				break;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				int nk = current.k;
				int np = current.p;
				int nu = current.u;
				
				if(!check(nr, nc) || v[current.k][nr][nc]) continue;
				
				if(map[nr][nc] == '.') {
					if(nu > 0) nu -= 1;
					else np -= 1;
				}
				
				// 우산이라면
				else if(map[nr][nc] == 'U') {
					map[nr][nc] = '.';
					nu = D - 1;
					nk = current.k + 1;
				}

				if(np == 0) continue;

				v[current.k][nr][nc] = true;
				q.add(new Point(nk, nr, nc, current.d + 1, np, nu));
			}
		}
		
		return result;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/BOJ22944.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 한 변의 길이
		H = Integer.parseInt(st.nextToken()); // 현재 체력
		D = Integer.parseInt(st.nextToken()); // 우산의 내구도
		
		q = new ArrayDeque<Point>();
		int uCnt = 0;
		
		int sr = 0, sc =0;
		
		map = new char[N][N];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			map[r] = s.toCharArray();
			
			for(int c = 0; c < N; c++) {
				if(map[r][c] == 'S') {
					sr = r; sc = c;
					map[r][c] = '.';
				}
				if(map[r][c] == 'U') uCnt += 1;
			}
		}

		v = new boolean[uCnt + 1][N][N];
		q.add(new Point(0, sr, sc, 0, H, 0));
		v[0][sr][sc] = true;
	}
}
