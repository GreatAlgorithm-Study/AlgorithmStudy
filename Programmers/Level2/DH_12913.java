/*
땅따먹기
 */

public class DH_12913 {

    class Solution {
        int solution(int[][] land) {
            int answer = 0;

            int R = land.length, C = land[0].length;
            int dp[][] = new int[R + 1][C];

            for(int r = 1; r < R + 1; r++) {
                for(int c1 = 0; c1 < C; c1++) {
                    for(int c2 = 0; c2 < C; c2++) {
                        if(c1 == c2) continue;
                        dp[r][c1] = Math.max(land[r - 1][c1] + dp[r - 1][c2], dp[r][c1]);
                        answer = Math.max(answer, dp[r][c1]);
                    }
                }
            }

            return answer;
        }
    }
}
