package day1104;

import java.io.*;
import java.util.*;

public class JY_전투_로봇 {
	
	static int N;
	static int[][] g;
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Pos robot;
	static class Pos {
		int x, y, level;
		public Pos(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}
	static class State implements Comparable<State> {
		int x, y, time;

		public State(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		@Override
		public int compareTo(State other) {
			if(this.time == other.time) {
				if(this.x == other.x) {
					return this.y - other.y;
				}
				return this.x - other.x;
			}
			return this.time - other.time;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		g = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
				
				// 전투로봇
				if(g[i][j] == 9) {
					robot = new Pos(i, j, 2);
					g[i][j] = 0;
				}
			}
		}
		
		int ans = 0;
		int cnt = 0;
		while(true) {
			if(robot.level == cnt) {
				robot.level++;
				cnt = 0;
			}
			
			int time = bfs();
			if(time == 0) break;
			ans += time;
			cnt++;
		}
		
		System.out.println(ans);
		

	}
	public static void print() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static int bfs() {
		boolean[][] visited = new boolean[N][N];
		Queue<State> q = new LinkedList<>();
		
		q.add(new State(robot.x, robot.y, 0));
		visited[robot.x][robot.y] = true;
		
		PriorityQueue<State> pq = new PriorityQueue<>();
		
		while(!q.isEmpty()) {
			State now = q.poll();
			// 없앨 수 있는 몬스터에 도착
			if(g[now.x][now.y] > 0 && g[now.x][now.y] < robot.level) {
				pq.add(now);
			}
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(g[nx][ny] > robot.level) continue;
				
				visited[nx][ny] = true;
				q.add(new State(nx, ny, now.time+1));
			}
		}
		
		if(pq.isEmpty()) return 0;
		State target = pq.poll();
		g[target.x][target.y] = 0;
		robot.x = target.x;
		robot.y = target.y;
		
		return target.time;
	}

}
