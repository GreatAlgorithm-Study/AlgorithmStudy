import java.io.*;
import java.util.*;

public class JY_회전하는_빙하 {
	
	static int n, N, Q;
	static int[][] g, t;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean[][] visited;
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		N = (int)Math.pow(2, n);
		
		g = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 회전 레벨 배열
		int[] arr = new int[Q];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// Q번 회전
		for(int q=0; q<Q; q++) {
			int r = arr[q];
			
			// 레벨 r 회전하기
			if(r > 0) {				
				int S = (int)Math.pow(2, r);
				t = new int[N][N];
				
				for(int i=0; i<N; i+=S) {
					for(int j=0; j<N; j+=S) {
						rotate(S, i, j);
					}
				}
				g = t;
			}
			
			// 빙하 감소시키기(회전 마다)
			decrease();
		}
		
		
		// 총 빙하의 양 구하기
		int amount = calAmount();
		System.out.println(amount);

		// 최대 빙하 군집 구하기
		int size= 0;
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && g[i][j] != 0) {
					int s = bfs(i, j);
					size = Math.max(size, s);
				}
			}
		}
		System.out.println(size);
		
		
		
	}
	public static void print() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static void rotate(int S, int sx, int sy) {
		int ts = S / 2;
		for(int x=sx; x<sx+S; x+=ts) {
			for(int y=sy; y<sy+S; y+=ts) {
				int ox = x - sx;
				int oy = y - sy;
				int nx = oy;
				int ny = S - ts - ox;
				t[nx+sx][ny+sy] = g[x][y];
				int tx = (nx+sx)-x;
				int ty = (ny+sy)-y;
				for(int i=x; i<x+ts; i++) {
					for(int j=y; j<y+ts; j++) {
						t[i+tx][j+ty] = g[i][j];
					}
				}
			}
		}
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static void decrease() {
		int[][] t = new int[N][N];
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				if(g[x][y] == 0) continue;
				t[x][y] = g[x][y];
				int cnt = 0;
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!inRange(nx, ny)) continue;
					if(g[nx][ny] == 0) continue;
					cnt++;
				}
				
				if(cnt < 3) {
					t[x][y]--;
				} 
			}
		}
		
		g = t;
	}
	public static int calAmount() {
		int total = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				total += g[i][j];
			}
		}
		
		return total;
	}
	public static int bfs(int x, int y) {
		int size = 1;
		visited[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(g[nx][ny] == 0) continue;
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
				size++;
			}
		}
		
		return size;
	}

}
