import java.io.*;
import java.util.*;

/*
 * 별자리 만들기
 */

public class DH_4386_2 {
	static class Point {
		double r, c;
		public Point(double r, double c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Node implements Comparable<Node> {
		int e;
		double w;
		
		public Node(int e, double w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.w, o.w);
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int s, e;
		double w;
		public Edge(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static Point[] points;
	
	public static void main(String[] args) throws Exception {
//		kruskalSolution();
		primSolution();
	}
	
	/*
	 * 프림 알고리즘: 정점 기준
	 */
	static void primSolution() throws Exception {
		
		initInput();
		
		boolean[] v = new boolean[n];
		ArrayList<Node> adj[] = new ArrayList[n]; // 정점을 기준으로 구현하기 때문에 인접행렬 사용
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++){
				if(i == j) continue;
				
				double dis = getDis(points[i], points[j]);
				adj[i].add(new Node(j, dis));
				adj[j].add(new Node(i, dis));
			}
		}
		
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(0, 0)); // 시작 정점: 0번 인덱스
		double result = 0;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			if(v[current.e])  continue;
			v[current.e] = true;
			result += current.w;
			
			for(Node next: adj[current.e]) {
				q.add(next);
			}
		}
		System.out.println(((int) (result * 100)) * (1.0) / 100);
	}
	
	/*
	 * 크루스칼 알고리즘: 간선 기준
	 */
	static void kruskalSolution() throws Exception {
		
		initInput();
		
		int[] p = new int[n];
		for(int i = 0; i < n; i++) p[i] = i;

		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		
		for(int i = 0; i < points.length; i++) {
			for(int j = 0; j < points.length; j++) {
				if(i == j) continue;
				
				double dis = getDis(points[i], points[j]);
				q.add(new Edge(i, j, dis));
			}
		}

		int cnt = 0;
		double result = 0;
		
		while(cnt < n - 1) {
			Edge current = q.poll();
			
			if(find(p, current.s) == find(p, current.e)) continue;
			union(p, current.s, current.e);
			result += current.w;
			cnt += 1;
		}
		
		System.out.println(((int) (result * 100)) * (1.0) / 100);
	}
	
	// 크루스칼을 위한 union
	static void union(int[] p, int a, int b) {
		a = find(p, a);
		b = find(p, b);
		
		if(a != b) p[b] = a;
	}
	
	// 크루스칼을 위한 find
	static int find(int[] p, int a) {
		return p[a] = a == p[a] ? a: find(p, p[a]); // 경로 압축
	}
	
	static double getDis(Point p1, Point p2) {
		double diffr = p1.r - p2.r;
		double diffc = p1.c - p2.c;
		
		return Math.sqrt(diffr * diffr + diffc * diffc);
	}
	
	static void initInput() throws Exception {
		n = Integer.parseInt(br.readLine());
		points = new Point[n];
		
		
		for(int i = 0; i < points.length; i++) {
			st = new StringTokenizer(br.readLine());
			
			double s = Double.parseDouble(st.nextToken());
			double e = Double.parseDouble(st.nextToken());
			
			points[i] = new Point(s, e);
		}
	}
}
