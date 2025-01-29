import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class JW_1938 {

    static int n;
    static boolean[][] board;
    static int[][][] visited;
    static int[] dy = { 1, -1, 0, 0 }, dx = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new boolean[n][n];
        visited = new int[n][n][2];
        int[][] bArr = new int[3][2];
        int[][] eArr = new int[3][2]; // 상태 포함 방문처리
        int bIdx = 0, eIdx = 0;
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                visited[i][j] = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };
                if (line[j] == 'B')
                    bArr[bIdx++] = new int[] { i, j };
                else if (line[j] == 'E')
                    eArr[eIdx++] = new int[] { i, j };
                else if (line[j] == '1')
                    board[i][j] = true;
            }
        }
        int sy = bArr[1][0], sx = bArr[1][1], st = bArr[0][0] == bArr[1][0] ? 0 : 1; // 처음 위치의 중심점 및 상태 저장
        int ey = eArr[1][0], ex = eArr[1][1], et = eArr[0][0] == eArr[1][0] ? 0 : 1; // 최종 위치의 중심점 및 상태 저장
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] { sy, sx, st });
        visited[sy][sx][st] = 0;
        // BFS 진행
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0], x = cur[1], t = cur[2];
            // 종료 조건
            if (y == ey && x == ex && t == et) {
                System.out.println(visited[y][x][t]);
                return;
            }
            // 이동 조건
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                // 유효한 범위이며, 기존 방문보다 빠를 경우 이동
                if (isValid(ny, nx, t) && visited[y][x][t] + 1 < visited[ny][nx][t]) {
                    dq.offer(new int[] { ny, nx, t });
                    visited[ny][nx][t] = visited[y][x][t] + 1;
                }
            }
            // 회전 조건
            if (canRotate(y, x)) {
                int nt = t == 0 ? 1 : 0; // 회전
                // 유효한 범위이며, 회전이 가능할 경우 이동
                if (visited[y][x][t] + 1 < visited[y][x][nt]) {
                    dq.offer(new int[] { y, x, nt });
                    visited[y][x][nt] = visited[y][x][t] + 1;
                }
            }
        }
        System.out.println(0);
    }

    // 회전이 가능한지 체크
    private static boolean canRotate(int y, int x) {
        // 중심점(y, x)을 기준으로 3x3에 나무가 있는지 확인 
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                int ny = y - 1 + i, nx = x - 1 + j;
                if (!isValid(ny, nx) || board[ny][nx])
                    return false;
            }
        return true;
    }

    // 통나무의 상태에 따라 이동할 수 있는지 체크
    private static boolean isValid(int y, int x, int t) {
        // 가로
        if (t == 0) {
            if (isValid(y, x - 1) && isValid(y, x) && isValid(y, x + 1)) // 유효한 범위이며
                if (!board[y][x - 1] && !board[y][x] && !board[y][x + 1]) // 부딪히는 나무가 없을 경우
                    return true; // 이동 가능
        // 세로
        } else {
            if (isValid(y - 1, x) && isValid(y, x) && isValid(y + 1, x))
                if (!board[y - 1][x] && !board[y][x] && !board[y + 1][x])
                    return true;
        }
        return false;
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}
