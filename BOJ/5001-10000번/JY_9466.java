package day1121;

import java.io.*;
import java.util.*;

public class JY_9466 {
	
	static int T, N;
	static int[] g;
	static boolean[] visited, finished;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {	
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			g  = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<N+1; i++) {
				g[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			cnt = N;
			for(int i=1; i<N+1; i++) {
				if(visited[i]) continue;
				dfs(i);
			}
			
			sb.append(cnt+"\n");
			
		}
		System.out.println(sb.toString());

	}
	public static void dfs(int now) {
		visited[now] = true;
		
		int next = g[now];
		
		// 아직 방문하지 않은 곳
		if(!visited[next]) {
			dfs(next);
		}
		// 순환 발생
		else {
			// 사이클 시작점으로 돌아감
			while(!finished[next]) {
				finished[next] = true;
				next = g[next];
				cnt--;
			}				
			
		}
		finished[now] = true;
		
	}

}
