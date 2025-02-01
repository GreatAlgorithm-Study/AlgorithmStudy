import java.io.*;
import java.util.*;

public class JY_9205_2 {

	static final int METER = 50;
	static int T, N;
	static Pos[] srr;
	static Pos start, end;
	static class Pos {
		int x, y;
		int beer;
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			
			// 상근이네 집
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			start = new Pos(sx, sy, 20);
			
			// 편의점
			srr = new Pos[N+1];
			for(int i=1; i<N+1; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				srr[i] = new Pos(x, y, 20);
			}
			
			// 페스티벌
			st = new StringTokenizer(br.readLine());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			end = new Pos(ex, ey, 0);
			
			bfs(start);
		}
	}
	public static boolean canGo(Pos s, Pos e) {
		int mDist = s.beer * METER;
		int d = Math.abs(s.x- e.x) + Math.abs(s.y - e.y);
		
		if(mDist >= d) return true;
		return false;
	}
	public static void bfs(Pos s) {
		boolean[] visited = new boolean[N+1];
		Deque<Pos> q = new ArrayDeque<>();
		
		q.add(s);
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			// 현재위치 -> 페스티벌로 갈 수 있는가
			if(canGo(now, end)) {
				System.out.println("happy");
				return;
			}
			
			for(int i=1; i<N+1; i++) {
				if(visited[i]) continue;
				Pos next = srr[i];
				// 현재위치 -> 다음위치(편의점)로 갈 수 있는가
				if(!canGo(now, next)) continue;
				
				visited[i] = true;
				q.add(next);
			}
		}
		
		System.out.println("sad");
	}

}
