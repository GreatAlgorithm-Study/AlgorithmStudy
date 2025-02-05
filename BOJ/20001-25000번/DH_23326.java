import java.io.*;
import java.util.*;

/*
 * 홍익 투어리스트
 */

public class DH_23326 {
	static int N, dh = 1;
	static StringBuilder sb = new StringBuilder();
	static TreeSet<Integer> set = new TreeSet<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 구역의 개수
		int Q = Integer.parseInt(st.nextToken()); // 쿼리의 개수

		st = new StringTokenizer(br.readLine());
		
		// 길이 N의 수열 A
		for(int i = 0; i < N; i++) {
			int current = Integer.parseInt(st.nextToken());
			if(current == 1) set.add(i + 1);
		}
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 1) solution1(st);
			if(a == 2) solution2(st);
			if(a == 3) {
				int result = Integer.MAX_VALUE;
				if(!set.isEmpty()) {
					if(set.contains(dh)) result = 0;
					else {
						if(set.higher(dh) != null) result = set.higher(dh) - dh;
						if(set.lower(dh) != null) result = Math.min(result, N - dh + set.first());
					}
				}
				
				sb.append(result == Integer.MAX_VALUE ? -1: result).append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	// 도현이가 시계방향으로 x만큼 이동
	static void solution2(StringTokenizer st) {
		int x = Integer.parseInt(st.nextToken());
		dh = (dh + x) % N;
		dh = dh == 0 ? N: dh;
	}
	
	// i 구역이 명소가 아니었다면 명소로 지정하고, 명소가 아니라면 지정 해제하기
	static void solution1(StringTokenizer st) {
		int i = Integer.parseInt(st.nextToken());
		
		if(set.contains(i)) set.remove(i);
		else set.add(i);
	}
}
