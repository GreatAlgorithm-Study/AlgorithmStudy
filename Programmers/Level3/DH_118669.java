import java.util.*;

/*
 * 등산코스 정하기
 */

public class DH_118669 {
	static class Node implements Comparable<Node> {
		int e, w, intensity;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
		
		public Node(int e, int w, int intensity) {
			this.e = e;
			this.w = w;
			this.intensity = intensity;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static ArrayList<Node> adj[];
	static final int INF = Integer.MAX_VALUE;
	
	static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int[] answer = {INF, INF};
		
		adj = new ArrayList[n + 1];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();
		
		// 간선리스트 만들어주기
		for(int[] path: paths) {
			int i = path[0];
			int j = path[1];
			int w = path[2];
			
			adj[i].add(new Node(j, w));
			adj[j].add(new Node(i, w));
		}
		
		// 산봉우리들을 저장하는 set. 결과 구할 때, 출입구의 크기대로 계산해야 되므로 treeset 이용
		TreeSet<Integer> summitSet = new TreeSet<Integer>();
		// 시작입구를 저장하는 set
		HashSet<Integer> gateSet = new HashSet<Integer>();
		
		for(int s: summits) summitSet.add(s);
		for(int g: gates) gateSet.add(g);
		
		// 산봉우리들을 기준으로 bfs 시작 (출입구를 만날때까지 탐색)
		for(int s: summitSet) bfs(n, s, gateSet, summitSet, answer);
		return answer;
	}
	
	static void bfs(int n, int s, HashSet<Integer> gateSet, TreeSet<Integer> summitSet, int[] answer) {
		
		boolean[] v = new boolean[n + 1];
		
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		
		q.add(new Node(s, 0, 0));
		v[s] = true;
		
		int intensity = INF;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			v[current.e] = true;
			
			// 출입구를 만났는데, intensity가 작다면 intensity 값 update 해주기
			if(gateSet.contains(current.e) && intensity > current.intensity) {
				intensity = current.intensity;
				break;
			}
			
			// 다음 노드가 출입구가 되면 안됨
			for(Node next: adj[current.e]) {
				if(summitSet.contains(next.e) || v[next.e]) continue;
				q.add(new Node(next.e, next.w, Math.max(current.intensity, next.w)));
			}
		}
		
		if(answer[1] > intensity) {
			answer[1] = intensity;
			answer[0] = s;
		}
	}

	public static void main(String[] args) {
		int n = 6;
		int[][] paths = {{1, 2, 3}, 
						 {2, 3, 5}, 
						 {2, 4, 2}, 
						 {2, 5, 4}, 
						 {3, 4, 4}, 
						 {4, 5, 3}, 
						 {4, 6, 1}, 
						 {5, 6, 1}};
		int[] gates = {1, 3};
		int[] summits = {5};

		System.out.println(Arrays.toString(solution(n, paths, gates, summits)));
	}
}
