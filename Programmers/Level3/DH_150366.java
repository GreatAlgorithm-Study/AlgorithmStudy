import java.util.*;

/*
 * 표 병합
 */

class DH_150366 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static String[][] table;
	static int[][] markGroupNum;
	static HashMap<Integer, HashSet<Point>> hashMap;
	
	static ArrayList<String> solution(String[] commands) {
		hashMap = new HashMap<Integer, HashSet<Point>>();
		ArrayList<String> answer = new ArrayList<>();
		table = new String[51][51];
		markGroupNum = new int[51][51];
		
		for(int r = 1; r < 51; r++) {
			for(int c = 1; c < 51; c++) {
				int key = r * 51 + c;
				hashMap.put(key, new HashSet<>());
				hashMap.get(key).add(new Point(r, c));
				markGroupNum[r][c] = key;
			}
		}
		
		for(String command: commands) {
			String[] input = command.split(" ");
			String order = input[0];
			
			if(order.equals("UPDATE")) {
				if(input.length == 4) {
					int r = getPoint(input, 1);
					int c = getPoint(input, 2);
					String value = input[3];
					update(r, c, value);
				}
				if(input.length == 3) {
					String value1 = input[1];
					String value2 = input[2];
					update(value1, value2);
				}
			} 
//			
			if(order.equals("MERGE")) {
				int r1 = getPoint(input, 1);
				int c1 = getPoint(input, 2);
				int r2 = getPoint(input, 3);
				int c2 = getPoint(input, 4);
				
				merge(r1, c1, r2, c2);
			}
			if(order.equals("UNMERGE")) {
				int r = getPoint(input, 1);
				int c = getPoint(input, 2);
				unmerge(r, c);
			}
			if(order.equals("PRINT")) {
				int r = getPoint(input, 1);
				int c = getPoint(input, 2);
				answer.add(print(r, c));
			}
		}
		
		return answer;
	}
	
	static String print(int r, int c) {
		return table[r][c] == null ? "EMPTY": table[r][c];
	}
	
	static void unmerge(int r, int c) {
		int key = markGroupNum[r][c];
		String value = table[r][c];
		
		hashMap.get(key).clear();
		
		for(int rr = 1; rr < table.length; rr++) {
			for(int cc = 1; cc < table[0].length; cc++) {
				if(markGroupNum[rr][cc] == key) {
					int realKey = rr * 51 + cc;
					table[rr][cc] = null;
					markGroupNum[rr][cc] = realKey;
					hashMap.get(realKey).add(new Point(rr, cc));
				}
			}
		}
		table[r][c] = value;
	}
	
	static void merge(int r1, int c1, int r2, int c2) {
		if(r1 == r2 && c1 == c2) return;
		
		int key = markGroupNum[r1][c1];
		String value = table[r1][c1];
		int removeKey = markGroupNum[r2][c2];
		
		if(key == removeKey) return;
		
		if(value == null && table[r2][c2] != null) {
			key = removeKey;
			removeKey = markGroupNum[r1][c1];
			value = table[r2][c2];
		}
		
		for(Point p: hashMap.get(removeKey)) {
			table[p.r][p.c] = value;
			markGroupNum[p.r][p.c] = key;
		}
		
		hashMap.get(key).addAll(hashMap.get(removeKey));
		hashMap.get(removeKey).clear();
	}
	
	static void update(String value1, String value2) {
		for(int r = 1; r < table.length; r++) {
			for(int c = 1; c < table[0].length; c++) {
				if(table[r][c] != null && table[r][c].equals(value1))
					table[r][c] = value2;
			}
		}
	}
	
	static void update(int r, int c, String value) {
		int key = markGroupNum[r][c];
		for(Point p: hashMap.get(key)) table[p.r][p.c] = value;
	}
	
	static int getPoint(String[] input, int idx) {
		return Integer.parseInt(input[idx]);
	}
	
	public static void main(String[] args) {
		String[] commands = {"UPDATE 1 1 apple", "MERGE 1 1 2 2", "MERGE 2 2 3 3", "UNMERGE 1 1", "UNMERGE 2 2", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3"};
		System.out.println(Arrays.toString(solution(commands).toArray()));
	}
}