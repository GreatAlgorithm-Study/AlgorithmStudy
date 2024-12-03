import java.io.*;
import java.util.*;

public class JY_1716 {
	
	static int N, M;
	static int[] d, parent, distance;
	static boolean[] c;
	static List<Node>[] g;
	static class Node {
		int n, dist;

		public Node(int n, int dist) {
			super();
			this.n = n;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Node [n=" + n + ", dist=" + dist + "]";
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		// 부모 노드 정보 배열
		parent = new int[N+1];
		
		// 깊이 정보 배열
		d = new int[N+1];
		
		// 깊이 계산 여부 배열
		c = new boolean[N+1];
		
		// 루트노드로 부터의 거리
		distance = new int[N+1];
		
		g = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			g[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			g[a].add(new Node(b, dist));
			g[b].add(new Node(a, dist));
		}
		
		// 깊이 구하기
		dfs(1, 0, 0);
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int p = lca(n1, n2);
			
			int ans = distance[n1]+distance[n2] - 2*distance[p];
			
			sb.append(ans+"\n");
		}
		
		System.out.println(sb.toString());

	}
	public static void dfs(int x, int depth, int dist) {
		c[x] = true;
		d[x] = depth;
		distance[x] = dist;
		
		for(Node next: g[x]) {
			if(c[next.n]) continue;
			
			parent[next.n] = x;
			dfs(next.n, depth+1, dist+next.dist);
		}
	}
	public static int lca(int a, int b) {
		while(d[a] != d[b]) {
			if(d[a] > d[b]) {
				a = parent[a];
			} else {
				b = parent[b];
			}
		}
		
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
	}

}
