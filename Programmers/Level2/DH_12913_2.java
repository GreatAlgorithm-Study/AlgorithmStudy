/*
 * 땅따먹기 
 */

public class DH_12913_2 {
	class Solution {
		int solution(int[][] land) {
	        int answer = 0;

	        int[][] dp = new int[land.length + 1][land[0].length];

	        for(int r = 1; r < dp.length; r++) {
	            for(int c = 0; c < land[0].length; c++) {
	                for(int c2 = 0; c2 < land[0].length; c2++) {
	                    if(c == c2) continue;
	                    dp[r][c] = Math.max(dp[r][c], dp[r - 1][c2] + land[r - 1][c]);
	                    answer = Math.max(dp[r][c], answer);
	                }
	            }
	        }

	        return answer;
	    }
	}
}