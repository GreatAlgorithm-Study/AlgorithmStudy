import java.io.*;
import java.util.*;
public class JY_2개의_사탕 {
	
	static int N, M;
	static char[][] g;
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Candy {
		int x, y;

		public Candy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Candy [x=" + x + ", y=" + y + "]";
		}
		
	}
	static int ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new char[N][M];
		Candy red = new Candy(-1, -1);
		Candy blue = new Candy(-1, -1);
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				g[i][j] = line.charAt(j);
				if(g[i][j] == 'R') {
					red.x = i;
					red.y = j;
					g[i][j] = '.';
				} else if(g[i][j] == 'B') {
					blue.x = i;
					blue.y = j;
					g[i][j] = '.';
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		exit(red, blue, 0);
		
		if(ans > 10) System.out.println(-1);
		else System.out.println(ans);
		
		
	}
	public static void printG() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static void exit(Candy r, Candy b, int cnt) {		
		// 파란 캔디가 먼저 도착점에 도달
		if(g[b.x][b.y] == 'O') return;
		// 빨간 캔디가 도착점에 도달
		if(g[r.x][r.y] == 'O') {
			ans = Math.min(ans, cnt);
			return;
		}
		if(cnt > 10) return;
		// 이미 구해진 값보다 많다면 중단
		if(cnt > ans) return;
		
		for(int i=0; i<4; i++) {
			Candy nr = move(r, i);
			Candy nb = move(b, i);
			
			// 둘 위치가 같음 -> 위치 재조정
			if(nr.x == nb.x && nr.y == nb.y) {	
				// 같은 위치가 도착점이면 X
				if(g[nr.x][nr.y] == 'O') continue;
				// 상
				if(i == 0) {
					if(r.x < b.x) {
						nb.x += 1;
					} else {
						nr.x += 1;
					}
				}
				// 하
				else if(i == 1) {
					if(r.x > b.x) {
						nb.x -= 1;
					} else {
						nr.x -= 1;
					}
				}
				// 좌
				else if(i == 2) {
					if(r.y < b.y) {
						nb.y += 1;
					} else {
						nr.y += 1;
					}
				}
				// 우
				else {
					if(r.y > b.y) {
						nb.y -= 1;
					} else {
						nr.y -= 1;
					}
				}
			}
			if(r.x == nr.x && r.y == nr.y && b.x == nb.x && b.y == nb.y) continue; 
			exit(nr, nb, cnt+1);

			
		}
	}
	public static Candy move(Candy c, int dir) {
		Candy nc = new Candy(c.x, c.y);
		while(true) {
			// 현제 위치가 도착점이면 중단
			if(g[nc.x][nc.y] == 'O') break;
			int nx = nc.x + dx[dir];
			int ny = nc.y + dy[dir];
			
			// 다음이 벽이면 중단
			if(g[nx][ny] == '#') break;
			nc.x = nx;
			nc.y = ny;
		}
		return nc;
	}

}
