import java.io.*;
import java.util.*;

/*
 * 조심해야 됐던 부분
 * - 나무가 성장하고 번식할 때, 벽의 정보가 없어지면 안됐음!
 */

public class DH_나무박멸 {
	
	static int N, M, K, C;
	static int totalRemoveTreeCnt, tmpRemoveTreeCnt;
	static int[][] map, remove; // map: 나무, remove: 제초제 지속 시간
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {-1, 0, 1, 1, 1, 0, -1, -1}; // 8방 배열
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
		System.out.println(totalRemoveTreeCnt);
	}
	
	static void solution() {
		
		while(M-- > 0) {
			grow(); // 성장
			spread(); // 번식
			remove(); // 제초제 뿌리기
			decreaseRemove(); // 제초제 감소
		}
	}
	
	// 제초제 지속 시간 1씩 줄이기
	static void decreaseRemove() {
		for(int r = 0; r < remove.length; r++) {
			for(int c = 0; c < remove[0].length; c++) {
				if(remove[r][c] == 0) continue;
				remove[r][c] -= 1;
			}
		}
	}
	
	static void remove() {
		// 제초제가 뿌려진 칸에는 C년 만큼 제초제가 남아있다가 C + 1년째가 될 때 사라짐
		int removeTreeCnt = -1;
		
		Queue<Integer> spreadPos = new ArrayDeque<Integer>(); // 제초제가 퍼져 나갈 위치
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(map[r][c] == -1) continue; // 벽이면 continue
				
				tmpRemoveTreeCnt = 0; // (r, c)에서 제거할 수 있는 나무 수
				
				Queue<Integer> tmp = getRemoveCnt(r, c);
				
				if(tmpRemoveTreeCnt <= removeTreeCnt) continue;
				removeTreeCnt = tmpRemoveTreeCnt;

				spreadPos = tmp;
			}
		}
		
		totalRemoveTreeCnt += removeTreeCnt;
		
		// 제초제 뿌려주기
		while(!spreadPos.isEmpty()) {
			int pos = spreadPos.poll();
			
			int r = pos / N, c = pos % N;
			if(map[r][c] == -1) continue; // 벽일 경우 넘어가야 됨!!✨
			
			map[r][c] = 0;
			remove[r][c] = (C + 1); // 제초제 뿌리기
		}
	}
	
	static Queue<Integer> getRemoveCnt(int r, int c) {
		Queue<Integer> spreadPos = new ArrayDeque<>();

		// (r, c)에서 대각선 4방으로 K칸 만큼 가기
		Queue<int[]> q = new ArrayDeque<>();
		
		int pos = r * N + c;
		
		// (r, c) 지점에서는 대각선 4방으로 보내기
		for(int dir = 0; dir < 8; dir += 2) {
			q.add(new int[] {pos, 0, dir});
		}
		
		spreadPos.add(pos); // 제초제가 퍼지는 위치
		tmpRemoveTreeCnt += map[r][c];
		
		while(!q.isEmpty()) {
			
			int[] current = q.poll();
			int cr = current[0] / N;
			int cc = current[0] % N;
			int dir = current[2];
			
			if(map[cr][cc] == 0) continue; // 처음에 (r, c)가 빈 공간으로 주어졌을 수도 있기 때문에 이거 처리해줘야 됨
			
			int nr = cr + dr[dir];
			int nc = cc + dc[dir];
			
			// 범위를 벗어나거나, 나무가 아예 없는 칸이거나, 뻗어간 거리가 K가 되었다면 continue
			if(!check(nr, nc) || current[1] == K) continue;
			
			int nPos = nr * N + nc;
			spreadPos.add(nPos);

			// 벽이 있거나, 나무가 아예 없는 칸은 그 칸 까지만 제초제가 뿌려짐
			if(map[nr][nc] <= 0) continue;
			
			q.add(new int[] {nPos, current[1] + 1, dir});
			tmpRemoveTreeCnt += map[nr][nc];
		}
		return spreadPos;
	}
	
	// 번식 - 벽, 다른 나무, 제초제 모두 없는 칸에 번식 진행 (번식이 가능한 칸의 개수만큼 나누어진 그루 수 만큼 번식)
	static void spread() {
		int[][] tmp = new int[N][N];
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(map[r][c] <= 0) continue;
				
				int cnt = 0; // 번식이 가능한 칸의 개수
				
				for(int d = 1; d < 8; d+= 2) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위를 벗어나거나, 벽 & 다른 나무가 있거나, 제초제가 있다면 continue
					if(!check(nr, nc) || map[nr][nc] != 0 || remove[nr][nc] != 0) continue;
					cnt++;
				}
				
				for(int d = 1; d < 8; d+= 2) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위를 벗어나거나, 벽 & 다른 나무가 있거나, 제초제가 있다면 continue
					if(!check(nr, nc) || map[nr][nc] != 0 || remove[nr][nc] != 0) continue;
					tmp[nr][nc] += map[r][c] / cnt;
				}
				
			}
		}
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] += tmp[r][c];
			}
		}
	}
	
	// 성장 - 인접한 네 칸의 칸 중 나무가 있는 수만큼 나무 성장
	static void grow() {
		
		int[][] tmp = new int[N][N]; // 동시에 성장해야 되므로, tmp 배열에 증감에 대한 정보 저장
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				if(map[r][c] <= 0) continue; // 나무가 아니라면 continue
				
				int tree = 0;
				
				// 인접한 네 칸
				for(int d = 1; d < 8; d += 2) {
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위를 벗어나거나, 나무가 아닐 때
					if(!check(nr, nc) || map[nr][nc] <= 0) continue;
					tree += 1;
				}
				
				tmp[r][c] += tree;
			}
		}
		
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] += tmp[r][c];
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/나무박멸.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 박멸이 진행되는 년 수
		K = Integer.parseInt(st.nextToken()); // 제초제의 확산 범위
		C = Integer.parseInt(st.nextToken()); // 제초제가 남아있는 년수
		
		map = new int[N][N]; // 나무
		remove = new int[N][N]; // 제초제
		
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < map[0].length; c++) map[r][c] = Integer.parseInt(st.nextToken());
		}
	}
}
