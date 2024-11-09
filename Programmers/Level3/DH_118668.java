import java.util.*;

public class DH_118668 {
    static public int solution(int alp, int cop, int[][] problems) {
        int answer = 0, maxAlpReq = 0, maxCopReq = 0;
        
        for(int i = 0; i < problems.length; i++) {
            maxAlpReq = Math.max(problems[i][0], maxAlpReq);
            maxCopReq = Math.max(problems[i][1], maxCopReq);
        }
        
        // dp[r][c]: 알고력이 r이고, 코딩력이 c가 되기 위해서 걸리는 최소 시간
        int[][] dp = new int[maxAlpReq + 1][maxCopReq + 1];
        
        for(int r = 0; r < dp.length; r++) Arrays.fill(dp[r], Integer.MAX_VALUE);
        
        // alp와 cop가 이미 최댓값을 넘었을 수도 있기 때문에 값 조정
        alp = Math.min(alp, maxAlpReq);
        cop = Math.min(cop, maxCopReq);
        
        dp[alp][cop] = 0;
        
        for(int r = alp; r < dp.length; r++) {
            for(int c = cop; c < dp[0].length; c++) {
                // 알고리즘을 공부해서 알고력을 1 높이는 경우
                if(r + 1 < dp.length) dp[r + 1][c] = Math.min(dp[r + 1][c], dp[r][c] + 1);

                // 코딩 공부를 해서 코딩력을 1 높이는 경우
                if(c + 1 < dp[0].length) dp[r][c + 1] = Math.min(dp[r][c + 1], dp[r][c] + 1);
                
                // 풀 수 있는 문제를 하나 풀어 알고력과 코딩력을 높이는 경우
                for(int k = 0; k < problems.length; k++) {
                    int alpReq = problems[k][0]; // 문제를 풀기 위해 필요한 알고력
                    int copReq = problems[k][1]; // 문제를 풀기 위해 필요한 코딩력
                    int alpRwd = problems[k][2]; // 문제를 풀었을 때 증가하는 알고력
                    int copRwd = problems[k][3]; // 문제를 풀었을 때 증가하는 코딩력
                    int cost = problems[k][4]; //문제를 푸는데 드는 시간
                    
                    if(r < alpReq || c < copReq) continue; // 현재 알고력이나 코딩력이 부족한 경우
                    
                    // 범위를 벗어나는 경우 continue를 하면 오답!
                    // 범위를 벗어나면 무조건 문제를 풀 수 있는 것이기 때문에 maxAlpReq, maxCopReq가 된 것이라고 생각해야 됨
                    // if(dp.length < r + alpRwd || maxCopReq < c + copRwd) continue; // 범위를 벗어가는 경우
                    // dp[r + alpRwd][c + copRwd] = Math.min(dp[r + alpRwd][c + copRwd], dp[r][c] + cost);
                    int nextAlp = Math.min(maxAlpReq, r + alpRwd);
                    int nextCop = Math.min(maxCopReq, c + copRwd);
                    
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[r][c] + cost);
                }
            }
        }
        
        answer = dp[maxAlpReq][maxCopReq];
        return answer;
    }
	public static void main(String[] args) {
		int alp = 10, cop = 10;
		int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
		System.out.println(solution(alp, cop, problems));
	}
}
