import java.util.*;

public class YJ_118668 {
    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};
        System.out.println(solution(alp,cop,problems));
    }

    static class Problem {
        int alpReq;
        int copReq;
        int alpRwd;
        int copRwd;
        int cost;

        public Problem(int alpReq, int copReq, int alpRwd, int copRwd, int cost) {
            this.alpReq = alpReq;
            this.copReq = copReq;
            this.alpRwd = alpRwd;
            this.copRwd = copRwd;
            this.cost = cost;
        }
    }

    static List<Problem> list = new ArrayList<>();
    static final int MAX_TIME = 300;
    static int[][] dp;

    static public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        for(int[] problem : problems){
            list.add(new Problem(problem[0], problem[1], problem[2], problem[3], problem[4]));
            maxAlp = Math.max(maxAlp,problem[0]);
            maxCop = Math.max(maxCop,problem[1]);
        }
        //모든 문제 풀 수 있는 알고력,코딩력 충족
        if(alp >= maxAlp && cop >= maxCop){
            return 0;
        }

        dp = new int[maxAlp+2][maxCop+2];
        for(int[] d : dp){
            Arrays.fill(d,MAX_TIME);
        }
        //현재 알고력과 코딩력이 목표치 보다 높을 경우 보정하기
        if(alp > maxAlp){
            alp = maxAlp;
        }
        if(cop > maxCop){
            cop = maxCop;
        }
        dp[alp][cop] = 0;

        calculateDp(alp, cop, maxAlp, maxCop);
        return dp[maxAlp][maxCop];
    }

    static void calculateDp(int alp, int cop, int maxAlp, int maxCop){
        for(int i=alp; i<maxAlp+1; i++){
            for(int j=cop; j<maxCop+1; j++){
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);

                for(Problem problem : list){
                    if(i < problem.alpReq || j < problem.copReq){
                        continue;
                    }
                    int solvedAlp = i + problem.alpRwd;
                    int solvedCop = j + problem.copRwd;

                    if(solvedAlp >= maxAlp && solvedCop >= maxCop){
                        dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[i][j]+problem.cost);
                    }
                    else if(solvedAlp >= maxAlp){
                        dp[maxAlp][solvedCop] = Math.min(dp[maxAlp][solvedCop], dp[i][j]+problem.cost);
                    }else if(solvedCop >= maxCop){
                        dp[solvedAlp][maxCop] = Math.min(dp[solvedAlp][maxCop], dp[i][j]+problem.cost);
                    }
                    else { //solvedAlp < maxAlp && solvedCop < maxCop
                        dp[solvedAlp][solvedCop] = Math.min(dp[solvedAlp][solvedCop], dp[i][j]+problem.cost);
                    }
                }

            }//j
        }//i
    }

}
