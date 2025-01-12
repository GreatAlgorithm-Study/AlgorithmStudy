class HW_12905 {
    public int solution(int [][]board) {
        int r = board.length;
        int c = board[0].length;
        int[][] dp = new int[r][c];
        int ans = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j]==1){
                    if(i==0 || j==0){
                        dp[i][j] = 1; // 1x1
                    } else{ // 정사각형임으로 가장 작은 변을 선택, +1 : 현재 칸을 포함하여 정사각형 크기 확장
                        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans * ans;
    }
}