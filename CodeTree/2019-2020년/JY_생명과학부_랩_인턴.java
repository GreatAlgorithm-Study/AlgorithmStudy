import java.io.*;
import java.util.*;

public class JY_생명과학부_랩_인턴 {
	
	static int N, M, K;
	static List<Mold> mList;
	static boolean[] isDied;
	// 상, 하, 우, 좌
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int total;
	static class Mold implements Comparable<Mold> {
		int num, x, y, s, d, size;

		public Mold(int num, int x, int y, int s, int d, int size) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.size = size;
		}

		@Override
		public int compareTo(Mold other) {
			// 열 -> 행의 오름차순
			if(this.y == other.y) {
				return this.x - other.x;
			}
			return this.y - other.y;
		}
		
		@Override
		public String toString() {
			return "Mold [num=" + num + ", x=" + x + ", y=" + y + ", s=" + s + ", d=" + d + ", size=" + size + "]";
		}
		
	}

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/day1126/mold.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		mList = new ArrayList<>();
		for(int k=1; k<K+1; k++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			mList.add(new Mold(k, x, y, s, d-1, b));
		}
		
		isDied = new boolean[K+1];
		
		total = 0;
		for(int m=1; m<M+1; m++) {
			// 1) 곰팡이 채취
			selectMold(m);
			
			// 2) 곰팡이 움직이기
			moveMold();
			
			// 살아있는 곰팡이 재정비
			aliveMold();
		}
		
		System.out.println(total);
		
	}
	public static void selectMold(int col) {
		Collections.sort(mList);
		
		for(Mold m: mList) {
			// 이전 열
			if(m.y < col) continue;
			// 다음 열
			if(m.y > col) break;
			
			// 현재 열 : 곰팡이 채취
			isDied[m.num] = true;
			total += m.size;
			break;
		}
	}
	public static boolean inRange(int x, int y) {
		return x>0 && x<=N && y>0 && y<=M;
	}
	public static int changeDir(int dir) {
		if(dir == 0) return 1;
		else if(dir == 1) return 0;
		else if(dir == 2) return 3;
		else return 2;
	}
	public static int findSize(int num) {
		for(Mold m: mList) {
			if(isDied[m.num]) continue;
			if(m.num == num) return m.size;
		}
		return -1;
	}
	public static void moveMold() {
		int[][] visited = new int[N+1][M+1];
		
		for(Mold m : mList) {
			if(isDied[m.num]) continue;
			
			// 곰팡이 움직이기
			int tx = m.x;
			int ty = m.y;
			for(int i=0; i<m.s; i++) {
				int nx = tx + dx[m.d];
				int ny = ty + dy[m.d];
				// 범위 벗어나면 방항 전환후 다시 진행
				if(!inRange(nx, ny)) {
					m.d = changeDir(m.d);
					nx = tx + dx[m.d];
					ny = ty + dy[m.d];
				}
				tx = nx;
				ty = ny;
			}
			m.x = tx;
			m.y = ty;
			// 이동한 곳에 다른 곰팡이 존재함
			if(visited[m.x][m.y] != 0) {
				int other = findSize(visited[m.x][m.y]);
				if(other > m.size) {
					isDied[m.num] = true;
				} else {
					isDied[visited[m.x][m.y]] = true;
					visited[m.x][m.y] = m.num;
				}
			}
			else {
				visited[m.x][m.y] = m.num;
			}
		}
	}
	public static void aliveMold() {
		List<Mold> tmp = new ArrayList<>();
		for(Mold m : mList) {
			if(isDied[m.num]) continue;
			tmp.add(m);
		}
		mList = tmp;
	}

}
