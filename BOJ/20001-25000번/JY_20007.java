import java.io.*;
import java.util.*;

public class JY_20007 {
	
	static long INF = Long.MAX_VALUE;
	static int N, M, X, Y;
	static List<Node>[] g;
	static long[] distance;
	static class Node implements Comparable<Node> {
		int n;
		long dist;

		public Node(int n, long dist) {
			super();
			this.n = n;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node other) {
			return Long.compare(this.dist, other.dist);
		}

		@Override
		public String toString() {
			return "Node [n=" + n + ", dist=" + dist + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		g = new ArrayList[N];
		for(int i=0; i<N; i++) {
			g[i] = new ArrayList<>();
		}
		
		distance = new long[N];
		for(int i=0; i<N; i++) {
			distance[i] = INF;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[a].add(new Node(b, c));
			g[b].add(new Node(a, c));
		}
		
		dijkstra(Y);
		
		Arrays.sort(distance);
		
//		System.out.println(Arrays.toString(distance));
		
		// 갈 수없는 곳 찾기
		for(int i=N-1; i>=0; i--) {
			if(2*distance[i] > X) {
				System.out.println(-1);
				return;
			}
		}
		
		// 투포인터 이거 왜안되죠???
		// X= 21, distance = [0, 1, 1, 9, 9]이면 최소일수는 2일아닌가요??!! ㅠ0ㅠ
//		int s = 0;
//		int e = N-1;
//		long sum = 2*distance[e];
//		while(s <= e) {
//			if(sum + 2*distance[s] <= X) {
//				sum += 2*distance[s];
//				s++;
//			} else {
//				e--;
//				ans++;
//				sum = 2*distance[e];
//			}
//		}
		
		int ans = 1;
		long sum = 0;
		for(int i=0; i<N; i++) {
			if(sum + 2*distance[i] > X) {
				ans++;
				sum = 2*distance[i];
			} else {
				sum += 2*distance[i];
			}
		}
		
		System.out.println(ans);
		
	}
	public static void dijkstra(int start) {
		distance[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(distance[now.n] < now.dist) continue;
			
			for(Node next: g[now.n]) {
				long cost = now.dist + next.dist;
				if(distance[next.n] > cost) {
					distance[next.n] = cost;
					pq.add(new Node(next.n, cost));
				}
			}
		}
	}

}
