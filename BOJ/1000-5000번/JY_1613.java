package day1113;

import java.io.*;
import java.util.*;

public class JY_1613 {
	
	static final int INF = 50_000;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] g = new int[N+1][N+1];
		for(int i=0; i<N+1; i++) {
			Arrays.fill(g[i], INF);
		}
		// 자신으로가는 비용은 0으로 처리
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(i == j) g[i][j] = 0;
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			g[a][b] = 1;
		}
		
		// 플로이드 와샬: i -> j로 갈 수 있는 최단거리 구함
		for(int k=1; k<N+1; k++) {
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					g[i][j] = Math.min(g[i][j], g[i][k]+g[k][j]);
				}
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new  StringBuilder();
		for(int s=0; s<S; s++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(g[a][b] != INF) {
				sb.append("-1\n");
			} else if(g[b][a] != INF) {
				sb.append("1\n");
			} else {
				sb.append("0\n");
			}
		}
		
		System.out.println(sb.toString());

	}

}
