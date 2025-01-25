import java.io.*;
import java.util.*;

/*
 * 미친 로봇
 */

public class DH_1405 {
	static int N;
	static int[] arr;
	static double result;
	static boolean[][] visit;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./input/BOJ1405.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[4];
		
		for(int i = 0; i < 4; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		// 방문배열 visit
		// 상, 하, 좌, 우에 대해 최대 14칸씩 갈 수 있으므로 최대 크기를 29로 설정함
		visit = new boolean[29][29];
		visit[14][14] = true;

		func(0, 14, 14, 1);
		System.out.println(result);
	}
	
	
	static void func(int depth, int r, int c, double percent) {
		
		// 끝까지 갔다면 현재 확률을 전체 결과에 더해주기
  		if(depth == N) {
  			result += percent;
			return;
		}
  		
  		
		// 0: 동, 1: 서, 2: 남, 3: 북
		for(int i = 0; i < 4; i++) {
			
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 이미 방문했던 곳이거나 갈 수 없는 방향이라면
			if(visit[nr][nc] || arr[i] == 0) continue;
			visit[nr][nc] = true;
			
			// 끝 지점까지 가기 위한 확률은 해당 지점까지 가기 위해 이동하는 경로의 확률들으 곱해준 값
			func(depth + 1, r + dr[i], c + dc[i], percent * arr[i] / 100.0);
			
			visit[nr][nc] = false;
		}
	}
}
