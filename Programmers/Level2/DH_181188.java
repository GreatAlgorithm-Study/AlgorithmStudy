import java.util.*;

/*
 * 요격 시스템
 */

public class DH_181188 {
	static int solution(int[][] targets) {
		int answer = 0;
		
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
			if(o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o1[0], o2[0]);
		});
		
		for(int[] target: targets) q.add(target);
		
		int missile = Integer.MIN_VALUE;
		while(!q.isEmpty()) {
			int[] current = q.poll();
			if(missile <= current[0]) {
				missile = current[0];
				answer += 1;
			}
		}
		return answer;
	}
}
