import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int N = land.length;
        int[][] dp = new int[N][4];
        
        // 초기화
        for(int j=0; j<4; j++){
            dp[0][j] = land[0][j];
        }
        
        for(int i=1; i<N; i++){
            for(int j=0; j<4; j++) {
                for(int k = 0; k<4; k++){
                    if(j != k){
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][k]+land[i][j]);
                    }
                }
            }
        }
        

        for(int j=0; j<4; j++){
            answer = Math.max(answer, dp[N-1][j]);
        }

        return answer;
    }
}