import java.util.Arrays;
class JW_214289 {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int INF = Integer.MAX_VALUE >> 2;
        int len = onboard.length;
        temperature += 10;
        t1 += 10;
        t2 += 10;
        int[][] dp = new int[len][51];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][temperature] = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < 51; j++) {
                // 사람이 탔을 때, 적절한 온도가 아니라면 스킵
                if (onboard[i] == 1 && !isPossible(j, t1, t2)) {
                    continue;
                }
                // 에어컨을 가동하지 않았을 때
                if (j < temperature && j < 50) {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j]);
                } else if (j > temperature && j > 0) {
                    dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j]);
                } else {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                }
                // 에어컨을 가동했을 때
                if (j > 0)
                    dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j] + a);
                if (j < 50)
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + a);
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + b);
            }
        }
        int min = INF;
        for (int i = 0; i < 51; i++) {
            // 가능한 값들 중 최솟값 찾기
            if (onboard[len - 1] == 1 && !isPossible(i, t1, t2)) 
                continue;
            min = Math.min(min, dp[len - 1][i]);
        }
        return min;
    }

    // 현재 온도가 적절한 온도인지 반환하는 함수
    private boolean isPossible(int now, int t1, int t2) {
        return t1 <= now && now <= t2;
    }
}