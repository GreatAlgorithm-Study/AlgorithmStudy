public class SB_42584 {
    public int[] solution(int[] prices) {
        int N = prices.length;

        int[] ans = new int[N];
        for (int i = N - 2; i >= 0; i--) {
            int cnt = 1;
            while (i+cnt < N && prices[i] <= prices[i+cnt]){ // 현재 값이 떨어지지 않을 동안
                if (ans[i+cnt] ==0) break;                   // 이용할 이전 값이 없음 >> 종료
                cnt += ans[i + cnt];                         // 값이 떨어지지 않은 동안(cnt)의 이전값 활용
            }
            ans[i] = cnt;
        }

        return ans;
    }
}
