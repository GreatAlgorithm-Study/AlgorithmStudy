import java.util.*;

class HW_118668 {
    public int solution(int alp, int cop, int[][] problems) {
        int tA = alp;
        int tC = cop;

        // 목표 알고력과 코딩력을 설정
        for (int i = 0; i < problems.length; i++) {
            tA = Math.max(tA, problems[i][0]); // target Alp
            tC = Math.max(tC, problems[i][1]); // target Cop
        }

        int[][] dp = new int[tA + 1][tC + 1]; // dp[알고력][코딩력]

        // dp배열 초기화
        for (int i = 0; i <= tA; i++) {
            for (int j = 0; j <= tC; j++) {
                dp[i][j] = Integer.MAX_VALUE; // 초기 상태에서 도달할 수 없는 값들은 큰 값으로 초기화
            }
        }

        dp[alp][cop] = 0; // 시작 지점 시간 0으로 설정

        for (int i = alp; i <= tA; i++) {
            for (int j = cop; j <= tC; j++) {
                if (i + 1 <= tA) { // 알고력 +1 경우
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }

                if (j + 1 <= tC) { // 코딩력 +1 경우
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 문제를 풀어서 능력치 올리기
                for (int[] problem : problems) {
                    int alp_req = problem[0];
                    int cop_req = problem[1];
                    int alp_rw = problem[2];
                    int cop_rw = problem[3];
                    int cost = problem[4];

                    if (i >= alp_req && j >= cop_req) {
                        int nA = Math.min(tA, i + alp_rw);
                        int nC = Math.min(tC, j + cop_rw);
                        dp[nA][nC] = Math.min(dp[nA][nC], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[tA][tC];
    }
}
