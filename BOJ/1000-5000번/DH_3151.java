import java.io.*;
import java.util.*;

/*
 * 합이 0
 */

public class DH_3151 {
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("./input/BOJ3151.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		long result = 0; // 10000C3은 int 범위를 벗어나기 때문에 long으로 선언
		
		for(int i = 0; i < arr.length - 2; i++) {
			for(int j = i + 1; j < arr.length - 1; j++) {
				
				// i와 j가 정해졌다면 세 번째 idx에 대해 이분탐색 진행
				// idx가 가능한 범위에서 upperbound와 lowerbound를 구해서 합이 0이 되는 경우 구하기
				int s = j + 1, e = arr.length - 1;
				
				int sumFirstAndSecond = arr[i] + arr[j];
				
				int upper = getUpperBound(arr, sumFirstAndSecond, s, e);
				int lower = getLowerBound(arr, sumFirstAndSecond, s, e);
				
				if(sumFirstAndSecond + arr[upper] != 0) continue;
				
				result += upper - lower + 1;
			}
		}
		
		System.out.println(result);
	}
	
	// s와 e 이내에서 sumFirstAndSecond와 더했을 때 0이 되는 수(lowerbound) 구하기
	static int getLowerBound(int[] arr, int sumFirstAndSecond, int s, int e) {
		int os = s, oe = e;
		
		while(os <= s && e <= oe && s <= e) {
			int m = (s + e) / 2;
			
			int sum = sumFirstAndSecond + arr[m];
			
			if(sum < 0) s = m + 1;
			else e = m - 1;
		}
		
		return s;
	}
	
	// s와 e 이내에서 sumFirstAndSecond와 더했을 때 0이 되는 수(upperbound) 구하기
	static int getUpperBound(int[] arr, int sumFirstAndSecond, int s, int e) {
		int os = s, oe = e;
		
		while(os <= s && e <= oe && s <= e) {
			int m = (s + e) / 2;
			
			int sum = sumFirstAndSecond + arr[m];
			
			if(sum <= 0) s = m + 1;
			else e = m - 1;
		}
		
		return e;
	}
}
