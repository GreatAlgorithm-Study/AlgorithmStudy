import java.io.*;
import java.util.*;

/*
 * 휴게소 세우기
 */

public class DH_1477_2 {
	static int N, M, L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 현재 휴게소의 개수
		M = Integer.parseInt(st.nextToken()); // 더 지으려는 휴게소 개수
		L = Integer.parseInt(st.nextToken()); // 고속도로의 길이
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		// 휴게소 사이 간격은 0이 될 수 없기 때문에
		// s = 0으로 하면 안됨
		int s = 1, e = L;
		
		// 휴게소가 없는 구간의 길이의 최댓값을 최소로
		// -> 휴게소 사이 간격을 최소로 한다는 말과 같음 
		// -> lower bound: 비교할 매개변수와 주어진 값이 같을 때, end값을 옮겨야 됨
		// -> 휴게소 사이 최소 간격을 x라고 한다면, 추가로 몇 개 지어야 되는지 확인해야 됨
		while(s <= e) {
			int m = (s + e) / 2;
			int addCnt = getAddCnt(arr, m);
			
			// 휴게소 사이 간격을 줄여야 됨
			if(addCnt <= M) e = m - 1;
			else s = m + 1;
		}
		
		System.out.println(s);
	}
	
	// 추가로 설치해야 되는 휴게소 개수 구하기
	static int getAddCnt(int[] arr, int m) {
		int cnt = 0;
		
		for(int i = 0; i < arr.length + 1; i++) {
			int current = i == arr.length ? L : arr[i];
			int prev = i == 0 ? 0: arr[i - 1];

			cnt += (current - prev - 1) / m;
		}
		
		return cnt;
	}
}
