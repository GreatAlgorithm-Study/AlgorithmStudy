package day1105;

import java.io.*;
import java.util.*;

public class JY_2660 {

	static int INF = Integer.MAX_VALUE;
	static int N;
	static List<Node>[] g;
	static int[] distance;
	static class Node implements Comparable<Node> {
		int w, dist;
		public Node(int w, int dist) {
			this.w = w;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node other) {
			return this.dist - other.dist;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		g = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			g[i] = new ArrayList<>();
		}
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if(n1 == -1 && n2 == -1) break;
			g[n1].add(new Node(n2, 1));
			g[n2].add(new Node(n1, 1));
		}
		
		int minLevel = Integer.MAX_VALUE;
		List<Integer> aList = new ArrayList<>();
		for(int i=1; i<N+1; i++) {
			distance = new int[N+1];
			Arrays.fill(distance, INF);
			
			dijkstra(i);
//			System.out.println(i+": "+Arrays.toString(distance));
			
			int level = findLevel();
			
			if(level < minLevel) {
				aList = new ArrayList<>();
				aList.add(i);
				minLevel = level;
				
			} else if (level == minLevel) {
				aList.add(i);
			}
		}
		
		System.out.println(minLevel+" "+aList.size());
		for(int i: aList) {
			System.out.print(i+" ");
		}
		System.out.println();

	}
	public static void dijkstra(int n) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(n, 0));
		
		distance[n] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.dist > distance[now.w]) continue;
			
			for(Node next: g[now.w]) {
				int cost = now.dist + next.dist;
				if(cost < distance[next.w]) {
					distance[next.w] = cost;
					pq.add(new Node(next.w, cost));
				}
			}
		}
	}
	public static int findLevel() {
		int maxL = Integer.MIN_VALUE;
		for(int i=1; i<N+1; i++) {
			maxL = Math.max(maxL, distance[i]);
		}
		
		return maxL;
	}

}
