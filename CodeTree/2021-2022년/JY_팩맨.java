import java.io.*;
import java.util.*;

public class JY_팩맨 {
	
	static final int N = 4;
	static int M, T;
	static int nowTurn;
	// 팩맨 위치
	static int px, py;
	// 시체
	static int[][] ghost;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	// 알 
	static List<Integer>[] egg;
	// 몬스터 배열 리스트
	static List<Integer>[] mrr;
	static List<Integer> tList;
	static List<List<Integer>> dList; 

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		px = Integer.parseInt(st.nextToken()) - 1;
		py = Integer.parseInt(st.nextToken()) - 1;
		
		mrr = new ArrayList[N*N];
		for(int i=0; i<N*N; i++) {
			mrr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			mrr[x*N + y].add(d);
		}
		
//		print(mrr);
		
		// 팩맨의 이동 방향 리스트 생성
		dList = new ArrayList<>();
		tList = new ArrayList<>();
		makePacDir(0);
		
//		System.out.println(dList);
//		System.out.println(dList.size());
		
		
		ghost = new int[N][N];
		
		// -------- for 문---------
		for(int t=1; t<T+1; t++) {
			// 1) 몬스터 복제 시도
			nowTurn = t;
			egg = new ArrayList[N*N];
			for(int i=0; i<N*N; i++) {
				egg[i] = new ArrayList<>();
			}
			makeEgg();
			
			// 2) 몬스터 이동
			moveMonster();
			
			// 3) 팩맨 이동
			movePacman();
			
			// 4) 몬스터 시제 소멸
			removeGhost();
			
			// 5) 몬스터 복제 완성
			makeMonster();
		}
		
		// 남은 몬스터 개수
		int ans = 0;
		for(int i=0; i<N*N; i++) {
			ans += mrr[i].size();
		}

		System.out.println(ans);
	}
	public static void printG(int[][] g) {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static void print(List<Integer>[] arr) {
		for(int i=0; i<N*N; i++) {
			System.out.println("i:"+i +" "+ arr[i]);
		}
		System.out.println();
	}
	public static void makePacDir(int depth) {
		if(depth == 3) {
//			System.out.println(tList);
			List<Integer> tmp = new ArrayList<>();
			// copy
			for(int t: tList) {
				tmp.add(t);
			}
			dList.add(tmp);
			return;
		}
		
		for(int i=0; i<8; i+=2) {
			tList.add(i);
			makePacDir(depth + 1);
			tList.remove(tList.size()-1);
		}
	}
	public static void makeEgg() {
		for(int i=0; i<N*N; i++) {
			if(mrr[i].isEmpty()) continue;
			
			for(int m: mrr[i]) {
				egg[i].add(m);
			}
		}
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static boolean isPac(int x, int y) {
		return x == px && y == py;
	}
	public static boolean isGhost(int x, int y) {
		return ghost[x][y] != 0;
	}
	public static void moveMonster() {
		List<Integer>[] tmp = new ArrayList[N*N];
		for(int i=0; i<N*N; i++) {
			tmp[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N*N; i++) {
			if(mrr[i].isEmpty()) continue;
			
			int x = i / N;
			int y = i % N;
			for(int m: mrr[i]) {
				int cnt = 0;
				int dir = m;
				while(cnt < 8) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					// 이동 가능
					if(inRange(nx, ny) && !isPac(nx, ny) && !isGhost(nx, ny)) {
						tmp[nx*N + ny].add(dir);
						break;
					}
					cnt++;
					dir = (dir + 1) % 8;
				}
				// 이동 불가 : 원래 위치
				if(cnt == 8) {
					tmp[i].add(m);
				}
			}
		}
		
		mrr = tmp;
	}
	public static int countMonster(List<Integer> drr) {
		int x = px;
		int y = py;
		int cnt = 0;
		boolean[] visited = new boolean[N*N];
//		visited[x*N + y] = true;
		for(int d: drr) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			// 범위 밖이면 불가
			if(!inRange(nx, ny)) return -1;
			// 방문했던 곳을 다시 방문하는 것은 불가
			if(!visited[nx*N + ny]) {
				cnt += mrr[nx*N + ny].size();
				visited[nx*N + ny] = true;				
			}
			
			x = nx;
			y = ny;
		}
		return cnt;
	}
	public static void movePacman() {
		int mCnt = 0;
		int idx = 0;
		for(int i=0; i<dList.size(); i++) {
			int cnt = countMonster(dList.get(i));
			if(mCnt < cnt) {
				mCnt = cnt;
				idx = i;
			}
		}
		
//		System.out.println("mCnt: "+mCnt+" moveIdx: "+ idx +" "+ dList.get(idx));
		// 진짜 이동하기
		for(int d : dList.get(idx)) {
			int nx = px + dx[d];
			int ny = py + dy[d];
			
			if(!inRange(nx, ny)) continue;
			// 몬스터 없애기 
			if(!mrr[nx*N + ny].isEmpty()) {				
				mrr[nx*N + ny] = new ArrayList<>();
				// 현재 턴수 기록
				ghost[nx][ny] = nowTurn;
			}
			
			px = nx;
			py = ny;
		}
	}
	public static void removeGhost() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(ghost[i][j] != 0 && ghost[i][j]+2 == nowTurn) {
					ghost[i][j] = 0;
				}
			}
		}
	}
	public static void makeMonster() {
		for(int i=0; i<N*N; i++) {
			if(egg[i].isEmpty()) continue;
			
			for(int e: egg[i]) {
				mrr[i].add(e);
			}
		}
	}

}
