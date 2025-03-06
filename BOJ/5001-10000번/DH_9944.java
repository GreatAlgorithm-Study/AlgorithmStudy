import java.io.*;
import java.util.*;

/*
 * NxM 보드 완주하기
 */

public class DH_9944 {
	static final char VISITED = '-', BLOCK = '*', EMPTY = '.';
	static final int INF = Integer.MAX_VALUE;

	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/BOJ9944.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input;

		int tc = 1;
		
		StringBuilder sb = new StringBuilder();
		
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[N][M];
			
			int emptyCnt = 0; // 빈 공간의 수
			
			for(int r = 0; r < N; r++) {
				map[r] = br.readLine().toCharArray();
				
				for(int c = 0; c < map[r].length; c++) {
					if(map[r][c] == EMPTY) emptyCnt++; 
				}
			}
			
			int minValue = INF;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == '*') continue;
					
					map[r][c] = VISITED;
					minValue = Math.min(minValue, dfs(map, r, c, 0, minValue, emptyCnt - 1));
					map[r][c] = EMPTY;
				}
			}
			
			sb.append("Case ").append(tc++).append(": ").append(minValue == INF ? -1 : minValue).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int dfs(char[][] map, int r, int c, int depth, int minValue, int emptyCnt) {
		
		if(minValue == depth) return minValue;
		if(emptyCnt == 0) return depth; // 빈 공간의 수가 0개라면 (모두 다 방문했다면) dfs 깊이 반환
		
		int result = Integer.MAX_VALUE;
		int n = map.length, m = map[0].length; 
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// (r, c) 지점 기준으로 d 방향으로 나아갈 수 없다면 continue
			if(!check(n, m, nr, nc) || map[nr][nc] == BLOCK || map[nr][nc] == VISITED) continue;
			
			// 앞으로 나아가기
			// nextInfo[0]: 이동 후 r의 좌표, nextInfo[1]: 이동 후 c의 좌표, nextInfo[2]: 원래 (r, c) 기준 얼마나 이동했는지 
			int[] nextInfo = forward(map, r, c, d);
			int removeEmptySpace = nextInfo[2];
			
			result = Math.min(result, dfs(map, nextInfo[0], nextInfo[1], depth + 1, minValue, emptyCnt - removeEmptySpace));
			
			// 뒤로 되돌아가기
			// nextInfo[2] 만큼 되돌아감
			backward(map, nextInfo[0], nextInfo[1], nextInfo[2], (d + 2) % 4);
		}
		
		return result;
	}
	
	// 뒤로 되돌아가기
	// (r, c)를 시작으로 dis만큼 d 방향으로 이동함
	static void backward(char[][] map, int r, int c, int dis, int d) {
		for(int i = 0; i < dis; i++) {
			map[r][c] = EMPTY;
			
			r += dr[d];
			c += dc[d];
		}
	}
	
	// 앞으로 나아가기
	// (r, c)에서 d 방향으로 나아가기
	// 방해물이 있거나 이미 방문한 곳이라면 앞으로 나아가지 못함
	static int[] forward(char[][] map, int r, int c, int d) {
		int dis = 0;
		
		int n = map.length, m = map[0].length;
		
		while(true) {
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(n, m, nr, nc) || map[nr][nc] == BLOCK || map[nr][nc] == VISITED) break;
			map[nr][nc] = VISITED;
			dis += 1;
			
			r = nr;
			c = nc;
		}
		
		return new int[] {r, c, dis};
	}
	
	static boolean check(int n, int m, int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}
