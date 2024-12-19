import java.io.*;
import java.util.*;

/*
 * 상근이의 여행
 */

public class DH_9372 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			boolean[] v = new boolean[n + 1];
			ArrayList<Integer> adj[] = new ArrayList[n + 1];
			for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adj[a].add(b);
				adj[b].add(a);
			}
		
			int answer = 0;
			
			for(int i = 1; i < n; i++) {
				if(v[i]) continue;
				q.add(i);
				v[i] = true;
				
				while(!q.isEmpty()) {
					int current = q.poll();
					
					for(int next: adj[current]) {
						if(v[next]) continue;
						answer += 1;
						q.add(next);
						v[next] = true;
					}
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
