import java.io.*;
import java.util.*;

/*
 * 외판원 순회
 * dp 배열을 -1로 초기화해야되는 이유
 * 한 지점에서 다른 지점들을 갈 수 없을 때, INF를 return 하게 되는데
 * 이 때, 방문을 한 후 INF인지 방문을 하지 못하는 경우인지 알 수 없게 되어 시간 초과가 발생함
 */

public class DH_2098 {
	static int N;
	static int[][] W, dp;
	
	static final int INF = Integer.MAX_VALUE >> 1;
	
	static int TSP(int now, int visitedCities) {
		if(visitedCities == (1 << N) - 1) {
			// 지금 노드에서 시작점(0)까지 갈 수 있는 경로가 없다면 INF 반환
			// 갈 수 있는 경로가 있다면 지금 노드에서 0까지 가는 값 반환
			return W[now][0] != 0 ? W[now][0]: INF;
		}
		
		if(dp[now][visitedCities] != -1) return dp[now][visitedCities];
		dp[now][visitedCities] = INF;
		
		for(int next = 0; next < N; next++) {
			int city = 1 << next;
			// (visitedCites & city) != 0: 이미 간 곳 
			// W[now][next] == 0: 지금 위치에서 탐색하고자 하는 지점까지 갈 수 있는 길이가 없는 경우
			if((visitedCities & city) != 0 || W[now][next] == 0) continue; 
			dp[now][visitedCities] = Math.min(dp[now][visitedCities], TSP(next, city | visitedCities) + W[now][next]);
		}
		
		return dp[now][visitedCities];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		dp = new int[N][1 << N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) W[r][c] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[r], -1);
		}
		
		System.out.println(TSP(0, 1 << 0));
	}
}
