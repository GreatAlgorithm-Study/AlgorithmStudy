import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_2629 {
    static int N;
    static int[] weight;
    static boolean[][] dp;

    private static void recursive(int idx, int w) {
        if (idx > N || dp[idx][w]) return;
        dp[idx][w] = true;
        recursive(idx + 1, w + weight[idx]);            // 새로운 추를 기존 무게와 더할 경우
        recursive(idx + 1, Math.abs(w - weight[idx]));     // 새로운 추를 기존 무게와 뺄 경우
        recursive(idx + 1, w);                                 // 새로운 추를 선택하지 않을 경우
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int mx = N * 500;                       // 주어진 구슬로 확인 가능한 최대 무게
        weight = new int[N+1];
        dp = new boolean[N + 1][(N * 500) + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        recursive(0, 0);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int mv = Integer.parseInt(st.nextToken());
            if (mv > mx || !dp[N][mv]) sb.append("N").append(" ");
            else sb.append("Y").append(" ");
        }
        System.out.println(sb);
    }
}
