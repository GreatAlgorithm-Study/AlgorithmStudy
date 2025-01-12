import java.io.*;
import java.util.*;

/*
 * 모노미노도미노 2
 * 오답이었던 이유
 * 	1. 연한 곳 블럭 내리고 안 없앰
 *  2. static과 local과 Arrays.copy...
 *     - checkColorBoardRow 부분에서 처음엔 마지막에 map = tmp로 했었음
 *        * 근데 값이 안변함 
 *     	  * 원인: 전역변수를 직접적으로 바꾸어 주지 않고, 전역변수의 메서드 로컬 변수로 전달했기 때문
 *     			 메서드 로컬 변수는 메서드 내부에서만 존재하고 함수가 끝나면 사라지는 존재임!
 *     - tmp[r] = Arrays.copyOf(tmp[r], COLUMN)으로 해결
 */

public class DH_20061 {
	
	static final int ROW = 6, COLUMN = 4;
	
	static class Point {
		int r, c;
		public Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void rotate() {
			int tmp = r;
			this.r = c;
			this.c = COLUMN - 1 - tmp;
		}
	}
	static int score;
	static boolean[][] green, blue;
	
	public static void main(String[] args) throws Exception {
		
		green = new boolean[ROW][COLUMN]; // 초록색 보드
		blue = new boolean[ROW][COLUMN]; // 파란색 보드

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < N; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 빨간색 보드에 (r, c)를 놓았을 때,
			// t와 보드의 색을 고려하여 추가되는 보드의 좌표가 달라져야 함
			ArrayList<Point> greenPoints = new ArrayList<>();
			ArrayList<Point> bluePoints = new ArrayList<>();
			
			
			Point greenPoint = new Point(0, c);
			Point bluePoint = new Point(r, c);

			bluePoint.rotate();
			bluePoint.r = 0;
		
			greenPoints.add(greenPoint);
			bluePoints.add(bluePoint);
		
			// t가 2일 때
			// 초록색 보드에는 1 × 2 크기의 블럭
			// 파란색 보드에는 2 x 1 크기의 블럭 
			if(t == 2) {
				greenPoints.add(new Point(0, c + 1));
				bluePoints.add(new Point(bluePoint.r + 1, bluePoint.c));
			}
			
			// t가 3일 때
			// 초록색 보드에는 2 × 1 크기의 블럭
			// 파란색 보드에는 1 x 2 크기의 블럭
			if(t == 3) {
				greenPoints.add(new Point(greenPoint.r + 1, greenPoint.c));
				bluePoints.add(new Point(0, bluePoint.c - 1));
			}
			
			putBlock(green, greenPoints); // 빨간색 보드에 있는 블럭을 초록색 보드에 옮겨주기
			checkColorBoardRow(green); // 초록색 보드의 행을 보면서 꽉 찬 행이 있는지 확인
			checkBeforeSecondRow(green);

			putBlock(blue, bluePoints);
			checkColorBoardRow(blue);
			checkBeforeSecondRow(blue);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(score).append("\n").append(countBlockCnt());
		System.out.println(sb);
	}
	
	// 보드에서 블럭들의 개수 세기
	static int countBlockCnt() {
		int count = 0;
		
		for(int r = 0; r < ROW; r++) {
			for(int c = 0; c < COLUMN; c++) {
				if(blue[r][c]) count += 1;
				if(green[r][c]) count += 1;
			}
		}
		
		return count;
	}
	
	// 빨간색 보드에 있는 블럭 (초록/파란)색 보드에 넣기
	static void putBlock(boolean[][] map, ArrayList<Point> points) {
		
		int startRow = 0;
		
		// 행의 기준을 0부터 시작하면서 블럭을 어디에서 시작 행을 어디서부터 할 수 있는지 확인하기
		L: for(int r = 0; r < ROW; r++) {
			
			// 블럭에 속하는 모든 좌표들이 들어갈 수 있어야 됨
			for(Point p: points) {
				int currentR = r + p.r;
				if(currentR >= ROW || map[currentR][p.c]) break L;
			}
			startRow = r;
		}
		
		// 보드에 블럭들 넣어주기
		for(Point p: points) map[(startRow + p.r)][p.c] = true;
	}
	
	// 꽉 찬 행을 찾고 블럭들을 없애줌
	static void checkColorBoardRow(boolean[][] map) {
		// 타일이 꽉 찬 행과 열의 idx를 저장하는 set
		Set<Integer> set = new HashSet<Integer>();

		// 행이 모두 꽉 차있다면 해당 행을 set에 저장
		checkIsFillFullRow(set, map);
		
		// 점수는 set의 size
		score += set.size();
		
		// set에 있는 행들을 비우기
		if(!set.isEmpty()) removeBlocks(set, map);
	}
	
	// 0~2행에 블럭이 있는지 확인하고, 하단에 있는 블럭 없애주기
	static void checkBeforeSecondRow(boolean[][] map) {
		int checkRowNumber = -1;
		
		L: for(int r = 0; r < 2; r++) {
			
			for(int c = 0; c < COLUMN; c++) {
				if(map[r][c]) {
					checkRowNumber = r;
					break L;
				}
			}
		}
		
		if(checkRowNumber == -1) return;

		// checkRowNumber가 0이면 아래에서 두 칸 없애야 됨
		// checkRownumber가 1이면 아래에서 한 칸 없애야 됨
		int diff = checkRowNumber == 0 ? 2: 1;
		
		for(int r = ROW - 1; r >= 0; r--) {
			
			Arrays.fill(map[r], false);
			for(int c = 0; c < COLUMN; c++) {
				if(r - diff >= 0) map[r][c] = map[r - diff][c];
				else map[r][c] = false;
			}
		}
	}
	
	// 꽉찬 행의 블럭들 없애기
	static void removeBlocks(Set<Integer> set, boolean[][] map) {

		for(int idx: set) {
			for(int c = 0; c < COLUMN; c++) {
				map[idx][c] = false;
			}
		}
		
		boolean[][] tmp = new boolean[ROW][COLUMN];
		
		// 없어진 곳 기준 위에 내리기
		int currentIdx = ROW - 1;
		
		for(int r = ROW - 1; r >= 0; r--) {
			
			boolean isEmpty = true;
			for(int c = 0; c < COLUMN; c++) {
				if(map[r][c]) {
					isEmpty = false;
					break;
				}
			}
			
			if(isEmpty) continue;
			
			tmp[currentIdx] = Arrays.copyOf(map[r], map[r].length);
			currentIdx -= 1;
		}
		
		for(int r = 0; r < ROW; r++) map[r] = Arrays.copyOf(tmp[r], COLUMN);
	}
	
	// 행이 보드로 꽉 찼는지 확인
	static void checkIsFillFullRow(Set<Integer> set, boolean[][] map) {
		for(int r = 0; r < ROW; r++) {
			boolean isFillFull = true;
			
			for(int c = 0; c < COLUMN; c++) {
				if(!map[r][c]) {
					isFillFull = false;
					break;
				}
			}
			
			if(!isFillFull) continue;
			set.add(r);
		}
	}
}