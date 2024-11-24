package day1122;

import java.io.*;
import java.util.*;

public class JY_19939 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		int cnt = 0;
		// 초기값 : 1부터 K개까지 분배
		for(int i=0; i<K; i++) {
			arr[i] = (i+1);
			cnt += arr[i];
		}
		
		if(cnt > N) {
			System.out.println(-1);
			return;
		}
		
		N -= cnt;
		int val = N / K;
		for(int i=0; i<K; i++) {
			arr[i] += val;
		}
		if(N % K != 0) {
			int re = N % K;
			
			// 맨 뒤부터 1씩 증가하기
			int idx = K-1;
			while(re > 0) {
				arr[idx]++;
				idx--;
				re--;
			}
		}
		
//		System.out.println(Arrays.toString(arr));
		System.out.println(arr[K-1]-arr[0]);

	}

}
