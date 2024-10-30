import java.io.*;
import java.util.*;

/*
 * 떡 돌리기
 */

public class DH_20007 {
	static final Long INF = Long.MAX_VALUE;
	static class Node implements Comparable<Node> {
		int e;
		long w;
		public Node(int e, long w) {
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.w, o.w);
		}
	}
	static ArrayList<Node> adj[];
	static int N, M, X, Y;
	static long[] dis;
	
	static void dijkstra() {
	    dis = new long[N];
		Arrays.fill(dis, INF);
		
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(Y, 0));
		dis[Y] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for(Node next: adj[current.e]) {
				if(dis[next.e] > dis[current.e] + next.w) {
					dis[next.e] = dis[current.e] + next.w;
					pq.add(new Node(next.e, dis[next.e]));
				}
			}
		}
	}
	
	static int getCnt() {
		// 가는 길이가 최소길이 순이 될 수 있도록 정렬
		Arrays.sort(dis);

		// 한 번에 갈 수 있는 경우의 수
		int cnt = 1;
		
		long tmpDis = 0; // 거리의 합계
		for(int i = 0; i < N; i++) {
			long doubleDis = dis[i] * 2; // 왕복 거리
			// 도달할 수 없는 지점이 있거나, 왕복 거리가 X보다 멀다면 -1 반환
			if(dis[i] == INF || doubleDis > X) return - 1;

			// 왕복 거리로 갈 수 있다면 거리의 합계에 더해주기
			if(tmpDis + doubleDis <= X) {
				tmpDis += doubleDis;
			}
			// 갈 수 없다면 cnt 늘려주고 거리의 합계를 왕복 거리로 갱신
			else {
				cnt++;
				tmpDis = doubleDis;
			}
		}
		
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		initInput();
		dijkstra();
		System.out.println(getCnt());
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		X = Integer.parseInt(st.nextToken()); // 갈 수 있는 최대 거리
		Y = Integer.parseInt(st.nextToken()); // 시작점
		
		adj = new ArrayList[N];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}
	}
}