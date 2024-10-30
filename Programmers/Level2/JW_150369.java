class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        // 배달할 수 있는 양과 가져올 수 있는 양의 누적
        int delivery = 0, pickup = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cnt = 0;
            // i번 째 집을 해결하기 위해선 몇 번을 움직여야하는지
            while (deliveries[i] > delivery || pickups[i] > pickup) {
                cnt++;
                delivery += cap;
                pickup += cap;
            }
            // i번 째 집 배달&수거 완료
            delivery -= deliveries[i];
            pickup -= pickups[i];
            answer += (i + 1) * 2 * cnt;
        }
        return answer;
    }
}