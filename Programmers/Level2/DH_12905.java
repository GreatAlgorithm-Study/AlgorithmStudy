/*
가장 큰 정사각형
 */

public class DH_12905 {
    public int solution(int [][]board) {
        int[][] dp = new int[board.length][board[0].length];

        int answer = 0;

        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(board[r][c] == 0) continue;
                if(r == 0 || c == 0) dp[r][c] = 1;
                else {
                    dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r][c - 1], dp[r - 1][c])) + 1;
                }

                answer = Math.max(answer, dp[r][c]);
            }
        }

        return answer * answer;
    }
}