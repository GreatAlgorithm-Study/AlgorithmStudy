import java.util.*;
import java.io.*;

public class JY_1647_2 {
	static int N, M;
	static List<Node>[] g;
	static class Node implements Comparable<Node> {
		int v;
		int cost;
		public Node(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}
		public int compareTo(Node other) {
			return this.cost - other.cost;
		}
		@Override
		public String toString() {
			return "Node [v=" + v + ", cost=" + cost + "]";
		}
		
		
	}
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			g[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			g[n1].add(new Node(n2, cost));
			g[n2].add(new Node(n1, cost));
		}
		
		ans = 0;
		prim(1);
		
		System.out.println(ans);
		
	}
	public static void prim(int start) {
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(start, 0));
		
		int maxCost = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
//			System.out.println("Now: "+now);
			
			if(visited[now.v]) continue;
			
			visited[now.v] = true;
			ans += now.cost;
			maxCost = Math.max(maxCost,now.cost);
			
			for(Node next : g[now.v]) {
				if(!visited[next.v]) {
					pq.add(new Node(next.v, next.cost));
				}
			}
		}
		
		// 값이 가장 큰 도로 삭제
		ans -= maxCost;
	}

}
