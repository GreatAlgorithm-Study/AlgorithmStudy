public class YJ_42898 {
     int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        final int CONDITION = 1000000007;
        //물웅덩이 초기화 [1] x, [0] y
        for(int i=0; i<puddles.length; i++){
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }

        dp[1][1] = 1;
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                //물웅덩이가 있을 경우 0으로 초기화
                if(dp[i][j] == -1){
                    continue;
                }
                //이때 특정지점을 구하는 방법은 이전 방향인 `왼쪽[j-1] +위[i-1] = 특정지점`
                if(dp[i-1][j] > 0){ //위에서 오는 방향
                    dp[i][j] = (dp[i-1][j]+dp[i][j]) % CONDITION;
                }
                if(dp[i][j-1] > 0){ //왼쪽에서 오는 방향
                    dp[i][j] = (dp[i][j] + dp[i][j-1]) % CONDITION;
                }
            }
        }

        return dp[n][m];
    }
}
