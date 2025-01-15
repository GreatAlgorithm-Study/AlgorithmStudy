import java.io.*;
import java.util.*;

/*
 * 회전 초밥
 */

public class DH_2531_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 초밥 벨트에 놓인 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int[] arr = new int[N], eat = new int[d + 1];
		
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
		
		// 초기 슬라이딩 윈도우 만들기
		int s = 0, e = 0, result = 0;
		
		eat[arr[s]] += 1;
		
		while(s < N) {
			
			if(e - s + 1 < k) {
				e += 1;
				eat[arr[e % N]]++;
			} else {
				eat[arr[s]]--;
				s += 1;
			}
			
			if(e - s + 1 != k) continue;
			
			int cnt = 0;
			for(int i = 0; i < eat.length; i++) {
				if(eat[i] == 0) continue;
				cnt += 1;
			}
			
			// 쿠폰으로 먹을 수 있는 초밥
			if(eat[c] == 0) cnt += 1;
			
			result = Math.max(result, cnt);
		}
		
		System.out.println(result);
	}
}