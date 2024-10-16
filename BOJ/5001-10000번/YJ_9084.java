import java.io.*;

public class YJ_9084 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            String[] c = br.readLine().split("\\s");
            for(int i=0; i<N; i++){
                coins[i] = Integer.parseInt(c[i]);
            }
            int M = Integer.parseInt(br.readLine());

            System.out.println(dp(coins,M));
        }
    }

    static int dp(int[] coins, int M){
        int[] dp = new int[M+1];
        dp[0] = 1;

        for(int coin : coins){
            for(int i=0; i<M+1; i++){
                if(coin>i){
                    continue;
                }
                dp[i] += dp[i-coin];
            }
        }
        return dp[M];
    }
}
