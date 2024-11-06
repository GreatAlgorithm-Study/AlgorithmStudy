import java.util.*;
import java.io.*;

public class JY_4386 {

	static int N;
	static int[] parent;
	static class Edge implements Comparable<Edge> {
		int s1, s2; 
		double cost;
		public Edge(int s1, int s2, double cost) {
			this.s1 = s1;
			this.s2 = s2;
			this.cost = cost;
		} 
		@Override
		public int compareTo(Edge other) {
			return Double.compare(this.cost, other.cost);
		}
		
		@Override
		public String toString() {
			return this.s1+","+this.s2+" :"+this.cost;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		double[][] stars = new double[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		
		// 엣지 구하기
		PriorityQueue<Edge> eList = new PriorityQueue<>();
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				double dist = calDist(stars[i], stars[j]);
				eList.add(new Edge(i, j, dist));
			}
		}
		
//		System.out.println(eList);
		
		// 부모 초기화
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		double ans = 0;
		int size = eList.size();
		for(int i=0; i<size; i++) {
			Edge now = eList.poll();
			if(find(now.s1) != find(now.s2)) {
				union(now.s1, now.s2);
				ans += now.cost;
			}
		}
		
		System.out.println(Math.round(ans*100)/100.0);
		

	}
	public static double calDist(double[] s1, double[] s2) {
		return Math.sqrt((s1[0]-s2[0])*(s1[0]-s2[0]) + (s1[1]-s2[1])*(s1[1]-s2[1]));
	}
	public static int find(int x) {
		if(parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa != pb) {
			parent[pb] = pa;
		}
	}

}
