import java.util.*;
import java.io.*;

public class JY_1967 {

	static int N;
	static int maxLen, lastNode, ans;
	static List<Node>[] nrr;
	static boolean[] visited;
	static class Node {
		int u, dist;
		public Node(int u, int dist) {
			this.u = u;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Node [u=" + u + ", dist=" + dist + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		nrr = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			nrr[i] = new ArrayList<>();
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			nrr[n1].add(new Node(n2, e));
			nrr[n2].add(new Node(n1, e));
			
		}
		
//		System.out.println(Arrays.toString(nrr));
		
		/**
		 * 임의의 노드에서 가장 먼 노드를 찾고, 그 노드에서 다시 가장 먼 노드를 찾으면 트리의 지름을 구할 수 있음
		 * 2번의 dfs 활용
		 * 1) 임의의 노드에서 가장 먼 노드 찾기
		 * 2) 찾은 노드로 시작하는 가장 긴 간선의 길이 찾기
		 * */
		
		// 1. 임의의 노드에서 가장 먼 노드 찾기
		// lastNode: 임의의 노드에서 가장 먼 노드의 길이
		// maxLen: 가장 먼 노드까지의 간선의 길이
		maxLen = Integer.MIN_VALUE;
		visited = new boolean[N+1];
		visited[1] = true;
		lastNode = 0;
		dfs(1, 0);
		
//		System.out.println(maxLen+", "+lastNode);
		
		// 2. 찾은 노드로 부터 가장 먼 노드까지의 길이가 트리의 지름
		maxLen = Integer.MIN_VALUE;
		visited = new boolean[N+1];
		visited[lastNode] = true;
		dfs(lastNode, 0);
		
		System.out.println(maxLen);
		
	}
	public static void dfs(int now, int len) {
		if(len > maxLen) {
			maxLen = len;
			lastNode = now;
		}
		
		for(Node next: nrr[now]) {
			if(!visited[next.u]) {
				visited[next.u] = true;
				dfs(next.u, len+next.dist);
			}
		}
	}

}
