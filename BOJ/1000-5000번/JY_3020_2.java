import java.util.*;
import java.io.*;

public class JY_3020_2 {

	static int N, H;
	static int[] urr, drr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		urr = new int[N/2];	 // 석순
		drr = new int[N/2];  // 종유순 
		
		for(int i=0; i<N; i++) {
			int h = Integer.parseInt(br.readLine());
			if(i % 2 == 0) {
				urr[i / 2] = h; 
			} else {
				drr[i / 2] = h;
			}
		}
		
		Arrays.sort(urr);
		Arrays.sort(drr);
//		System.out.println(Arrays.toString(urr));
//		System.out.println(Arrays.toString(drr));
		
		int s = 0;
		int e = H;
		// 파괴하는 장애물 수
		int ans = Integer.MAX_VALUE;
		// 구간 수
		int total = 0;
		
		// 높이 반복
		for(int i=1; i<H+1; i++) {
			int cnt = bs(i, urr) + bs(H-i+1, drr);	// 구해야 하는 것: 파괴되는 장애물 수
			
			if(cnt == ans) {
				total++;
			} else if (cnt < ans) {
				total = 1;
				ans = cnt;
			}
		}
		
		System.out.println(ans+" "+total);

	}
	public static int bs(int h, int[] arr) {
		int s = 0;
		int e = N/2 -1;
		int idx = N/2;
		
		while(s <= e) {
			int mid = (s+e) / 2;
			
			// mid 번째 이후의 모든 장애물을 깨트릴 수 있음 -> 탐색 인덱스를 줄여야 함(정렬되었다고 가정)
			if(arr[mid] >= h) {
				idx = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		
		return arr.length - idx;
	}

}
