import java.io.*;
import java.util.*;

/*
 * 정점들의 거리
 */

public class DH_1761 {
	static class Node {
		int e, w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static ArrayList<Node> adj[];
	static int[] depth, p, dis;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() throws Exception {
		bfs();
		getDis();
	}
	
	static void getDis() throws Exception {
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// a, b의 최소공통조상 구하고, 두 점 간 사이 구하기
			sb.append(LCA(a, b)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static long LCA(int a, int b) {
		// result = (루트 ~ a) + (루트 ~ b) - 2 * (루트 ~ 최소공통조상)
		long result = dis[a] + dis[b];
		
		int depthA = depth[a];
		int depthB = depth[b];
		
		// 무조건 a가 depth가 작도록
		if(depthB < depthA) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		int diffDis = Math.abs(depthA - depthB);

		// 깊이 맞추기
		for(int i = 0; i < diffDis; i++) b = p[b];
		
		while(a != b) {
			a = p[a];
			b = p[b];
		}
		
		return result - 2 * dis[a];
	}
	
	static void bfs() {
		boolean[] v = new boolean[N + 1];
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(1, 0));
		v[1] = true;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			for(Node next: adj[current.e]) {
				if(v[next.e]) continue;
				p[next.e] = current.e;
				depth[next.e] = depth[current.e] + 1;
				dis[next.e] = dis[current.e] + next.w;
				v[next.e] = true;
				q.add(next);
			}
		}
		
	}

	static void initInput() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N + 1];
		depth = new int[N + 1];
		dis = new int[N + 1];
		p = new int[N + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[s].add(new Node(e, w));
			adj[e].add(new Node(s, w));
		}
	}
}
