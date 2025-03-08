import java.io.*;
import java.util.*;
public class JY_12784 {

	static int N, M;
	static List<Node>[] g;
	static boolean[] visited;
	static class Node {
		int num, cost;
		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", cost=" + cost + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			g = new ArrayList[N+1];
			for(int i=1; i<N+1; i++) {
				g[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				g[a].add(new Node(b, c));
				g[b].add(new Node(a, c));
			}
			
			visited = new boolean[N+1];
			visited[1] = true;
			int ans = dfs(1);
			sb.append(ans+"\n");
		}
		
		System.out.println(sb.toString());
	}
	public static int dfs(int now) {
		visited[now] = true;
		
		// 리프노드라면 자신의 비용 반환
		if(now !=1 && g[now].size() == 1) return g[now].get(0).cost;
		
		// 리프노드 아님 => 자식 순회
		int minCost = 0;
		for(Node next: g[now]) {
			if(visited[next.num]) continue;
			minCost += Math.min(next.cost, dfs(next.num));
		}
		
		return minCost;
	}

}