/*
택배 배달과 수거하기
 */

class DH_150369 {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		int d = 0, p = 0;

		for(int i = n - 1; i >= 0; i--) {
			d -= deliveries[i];
			p -= pickups[i];

			while(d < 0 || p < 0) {
				d += cap;
				p += cap;

				// 왕복 왔다갔다 하는 거리
				answer += (i + 1) * 2;
			}
		}
		return answer;
	}
}