import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] g;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static int rCnt;
	static class Bomb implements Comparable<Bomb> {
		int x, y, color;

		public Bomb(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
		
		@Override
		public int compareTo(Bomb other) {
			if(this.x == other.x) {
				// 2) 열이 작은 순
				return this.y - other.y;
			}
			// 1) 행이 큰 순
			return other.x - this.x;
		}

		@Override
		public String toString() {
			return "Bomb [x=" + x + ", y=" + y + ", color=" + color + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		while(true) {
			// 1) 폭탄 묶음 찾기
			List<Bomb> bList = findBundle();
			
			// 더이상 폭탄 묶음이 없으면 종료
			if(bList.size() == 0) break;
			
			// 폭탄 묶음 점수 계산
			int c = bList.size();
			ans += (c*c);
			
			// 2) 폭탄 제거 & 중력 작용
			removeBomb(bList);
			
			setGravity();
			
			// 3) 반시계 90 회전
			rotate();
			
			// 4) 중력 작용
			setGravity();
			
		}
		System.out.println(ans);
		
	}
	public static void print(int[][] a) {
		for(int i=0; i<g.length; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static List<Bomb> bfs(Bomb start) {
		Queue<Bomb> q = new LinkedList<>();
		List<Bomb> aList = new ArrayList<>();
		
		visited[start.x][start.y] = true;
		q.add(start);
		aList.add(start);
		
		// 빨간 폭탄 리스트
		List<Bomb> rList = new ArrayList<>();
	
		while(!q.isEmpty()) {
			Bomb now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(g[nx][ny] != 0 && g[nx][ny] != start.color) continue;
				
				Bomb next = new Bomb(nx, ny, g[nx][ny]);
				if(g[nx][ny] == 0) {
					rList.add(next);
				}
				
				visited[nx][ny] = true;
				q.add(next);
				aList.add(next);
			}
		}
		
		rCnt = rList.size();
		// 빨간 폭탄들 visited 원상복귀
		for(Bomb r: rList) {
			visited[r.x][r.y] = false;
		}
		
		if(aList.size() <= 1) {
			return new ArrayList<>();
		}
		// 우선순위에 맞게 정렬 후 반환
		Collections.sort(aList);
		return aList;
		
	}
	public static List<Bomb> findBundle() {
		// 기준점 우선순위 큐 (크기->빨간폭탄->행->열)
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) {
						if(o1[2] == o2[2]) {
							// 4) 열이 작은 순
							return o1[3] - o2[3];
						}
						// 3) 행이 큰 순
						return o2[2] - o1[2];
					}
					// 2) 빨간 폭탄의 개수가 적은 순
					return o1[1] - o2[1];
				}
				// 1) 묶음 크기가 큰 순
				return o2[0] - o1[0];
			}
		});
		// 기준점의 위치와 폭탄 묶음 저장 해쉬맵
		Map<Integer, List<Bomb>> tMap = new HashMap<>();
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 빨강(0), 돌(-1), 빈칸(-2) 제외 폭탄들이 대상
				if(g[i][j] > 0 && !visited[i][j]) {
					rCnt = 0;
					List<Bomb> bList = bfs(new Bomb(i, j, g[i][j]));
					
					if(bList.size() == 0) continue;
					
					Bomb center = bList.get(0);
					int cNum = center.x*N + center.y;
					if(tMap.getOrDefault(cNum, new ArrayList<>()).size() > bList.size()) {
						continue;
					}
					tMap.put(cNum, bList);
					pq.add(new int[] {bList.size(), rCnt, center.x, center.y});
				}
			}
		}
		// pq가 비어있다면 만족하는 폭탄 묶음들 없음
		if(pq.size() == 0) return new ArrayList<>();
		// 가장 우선순위가 높은 기준점
		int[] target = pq.poll();
		return tMap.get(target[2]*N + target[3]);
		
	}
	public static void removeBomb(List<Bomb> bList) {
		// -2 : 빈칸
		for(Bomb b: bList) {
			g[b.x][b.y] = -2;
		}
	}
	public static void setGravity() {
		for(int y=0; y<N; y++) {
			for(int x=N-1; x>=0; x--) {
				if(g[x][y] < 0) continue;
				
				// 폭탄
				int color = g[x][y];
				g[x][y] = -2;
				int t = x;
				while(t < N) {
					if(t+1 == N || g[t+1][y] != -2) break;
					t++;
				}
				g[t][y] = color;
			}
		}
	}
	public static void rotate() {
		int[][] t = new int[N][N];
		
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				int nx = N - y - 1;
				int ny = x;
				t[nx][ny] = g[x][y];
			}
		}
		
		g = t;
	}

}
