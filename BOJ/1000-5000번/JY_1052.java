package day1120;

import java.io.*;
import java.util.*;

public class JY_1052 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(K >= N) {
			System.out.println(0);
			return;
		}
		
		// N은 2^i가 되어야 함
		for(int k=0; k<K-1; k++) {
			int i = 0;
			while(Math.pow(2, i) < N) {
				i++;
			}
			N -= Math.pow(2, (i-1));
			
			if(N == 0) {
				System.out.println(0);
				return;
			}
		}
		
		
		int i = 0;
		while(Math.pow(2, i) < N) {
			i++;
		}
		int ans = (int)Math.pow(2, i) - N;
		System.out.println(ans);
	}

}
