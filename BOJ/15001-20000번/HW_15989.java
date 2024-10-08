import java.io.*;
// 시간 복잡도 : n<=10,000, O(N)
// n을 1, 2, 3의 합으로 나타내는 방법
public class HW_15989 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dp[] = new int[10001];
        dp[0] =1;  // 초기값 선언

        for(int i=1; i<=3; i++) {
            for(int j=i; j<10001; j++) {
                dp[j] += dp[j-i];
            }
        }

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
        br.close();
    }
}
