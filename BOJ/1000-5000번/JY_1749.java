package day1204;

import java.io.*;
import java.util.*;

public class JY_1749 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] g = new int[N+1][M+1];
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<M+1; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		print(g);
		
		
		// 2차원 배열 누적합
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				g[i][j] += g[i][j-1];
			}
		}
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				g[i][j] += g[i-1][j];
			}
		}
		
//		print(g);
		
		
		// (sx, sy) ~ (ex, ey) 범위 탐색
		int ans = Integer.MIN_VALUE;
		for(int sx=1; sx<N+1; sx++) {
			for(int sy=1; sy<M+1; sy++) {
				for(int ex=sx; ex<N+1; ex++) {
					for(int ey=sy; ey<M+1; ey++) {
						ans = Math.max(ans, g[ex][ey] - g[sx-1][ey] - g[ex][sy-1] + g[sx-1][sy-1]);
					}
				}
			}
		}
		System.out.println(ans);
		

	

	}
	public static void print(int[][] a) {
		for(int i=0; i<a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		System.out.println();
	}

}
