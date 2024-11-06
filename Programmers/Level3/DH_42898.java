/*
등굣길
 */

class DH_42898 {
    static final int MOD = 1_000_000_007;
    static int[] dr = {0, -1}, dc = {-1, 0};
    static int[][] dp; // 해당 지점까지 갈 수 있는 경우의 수를 저장하는 변수
    static boolean[][] existPuddle; // 물에 잠긴 지역 확이하는 변수

    static boolean check(int r, int c) {
        return r >= 0 && r < dp.length && c >= 0 && c < dp[0].length;
    }

    public int solution(int m, int n, int[][] puddles) {
        existPuddle = new boolean[n][m];

        // 물에 잠긴 지역 체크
        for(int[] p: puddles) existPuddle[p[1] - 1][p[0] - 1] = true;

        dp = new int[n][m];

        dp[0][0] = 1;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                for(int d = 0; d < 2; d++) {
                    int prevR = r + dr[d];
                    int prevC = c + dc[d];

                    // 해당 지점 기준 가능한 이전 지점에서의 가지수 더해주기
                    if(!check(prevR, prevC) || existPuddle[prevR][prevC]) continue;
                    dp[r][c] = (dp[r][c] + dp[prevR][prevC]) % MOD;
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}