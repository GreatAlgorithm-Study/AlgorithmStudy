import java.io.*;
import java.util.*;

/*
 * 어른 상어
 * 시간 범위 설정 잘 하기!!
 */

public class DH_19237 {
	static class Node {
		int sharkIdx, time;
		
		public Node() {}
		public Node(int sharkIdx, int time) {
			this.sharkIdx = sharkIdx;
			this.time = time;
		}
		
		public void update(int sharkIdx, int time) {
			this.sharkIdx = sharkIdx;
			this.time = time;
		}
	}
	static class Shark {
		boolean isDie;
		int pos, dir;
		int[][] priority;
		
		public Shark() {}
		
		public Shark(int pos, int dir) {
			this.pos = pos;
			this.dir = dir;
		}
		
		public void setPos(int pos) {
			this.pos = pos;
		}
		
		public void setDir(int dir) {
			this.dir = dir;
		}
		
		public void setPriority(int[][] priority) {
			this.priority = priority;
		}
	}
	static int N, M, k, liveSharkCnt; // liveSharkCnt: 살아있는 상어 수
	static int[][] idx; // 상어가 있는 위치를 idx로 저장
	static Node[][] smell; // 냄새에 대한 정보 (상어 idx, 냄새 유지 시간)
	static Shark[] sharks; // 상어들에 대한 정보
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}; // 상, 하, 좌, 우
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		int time = 0;
		boolean flag = false;
		
		// while(time < 1_001)으로 하면 1001초까지 보게 됨 !! =ㅅ=,,,
		while(time < 1_000) {
			
			moveSharks();
			
			decreaseSmell(); // 기존에 있던 냄새 1씩 줄이기
			updateSmell(); // 상어들이 움직인 자리에 상어 냄새 표시해주기
			
			time++;
			
			if(liveSharkCnt == 1) {
				flag = true;
				break;
			}
		}
		
		System.out.println(flag ? time : -1);
	}
	
	// 상어가 새로 그 자리에 갔을 때, 해당 좌표에서 냄새 업데이트해주기
	static void updateSmell() {
		for(int idx = 1; idx < sharks.length; idx++) {
			if(sharks[idx].isDie) continue;
			
			int pos = sharks[idx].pos;
			int r = pos / N;
			int c = pos % N;
			
			smell[r][c].update(idx, k); // (r, c) 지점에 idx 상어의 냄새가 k초 동안 지속됨
		}
	}
	
	// 1초가 지날 때 마다 상어 냄새 유지도 줄여주기
	static void decreaseSmell() {
		for(int r = 0; r < smell.length; r++) {
			for(int c = 0; c < smell[0].length; c++) {
				if(smell[r][c].time == 0) continue;
				
				smell[r][c].time--;
				if(smell[r][c].time == 0) smell[r][c].sharkIdx = 0;
			}
		}
	}

	// 상어들 움직여주기
	static void moveSharks() {
		int[][] nextIdxMap = new int[N][N]; // 이동한 다음 상어들의 인덱스를 저장하는 배열
		
		// idx가 큰 상어부터 이동해주기
		for(int idx = sharks.length - 1; idx > 0; idx--) {
			if(sharks[idx].isDie) continue; // 겪자 밖으로 쫓겨난 상어는 움직이지 못함
			
			Shark current = sharks[idx];
			
			int moveDir = -1; // 상어가 움직일 방향
			int cr = current.pos / N, cc = current.pos % N; // 현재 상어의 위치

			int mr = cr, mc = cc; // 이동할 상어의 위치
			
			for(int d = 0; d < 4; d++) {
				int dir = current.priority[current.dir][d]; // 상어 방향의 우선순위에 따라 4방으로 확인
				
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				
				if(!check(nr, nc)) continue; // 범위를 벗어난다면 다른 방향 확인
				// 다른 상어의 냄새가 있다면 다른 방향으로 확인
				if(smell[nr][nc].sharkIdx != idx && smell[nr][nc].sharkIdx != 0) continue;
				
				// 이동할 위치를 정하지 못했는데, 그 다음 좌표에 자신의 냄새가 난다면 일단 이동할 위치로 정해주기
				if(moveDir == -1 && smell[nr][nc].sharkIdx == idx) {
					moveDir = dir;
					mr = nr;
					mc = nc;
				}
				
				// 냄새가 없는 칸이 나오면 바로 그 방향으로 이동하기
				if(smell[nr][nc].sharkIdx == 0) {
					moveDir = dir;
					mr = nr;
					mc = nc;
					break;
				}
			}
			
			// 상어가 향하고 있는 방향 수정
			current.dir = moveDir;

			// 혹시라도 상어가 움직이지 못하는 경우가 있을까봐 조건문으로 설정함
			// 상어가 이동할 방향이 있다면, 해당 방향으로 상어 움직여주기
			if(moveDir != -1) {
				int nextPos = mr * N + mc;
				current.pos = nextPos;
			}
			
			// 이미 이동한 상어가 있는 경우 
			// => idx가 작은 상어가 오게 되고, 기존에 있는 상어는 격자 밖으로 내쫓기게 됨
			if(nextIdxMap[mr][mc] != 0) {
				int biggerIdx = nextIdxMap[mr][mc];
				
				sharks[biggerIdx].isDie = true;
				liveSharkCnt--;
			}

			nextIdxMap[mr][mc] = idx;
		}
		
		// idx배열 갱신하기
		idx = nextIdxMap;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/BOJ19237.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // N × N 크기의 mapN
		M = Integer.parseInt(st.nextToken()); // 1 ≤ 상어의 번호 ≤ M
		k = Integer.parseInt(st.nextToken()); // 냄새 유지 시간
		liveSharkCnt = M; // 총 M개의 상어가 살아있음
		
		idx = new int[N][N];
		smell = new Node[N][N];
		
		sharks = new Shark[M + 1];
		for(int idx = 1; idx < sharks.length; idx++) sharks[idx] = new Shark();
		
		for(int r = 0; r < idx.length; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < idx[0].length; c++) {
				
				smell[r][c] = new Node();
				
				int currentIdx = Integer.parseInt(st.nextToken());
				idx[r][c] = currentIdx;
				
				if(currentIdx == 0) continue;
				smell[r][c] = new Node(idx[r][c], k);
				
				int pos = r * N + c;
				sharks[currentIdx].setPos(pos);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < sharks.length; i++) {
			int dir = Integer.parseInt(st.nextToken()) - 1;
			sharks[i].setDir(dir);
		}
		
		for(int idx = 1; idx < sharks.length; idx++) {
			int[][] priorityDir = new int[4][4];
			
			for(int d = 0; d < 4; d++) {
				st = new StringTokenizer(br.readLine());
				
				for(int i = 0; i < 4; i++) {
					int dir = Integer.parseInt(st.nextToken()) - 1;
					priorityDir[d][i] = dir;
				}
			}
			
			sharks[idx].setPriority(priorityDir);
		}
	}
}
