package day1203;

import java.io.*;
import java.util.*;

public class JY_1194 {

	static int N, M;
	static char[][] g;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans;
	static class State {
		int x, y, dist, key;

		public State(int x, int y, int dist, int key) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.key = key;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", dist=" + dist + ", key=" + key + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new char[N][M];
		int mx = -1;
		int my = -1;
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				g[i][j] = line.charAt(j);
				if(g[i][j] == '0') {
					mx = i;
					my = j;
				}
			}
		}	
//		printG();
		
		System.out.println(bfs(mx, my));
		

	}
	public static void printG() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	public static boolean isDoor(char c) {
		return c >= 'A' && c <= 'Z';
	}
	public static boolean isKey(char k) {
		return k >= 'a' && k <= 'z';
	}
	public static int bfs(int x, int y) {
		Queue<State> q = new LinkedList<>();
		boolean[][][] visited = new boolean[64][N][M];
		
		q.add(new State(x, y, 0, 0));
		visited[0][x][y] = true;
		
		while(!q.isEmpty()) {
			State now = q.poll();
			
			// 문이면 탈출
			if(g[now.x][now.y] == '1') {
				return now.dist;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(!inRange(nx, ny)) continue;
				if(visited[now.key][nx][ny]) continue;
				if(g[nx][ny] == '#') continue;
				
				int key = now.key;
				// 문 방문
				if(isDoor(g[nx][ny])) {
					int hasKey = key & 1 << (g[nx][ny] - 'A');
					// 열쇠 없음
					if(hasKey == 0) continue;
				}
				// 열쇠 방문
				else if(isKey(g[nx][ny])) {
					int newKey = key | (1 << g[nx][ny]-'a');
					if(visited[newKey][nx][ny]) continue;
					// 현재 키 방문 처리
					visited[key][nx][ny] = true;
					// 키 변경
					key = newKey;
				}
				
				// 그외 방문 가능
				visited[key][nx][ny] = true;
				q.add(new State(nx, ny, now.dist+1, key));
				
			}
			
		}
		return -1;
	}

}
