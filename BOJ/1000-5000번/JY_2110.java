import java.util.*;
import java.io.*;

public class JY_2110 {

	static int N, C;
	static long[] wrr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		wrr = new long[N];
		for(int i=0; i<N; i++) {
			wrr[i] = Long.parseLong(br.readLine());
		}
		
		// 집위치 정렬
		Arrays.sort(wrr);
		
		long s = 0;
		long e = (wrr[N-1] - wrr[0]);
		while(s < e) {
			// 인접한 두 공유기 사이의 거리
			long mid = (s + e) / 2;
			
			long nextPos = 0;
			int cnt = 0;
			for(int i=0; i<N; i++) {
				// 공유기 새로 설치해야 함
				if(wrr[i] >= nextPos) {
					nextPos = wrr[i] + mid;
					cnt++;
				}
			}
			
			// 필요한 공유기수가 더 많거나 같으면 거리를 늘릴 수 있음
			if(cnt >= C) {
				s = mid + 1;
			} 
			// 필요한 공유기수가 더 작으면 거리를 좁혀야 함
			else {
				e = mid;
			}
		}
		
		System.out.println(e);
	}

}
