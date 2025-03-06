import java.util.*;
class JY_389480 {
    
    static final int INF = 1000;
    static int[][] dp;
    static int SIZE, M;
    
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        SIZE = info.length;
        M = m;
        
        dp = new int[SIZE][M];
        // dp[i][j] : i번째 물건까지 탐색했고 B의 흔적이 j일때, A의 최소 흔적
        // -1로 초기화 : 방문 체크 여부
        for(int i=0; i<SIZE; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        answer = dfs(info, 0, 0);
        // 최소값이 n보다 크다면 불가능
        if(answer >= n) return -1;
        return answer;
    }
    public static int dfs(int[][] info, int i, int j) {
        // B가 훔친 것이 M이상이면 불가능 stop
        if(j >= M) return INF;
        // 모든 물건 탐색 -> 더이상 탐색할 물건 없음
        if(i == info.length) return 0;
        
        // 이미 i번쨰 물건을 B가 훔쳤음(== 이미 최솟값 갱신됨)
        if(dp[i][j] != -1) return dp[i][j];
        

        int a = info[i][0];
        int b = info[i][1];
        
        // A가 훔치는 경우
        int ca = dfs(info, i+1, j) + a;
        // B가 훔치는 경우
        int cb = dfs(info, i+1, j+b);
        
        return dp[i][j] = Math.min(ca, cb);
    }
}