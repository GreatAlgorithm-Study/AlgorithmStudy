import java.io.*;

public class YJ_2073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] DP = br.readLine().split("\\s");
        int D = Integer.parseInt(DP[0]);
        int P = Integer.parseInt(DP[1]);

        int[] length = new int[P];
        int[] capacity = new int[P];
        for(int i=0; i<P; i++){
            String[] data = br.readLine().split("\\s");
            length[i] = Integer.parseInt(data[0]);
            capacity[i] = Integer.parseInt(data[1]);
        }

        int[] dp = new int[D+1]; //dp[특정거리] = 최대 용량
        dp[0] = Integer.MAX_VALUE;  //초기 상태에서의 최소 용량을 설정하기 위해서 최대 정수를 설정

        // 특정 거리에 만들 수 있는 수도관(여러 파이프들로 구성) 중 가장 큰 용량 구하기
        for(int i=0; i<P; i++){
            for(int distance=D; distance>=length[i]; distance--){
                                                //특정 거리에 만들 수 있는 수도관(여러 파이프들로 구성) 중 가장 큰 용량 구하기
                dp[distance] = Math.max(dp[distance], Math.min(capacity[i],dp[distance-length[i]]));
            }
        }

        System.out.println(dp[D]);
    }
}