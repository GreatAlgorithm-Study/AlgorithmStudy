import java.io.*;
import java.util.*;

/*
 * 샘터
 * 주의점
 * 1. 샘터의 위치는 -100_000_000 ~ 100_000_000이지만, 집의 위치는 이걸 벗어날 수 있음
 * 2. 불행도를 계산할 때, 불행도의 범위 long으로 설정하기
 * - 1 + 2 + ... + 500,000 = 125,000,250,000
 */

public class DH_18513 {
	
	static final int INF = 100_200_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 샘터의 개수
		int K = Integer.parseInt(st.nextToken()); // 집의 개수
		
		boolean[] v = new boolean[2 * INF + 1]; // -INF ~ INF
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		st = new StringTokenizer(br.readLine()); // 샘터에 대한 정보
		for(int i = 0; i < N; i++) {
			int current = Integer.parseInt(st.nextToken());
			q.add(new int[] {current, 0});
			v[current + INF] = true;
		}
		
		int cnt = 0;
		int badCnt = 0;
		
		L: while(!q.isEmpty()) {
			int[] current = q.poll();
			
			// 좌, 우 확인하기
			for(int d = -1; d < 2; d += 2) {
				int next = current[0] + d;
				
				if(next < -INF || next > INF || v[next + INF]) continue;
				cnt += 1;
				badCnt += current[1] + 1;
				
				if(cnt ==K) break L;

				q.add(new int[] {next, current[1] + 1});
				v[next + INF] = true;
			}
		}
		
		System.out.println(badCnt);
	}
}
