import java.io.*;
import java.util.*;

/*
 * 고대 문명 유적 탐사
 * step1에서 r과 c의 좌표를 for(int r = 1; r < 3; r++) 이렇게 해서 틀렸음
 */

public class DH_고대_문명_유적_탐사 {
	static final int SIZE = 5, ROTATE_SIZE = 3;
	static int K, M;
	static int[][] map; // 유적지 정보
	static int[] arr; // 조각 정보
	static int result, IDX; // result: 각 턴마다 획득한 유물 가치의 총 합, IDX: 조각 정보의 인덱스
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		
		StringBuilder sb = new StringBuilder();
		
		while(K-- > 0) {
			
			result = 0;

			// 탐사 진행 (1차 유물 획득을 최대로 할 수 있게)
			int[][] currentMap = step1();
			
			// 1차 유물 획득 이후 빈 공간 채워주기
			currentMap = fillMap(currentMap);
			
			// 회전을 안했다(획득한 가치가 0)면 탐사 종료
			if(result == 0) break;

			int value = 0;
			
			// 유물 연쇄 획득
			// 3개 이상 연결된거 찾아주고, 유적지 채워주기
			while((value = getValue(currentMap)) != 0) {
				currentMap = fillMap(currentMap);
				result += value;
			}
			
			// 각 턴마다 currentMap이라는 배열을 사용하고 있으므로 최종적으로 map배열을 currentMap으로 바꿔줌
			map = currentMap;

			sb.append(result).append(" ");
		}
		System.out.println(sb);
	}
	
	static int[][] fillMap(int[][] map) {
		
		for(int c = 0; c < SIZE; c++) {
			for(int r = SIZE - 1; r >= 0; r--) {
				if(map[r][c] != 0) continue;
				
				map[r][c] = arr[IDX];
				IDX = (IDX + 1) % M;
			}
		}
		return map;
	}
	
	static int[][] step1() {
		// (1) 유물 1차 획득 가치를 최대화하고
		// (2) 회전 각도가 가장 작은 방법
		// (3) 열이 가장 작으면서, (4) 행이 가장 작도록
		int maxValue = Integer.MIN_VALUE;
		
		int[][] resultMap = new int[SIZE][SIZE];
		
		for(int t = 0; t < 3; t++) { // (2) 회전한 각도가 가장 작은
			for(int c = 1; c < 4; c++) { // (3) 중심 좌표의 열이 가장 작은
				for(int r = 1; r < 4; r++) { // (4) 행이 가장 작은
					int[][] tmp = rotate(r, c, t);

					int value = getValue(tmp);
					
					if(maxValue < value) { // (1) 유물 1차 획득 가치를 최대화
						maxValue = value;
						resultMap = tmp;
					}
				}
			}
		}
		
		result += maxValue; // 최대 유물 가치 더해주기
		
		return resultMap;
	}
	
	static void print(int[][] arr) {
		System.out.println("map 출력 ----------");
		for(int r = 0; r < arr.length; r++) {
			System.out.println(Arrays.toString(arr[r]));
		}
	}
	
	static int[][] rotate(int r, int c, int t) {
		int[][] rotateMap = new int[SIZE][SIZE];
		
		// 배열 복사
		for(int cr = 0; cr < SIZE; cr++) rotateMap[cr] = Arrays.copyOf(map[cr], SIZE);

		// 회전 기준점을 (1, 1)로 놓고 회전 진행
		for(int tr = -1; tr < 2; tr++) {
			for(int tc = -1; tc < 2; tc++) {
				int cr = r + tr, cc = c + tc;

				int tmp = map[cr][cc];
				
				int sr = r - 1, sc = c - 1;
				
				int tmpR = r + tr - sr, tmpC = c + tc - sc; 
				int nr = 0, nc = 0;
				
				// (r, c)를 기준으로 90도 회전
				if(t == 0) {
					nr = tmpC;
					nc = (ROTATE_SIZE - 1) - tmpR;
				}
				
				// (r, c)를 기준으로 180도 회전
				if(t == 1) {
					nr = ROTATE_SIZE - 1 - tmpR;
					nc = ROTATE_SIZE - 1 - tmpC;
				}
				
				// (r, c)를 기준으로 270도 회전
				if(t == 2) {
					nr = ROTATE_SIZE - 1 - tmpC;
					nc = tmpR;
				}
				
				rotateMap[nr + sr][nc + sc] = tmp;
			}
		}
		
		return rotateMap;
	}

	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	// bfs를 하면서 3개 이상 연속으로 있는 그룹 찾아주기
	static int getValue(int[][] tmp) {
		
		int value = 0;
		boolean[][] v = new boolean[SIZE][SIZE];
		
		for(int r = 0; r < SIZE; r++) {
			for(int c = 0; c < SIZE; c++) {
				if(v[r][c]) continue;

				Queue<Integer> q = new ArrayDeque<Integer>(); // bfs 탐색을 위한 큐
				Queue<Integer> removeQ = new ArrayDeque<Integer>(); // 그룹이 생성된 후, 없앨 위치를 저장하기 위한 큐

				v[r][c] = true;
				
				int currentValue = 1;
				int pos = r * SIZE + c;

				q.add(pos);
				removeQ.add(pos);
				
				while(!q.isEmpty()) {
					int currentPos = q.poll();
					int cr = currentPos / SIZE, cc = currentPos % SIZE;
					
					for(int d = 0; d < 4; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						
						if(!check(nr, nc) || v[nr][nc] || tmp[nr][nc] != tmp[r][c]) continue;
						
						int nextPos = nr * SIZE + nc;
						q.add(nextPos);
						removeQ.add(nextPos);
						v[nr][nc] = true;
						currentValue += 1;
					}
				}
				
				if(currentValue < 3) continue;
				
				value += currentValue;
				
				while(!removeQ.isEmpty()) {
					int removePos = removeQ.poll();
					int rr = removePos / SIZE;
					int rc = removePos % SIZE;
					
					tmp[rr][rc] = 0;
				}
			}
		}
		
		return value;
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < SIZE && c >= 0 && c < SIZE;
	}

	static void initInput() throws Exception {
		
		System.setIn(new FileInputStream("./input/고대문명유적탐사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken()); // 탐사 반복 횟수
		M = Integer.parseInt(st.nextToken()); // 유물 조각의 개수
		
		arr = new int[M];
		
		map = new int[SIZE][SIZE];
		
		// 유적지 정보
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		// 유적 벽면에 써 있는 숫자
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
}
