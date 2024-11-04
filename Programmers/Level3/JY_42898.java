import java.util.*;

class JY_42898 {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n+1][m+1];
        boolean[][] p = new boolean[n+1][m+1];
        
        // 잠긴 곳의 좌표를 2차원 배열로 옮기기
        for(int[] puddle: puddles) {
            p[puddle[1]][puddle[0]] = true;
        }
        
        dp[0][1] = 1;   // 시작점 (1,1)에 1을 대입하기 위한 초기화
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<m+1; j++) {
                if(p[i][j]) continue;
                // 왼쪽에서 오는 방법 + 위에서 내려오는 방법
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1_000_000_007;
            }
        }
        // for(int i=0; i<n+1; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        
        answer = dp[n][m] % 1_000_000_007;
        
        return answer;
    }
}