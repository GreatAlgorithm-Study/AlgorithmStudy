import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 가게에 있는 사탕의 개수
            double temp = Double.parseDouble(st.nextToken()); // 가지고 있는 돈의 양

            if(n ==0 && temp == 0.00){ // 종료 조건
                System.out.println(sb);
                return;
            }

            int m = (int) (temp * 100 + 0.5); // 변환하는 이유 : rounding error

            int[] dp = new int[m+1];

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken()); // 칼로리
                int p = (int) (Double.parseDouble(st.nextToken()) * 100.0 + 0.5); // 가격

                for(int j=0; j<=m; j++){
                    if(j-p >=0){
                        dp[j] = Math.max(dp[j], dp[j - p] + c);
                    }
                }
            }
            sb.append(dp[m]).append("\n");
        }

    }
}

