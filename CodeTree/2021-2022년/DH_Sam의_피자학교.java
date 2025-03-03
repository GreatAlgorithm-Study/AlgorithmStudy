import java.io.*;
import java.util.*;

public class DH_Sam의_피자학교 {
	static final int INF = Integer.MAX_VALUE;
	static int N, K, MAX, MIN;
	static int[][] roll;
	static int[] dr = {0, -1, 0, 1}, dc = {-1, 0, 1, 0}; // 좌, 상, 우, 하
	static int[] arr, left;
	
	public static void main(String[] args) throws Exception {
		initInput();
		
		int turn = 0;
		
		while(true) {
			getMinAndMaxValue();
			
			if(MAX - MIN <= K) break;
			
			step1(); // 최솟값에 1 더해주기
			step2(); // 도우 말아주기
			step3(true); // 도우 꾹 눌러주기
			
			fold(); // 도우 접기
			
			step3(false); // 도우 다시 접기 (roll만 남아 있음, left는 없어짐)

			turn++;
		}
		
		System.out.println(turn);
	}
	
	static void fold() {		
		int[][] tmp = new int[2][arr.length / 2];
		
		// 첫 번째 접기 --------------------------------
		for(int i = 0; i < arr.length / 2; i++) {
			tmp[0][i] = arr[arr.length / 2 - 1 - i];
		}

		for(int i = 0; i < arr.length / 2; i++) {
			tmp[1][i] = arr[arr.length / 2 + i];
		}
		
		roll = new int[4][tmp[0].length / 2];
		
		for(int r = 0; r < 2; r++) {
			for(int c = 0; c < tmp[0].length / 2; c++) {
				roll[r][c] = tmp[1 - r][tmp[0].length / 2 - 1 - c];
			}
		}
		
		for(int r = 0; r < 2; r++) {
			for(int c = 0; c < tmp[0].length / 2; c++) {
				roll[r + 2][c] = tmp[r][tmp[0].length / 2 + c];
			}
		}
	}
	
	// step3: 도우 꾹 눌러주기 단계
	// 상, 하, 좌, 우를 비교해야 되는데 4방을 모두 비교한다면 중복이 발생하기 때문에 우, 하 (2가지 방향)만 확인함
	// existLeft: 도우를 말아주고, 남은 도우가 있는지 여부
	static void step3(boolean existLeft) {
		
		// check 배열: 변화량을 저장
		int[][] rollCheck = new int[roll.length][roll[0].length];
		int[] leftCheck = null;
		
		if(existLeft) leftCheck = new int[left.length];

		// 상, 하, 좌, 우를 비교하면서 업데이트 할 값 계산하기 =============================
		// (1) roll 배열과 left 배열 탐색
		// roll 배열 확인
		for(int r = 0; r < roll.length; r++) {
			for(int c = 0; c < roll[0].length; c++) {
				for(int dir = 2; dir < 4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					
					if(!check(nr, nc)) continue;
					int d = Math.abs(roll[r][c] - roll[nr][nc]) / 5;

					if(roll[r][c] > roll[nr][nc]) {
						rollCheck[r][c] -= d;
						rollCheck[nr][nc] += d;
					}  else {
						rollCheck[r][c] += d;
						rollCheck[nr][nc] -= d;
					}
				}
			}
		}
		
		// left배열 확인
		if(existLeft) {
			for(int i = left.length - 1; i >= 0; i--) {
				int prevValue = 0;
				
				if(i == 0) prevValue = roll[roll.length - 1][roll[0].length - 1];
				else prevValue = left[i - 1];
				
				int d = Math.abs(left[i] - prevValue) / 5;
				
				if(left[i] > prevValue) {
					leftCheck[i] -= d;
					
					if(i == 0) rollCheck[roll.length - 1][roll[0].length - 1] += d;
					else leftCheck[i - 1] += d;
				} else {
					leftCheck[i] += d;
					
					if(i == 0) rollCheck[roll.length - 1][roll[0].length - 1] -= d;
					else leftCheck[i - 1] -= d;
				}
			}
		}
		
		// (2) check 배열 더하기 --------------------------------------
		for(int r = 0; r < roll.length; r++) {
			for(int c = 0; c < roll[0].length; c++) {
				roll[r][c] += rollCheck[r][c];
			}
		}
		
		if(existLeft) {
			for(int i = 0; i < left.length; i++) {
				left[i] += leftCheck[i];
			}
		}
		
		// 다시 도우 피기 ===============================================
		// roll, left에 있는 정보 arr에 담기
		int idx = 0;
		
		for(int c = 0; c < roll[0].length; c++) {
			for(int r = roll.length - 1; r >= 0; r--) {
				arr[idx++] = roll[r][c];
			}
		}
		
		if(existLeft) {
			for(int i = 0; i < left.length; i++) {
				arr[idx++] = left[i];
			}
		}
	}
	
	// 도우 말기
	static void step2() {
		// 도우를 통해 몇 단계까지 말아줄 수 있는지 확인
		int depth = getPossibleDepth(arr.length);
		
		// depth 번쨰 단계일 때 말아준 도우의 열, 행의 크기
		int row = 0, column = 0;
		
		if(depth % 2 != 0) {
			row = depth / 2 + 1;
			column = depth / 2 + 1;
		} else {
			row = depth / 2 + 1;
			column = depth / 2;
		}
		
		roll = new int[row][column]; // 돌돌 만 도우에 대한 정보
		left = new int[arr.length - row * column]; // 돌돌 만 도우 이외에 남은 도우
		
		int idx = arr.length - 1;
		
		// left 먼저 채워주기
		for(int i = left.length - 1; i >= 0; i--) {
			left[i] = arr[idx--];
		}
		
		// roll 채워주기
		int r = row - 1, c = column;

		int dir = 0;
		
		while(idx >= 0) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			roll[nr][nc] = arr[idx--];

			r = nr;
			c = nc;

			nr = r + dr[dir];
			nc = c + dc[dir];
			
			// 범위를 벗어나거나, 이미 숫자를 채웠다면 방향 바꾸기
			if(!check(nr, nc) || roll[nr][nc] != 0) {
				dir = (dir + 1) % 4;
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < roll.length && c >= 0 && c < roll[0].length;
	}
	
	// 도우를 통해 몇 단계까지 말아줄 수 있는지 확인
	static int getPossibleDepth(int cnt) {
		int depth = 0;
		
		while(true) {
			depth++;
			
			int tmp = (depth / 2 + 1) * (depth / 2 + depth % 2);
			if(tmp > cnt) {
				depth -= 1;
				break;
			}
		}
		
		return depth;
	}

	// 최솟값에 1 더해주기
	static void step1() {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != MIN) continue;
			arr[i] += 1;
		}
	}
	
	static void getMinAndMaxValue() {
		MIN = INF;
		MAX = -INF;
		
		for(int a: arr) {
			MIN = Math.min(MIN, a);
			MAX = Math.max(MAX, a);
		}
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
}

