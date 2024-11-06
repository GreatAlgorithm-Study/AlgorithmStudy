import java.util.*;
import java.io.*;

public class JY_1647 {

	static int N, M;
	static int[] parents;
	static List<Edge> eList;
	static class Edge implements Comparable<Edge>{
		int n1, n2, cost;
		public Edge(int n1, int n2, int cost) {
			this.n1 = n1;
			this.n2 = n2;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge other) {
			return this.cost - other.cost;
		}
		@Override
		public String toString() {
			return "("+this.n1+","+this.n2+"):"+this.cost; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		eList = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			eList.add(new Edge(n1, n2, cost));
		}
		// 엣지 정렬
		Collections.sort(eList);
//		System.out.println(eList);
		
		// 부모 초기화(자기자신으로)
		parents = new int[N+1];
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
		
		List<Edge> g = new ArrayList<>();
		// 엣지 반복
		for(Edge e: eList) {
			// 부모가 같지 않으면 union
			if(find(e.n1) != find(e.n2)) {
				union(e.n1, e.n2);
				g.add(e);
			}
		}
		int dist = 0;
		// 마지막 엣지 제거
		for(int i=0; i<g.size()-1; i++) {
			dist += g.get(i).cost;
		}
		System.out.println(dist);
		
		

	}
	public static int find(int x) {
		if(parents[x] != x) {
			parents[x] = find(parents[x]);
		}
		return parents[x];
	}
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa != pb) {
			parents[pb] = pa;
		}
	}

}
