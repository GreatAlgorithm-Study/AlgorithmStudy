import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_회전하는_빙하 {

    static int n, m, q;
    static int[][] board;       // 빙하를 저장할 배열
    static int[][] tempBoard;   // 다음 빙하 결정에 영향을 주는 배열
    static boolean[][] visited;
    static int maxCnt = 0;      // 최대 군집의 크기
    static int sum = 0;         // 남은 빙하의 합
    static int[] dy = { 0, 1, -1, 0 };
    static int[] dx = { 1, 0, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = (int) Math.pow(2, n);
        q = Integer.parseInt(st.nextToken());
        board = new int[m][m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        int[] level = new int[q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++)
            level[i] = Integer.parseInt(st.nextToken());
        for (int t = 0; t < q; t++) {
            // 레벨이 1이상인 경우에만 회전
            if (level[t] > 0)
                rotate(level[t]); // 빙하의 회전 구현
            melt(); // 빙하의 녹음 구현
        }
        caclulate(); // 빙하의 최대 군집의 크기 계산 및 총합 계산
        System.out.println(sum);
        System.out.println(maxCnt);
    }

    // 빙하의 회전 구현
    private static void rotate(int l) {
        int M = (int) Math.pow(2, l);
        int N = (int) Math.pow(2, l - 1);
        tempBoard = new int[m][m];
        for (int i = 0; i < m; i += M) {
            for (int j = 0; j < m; j += M) {
                // 각 꼭짓점을 기준으로 덩어리들이 움직임
                move(i, j, N, 0);
                move(i, j + N, N, 1);
                move(i + N, j, N, 2);
                move(i + N, j + N, N, 3);
            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                board[i][j] = tempBoard[i][j];
    }

    // 빙하의 움직임 구현
    private static void move(int sy, int sx, int N, int dir) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int y = (sy + i) + N * dy[dir];
                int x = (sx + j) + N * dx[dir];
                tempBoard[y][x] = board[sy + i][sx + j];
            }
    }

    // 빙하의 녹음 구현
    private static void melt() {
        tempBoard = new int[m][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                if (board[i][j] > 0) {
                    int cnt = 0;
                    // 주변 빙하의 수 계산
                    for (int k = 0; k < 4; k++) {
                        int y = i + dy[k];
                        int x = j + dx[k];
                        if (isValid(y, x) && board[y][x] > 0)
                            cnt++;
                    }
                    // 3개 미만이면 빙하 감소
                    if (cnt < 3)
                        tempBoard[i][j]--;
                }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                board[i][j] += tempBoard[i][j];
    }

    // 군집 크기 및 총합 계산
    private static void caclulate() {
        visited = new boolean[m][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                if (!visited[i][j] && board[i][j] > 0) {
                    // 빙하 군집의 크기를 계산하기 위한 BFS
                    int cnt = bfs(i, j);
                    maxCnt = Math.max(maxCnt, cnt);
                }
    }

    // BFS
    private static int bfs(int y, int x) {
        int cnt = 0;
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] { y, x });
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            if (visited[cur[0]][cur[1]])
                continue;
            visited[cur[0]][cur[1]] = true;
            cnt++;  // 빙하 수 카운트
            sum += board[cur[0]][cur[1]]; // 총 합 증가
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if (isValid(ny, nx) && !visited[ny][nx] && board[ny][nx] > 0) {
                    dq.offer(new int[] { ny, nx });
                }
            }
        }
        return cnt;
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < m && 0 <= x && x < m;
    }
}