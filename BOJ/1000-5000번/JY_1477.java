package day1002;

import java.util.*;
import java.io.*;

public class JY_1477 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+2];
		arr[0] = 0;
		arr[1] = L;
		st = new StringTokenizer(br.readLine());
		for(int i=2; i<N+2; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
//		System.out.println(Arrays.toString(arr));
		
		int s = 1;
		int e = L-1;
		int ans = 0;
		while(s <= e) {
			int mid = (s + e) / 2;		// 휴게소 사이의 간격
			int cnt = 0;				// 새로 세울 수 있는 휴게소 개수
			for(int i=1; i<N+2; i++) {
				cnt += (arr[i]-arr[i-1]-1) / mid;
			}
			if(cnt <= M) {				// 개수가 부족하거나 같으면 간격 더 좁힐 수 있음
				ans = mid;
				e = mid - 1;
			} else {					// 개수가 많으면(초과하면) 간격을 줄여야 함
				s = mid + 1;
			}
		}
		System.out.println(ans);

	}

}
