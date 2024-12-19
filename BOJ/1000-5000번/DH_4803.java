import java.io.*;
import java.util.*;

/*
 * 트리
 */

public class DH_4803 {
	static boolean[] v;
	static ArrayList<Integer> adj[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String s = "";
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while(!(s = br.readLine()).equals("0 0")) {
			st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			v = new boolean[n + 1];
			adj = new ArrayList[n + 1];

			for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
			
			// 간선 리스트 생성
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a].add(b);
				adj[b].add(a);
			}
			
			// 트리의 개수를 세는 변수
			int treeCnt = 0;
			
			for(int i = 1; i < adj.length; i++) {
				if(v[i]) continue;
				treeCnt += dfs(0, i);
			}
			
			sb.append("Case ").append(tc++).append(": ");
			if(treeCnt == 0) sb.append("No trees.");
			else if(treeCnt == 1) sb.append("There is one tree.");
			else sb.append("A forest of ").append(treeCnt).append(" trees.");
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 사이클 확인 (사이클이 발생한다면 0, 발생하지 않는다면 1 반환)
	// prev: 직전 노드, current: 현재 노드
	static int dfs(int prev, int current) {
		v[current] = true;
		
		for(int next: adj[current]) {
			// 현재 노드를 기준으로 다음 노드로 감
			// 양방향 그래프이기 때문에 다음 노드를 갈 때는 직전 노드를 제외한 다음 노드로 가야됨
			if(next == prev) continue;
			
			// 이미 방문했던 노드거나, 사이클이 발생한다면 0 반환
			if(v[next] || dfs(current, next) == 0) return 0;
		}
		// 사이클이 발생하지 않는다면 1 반환
		return 1;
	}
}
