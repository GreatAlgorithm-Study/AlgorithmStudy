import java.io.*;
import java.util.*;

public class JY_1911 {
	
	static int N, L;
	static long[][] hrr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		hrr = new long[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			hrr[i][0] = Integer.parseInt(st.nextToken());
			hrr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 물웅덩이 위치순으로 정렬
		Arrays.sort(hrr, (o1, o2)->Long.compare(o1[0], o2[0]));
		
		long s = 0;
		long e = hrr[N-1][1];	// 최대의 널빤지수 == L이 1이고, 가장 마지막 물웅덩이까지 다 덮을 때 
		long cnt = 0;
		
		while(s <= e) {
			long mid = (s + e) / 2;
			
			if(isPossible(mid)) {
				cnt = mid;
				e = mid - 1;
			}else {
				s = mid + 1;
			}
		}
		
		System.out.println(cnt);

	}
	public static boolean isPossible(long mid) {
		long pre = 0;				// 마지막 널빤지 위치
		long total = 0;				// 총 필요한 널빤지 개수
		for(int i=0; i<N; i++) {
			long[] now = hrr[i];
			// 현재 물웅덩이 시작점에 널빤지 없다면 새로운 널빤지 시작점
			if(pre < now[0]) {
				pre = now[0];
			} 
			
			// 물 웅덩이 크기
			long size = now[1] - pre;
			if(size <= 0) continue;
			
			// 필요한 널빤지 개수
			long cnt = (size / L);
			if(size % L != 0) cnt++;
			total += cnt;
			if(total > mid) return false;
			
			pre = pre + cnt * L;	// 마지막 널빤지 위치 갱신
		}
		
		return true;
	}

}
