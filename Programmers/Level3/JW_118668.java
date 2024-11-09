import java.util.Arrays;

class JW_118668 {
    public int solution(int alp, int cop, int[][] problems) {
        // 모든 문제를 풀기 위한 최솟값을 찾기 위해 초기화
        int maxAlp = alp, maxCop = cop;
        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        // DP 배열 초기화
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i < maxAlp + 1; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE >> 2);
        dp[alp][cop] = 0;
        
        // DP
        // 현재 값 기준으로 다음 값 결정하기
        for (int i = alp; i < maxAlp + 1; i++) {
            for (int j = cop; j < maxCop + 1; j++) {
                // 알고력 증가
                if (i < maxAlp)
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                // 코딩력 증가
                if (j < maxCop)
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                // 문제 풀기
                for (int[] problem : problems)
                    // 풀 수 있는 문제라면
                    if (i >= problem[0] && j >= problem[1]) {
                        // 능력치 증가
                        int nextAlp = Math.min(maxAlp, i + problem[2]);
                        int nextCop = Math.min(maxCop, j + problem[3]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
            }
        }
        return dp[maxAlp][maxCop];
    }
}