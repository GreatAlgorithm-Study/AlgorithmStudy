package day1031;

import java.io.*;
import java.util.*;

public class JY_2461 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] srr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				srr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 반 정렬
		for(int i=0; i<N; i++) {
			Arrays.sort(srr[i]);
		}
		
		// 각 반의 학생 인덱스 포인터 배열
		int[] n = new int[N];
		
		int ans = Integer.MAX_VALUE;
		while(true) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			
			// 각 반 반복
			for(int i=0; i<N; i++) {
				int j = n[i];         // 각 반이 현재 가리키고 있는 학생
				if(max < srr[i][j]) {
					max = srr[i][j];
				}
				if(min > srr[i][j]) {
					min = srr[i][j];
					minIdx = i;
				}
			}

			ans = Math.min(ans, max-min);
			n[minIdx]++;
			if(n[minIdx] == M) break;
			
		}
		
		System.out.println(ans);
		
	}
	

}
