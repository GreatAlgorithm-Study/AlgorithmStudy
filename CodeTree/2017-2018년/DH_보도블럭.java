import java.io.*;
import java.util.*;

public class DH_보도블럭 {
	static int N, L, result;
	static int[][] map;
	static boolean[][] install;
	
	public static void main(String[] args) throws Exception {
		initInput();

		solution();

		rotateMap();
		
		solution();
		
		System.out.println(result);
	}
	
	static void solution() {
		install = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			L: for(int c = 0; c < N; c++) {
				if(c == N - 1) {
					result += 1;
					continue;
				}
				
				if(map[r][c] != map[r][c + 1]) {
					// 오른쪽이 더 큰 경우
					// map[r][c]을 오른쪽 끝으로 하는 오르막길 설치해야 됨
					if(map[r][c + 1] == map[r][c] + 1) {
						
						for(int l = 0; l < L; l++) {
							int nc = c - l;
							
							if(nc < 0 || map[r][c] != map[r][nc] || install[r][nc]) break L;
							install[r][nc] = true;
						}
						
 						c = (c - (L - 1) + L - 1);
					}
					
					// 오른쪽이 더 작은 경우
					// map[r][c + 1] 지점을 왼쪽 끝으로 하는 내리막길 설치해야 됨
					else if(map[r][c + 1] == map[r][c] - 1) {
						
						for(int l = 0; l < L; l++) {
							int nc = c + 1 + l;
							
							if(nc >= N || map[r][c + 1] != map[r][nc] || install[r][nc]) break L;
							install[r][nc] = true;
						}
						
						c += (L - 1);
					}
					
					// 열에 대해서 나가기 (불가능한 경우)
					else break;
				}
			}
		}
	}
	
	static void rotateMap() {
		int tmp[][] = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				tmp[c][r] = map[r][c];
			}
		}
		
		map = tmp;
	}

	static void initInput() throws Exception {
		
		System.setIn(new FileInputStream("./input/보도블럭.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
