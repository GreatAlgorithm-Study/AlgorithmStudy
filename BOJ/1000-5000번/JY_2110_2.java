import java.io.*;
import java.util.*;

public class JY_2110_2 {
	
	static int N, C;
	static long[] hrr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		hrr = new long[N];
		for(int i=0; i<N; i++) {
			hrr[i] = Long.parseLong(br.readLine());
		}
		
		// 집 위치 정렬
		Arrays.sort(hrr);
		
		// 구해야 하는 것: 두 공유기 사이의 최대 거리
		long D = findD();
		
		System.out.println(D);
	}
	public static long findD() {
		long s = 0;
		long e = hrr[N-1];
		
		long ans = 0;
		while(s <= e) {
			long mid = (s + e) / 2;
			
			long pos = 0;
			int cnt = 0;
			for(int i=0; i<N; i++) {
				// 공유기 설치 안해도 됨
				if(hrr[i] < pos) continue;
				pos = hrr[i] + mid;
				cnt++;
			}
			
			// 공유기 개수가 C보다 많거나 같음 -> 거리를 늘려야 함
			if(cnt >= C) {
				ans = mid;
				s = mid + 1;
			} 
			// 공유기 개수가 C보다 적음 -> 거리를 줄여야 함
			else {
				e = mid - 1;
			}
		}
		return ans;
	}

}
