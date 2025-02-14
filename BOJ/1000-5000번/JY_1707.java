import java.io.*;
import java.util.*;
public class JY_1707 {
	
	static int T, N, M;
	static List<Integer>[] g;
	static boolean[] visited;
	static int[] crr;
	static boolean isOk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			g = new ArrayList[N+1];
			for(int i=1; i<N+1; i++) {
				g[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				g[a].add(b);
				g[b].add(a);
			}
			
			visited = new boolean[N+1];
			crr = new int[N+1];	// 집합 번호 저장 배열
			isOk = true;
			for(int i=1; i<N+1; i++) {
				if(visited[i]) continue;
				dfs(i, 1);
				if(!isOk) break;
				
			}
			
			if(isOk) sb.append("YES\n");
			else sb.append("NO\n");
				
		}
		
		System.out.println(sb.toString());

	}
	public static void dfs(int now, int group) {
		crr[now] = group;
		visited[now] = true;
		
		for(int next: g[now]) {
			// 인접 노드가 현재 노드의 그룹과 같다면 fail
			if(crr[next] == group) {
				isOk = false;
				return;
			}
			
			// 방문 X
			if(!visited[next]) {
				dfs(next, -group);
			}
			
		}
	}


}
