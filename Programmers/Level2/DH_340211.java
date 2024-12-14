import java.util.*;

/*
 * 충돌위험 찾기
 */

class DH_340211 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {0, 0, -1, 1}, dc = {-1, 1, 0, 0};
	static Point[][][] checkPoints; // bfs 경로를 역추적하기 위해 사용
	static HashSet<Integer>[][][] dis; // dis[k][r][c]: k번째 routes에서 r, c 지점을 가는데, 거리가 몇인지 저장
	static boolean[][][] v; // v[k][r][c]: r, c 지점까지의 거리가 k일 때, 방문 했는지 확인
	static int result, routeDis;
	
	static int solution(int[][] points, int[][] routes) {
		
		checkPoints = new Point[routes.length][101][101];
		dis = new HashSet[routes.length][101][101];
		
		for(int k = 0; k < dis.length; k++) {
			for(int r = 0; r < dis[0].length; r++) {
				for(int c = 0; c < dis[0][0].length; c++) dis[k][r][c] = new HashSet<Integer>();
			}
		}
		
		v = new boolean[20_000][101][101];
		
		for(int i = 0; i < routes.length; i++) {
			
			// routes를 따라 갈 때, 총 길이가 몇인지 확인하기 위한 변수
			routeDis = 1;
			
			for(int g = 0; g < routes[i].length - 1; g++) {
				int[] goal = points[routes[i][g + 1] - 1];
				int[] current = points[routes[i][g] - 1];
				// bfs는 목표 지점에서부터 시작지점으로 도달할 수 있도록 해야됨
				bfs(goal, current, i);
				
				// bfs지점을 역추적하면서 가야될 길을 최단거리로 가면서, 시작지점으로부터 거리가 몇인지 저장함
				back(current[0], current[1], goal[0], goal[1], i, routeDis);
			}
		}
		
		return result;
	}
	
	// 목표지점에서부터 시작지점으로 bfs를 한 뒤에
	// bfs 경로를 역추적하면서, 시작 지점으로부터 목표지점까지 최단거리가 어떻게 되는지 확인하고
	// 시작지점으로부터 떨어진 거리를 저장함
	static void back(int r, int c, int er, int ec, int i, int d) {
		
		for(int k = 0; k < dis.length; k++) {
			if(k == i) continue;
			
			if(dis[k][r][c].contains(d) && !v[d][r][c]) {
				v[d][r][c] = true;
				result += 1;
				break;
			}
		}
		
		dis[i][r][c].add(d);
		
		if((r == er && c == ec) || checkPoints[i][r][c] == null) return;
		int nr = checkPoints[i][r][c].r;
		int nc = checkPoints[i][r][c].c;
		
		routeDis += 1;
		back(nr, nc, er, ec, i, d + 1);
	}
	
	// bfs를 통해 목표지점에서부터 시작지점으로 최단거리 탐색
	static void bfs(int[] g, int[] c, int i) {
		
		Deque<Point> q = new ArrayDeque<Point>();
		q.add(new Point(g[0], g[1]));
		
		boolean[][] v = new boolean[101][101];
		v[g[0]][g[1]] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.r == c[0] && current.c == c[1]) return;
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!isValid(nr, nc) || v[nr][nc]) continue;

				checkPoints[i][nr][nc] = new Point(current.r, current.c);
				
				q.add(new Point(nr, nc));
				v[nr][nc] = true;
			}
		}
	}
	
	static boolean isValid(int r, int c) {
		return r > 0 && r < 101 && c > 0 && c < 101;
	}
    
    public static void main(String[] args) {
    	int[][] points = {{1, 1}, {1, 2}, {1, 3}};
    	int[][] routes = {{2, 1}, {2, 3, 1}};
    	System.out.println(solution(points, routes));
    }
}