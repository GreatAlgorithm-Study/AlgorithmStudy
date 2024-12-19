import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SB_1992 {
    static int N;
    static int[][] board;

    private static boolean check(int n, int sx, int sy) {
        int num = board[sx][sy];
        for (int i = sx; i < sx+n; i++) {
            for (int j = sy; j < sy+n; j++) {
                if ((num!=board[i][j])) return false;
            }
        }
        return true;
    }

    private static StringBuilder dfs(int n, int sx, int sy, StringBuilder sb) {
        // 해당 영역이 모두 같은 숫자면 바로 리턴
        if (check(n, sx, sy)) return sb.append(board[sx][sy]);

        // 아니면 분할
        sb.append('(');
        int half = n >> 1;
        dfs(half, sx, sy, sb);
        dfs(half, sx, sy + half, sb);
        dfs(half, sx + half, sy, sb);
        dfs(half, sx + half, sy + half, sb);

        return sb.append(')');
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(dfs(N, 0, 0, new StringBuilder()));
    }
}
