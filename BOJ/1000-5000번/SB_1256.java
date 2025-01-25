import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_1256 {
    static int N, M, K;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[N+1][M+1];

        // dp배열 만들기
        dp[0][0] = 1;                       // 아무것도 못만드는 경우 1개
        for (int i = 1; i <= N; i++) {      // a만으로 이루어진 경우
            dp[i][0] = 1;
        }
        for (int j = 1; j <= M; j++) {      // z만으로 이루어진 경우
            dp[0][j] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1], K);     // a를 앞에 추가해서 만들기 + z를 앞에 추가해서 만들기
            }
        }

        // K가 범위 벗어나면 -1
        if (dp[N][M] < K) {
            System.out.println(-1);
            return;
        }

        // 사전순으로 문자열 만들기
        while (N > 0 && M > 0) {
            long tmp = dp[N - 1][M];         // a로 시작하는 문자열의 개수
            if (K <= tmp) {                 // K가 tmp(a로 시작하는 범위의 개수)에 속할경우
                N -= 1;                     // 현재 tmp에서 N=-1해서 다음 경우 살피기 (현재 자리값 고정, 다음 자리 확인)
                sb.append('a');             // a로 시작하기에 a붙이기
            } else {                        // K가 tmp보다 클 경우 z를 시작으로하는 범위에서 살피기
                M -= 1;                     // M=-1해서 다음 경우 살피기
                K -= tmp;                   // K에서 제외된 tmp값 빼주기
                sb.append('z');             // z로 시작하기에 z붙이기
            }
        }

        // 남은 문자 처리
        while (N > 0) {
            sb.append('a');
            N--;
        }

        while (M > 0) {
            sb.append('z');
            M--;
        }

        System.out.println(sb);

    }
}
