import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_3109 {
    static int R, C;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    private static boolean backtracking(int r, int c) {
        if (c == C - 1) return true;

        for (int i = 0; i < 3; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (!isValid(nr, nc) || board[nr][nc]=='x' || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            if (backtracking(nr, nc)) return true;      // 그냥 함수값을 바로 리턴하면 실패했을때 더 못돌아봄
        }
        return false;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int ans = 0;
        for (int r = 0; r < R; r++) {
            if(backtracking(r, 0)) ans++;
        }
        System.out.println(ans);
    }
}
