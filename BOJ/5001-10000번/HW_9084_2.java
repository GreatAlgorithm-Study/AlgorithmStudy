import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도 : N! (<=20) 시간 초과 -> dp로 중복 계산 줄이기

class HW_9084_2{
    static int[] coins;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1; // 0원
            for(int i=0; i<N; i++ ){
                for(int j=coins[i]; j<M+1; j++){
                    dp[j] += dp[j - coins[i]]; // 금액 j만들 때 동전(coin)추가
                }
            }
            System.out.println(dp[M]);
        }
    }
}