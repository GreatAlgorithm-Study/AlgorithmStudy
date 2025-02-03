import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_2개의_사탕 {

    static int n, m, g; // 각 인덱스
    static boolean[][] board, visited;  
    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        visited = new boolean[n * m][n * m]; // 빨간, 파란 사탕 각각 방문처리
        int r = 0, b = 0;
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char c = line[j];
                if (c == '#')
                    board[i][j] = true;
                else if (c == 'R')
                    r = i * m + j; // 빨간 사탕의 인덱스
                else if (c == 'B')
                    b = i * m + j; // 파란 사탕의 인덱스
                else if (c == 'O')
                    g = i * m + j; // 구멍의 인덱스
            }
        }
        System.out.println(bfs(r, b));
    }

    private static int bfs(int sr, int sb) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] { sr, sb }); // 빨간 공의 인덱스, 파란 공의 인덱스스
        visited[sr][sb] = true;
        int time = 0;
        while (!dq.isEmpty()) {
            int t = dq.size();
            while (t-- > 0) {
                int[] cur = dq.poll();
                int r = cur[0], b = cur[1];
                // 파란 공이 먼저 들어갔다면
                if (b == g)
                    continue;
                // 빨간 공이 들어갔다면 종료
                if (r == g)
                    return time;
       절
            case 0:
                if (r < b) nb += m;
                else nr += m;
                break;
            case 1:
                if (r < b) nr -= m;
                else nb -= m;
                break;
            case 2:
                if (r < b) nb += 1;
                else nr += 1;
                break;
            case 3:
                if (r < b) nr -= 1;
                else nb -= 1;
                break;
            }
        }
        return new int[] { nr, nb };
    }

    // 직선 움직임 구현
    private static int goStraight(int dir, int cur) {
        int y = cur / m, x = cur % m;
        int gy = g / m, gx = g % m;
        // 다음 위치로 이동할 수 있다면
        while (!board[y + dy[dir]][x + dx[dir]]) {
            y += dy[dir];
            x += dx[dir];
            // 구멍에 들어갔을 경우 종료
            if (y == gy && x == gx)
                return y * m + x;
        }
        return y * m + x;
    }
}
