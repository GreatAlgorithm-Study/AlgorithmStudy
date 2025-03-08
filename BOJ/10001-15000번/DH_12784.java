import java.io.*;
import java.util.*;

/*
 * 인하니카 공하국
 */

public class DH_12784 {
	static final int INF = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static ArrayList<int[]>[] adj;
	static int N, M;
	static boolean[] v;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			setAdj(); // 인접리스트 생성
			
			v = new boolean[N + 1];
			dp = new int[N + 1];
			Arrays.fill(dp, INF);

			// 간선의 개수가 0일 때 처리해주기
			sb.append(M == 0 ? 0 : dfs(1)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int dfs(int current) {
		v[current] = true;
		
		for(int[] next: adj[current]) {
			if(v[next[0]]) continue;
			
			// 다음 노드의 다이너마이트 최소 개수 구하기
			int nextValue = dfs(next[0]);

			// 첫 번째 자식을 확인했다면
			// nextValue(next노드까지 다이너마이트 몇 개 폭파햐야되는지)와 next[1] (간선의 가중치) 중 작은걸로 선택
			if(dp[current] == INF) dp[current] = Math.min(nextValue, next[1]);
			
			// 두번째 자식부터는 값 더해주기
			else dp[current] += Math.min(nextValue, next[1]);
		}
		
		return dp[current];
	}
	
	static void setAdj() throws Exception {
		adj = new ArrayList[N + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[a].add(new int[] {b, w});
			adj[b].add(new int[] {a, w});
		}
	}
}
