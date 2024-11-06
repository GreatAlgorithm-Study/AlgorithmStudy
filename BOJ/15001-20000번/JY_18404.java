import java.util.*;
import java.io.*;

public class JY_18404 {
	
	static int N, M;
	static int[][] g;
	static int[] crr;
	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
	static class Pos {
		int x, y, cnt;
		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new int[N+1][N+1];
		crr = new int[M+1];

		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			g[x][y] = i;
			crr[i] = Integer.MAX_VALUE;
		}
		
//		System.out.println("sx:"+sx+",sy:"+sy);
//		for(int i=0; i<N+1; i++) {
//			System.out.println(Arrays.toString(g[i]));
//		}
//		System.out.println(Arrays.toString(crr));
		
		// bfs 탐색으로 최소 움직임 찾기
		bfs(sx, sy);
		
		for(int i=1; i<M+1; i++) {
			System.out.print(crr[i]+" ");
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>0 && x<=N && y>0 && y<=N;
	}
	public static void bfs(int x, int y) {
		ArrayDeque<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N+1][N+1];
		q.add(new Pos(x, y, 0));
		visited[x][y] = true;
		
		int isDone = 0;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			// 상대편 말을 잡았다면 최소 움직임 기록
			if(g[now.x][now.y] != 0) {
				int num = g[now.x][now.y];
				crr[num] = now.cnt;
				isDone++;
			}
			// 모든 상대편 말을 잡았다면 break
			if(isDone == M) break;
			
			for(int i=0; i<8; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(inRange(nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Pos(nx, ny, now.cnt+1));
				}
			}
		}
		
	}


}
