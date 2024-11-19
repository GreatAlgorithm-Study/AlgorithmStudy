import java.io.*;
import java.util.*;

/*
 * 역사
 */

public class DH_1613 {
	static final int INF = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
		getOrder();
	}
	
	static void getOrder() throws Exception {
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			// s지점에서 e지점으로 가거나, e지점에서 s지점으로 가는 길이 둘 다 없는 경우는 선후 관계를 알 수 없음
			if(arr[s][e] == INF && arr[e][s] == INF) sb.append(0);
			else if(arr[s][e] != Integer.MAX_VALUE) sb.append(-1); // s에서 e지점으로 갈 수 있다면 s가 더 빠른 사건
			else sb.append(1); // 반대의 경우라면 e가 더 빠른 사건
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 플로이도워셜을 통해 각 지점간 거리 구하기
	static void solution() {
		for(int k = 1; k < arr.length; k++) {
			for(int s = 1; s < arr.length; s++) {
				if(s == k || arr[s][k] == INF) continue;
				for(int e = 1; e < arr.length; e++) {
					if(e == k || e == s || arr[k][e] == INF) continue;
					
					if(arr[s][e] > arr[s][k] + arr[k][e]) 
						arr[s][e] = arr[s][k] + arr[k][e];
				}
			}
		}
	}
	
	static void initInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		arr = new int[n + 1][n + 1];
		
		// 초기 지점 간 거리 무한대로 초기화해주기
		for(int r = 0; r < arr.length; r++) Arrays.fill(arr[r], INF);

		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			arr[s][e] = 1;
		}
	}
}
