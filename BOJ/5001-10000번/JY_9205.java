import java.util.*;
import java.io.*;

public class JY_9205 {
	
	static int N;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static Pos[] stores;
	static boolean canGo;
	static Pos home, festival;
	
	static class Pos {
		int x, y, beer;

		public Pos(int x, int y, int beer) {
			super();
			this.x = x;
			this.y = y;
			this.beer = beer;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", beer=" + beer + "]";
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int hx = Integer.parseInt(st.nextToken());
			int hy = Integer.parseInt(st.nextToken());
			home = new Pos(hx, hy, 20);
			
			stores = new Pos[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int sx = Integer.parseInt(st.nextToken());
				int sy = Integer.parseInt(st.nextToken());
				stores[i] = new Pos(sx, sy, 0);
			}
			
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			festival = new Pos(fx, fy, 0);
			
			// BFS 탐색
			canGo = false;
			bfs();
			
			if(canGo) System.out.println("happy");
			else System.out.println("sad");
		}

	}
	public static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[] visited = new boolean[N];
		q.add(home);
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			// 현재 위치에서 페스티벌로 갈 수 있다면 break
			if(isGo(now, festival)) {
				canGo = true;
				break;
			}
			// 아직 못간다면, 갈 수 있는 편의점 탐색
			for(int i=0; i<N; i++) {
				if(!visited[i]) {
					Pos nextStore = stores[i];
					if(isGo(now, nextStore)) {
						visited[i] = true;
						// 맥주 장전
						nextStore.beer = 20;
						q.add(nextStore);
					}
				}
			}
		}
	
	}
	public static boolean isGo(Pos from, Pos to) {
		// 현재 맥주로 갈 수 있는 최대거리
		int maxDist = from.beer * 50;
		// 맨하탄 거리
		int distance = Math.abs(from.x-to.x)+Math.abs(from.y-to.y);
		// 남은 거리가 더 많으면 불가능
		if(distance > maxDist) return false;
		return true;
		
	}

}
