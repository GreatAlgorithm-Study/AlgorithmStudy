import java.io.*;
import java.util.*;

public class JY_19237 {
	
	static int N, M, K;
	static int[][] g;
	static int[][] t;
	static int[][][] drr;
	static Shark[] srr;
	static int[][] visited;
	// 상 하 좌 우
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static boolean[] isDied;
	static class Shark {
		int num, x, y, dir;

		public Shark(int num, int x, int y, int dir) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Shark [num=" + num + ", x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}
		
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		g = new int[N][N];			// 냄새카운트
		visited = new int[N][N];	// 상어 경로
		srr = new Shark[M+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
				if(g[i][j] != 0) {
					srr[g[i][j]] = new Shark(g[i][j], i, j, 0);
					visited[i][j] = g[i][j];
					g[i][j] = K;
				}
			}
		}

		// 상어 초기 방향 
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<M+1; i++) {
			srr[i].dir = Integer.parseInt(st.nextToken());
		}
		
		// 상어 우선순위 배열
		drr = new int[M+1][5][5];
		for(int i=1; i<M+1; i++) {
			for(int j=1; j<5; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1; k<5; k++) {
					drr[i][j][k] = Integer.parseInt(st.nextToken());
				}
				
			}
		}
		
		
		isDied = new boolean[M+1];
		int time = 1;
		
		while(time <= 1000) {
			// 1) 상어 이동(동시에!)
			moveShark();

			// 2) 냄새 처리
			spreadSmell();
			
			// 3) 남은 상어 체크
			if(checkShark()) break;
			
			time++;
			
		}
		if(time > 1000) System.out.println(-1);
		else System.out.println(time);

	}
	// -------- print
	public static void printG(int[][] a) {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		System.out.println();
	}
	public static void printD(int[][][] a) {
		for(int i=1; i<a.length; i++) {
			// 상어
			System.out.println("num: "+i);
			for(int j=0; j<a[0].length; j++) {
				System.out.println(Arrays.toString(a[i][j])+" ");				
			}
			System.out.println();
		}
		System.out.println();
	}
	// --------------
	
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static void moveShark() {
		t = new int[N][N];
		
		// 상어 반복
		for(int i=1; i<M+1; i++) {
			if(isDied[i]) continue;
			Shark now = srr[i];

			// 1-1) 4방향 중 냄새 없는 칸 찾기
			int nDir = -1;
			for(int d=1; d<5; d++) {
				int dir = drr[i][now.dir][d];
				int nx = now.x + dx[dir];
				int ny = now.y + dy[dir];
				if(!inRange(nx, ny)) continue;
				if(g[nx][ny] != 0) continue;
				nDir = dir;
				break;
			}
			
			// 1-2) 냄새없는 칸없음 -> 자신의 냄새가 있는 칸으로 결정
			if(nDir == -1) {
				for(int d=1; d<5; d++) {
					int dir = drr[i][now.dir][d];
					int nx = now.x + dx[dir];
					int ny = now.y + dy[dir];
					if(!inRange(nx, ny)) continue;
					if(visited[nx][ny] != now.num) continue;
					nDir = dir;
					break;
				}
			}
			
			// 2) 가장 우선순위 높은 방향으로 상어이동
			now.x += dx[nDir];
			now.y += dy[nDir];
			now.dir = nDir;
			
			// 이동한 곳에 번호가 낮은 다른 상어가 있음 -> 쫒겨남
			if(t[now.x][now.y] != 0) {
				isDied[i] = true;
			}
			else {
				visited[now.x][now.y] = now.num;
				t[now.x][now.y] = K;
			}
			
		}
	}
	public static void spreadSmell() {
		// 원본배열 냄새는 -1
		// 새로운 배열 냄새 반영
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(g[i][j] > 0) {
					g[i][j]--;
				}
				if(t[i][j] > 0) {
					g[i][j] = t[i][j];
				}
			}
		}
	}
	public static boolean checkShark() {
		for(int i=2; i<M+1; i++) {
			if(!isDied[i]) return false;
		}
		return true;
	}

}
