import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_1194 {

    static int n, m;
    static char[][] board;

    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        int sy = 0, sx = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                // 초기 위치 설정
                if (board[i][j] == '0') {
                    sy = i;
                    sx = j;
                }
            }
        }
        // 열쇠 상태에 따라 방문 체크를 해주기 위해서 3차원 방문 체크 생성
        boolean[][][] visited = new boolean[n][m][1 << 6];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] { sy, sx, 0, 0 });
        visited[sy][sx][0] = true;
        
        // BFS
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            // 현재 좌표, 열쇠 상태, 움직임 횟수
            int y = cur[0], x = cur[1], keys = cur[2], moveCnt = cur[3];
            // 종료 조건
            if (board[y][x] == '1') {
                System.out.println(moveCnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                int newKeys = keys;
                
                // 이동 조건
                if (!isValid(ny, nx) || board[ny][nx] == '#') {
                    continue;
                }

                // 열쇠일 경우
                if (isKey(ny, nx))
                    // 해당 열쇠 추가
                    newKeys |= (1 << (board[ny][nx] - 'a'));
                
                // 문일 경우, 열쇠가 없다면 이동 불가
                else if (isDoor(ny, nx) && (keys & (1 << (board[ny][nx] - 'A'))) == 0)
                    continue;

                // 현재 열쇠 상태에서 방문하지 않았다면 방문
                if (!visited[ny][nx][newKeys]) {
                    dq.offer(new int[] { ny, nx, newKeys, moveCnt + 1 });
                    visited[ny][nx][newKeys] = true;
                }
            }
        }
        System.out.println(-1);
    }

    // 열쇠 체크
    private static boolean isKey(int y, int x) {
        return 'a' <= board[y][x] && board[y][x] <= 'f';
    }

    // 문 체크
    private static boolean isDoor(int y, int x) {
        return 'A' <= board[y][x] && board[y][x] <= 'F';
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}