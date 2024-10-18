package day1015;

import java.util.*;
import java.io.*;

public class JY_2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] trr = new int[N];
		int maxH = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trr[i] = Integer.parseInt(st.nextToken());
			maxH = Math.max(maxH, trr[i]);
		}
		
		
		int s = 0;
		int e = maxH;
		int ans = 0;
		while(s <= e) {
			int mid = (s + e) / 2;
			
			long total = 0;
			for(int i=0; i<N; i++) {
				if(trr[i] > mid) total += (trr[i]-mid);
			}
			
			// 자른길이가 M보다 작다면 높이를 더 낮게 설정해서 자른길이의 합을 늘려야 함
			if(total < M) {
				e = mid - 1;
			} else {
				ans = mid;
				s = mid + 1;
			}
		}
		
		System.out.println(ans);

	}

}
