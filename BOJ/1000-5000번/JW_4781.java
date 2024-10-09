import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = (int) Math.round(Double.parseDouble(st.nextToken()) * 100); // 계산을 편하게 하기 위해 정수화
            // 종료 조건
            if (n == 0 && m == 0)
                break;
            int[][] candies = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                candies[i][0] = Integer.parseInt(st.nextToken());
                candies[i][1] = (int) Math.round(Double.parseDouble(st.nextToken()) * 100); // 계산을 편하게 하기 위해 정수화
            }
            // 0-1 KnapSack
            int[] dp = new int[m + 1];
            for (int[] candy : candies)
                for (int i = candy[1]; i < m + 1; i++)
                    dp[i] = Math.max(dp[i], dp[i - candy[1]] + candy[0]);
            sb.append(dp[m]).append("\n");
        }
        System.out.println(sb);
    }
}