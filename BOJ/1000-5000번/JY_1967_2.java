import java.io.*;
import java.util.*;

public class JY_1967_2 {
	
	static int N;
	static List<int[]>[] g;
	static boolean[] visited;
	static int last;
	static int mLen;

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
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			g[p].add(new int[] {c, v});
			g[c].add(new int[] {p, v});
		}
		
		// 1) 아무 노드에서 가장 긴 노드 찾기
		visited = new boolean[N+1];
		mLen = Integer.MIN_VALUE;
		last = 0;
		dfs(1, 0);
		
		// 2) 가장 멀었던 노드에서 다시 가장 긴 길이 == 트리의 지름
		visited = new boolean[N+1];
		mLen = Integer.MIN_VALUE;
		dfs(last, 0);
		
		System.out.println(mLen);
		
		

	}
	public static void dfs(int now, int cost) {
		visited[now] = true;
		if(cost > mLen) {
//			System.out.println(">>now: "+now+", cost:"+cost);
			last = now;
			mLen = cost;
		}
		
		for(int[] arr: g[now]) {
			if(visited[arr[0]]) continue;
			dfs(arr[0], cost+arr[1]);
		}
	}

}
