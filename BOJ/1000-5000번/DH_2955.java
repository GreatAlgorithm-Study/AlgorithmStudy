import java.io.*;
import java.util.*;

/*
 * 스도쿠 풀기
 */

public class DH_2955 {
	static boolean error; // 스도쿠에 모순이 일어나는지 확인하는 변수
	static char[][] map;
	static boolean[][][] v; // 1부터 9까지 위치할 수 있는 곳을 표시하는 변수
	static HashSet<Integer>[] set; // 스코쿠 판을 3 X 3으로 나누었을 때, 1 ~ 9번째 칸에서 어떤 수가 있는지 저장하는 변수
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(solution() ? printMap(): "ERROR");
	}

	static boolean solution() {
		if(error) return false;
		L: while(true) {
			int putCnt = 0;
			
			// 1부터 9까지 차례로 입력
			for(int k = 1; k < 10; k++) {
				// map에서 3 X 3크기의 배열씩 확인함
				for(int r = 0; r < 7; r += 3) {
					for(int c = 0; c < 7; c += 3) {
						// 3 X 3 배열에 k가 있으면 continue;
						int idx = (r / 3) * 3 + c / 3;
						if(set[idx].contains(k)) continue;
						
						// 3 X 3 배열에 k가 없으면 있을 수 있는 지점 개수 세기
						int canExistPoint = 0; // 있을 수 있는 지점 개수
						int pr = 0, pc = 0;
						for(int sr = r; sr < r + 3; sr++) {
							for(int sc = c; sc < c + 3; sc++) {
								if(!v[k][sr][sc] && map[sr][sc] == '.') {
									canExistPoint += 1;
									pr = sr;
									pc = sc;
								}
							}
						}
						
						// 있을 수 있는 곳이 없으면 잘못된 스도쿠
						if(canExistPoint == 0) {
							error = true;
							break L;
						}
						
						// 있을 수 있는 곳이 한 곳이라면
						// 숫자 입력하기
						if(canExistPoint == 1) {
							map[pr][pc] = (char) (k + '0');
							// 숫자 입력하고 가로, 세로 표시 남기기
							markCross(pr, pc, k);
							// 구역에 해당 숫자 저장해주기
							set[idx].add(k);
							// map에 입력한 숫자 개수 + 1
							putCnt += 1;
						}
					}
				}
			}

			// map에 입력한 숫자가 없다면 더이상 스도쿠 탐색 끝!
			if(putCnt == 0) break;
			
		}
		return !error;
	}
	
	static String printMap() {
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[0].length; c++) sb.append(map[r][c]);
			sb.append("\n");
		}
		
		return sb.toString();
	}

	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/BOJ2955.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		v = new boolean[10][9][9];
		map = new char[9][9];
		set = new HashSet[9];
		
		for(int i = 0; i < 9; i++) set[i] = new HashSet<Integer>();
		for(int r = 0; r < map.length; r++) {
			String s = br.readLine();
			for(int c = 0; c < map[0].length; c++) {
				map[r][c] = s.charAt(c);
				
				if(map[r][c] == '.') continue;
				int num = map[r][c] - '0';
				
				// map을 입력받으면서 숫자가 입력받은 지점 기준 가로, 세로 표시 남기기
				markCross(r, c, num);
				
				// map을 3 X 3으로 나누었을 때, 해당 칸에 어떤 수가 저장되어 있는지 저장
				// 숫자를 입력 받았는데, 해당 구역에 이미 그 숫자가 있으면 모순이 일어나는 스도쿠
				if(!putNumSet(r, c, num)) error = true;
			}
		}
	}
	
	static boolean putNumSet(int r, int c, int num) {
		int idx = (r / 3) * 3 + c / 3;
		if(set[idx].contains(num)) return false;
		set[idx].add(num);
		return true;
	}
	
	static void markCross(int r, int c, int num) {
		for(int rr = 0; rr < 9; rr++) v[num][rr][c] = true;
		for(int cc = 0; cc < 9; cc++) v[num][r][cc] = true;
	}
}
