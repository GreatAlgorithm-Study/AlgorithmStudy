package day1112;

import java.io.*;
import java.util.*;

public class JY_2955 {
	
	static final int N = 9;
	static int[][] g;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		g = new int[N][N];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				if(s.charAt(j) == '.') {
					g[i][j] = 0;
				} else {
					g[i][j] = s.charAt(j) - '0';
				}
			}
		}
		
		
		
		// cross hatching
		while(true) {
			boolean isDone = true;
			
			for(int n=1; n<=9; n++) {
				// 유효성 체크
				if(!isValid(n)) {
					System.out.println("ERROR");
					return;
				} 
				int cnt = crossHatching(n);
				if(cnt > 0) isDone = false;
			}
			
			if(isDone) break;
			
		}

		printS();

	}
	public static void printG() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static void printV() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println();
	}
	public static void printS() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(g[i][j] == 0) System.out.print(".");
				else System.out.print(g[i][j]);
			}
			System.out.println();
		}
	}
	public static void fillLine(int x, int y) {
		// 세로줄 
		for(int i=0; i<N; i++) {
			visited[i][y] = true;
		}
		
		// 가로줄
		for(int j=0; j<N; j++) {
			visited[x][j] = true;
		}
		
	}
	public static void fillSquare(int sx, int sy) {
		// 3*3 
		for(int i=sx; i<sx+3; i++) {
			for(int j=sy; j<sy+3; j++) {
				visited[i][j] = true;
			}
		}
	}
	public static void fillArea(int n) {
		for(int i=0; i<N; i += 3) {
			for(int j=0; j<N; j += 3) {
				for(int x=i; x<i+3; x++) {
					for(int y=j; y<j+3; y++) {
						if(g[x][y] == n) {
							fillLine(x, y);
							fillSquare(i, j);
						} 
						if(g[x][y] != 0) {
							visited[x][y] = true;
						}
					}
				}
			}
		}
	}
	public static boolean checkLine(int n) {
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				if(g[x][y] != n) continue;
				
				// 세로줄 체크
				int cnt = 0;
				for(int i=0; i<N; i++) {
					if(g[i][y] == n) cnt++;
				}
				if(cnt > 1) return false;
				
				// 기로즐 체크
				cnt = 0;
				for(int j=0; j<N; j++) {
					if(g[x][j] == n) cnt++;
				}
				if(cnt > 1) return false;
				
			}
		}
		return true;
	}
	public static boolean checkBox(int n) {
		// 유효한 3 * 3 찾기
		for(int i=0; i<N; i+=3) {
			for(int j=0; j<N; j += 3) {
				int cnt = 0;
				int empty = 0;
				for(int x=i; x<i+3; x++) {
					for(int y=j; y<j+3; y++) {
						if(g[x][y] == n) cnt++;
						if(!visited[x][y]) empty++;
					}
				}
				
				if(cnt > 1) return false;
				if(cnt == 0 && empty == 0) return false;
			}
		}
		return true;
	}
	public static boolean isValid(int n) {
		visited = new boolean[N][N];
		
		fillArea(n);
		
		return checkBox(n) && checkLine(n);
	}
	public static int findSpot(int n) {
		// 유효한 3 * 3 찾기
		int fCnt = 0;
		for(int i=0; i<N; i+=3) {
			for(int j=0; j<N; j += 3) {
				int cnt = 0;
				int empty = 0;
				int px = -1, py = -1;
				for(int x=i; x<i+3; x++) {
					for(int y=j; y<j+3; y++) {
						if(g[x][y] == n) cnt++;
						if(!visited[x][y]) {
							empty++;
							px = x;
							py = y;
						}
					}
				}
				// n이없고, 빈곳이 1개이면 => CrossHatching 
				if(cnt == 0 && empty == 1) {
					visited[px][py] = true;
					g[px][py] = n;
					fillLine(px, py);
					fCnt++;
				}
			}
		}
		return fCnt;
	}
	public static int crossHatching(int n) {
		visited = new boolean[N][N];
		fillArea(n);
		
		
		return findSpot(n);
	}

}
