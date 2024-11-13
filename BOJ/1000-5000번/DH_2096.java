import java.io.*;
import java.util.*;

/*
 * 내려가기
 */

public class DH_2096 {
	static int[][] map, max, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		map = new int[N + 1][3];
		max = new int[N + 1][3]; // 각 줄마다 최댓값을 저장할 변수
		min = new int[N + 1][3]; // 각 줄마다 최솟값을 저장할 변수
		
		// 최솟값을 저장하는 변수는 무한으로 초기화
		for(int r = 1; r < min.length; r++) Arrays.fill(min[r], Integer.MAX_VALUE >> 2);
		for(int r = 1; r < map.length; r++) {
			st= new StringTokenizer(br.readLine());
			for(int c = 0; c < 3; c++) map[r][c] = Integer.parseInt(st.nextToken());
			
			for(int pc = 0; pc < 3; pc++) {
				for(int dc = -1; dc < 2; dc++) { 
					int nc = pc + dc;
					if(nc < 0 || nc >= 3) continue;
					
					max[r][nc] = Math.max(max[r - 1][pc] + map[r][nc], max[r][nc]);
					min[r][nc] = Math.min(min[r - 1][pc] + map[r][nc], min[r][nc]);
				}
			}
		}

		int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
		for(int c = 0; c < 3; c++) {
			maxValue = Math.max(maxValue, max[N][c]);
			minValue = Math.min(minValue, min[N][c]);
		}
		
		System.out.println(maxValue + " " + minValue);
	}
}