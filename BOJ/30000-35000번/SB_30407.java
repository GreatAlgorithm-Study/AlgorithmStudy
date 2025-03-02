import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_30407 {
    static int N, H, D, K;
    static int[] attack;
    static int ans = 0;

    private static void dfs(int depth, int hp, int d, int atk, boolean isUse) {
        if (depth == N) {
            ans = Math.max(ans, hp);
            return;
        }
        if (hp <= ans || hp <= 0) return;

        int wounk = Math.max(0, atk - d);
        dfs(depth + 1, hp - (wounk / 2), d, attack[depth + 1], isUse);

        int run = Math.max(0, atk - (d + K));
        dfs(depth + 1, hp - run, d + K, attack[depth + 1], isUse);

        if (!isUse) {
            int fright = Math.max(0, atk - d);
            dfs(depth + 1, hp - fright, d, 0, true);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());       // 체력
        D = Integer.parseInt(st.nextToken());       // 거리
        K = Integer.parseInt(st.nextToken());       // 이동 거리

        attack = new int[N + 1];
        for (int i = 0; i < N; i++) {
            attack[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, H, D, attack[0], false);
        System.out.println(ans <= 0 ? -1 : ans);
    }
}
