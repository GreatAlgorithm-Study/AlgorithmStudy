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
        dq.offer(new int[] { sr, sb });
        visited[sr][sb] = true;
        int time = 0;
        while (!dq.isEmpty()) {
            int t = dq.size();
            while (t-- > 0) {
                int[] cur = dq.poll();
                int r = cur[0], b = cur[1];
                // 파란 사탕이 들어갔다면 다음 탐색
                if (b == g)
                    continue;
                // 빨간 사탕이 들어갔다면 종료
                if (r == g)
                    return time;
                for (int i = 0; i < 4; i++) {
                    int[] next = move(i, r, b);
                    // 잘못된 움직임일 경우 다음 탐색
                    if ((next[0] == -1 && next[1] == -1) || visited[next[0]][next[1]])
                        continue;
                    visited[next[0]][next[1]] = true;
                    dq.offer(next);
                }
            }
            time++;
            if(time > 10)
                return -1;
        }
        return -1;
    }

    private static int[] move(int dir, int r, int b) {
        int nr = goStraight(dir, r), nb = goStraight(dir, b); // 상자를 기울인 방향으로 직선 운동
        // 두 인덱스가 겹쳤을 경우
        if (nr == nb) {
            // 구멍에 빠졌다면 → 두 사탕이 모두 구멍에 빠짐
            if (nr == g)
                return new int[] { -1, -1 }; // 잘못된 움직임을 알려주기 위한 반환
            // 상하좌우
            // 부딪혔을 경우, 인덱스를 조정
            switch (dir) {
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
