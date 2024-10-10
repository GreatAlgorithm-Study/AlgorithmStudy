import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_4781 {
    static int N;
    static int M;

    private static int calMx(int[] cal, int[] price) {      // cal: 가치, price: 무게
        int[] dp = new int[M+1];    // dp[i] = i원으로 얻을 수 있는 최대 칼로리
        for (int i = 1; i <= N; i++) {
            for (int j = price[i]; j <= M; j++) {
                dp[j] = Math.max(dp[j], dp[j - price[i]] + cal[i]);     // 사탕 구매X, 사탕 추가했을 때
            }
        }
        return dp[M];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());               // 사탕 수
            M = (int)(Double.parseDouble(st.nextToken())*100+0.5);    // 돈

            if (N==0 && M==0) break;

            // 배낭 초기화
            int[] cal = new int[N + 1];
            int[] price = new int[N + 1];
            for (int i=1; i<N+1; i++){
                st = new StringTokenizer(br.readLine());
                cal[i] = Integer.parseInt(st.nextToken());
                price[i] = (int)(Double.parseDouble(st.nextToken())*100+0.5);
            }
            sb.append(calMx(cal, price)).append('\n');
        }
        System.out.println(sb);
    }
}
