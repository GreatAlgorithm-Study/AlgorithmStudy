import java.io.*;
import java.util.*;

/*
 * 양팔저울
 */

public class DH_2629 {
	static final int LIMIT = 15_000; // 추를 통해 알 수 있는 최대 무게
	static int N, M;
	static int[] arr;
	static boolean[][] dp; // [idx][weight]: idx번째 추까지 고려했을 때, weight 무게를 만들 수 있는지
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() throws Exception  {
		StringBuilder sb = new StringBuilder();
		
		dp = new boolean[N + 1][LIMIT + 1];

		getPossibleWeight(0, 0);
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			
			int num = Integer.parseInt(st.nextToken());
			
			// 제한 무게를 넘거나, 모든 구슬을 확인했을 때 num무게를 만들 수 없다면 N출력
			if(num > LIMIT || !dp[N][num]) sb.append("N ");
			// 가능하다면 Y 출력
			else sb.append("Y ");
		}

		System.out.println(sb);
	}
	
	static void getPossibleWeight(int idx, int weight) {
		if(dp[idx][weight]) return;
		dp[idx][weight] = true;
		
		if(idx == N) return;
		
		getPossibleWeight(idx + 1, weight + arr[idx]); // 현재 기준이 되는 저울에 올리기
		getPossibleWeight(idx + 1, weight); // 넘어가기
		getPossibleWeight(idx + 1, Math.abs(weight - arr[idx])); // 반대 저울에 올리기
	}
	
	static void initInput() throws Exception {
		
		N = Integer.parseInt(br.readLine()); // 추의 개수
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
	}
}