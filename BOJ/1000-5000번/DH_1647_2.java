import java.io.*;
import java.util.*;

/*
 * 도시 분할 계획
 * 
 * 오답 원인 1: union할 때, find가 아닌 p로 부모 구했음
 * 오답 원인 2: 경로 압축 안해서 시간 초과 발생함
 */

public class DH_1647_2 {
	static class Edge implements Comparable<Edge> {
		int s, e, w;
		
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w); // 가중치 오름차순
		}
	}
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		initInput();
		System.out.println(mst());
	}
	
	static long mst() {
		int N = p.length - 1, cnt = 0;
		long result = 0;
		
		// 간선 N - 2개 이으면 됨
		while(!pq.isEmpty() && cnt < N - 2) {
			Edge current = pq.poll();
			
			if(find(current.s) == find(current.e)) continue;
			
			union(current.s, current.e);
			cnt += 1;
			result += current.w;
		}
		
		return result;
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) p[b] = a;
	}

	static int find(int a) {
		return p[a] = a == p[a] ? a: find(p[a]);
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		p = new int[N + 1];
		for(int i = 0; i < N + 1; i++) p[i] = i;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(a, b, w));
		}
	}
}
