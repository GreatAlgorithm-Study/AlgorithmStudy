import java.util.*;

class SB_389480 {
    public int solution(int[][] info, int n, int m) {
        int INF = 1000;
        int idx = info.length;
        
        int[][] dp = new int[idx+1][m];     // dp[i][j]=k, i만큼 물건을 훔칠때 B의 흔적개수가 j, 이때 A의 최소 흔적 개수
        for(int i=0; i<=idx; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        
        for(int i=1; i<=idx; i++){
            int a = info[i-1][0];       // 현재 물건을 훔칠때 a,b의 각 흔적
            int b = info[i-1][1];
            for(int j=0; j<m; j++){
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+a);
                if(j+b < m){
                    dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
                }
            }
        }
        
        int mn = INF;
        for(int j=0; j<m; j++){
            mn = Math.min(mn, dp[idx][j]);
        }
        return mn >= n ? -1 : mn;
        
    }
}