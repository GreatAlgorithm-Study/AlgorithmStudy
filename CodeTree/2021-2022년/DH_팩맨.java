import java.io.*;
import java.util.*;

public class DH_팩맨 {
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void updatePoint(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void move(int dr, int dc) {
			this.r += dr;
			this.c += dc;
		}
	}
	static Point packman;
	static int M, T;
	static int[][][] monsters; // 방향별로 몬스터가 몇 마리 있는지 저장하는 변수
	static int[][][] tmpMonsters; // 몬스터를 이동시킨 후 방향별로 몇 마리 있는지 저장하는 변수
	static int[][][] newMonsters; // 몬스터 복제를 시도할 때, monsters의 정보를 복제하기 위해 사용하는 변수
	static int[][] dieTime; // monster가 죽고, 2초를 세기 위해 사용
	static int[][] packmanMoveCnt; // 팩맨이 이동할 때, 해당 위치를 몇 번 지나갔는지 저장하는 변수
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {1, 0, -1, -1, -1, 0, 1, 1};
	static int[] sel, move; // 팩맨의 이동 위치를 파악하기 위한 변수
	static int maxMonsterCnt;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	 
	static void solution() {
		while(T-- > 0) {
			step4(); // 시체 소멸 (턴이 시작 될 떄마다 시작해야 시간을 1 감소시켜야 되므로 루프 맨 처음에 시작)
			step1(); // 몬스터 복제 시도
			step2(); // 몬스터 이동 
			step3(); // 팩맨 이동
			step5(); // 몬스터 복제 완성
		}
		
		System.out.println(printMonsterCnt());
	}
	
	static int printMonsterCnt() {
		int result = 0;
		for(int k = 0; k < 8; k++) {
			for(int r = 0; r < 4; r++) {
				for(int c = 0; c < 4; c++) result += monsters[k][r][c];
			}
		}
		
		return result;
	}
	
	// 몬스터 복제
	static void step5() {
		for(int k = 0; k < 8; k++) {
			for(int r = 0; r < 4; r++) {
				for(int c = 0; c < 4; c++) {
					monsters[k][r][c] += newMonsters[k][r][c];
				}
			}
		}
	}
	
	// 몬스터 시체 소멸
	static void step4() {
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				if(dieTime[r][c] == 0) continue;
				dieTime[r][c] -= 1;
			}
		}
	}
	
	// 팩맨이 움직일 위치를 구하기 위한 재귀함수
	// int[] move에 팩맨이 진짜 움직일 위치를 저장함
	static void func(int r, int c, int depth, int eatCnt) {
		if(depth == 3) {
//			System.out.println("팩맨이 움직일 위치: " + printPackManMoveDir(sel) + " " + eatCnt);
			if(eatCnt > maxMonsterCnt) {
				maxMonsterCnt = eatCnt;
				move = Arrays.copyOf(sel, sel.length);
			}
			return;
		}
		
		// 상, 좌, 하, 우
		for(int d = 1; d < 8; d += 2) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc)) continue;
			sel[depth] = d; 
			
			// packmanMoveCnt[][]: 팩맨이 해당 위치를 몇 번 지나갔는지 저장하는 변수
			// packmanMoveCnt[nr][nc]를 처음 갔다면, monsters[k][nr][nc] 더해주기
			// [상, 하, 상]과 같이 이전에 갔던 곳을 다시 가는 경우 잡을 수 있는 몬스터 수를 중복해서 셀 수도 있기 때문에
			// 이러한 경우를 방지하기 위해 boolean[][]이 아닌 int[][]로 선언
			if(packmanMoveCnt[nr][nc] == 0) {
				for(int k = 0; k < 8; k++) eatCnt += monsters[k][nr][nc];
			}
			
			packmanMoveCnt[nr][nc] += 1;

			func(nr, nc, depth + 1, eatCnt);
			
			packmanMoveCnt[nr][nc] -= 1;
			if(packmanMoveCnt[nr][nc] == 0) {
				for(int k = 0; k < 8; k++) eatCnt -= monsters[k][nr][nc];
			}
		}
	}

	// 팩맨 이동
	static void step3() {
		// 순열을 통해 팩맨이 갈 수 있는 모든 위치를 파악하고
		// 잡을 수 있는 몬스터가 최대일 때, 가야되는 방향 구하기
		findPackManMoveDir();
		
		// 가야되는 방향을 구한 뒤, 팩맨 이동시키기
		movePackMan();
	}
	
	static void findPackManMoveDir() {
		maxMonsterCnt = -1;
		
		// 상, 하, 좌, 우 방향으로 3칸 (순열)
		func(packman.r, packman.c, 0, 0);
//		System.out.println("백맨이 움직일 위치: " + printPackManMoveDir(move));
	}
	
	// 팩맨 이동 시키기
	static void movePackMan() {
		
		for(int d: move) {
			packman.move(dr[d], dc[d]);
			
			boolean isDie = false;
			
			for(int k = 0; k < 8; k++) {
				if(monsters[k][packman.r][packman.c] > 0) {
					isDie = true;
					monsters[k][packman.r][packman.c] = 0;
				}
			}
			
			if(isDie) dieTime[packman.r][packman.c] = 3;
		}
	}

	// 몬스터 이동 시키기
	// 이동시킨 몬스터의 수는 tmpMonsters에 저장하고 나중에 monsters을 tmpMonsters로 바꾸긴
	static void step2() {
		tmpMonsters = new int[8][4][4];
		
		for(int k = 0; k < 8; k++) {
			for(int r = 0; r < 4; r++) {
				for(int c = 0; c < 4; c++) {
					if(monsters[k][r][c] == 0) continue;
					moveMonster(k, r, c);
				}
			}
		}
		
		monsters = tmpMonsters;
	}
	
	// 몬스터들을 모두 이동시킴
	static void moveMonster(int k, int r, int c) {
		for(int d = 0; d < 8; d++) {
			
			int nd = (k + d) % 8;
			
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			
			if(!check(nr, nc) || dieTime[nr][nc] > 0 || existPackMan(nr, nc)) continue;
	
			tmpMonsters[nd][nr][nc] += monsters[k][r][c];
			return;
		}
		
		tmpMonsters[k][r][c] += monsters[k][r][c];
	}
	
	// 몬스터 복제 시도
	// newMonsters에 복제할 몬스터들의 정보 저장함
	static void step1() {
		newMonsters = new int[8][4][4];

		for(int k = 0; k < 8; k++) {
			for(int r = 0; r < 4; r++) {
				newMonsters[k][r] = Arrays.copyOf(monsters[k][r], 4);
			}
		}
	}

	// 해당 위치에 팩맨이 있는지 확인
	static boolean existPackMan(int r, int c) {
		return packman.r == r && packman.c == c;
	}
	
	// map의 범위를 벗어나는지 확인
	static boolean check(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}
	
	static String printPackManMoveDir(int[] sel) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sel.length; i++) {
			if(sel[i] == 1) sb.append("상");
			if(sel[i] == 5) sb.append("하");
			if(sel[i] == 3) sb.append("좌");
			if(sel[i] == 7) sb.append("우");
		}
		return sb.toString();
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/팩맨.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		packman = setPoint(st);
		
		monsters = new int[8][4][4];
		dieTime = new int[4][4];
		packmanMoveCnt = new int[4][4];
		sel = new int[3];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) % 8;
			monsters[d][r][c] += 1;
		}
	}
	
	static void printPackManInfo() {
		System.out.println("팩맨 정보: " + packman.toString());
	}
	static Point setPoint(StringTokenizer st) {
		return new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
	}
}
