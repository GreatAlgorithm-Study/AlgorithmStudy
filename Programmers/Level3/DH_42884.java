import java.util.*;

/*
 * 단속 카메라
 */

public class DH_42884 {
	static int solution(int[][] routes) {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1, o2) -> {
			if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o1[0], o2[0]);
		});
		
		for(int[] route: routes) q.add(route);
		
		int answer = 0;
		int camera = Integer.MIN_VALUE;
		while(!q.isEmpty()) {
			int[] current = q.poll();
			if(camera < current[0]) {
				camera = current[1];
				answer += 1;
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
		System.out.println(solution(routes));
	}
}
