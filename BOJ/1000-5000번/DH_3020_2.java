import java.io.*;
import java.util.*;

/*
 * 개똥벌레
 */

public class DH_3020_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] down = new int[N / 2];
		int[] up = new int[N / 2];
		
		
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			
			if(i % 2 == 0) down[i / 2] = height;
			else up[i / 2] = height;
		}
		
		// down과 up 둘 다 정렬 한 뒤에, 이분탐색으로 height의 시작 idx 구하기
		// height의 시작 idx를 구하는거긴 한데, lowerBound를 구해야 됨
		Arrays.sort(down);
		Arrays.sort(up);
		
		int minCnt = Integer.MAX_VALUE, heightCnt = 0;
		
		for(int i = 1; i < H + 1; i++) {
			int cnt = getCnt(down, i) + getCnt(up, H + 1 - i);
			
			if(cnt < minCnt) {
				minCnt = cnt;
				heightCnt = 1;
			} else if(cnt == minCnt) heightCnt += 1;
		}
		
		System.out.println(minCnt + " " + heightCnt);
	}
	
	// lowerBound를 구하기
	static int getCnt(int[] arr, int height) {
		
		int s = 0, e = arr.length - 1;
		
		while(s <= e) {
			// 같다면 end를 줄여야 됨
			int m = (s + e) / 2;

			if(arr[m] < height) s = m + 1;
			else e = m - 1;
		}
		
		return arr.length - s;
	}
}
