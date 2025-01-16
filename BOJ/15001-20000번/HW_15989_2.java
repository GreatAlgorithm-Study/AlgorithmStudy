import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// n을 1, 2, 3의 합으로 나타내는 방법의 수 구하기
// 시간 복잡도 : n<=10,000 O(N^2) 이하로 풀기 -> O(N)으로 풀이
// dp -> 작은 문제 누적 -> 큰 문제
class HW_15989_2{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // tk

        while(T-->0){
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[n+1]; // dp[i] : i를 1, 2, 3의 합으로 만드는 방법의 수

            dp[0] = 1;
            // dp[i-num] : i-num을 1, 2, 3으로 만드는 방법 수
            for(int num=1; num<=3; num++){ // 작은 수부터 쌓으며 중복 방지
                for(int i=num; i<=n; i++){
                    dp[i] = dp[i-num] + dp[i];
                }
            }
            System.out.println(dp[n]);
            // dp[i][j] = dp[i][1] + dp[i][2] + dp[i][3]
            // 1, 2, 3을 몇번 썼는지 따지지않기 때문에 2차원 필요 없었음
        }
    }
}