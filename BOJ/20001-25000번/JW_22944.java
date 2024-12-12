import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_22944 {

    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][n];
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'S') {
                    dq.offer(new int[] { i, j, 0, h, 0 });
                    visited[i][j] = h;
                }
            }
        }
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
                int nh = cur[3], nd = cur[4];
                if (isValid(ny, nx)) {
                    if (board[ny][nx] == 'E') {
                        System.out.println(cur[2] + 1);
                        return;
                    }
                    if (board[ny][nx] == 'U') {
                        board[ny][nx] = '.';
                        nd = d;
                    }
                    if (nd > 0) {
                        nd--;
                    } else {
                        nh--;
                    }
                    if (visited[ny][nx] < nh + nd) {
                        visited[ny][nx] = nh + nd;
                        dq.offer(new int[] { ny, nx, cur[2] + 1, nh, nd });
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}