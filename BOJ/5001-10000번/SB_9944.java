import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_9944 {
    static int N, M;
    static char[][] board;
    static int mn;
    static final int INF = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static boolean isAllVisited(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) return false;
            }
        }
        return true;
    }

    private static void dfs(int x, int y, int cnt, boolean[][] visited) {
        if (cnt >= mn) return;      // 가지 치기

        boolean canMove = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isValid(nx, ny) || visited[nx][ny]) continue;
            canMove = true;

            while (isValid(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
                nx += dx[i];
                ny += dy[i];
            }

            nx -= dx[i];                        // 위에서 한 칸 삐져나오니까 한칸 뒤로가기
            ny -= dy[i];

            dfs(nx, ny, cnt + 1, visited);  // 한 방향 당 cnt++

            while (nx != x || ny != y) {        // 처음위치까지 이동
                visited[nx][ny] = false;
                nx -= dx[i];
                ny -= dy[i];
            }
        }

        if (!canMove && isAllVisited(visited)) {        // 더이상 움직일 수 없고 모든 칸 방문 시 mn 업데이트
            mn = Math.min(mn, cnt);
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String line;
        int turn = 1;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new char[N][M];
            boolean[][] visited = new boolean[N][M];
            mn = INF;

            for (int i = 0; i < N; i++) {
                line = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = line.charAt(j);
                    if (board[i][j] == '*') visited[i][j] = true;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == '*' || visited[i][j]) continue;
                    visited[i][j] = true;
                    dfs(i, j, 0, visited);
                    visited[i][j] = false;
                }
            }
            sb.append("Case ").append(turn++).append(": ").append(mn == INF ? -1 : mn).append("\n");
        }

        System.out.println(sb);
    }
}
