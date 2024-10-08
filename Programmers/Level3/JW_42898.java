class Solution {
    final int MOD = 1_000_000_007; // 나머지 연산
    public int solution(int m, int n, int[][] puddles) {
        int[][] board = new int[n + 1][m + 1];
        // 웅덩이가 있는 위치 표시
        for (int[] puddle : puddles)
            board[puddle[1]][puddle[0]] = -1;
        board[0][1] = 1;    // 시작 위치의 위 혹은 왼쪽을 1로 초기화
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < m + 1; j++)
                // 현재 위치가 웅덩이가 아니라면
                if (board[i][j] != -1) {
                    int up = Math.max(0, board[i - 1][j]);  // 위쪽에서 오는 경우
                    int lf = Math.max(0, board[i][j - 1]);  // 왼쪽에서 오는  경우
                    board[i][j] = (up + lf) % MOD; // 가능한 경우의 수 계산
                }
        return board[n][m];
    }
}