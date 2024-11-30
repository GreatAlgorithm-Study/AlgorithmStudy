import java.io.*;
import java.util.*;

public class JY_2533 {

	static int N;
	static List<Integer>[] g;
	static int[][] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		g = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			g[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			g[a].add(b);
			g[b].add(a);
		}
		
		// dp[p][0] : 부모가 얼리어답터 X
		// dp[p][1] : 부모가 얼리어답터 O
		dp = new int[N+1][2];
		for(int i=0; i<N+1; i++) {
			dp[i][1] = 1;
		}
		
		visited = new boolean[N+1];
		visited[1] = true;
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
		
	}
	public static void dfs(int now) {
		for(int next: g[now]) {
			if(visited[next]) continue;
			visited[next] = true;
			dfs(next);
			// now가 얼리어답터가 아니라면 자식들이 모두 얼리어답터
			dp[now][0] += dp[next][1];
			// now가 얼리어답터라면 자식들 중 최소 얼리어답터 구하기
			dp[now][1] += Math.min(dp[next][0], dp[next][1]);
		}
	}

}
