import java.io.*;
import java.util.*;

/*
 * 나무 자르기 
 */

public class DH_2805_2 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		// s가 arr의 최솟값보다 작아질 수 있으므로 0으로 설정해주기
		long s = 0, e = Integer.MIN_VALUE;

		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			e = Math.max(e, arr[i]);
		}
		
		Arrays.sort(arr);
		
		// upper bound 구하기
		while(s <= e) {
			long m = (s + e) / 2;
			
			long totalCutLength = getTotalCutLength(arr, m);
			
			// upper bound → 같을 때, 시작점 옮기기 
			if(totalCutLength >= M) s = m + 1;
			else e = m - 1;
		}
		
		// upper bound 구하는 것이기 때문에 e 출력
		System.out.println(e);
	}
	
	// m을 제한으로 했을 때, 잘리는 나무의 합계
	static long getTotalCutLength(int[] arr, long m) {
		
		long result = 0;
		
		for(long a: arr) {
			if(a <= m) continue;
			result += (a - m);
		}
		
		return result;
	}
}
