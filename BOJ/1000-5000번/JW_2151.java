import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class JW_2151 {

    static int n, sy, sx, sd, ey, ex;
    static char[][] board;
    static boolean[][] visited;
    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        visited = new boolean[n][n];
        boolean find = false;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == '#')
                    if (!find) {
                        sy = i;
                        sx = j;
                        find = true;
                    } else {
                        ey = i;
                        ex = j;
                    }
            }
        }
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] { sy, sx, 0 });
        visited[sy][sx] = true;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0], x = cur[1], cnt = cur[2];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                // 직선 움직임
                while (isValid(ny, nx) && board[ny][nx] != '*') {
                    // 거울을 놓을 수 있는 위치라면 큐 삽입
                    if (!visited[ny][nx] && board[ny][nx] == '!') {
                        visited[ny][nx] = true;
                        dq.offer(new int[] { ny, nx, cnt + 1 });
                    // 종료 조건
                    } else if (ny == ey && nx == ex) {
                        System.out.println(cnt);
                        return;
                    }
                    ny = ny + dy[i];
                    nx = nx + dx[i];
                }
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}
