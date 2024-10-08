package day0924;

import java.util.*;
import java.io.*;

public class JY_15989 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dp = new int[10001];
		// 1로 초기화 : 1만 써서 만들기
		for(int i=0; i<10001; i++) {
			dp[i] = 1;
		}
		
		// 2도 추가하기
		for(int i=2; i<10001; i++) {
			dp[i] += dp[i-2];
		}
		
		// 3도 추가하기
		for(int i=3; i<10001; i++) {
			dp[i] += dp[i-3];
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}

	}

}
