package day1106;

import java.io.*;
import java.util.*;

public class JY_2908 {

	static int INF = 15_000_000;
	static int FullBit;
	static int N;
	static int[][] w;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		w = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 노드를 방문한 비트마스킹 값
		FullBit = (1 << N);
		
		dp = new int[N][FullBit];
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		// 0번째 노드부터 시작
		System.out.println(dfs(0, 1));
	}
	public static int dfs(int now, int visited) {
		// 모든 정점 방문
		if(visited == FullBit-1) {
			if(w[now][0] == 0) return INF;
			return w[now][0];
		}
		
		// 이미 방문한 노드
		if(dp[now][visited] != -1) return dp[now][visited];
		
		// 아직 방문 X
		dp[now][visited] = INF;
		
		for(int i=0; i<N; i++) {
			// i로 가는 것 방문처리
			int next = visited | (1 << i);
			
			// i로 가는 경로가 없거나, 이미 방문했다면 continue
			if(w[now][i] == 0) continue;
			if((visited & (1 << i)) != 0) continue;
			
			dp[now][visited] = Math.min(dp[now][visited], dfs(i, next) + w[now][i]);
		}
		
		return dp[now][visited];
	}

}
