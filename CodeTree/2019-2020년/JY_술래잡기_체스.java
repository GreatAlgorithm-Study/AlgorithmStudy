package day1202;

import java.io.*;
import java.util.*;

public class JY_술래잡기_체스 {

	static final int N = 4;
	// 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static boolean[] isDied;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] g = new int[N][N];
		int[][] prr = new int[N*N+1][3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				g[i][j] = n;
				prr[n][0] = d;
				prr[n][1] = i;
				prr[n][2] = j;
			}
		}

		isDied = new boolean[N*N+1];
		ans = 0;
		
		// 0) 초기 도둑 잡기
		int sx = 0;
		int sy = 0;
		int pNum = g[sx][sy];
		int sDir = prr[pNum][0];
		g[sx][sy] = 0;
		isDied[pNum] = true;
		
		play(sx, sy, sDir, pNum, g, prr);
		
		
		System.out.println(ans);
		
	}
	public static void print(int[][] a) {
		for(int i=0; i<a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static void swap(int x, int y, int nx, int ny, int[][] t, int[][] trr) {
		int n = t[x][y];
		int oNum = t[nx][ny];
		t[nx][ny] = t[x][y];
		trr[oNum][1] = x;
		trr[oNum][2] = y;
		
		t[x][y] = oNum;
		trr[n][1] = nx;
		trr[n][2] = ny;
	}
	public static void movePlayer(int sx, int sy, int[][] t, int[][] trr) {
		for(int i=1; i<N*N+1; i++) {
			if(isDied[i]) continue;
			
			int[] now = trr[i];
			int dir = now[0];
			for(int c=0; c<8; c++) {
				int nx = now[1] + dx[dir];
				int ny = now[2] + dy[dir];
				
				// 격자 벗어남 or 술래위치
				if(!inRange(nx, ny) || (nx == sx && ny == sy)) {
					dir = (dir + 1) % 8;
					continue;
				}
				
				// 다른 도둑 칸 or 빈칸
				swap(now[1], now[2], nx, ny, t, trr);
				trr[i][0] = dir;		// 방향 전환
				break;
			}
		}
	}
	public static void play(int sx, int sy, int sDir, int total, int[][] g, int[][] prr) {
		// 원본배열 복사 copy g & prr
		// 매개변수로 받은 배열은 주소값이 전달되므로 함수 내에서 변경됨 (배열은 참조 타입)
		int[][] t = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				t[i][j] = g[i][j];
			}
		}
		int[][] trr = new int[N*N+1][3];
		for(int i=1; i<N*N+1; i++) {
			for(int j=0; j<3; j++) {
				trr[i][j] = prr[i][j];
			}
		}
		
		// 1) 도둑 움직이기
		movePlayer(sx, sy, t, trr);

		// 2) 술래 움직이기
		boolean isCon = false;
		for(int c=1; c<N; c++) {
			int nx = sx + c*dx[sDir];
			int ny = sy + c*dy[sDir];
			
			if(!inRange(nx, ny)) break;
			if(t[nx][ny] == 0) continue;
			
			// 움직일 수 있음
			isCon = true;
			int pNum = t[nx][ny];
			isDied[pNum] = true;
			t[nx][ny] = 0;
			play(nx, ny, trr[pNum][0], total+pNum, t, trr);
			t[nx][ny] = pNum;
			isDied[pNum] = false;
		}
		
		// 술래가 더이상 움직일 곳이 없음
		if(!isCon) {
			ans = Math.max(ans, total);
			return;
		}
		
	}

}
