import java.util.*;

/*
완전 범죄
*/

public class DH_389480 {
    static final int INF = Integer.MAX_VALUE - (40 * 3 + 1);

    public int solution(int[][] info, int n, int m) {

        int[][] dp = new int[info.length + 1][m];

        for(int r = 0; r < dp.length; r++) Arrays.fill(dp[r], INF);
        dp[0][0] = 0;

        // dp[i][j]: i번째 까지 고려하고 B의 흔적이 j일 때, A의 최소 흔적
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < m; j++) {
                // a: A의 흔적 크기, b: B의 흔적 크기
                int a = info[i - 1][0], b = info[i - 1][1];

                // a를 선택하는 경우
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                // b를 선택하는 경우
                if(j + b < m) dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
            }
        }
    
        // a 흔적의 최솟값
        int answer = INF;

        for(int j = 0; j < m; j++) answer = Math.min(answer, dp[dp.length - 1][j]);

        return answer >= n ? -1 : answer;
    }
}