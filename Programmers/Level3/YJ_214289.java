import java.util.Arrays;

public class YJ_214289 {
    static int min;
    static int max;
    static public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        final int MAX = 1_000_001;
        min = t1 + 10;
        max = t2 + 10;
        temperature += 10;

        //특정 시간에 특정온도일 때 필요한 최소 전력 찾기
        int length = onboard.length;
        int[][] dp = new int[length][51];
        for(int i=0; i<length; i++){
            Arrays.fill(dp[i],MAX);
        }
        //"현재(0분) 실내온도는 실외온도와 같습니다." > 0분일때 실외온도와 같고, 에어컨도 안킨상태니까 전력소비가 없음
        dp[0][temperature] = 0;

        //i i-1 / j+1 j j-1 조합에 따라서 dp 점화식 세우기
        //i 는 시간 / j 는 온도
        for(int i=0; i<length-1; i++){
            for(int j=0; j<51; j++){
                if(cantOnboard(onboard[i], j)){
                    continue;
                }

                //에어컨 ON: 온도 유지 > b 에너지 소비
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+b);
                //에어컨 ON: 온도 내림 > a 에너지 소비
                if(j > 0){
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+a);
                }
                //에어컨 ON: 온도 올림 > a 에너지 소비
                if(j < 50){
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+a);
                }

                //에어컨 OFF: 온도 유지 > 0 에너지 소비
                if(temperature == j){
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                }
                //에어컨 OFF: 실외온도 보다 현재 온도가 높을 때 온도 내림 > 0 에너지 소비
                else if(temperature < j){
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
                }
                //에어컨 OFF: 실외온도 보다 현재 온도가 낮을 때 온도 올림 > 0 에너지 소비
                else if(temperature > j){
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
                }
            }
        }

        int answer = MAX;
        for(int i=0; i < 51; i++){
            if(cantOnboard(onboard[length-1],i)){   //마지막에 손님이 있을 때 적정온도가 아니라면 통과X
                continue;
            }
            answer = Math.min(answer,dp[length-1][i]);
        }

        return answer;
    }

    //손님이 있을 때는 적정온도가 t1~t2 사이여야함
    static boolean cantOnboard (int customer, int temperature){
        return customer == 1 && (temperature < min || temperature > max);
    }
}
