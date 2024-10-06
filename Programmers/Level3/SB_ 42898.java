import java.util.*;

class Solution {
    private static int MOD = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        
        for(int[] p: puddles){
            dp[p[1]][p[0]] = -1;
        }
        
        dp[1][1] = 1;
        for(int i=1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(dp[i][j]==-1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] += (dp[i-1][j]+dp[i][j-1])%MOD;
            }
        }
        return dp[n][m]%MOD;
        
    }
}