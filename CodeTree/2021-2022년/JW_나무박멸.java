import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JW_나무박멸 {

    static int n, m, k, c;
    static int[][] board, visited;
    static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 }, dx = { 0, 0, -1, 1, -1, 1, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        int total = 0;
        while (m-- > 0) {
            grow();
            breed();
            int[] target = find();
            total += target[2];
            if (target[0] == -1 && target[1] == -1)
                break;
            kill(target);
            heal();
        }
        System.out.println(total);
    }

    private static void heal() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (visited[i][j] > 0)
                    visited[i][j]--;
    }

    private static void grow() {
        int[][] dBoard = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d], nx = j + dx[d];
                        if (isValid(ny, nx) && board[ny][nx] > 0)
                            dBoard[i][j]++;
                    }
                }
            }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] += dBoard[i][j];
    }

    private static void breed() {
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] dBoard = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d], nx = j + dx[d];
                        if (isValid(ny, nx) && visited[ny][nx] == 0 && board[ny][nx] == 0)
                            dq.offer(new int[] { ny, nx });
                    }
                    if (dq.isEmpty())
                        continue;
                    int amount = board[i][j] / dq.size();
                    while (!dq.isEmpty()) {
                        int[] pos = dq.poll();
                        dBoard[pos[0]][pos[1]] += amount;
                    }
                }
            }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] += dBoard[i][j];
    }

    private static int[] find() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> o1[2] != o2[2] ? o2[2] - o1[2] : o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] > 0) {
                    int sum = board[i][j];
                    for (int d = 4; d < 8; d++) {
                        for (int l = 1; l <= k; l++) {
                            int ny = i + dy[d] * l, nx = j + dx[d] * l;
                            if (isValid(ny, nx) && board[ny][nx] > 0)
                                sum += board[ny][nx];
                            else
                                break;
                        }
                    }
                    pq.offer(new int[] { i, j, sum });
                }
        if (pq.isEmpty())
            return new int[] { -1, -1, 0 };
        return pq.poll();
    }

    private static void kill(int[] target) {
        int y = target[0], x = target[1];
        board[y][x] = 0;
        visited[y][x] = c + 1;
        for (int d = 4; d < 8; d++) {
            for (int l = 1; l <= k; l++) {
                int ny = y + dy[d] * l, nx = x + dx[d] * l;
                if (isValid(ny, nx)) {
                    visited[ny][nx] = c + 1;
                    if (board[ny][nx] <= 0) {
                        break;
                    } else {
                        board[ny][nx] = 0;
                    }
                }
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}
