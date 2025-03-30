import java.util.*;

/*
 * 합승 택시 요금
 */

class DH_72413 {
    static class Node {
		int e, c; // 도착점과 비용
		
		public Node(int e, int c) {
			this.e = e;
			this.c = c;
		}
	}
    
	static public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        	
        ArrayList<int[]>[] adj = initArrayList(n, fares);
        
        int[] dis = dijkstra(adj, s); // 시작점으로부터 각 노드까지의 거리를 구함
        
        for(int i = 1; i < n + 1; i++) {
        	// i까지 가는게 공통거리, 그리고 A, B 각각 집에 가야 됨
        	int[] tmp = dijkstra(adj, i); // i에서부터 A, B까지 도달하는 거리를 구함
        	answer = Math.min(dis[i] + tmp[a] + tmp[b], answer);
        }
        return answer;
    }
	
	// s로부터 모든 노드까지 가는 거리를 구함
	static int[] dijkstra(ArrayList<int[]>[] adj, int s) {
		int[] dis = new int[adj.length];
		boolean[] v = new boolean[adj.length];
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		dis[0] = 0;
		PriorityQueue<Node> q = new PriorityQueue<Node>(Comparator.comparingInt(o -> o.c));
		
		q.add(new Node(s, 0));
		dis[s] = 0;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			if(v[current.e]) continue;
			v[current.e] = true;
			
//			System.out.println(">> " + current.c);
			for(int[] next: adj[current.e]) {
				
				if(dis[current.e] + next[1] < dis[next[0]]) {
					dis[next[0]] = dis[current.e] + next[1];
					
					q.add(new Node(next[0], dis[next[0]]));
				}
			}
		}
		return dis;
	}
	
	static ArrayList<int[]>[] initArrayList(int n, int[][] fares) {
		ArrayList<int[]>[] adj = new ArrayList[n + 1];
		
		for(int i = 0; i < n + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int[] fare: fares) {
			int a = fare[0];
			int b = fare[1];
			int c = fare[2];
			
			adj[a].add(new int[] {b, c});
			adj[b].add(new int[] {a, c});
		}
		return adj;
	}
	public static void main(String[] args) {

		int n = 7;
		int s = 3;
		int a = 4;
		int b = 1;
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, 
						 {3, 2, 3}, {2, 1, 6}};

		System.out.println(solution(n, s, a, b, fares));
	}
}
