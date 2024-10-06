// 시간 복잡도 : 주어진 내에서 구현 가능

import java.util.*;
import java.io.*;

// 오른쪽과 아래만 움직여서
// 집에서 학교까지 갈 수 있는 최단경로의 개수
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int dp[][] = new int[n+1][m+1];
        int mod = 1000000007;
        for(int i=0; i<puddles.length; i++){
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }
        dp[1][1] = 1; // (1, 1) 설정

        for(int i=1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(dp[i][j] == -1){ // 물에 잠긴 지역이라면
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] += (dp[i-1][j]+dp[i][j-1])%MOD;
            }
        }

        return dp[n][m];
    }
}