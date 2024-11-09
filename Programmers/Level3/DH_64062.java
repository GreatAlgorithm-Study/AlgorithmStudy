
/*
 * 징검다리 건너기
 */

public class DH_64062 {
	static int solution(int[] stones, int k) {
		// 돌을 건넌 사람의 최대 수 구하기 - upper bound 구하기
		int s = 0, e = 200_000_001;
		
		while(s <= e) {
			// 돌을 건넌 친구 수
			int m = (s + e) / 2;
			
			int cnt = 0;
			
			// 친구들이 모두 길을 건넜을 때
			// 값이 0이하가 되는 곳이 k개 이상이 된다면 못건너감
			boolean canGo = true;
			for(int stone: stones) {
				if(stone - m < 0) cnt++;
				else cnt = 0;
				
				if(cnt >= k) canGo = false;
			}
			
			if(canGo) s = m + 1;
			else e = m - 1;
		}
		
		return e;
	}
	
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		System.out.println(solution(stones, k));
	}
}
