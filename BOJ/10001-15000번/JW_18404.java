import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    // 나이트의 움직임
    static int[] dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
    static int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int n;

    public static void main(String[] args) throws Exception {
        n = read();
        int m = read();
        int x = read(), y = read();             // 초기 나이트
        int[][] EArr = new int[m][2];           // 상대 말의 위치를 저장할 배열
        for (int i = 0; i < m; i++) {
            int A = read(), B = read();
            EArr[i] = new int[] { A, B };       // 상대 말의 위치 저장
        }
        int[][] board = new int[n + 1][n + 1];  // 각 좌표의 최소 시간을 저장할 배열
        for (int i = 0; i < n + 1; i++)
            Arrays.fill(board[i], -1);          // 방문 체크를 위해 -1로 초기화
        // BFS
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] { x, y });
        board[x][y] = 0;                        // 초기 방문체크
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();              // 현재 나이트의 위치
            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                // 다음 위치로 이동할 수 있고, 방문하지 않았다면
                if (isValid(nx, ny) && board[nx][ny] == -1) {
                    dq.offer(new int[] { nx, ny });
                    board[nx][ny] = board[cur[0]][cur[1]] + 1; // 이전 위치의 최소 시간 + 1
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        // 적들의 위치를 순회하면서 최소 시간을 출력
        for (int[] E : EArr)
            sb.append(board[E[0]][E[1]]).append(" ");
        System.out.println(sb);
    }

    // 경계 체크
    private static boolean isValid(int x, int y) {
        return 0 < x && x <= n && 0 < y && y <= n;
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}