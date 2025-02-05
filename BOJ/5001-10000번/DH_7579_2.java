import java.io.*;
import java.util.*;

/*
 * 앱
 */

public class DH_7579_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] weight = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < weight.length; i++) weight[i] = Integer.parseInt(st.nextToken());
		
		int[] cost = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		
		int costSum = 0;
		
		for(int i = 1; i < cost.length; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			costSum += cost[i];
		}
		
		// 1 ≤ M ≤ 10,000,000이기 때문에 바이트에 대해 2차원 배열을 만들면 메모리 초과가 발생함!
		// knapsack[i][j]: i번째 바이트까지 확인했을 때, j비용을 사용한 경우 추가로 확보할 수 있는 메모리 크기
		int[][] knapsack = new int[N + 1][costSum + 1];
		
		int result = 0;
		
		// M바이트를 확보하기 위한 앱 비활성화의 최소 비용 구하기
		for(int i = 1; i < weight.length; i++) {
			for(int c = 0; c < knapsack[0].length; c++) {
				if(cost[i] <= c) 
					knapsack[i][c] = Math.max(knapsack[i - 1][c - cost[i]] + weight[i], knapsack[i - 1][c]);
				else knapsack[i][c] = knapsack[i - 1][c];

				if(knapsack[i][c] >= M) {
					result = c;
					break;
				}
			}
		}
		
		System.out.println(result);
	}
}