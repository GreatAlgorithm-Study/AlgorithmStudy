import java.io.*;
import java.util.*;

/*
 * 공유기 설치
 * 집의 좌표: x1, ..., xn
 * 공유기 C개를 설치할 때, 인접한 거리를 가능한 크게 하여 설치하기
 * 집의 개수: N, 공유기 개수: C
 */

public class DH_2110_2 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
		
		int[] arr = new int[N];
		
		int s = 0, e = 0;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			e = Math.max(e, arr[i]);
		}
		
		// 이분탐색을 위한 정렬
		Arrays.sort(arr);
		
		// 두 공유가 사이 거리의 최대값 → upper bound 
		while(s <= e) {
			int m = (s + e) / 2; // 사이 간격이 tmp 일 때, 설치할 수 있는 공유기의 개수 구하기
			int cnt = getCnt(arr, m);
			
			// tmp일 때, 설치해야 되는 공유기 개수가 작다면 크기 줄이기
			if(cnt < C) e = m - 1;
			else s = m + 1;
		}
		
		System.out.println(e);
	}
	
	// 공유기 사이 최대 거리가 tmp일 때, 설치해야 되는 공유기 개수
	// 왼쪽에 설치할 수록, 공유기를 최대한 많이 멀리 설치할 수 있음 → 첫 번째 집에 무조건 공유기 설치 
	static int getCnt(int[] arr, int tmp) {
		int cnt = 1, prev = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] - prev >= tmp) {
				cnt += 1;
				prev = arr[i];
			}
		}
		
		return cnt;
	}
}
