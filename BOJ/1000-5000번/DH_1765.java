import java.io.*;
import java.util.*;

/*
 * 닭싸움 팀 정하기
 */

public class DH_1765 {
	static class Node {
		int e;
		boolean isFriend;
		
		public Node(int e, boolean isFriend) {
			this.e = e;
			this.isFriend = isFriend;
		}
	}
	static boolean[] check;
	static ArrayList<Node> adj[];
	static int groupCnt;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		for(int i = 1; i < check.length; i++) {
			if(check[i]) continue;
			groupCnt += 1;
			dfs(i);
		}
		
		System.out.println(groupCnt);
	}
	
	static void dfs(int node) {
		check[node] = true;
		
		for(Node next: adj[node]) {
			// 현재 사람의 바로 옆 사람이 친구라면 같은 팀을 할 수 있음
			if(next.isFriend) {

				if(check[next.e]) continue;
				dfs(next.e);
				
			} 
			// 현재 사람의 바로 옆 사람이 원수라면
			else {
				// 옆사람과 옆옆사람이 원수일 때, 현재 사람과 같은 팀을 할 수 있음
				for(Node nnext: adj[next.e]) {
					if(check[nnext.e] || nnext.isFriend) continue;
					dfs(nnext.e);
				}
			}
		}
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		check = new boolean[n + 1];
		adj = new ArrayList[n + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			boolean isFriend = st.nextToken().charAt(0) == 'F';
			
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			adj[p].add(new Node(q, isFriend));
			adj[q].add(new Node(p, isFriend));
		}
	}
}