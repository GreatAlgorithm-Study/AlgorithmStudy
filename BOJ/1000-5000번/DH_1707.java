import java.io.*;
import java.util.*;

/*
 * 이분 그래프
 */

public class DH_1707 {
	static ArrayList<Integer> adj[];
	static boolean[] v, check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());

		for(int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
			v = new boolean[V + 1];

			adj = new ArrayList[V + 1]; // 인접리스트
			check = new boolean[V + 1]; // 그래프가 어떻게 나뉘는지에 대한 정보
			
			for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
			
			for(int k = 0; k < E; k++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adj[a].add(b);
				adj[b].add(a);
			}

			boolean isEven = true;
			
			// 첫 번째 노드부터 마지막 노드까지 BFS를 통해 탐색
			for(int i = 0; i < V + 1; i++) {
				if(v[i]) continue;
				if(!(isEven = bfs(i))) break;
			}
			
			sb.append(isEven ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean bfs(int node) {
		
		boolean isEven = true;
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		
		q.add(node);
		v[node] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: adj[current]) {
				// 다음 노드가 이미 방문된 상태일 때
				// 그래프를 이분그래프로 만들기 위해 분리했을 때, 분리되는 결과가 같다면 이분그래프를 만들지 못함
				if(v[next]) {
					if(check[next] == check[current]) return false;
					continue;
				} 

				// 다음 노드를 방문하지 않았다면
				// 그래프를 분리할 때, 현재 노드와 인접한 노드가 같은 그래프에 속하지 않도록 설정해줌
				v[next] = true;
				check[next] = !check[current];
				q.add(next);
			}
		}
		return isEven;
	}
}
