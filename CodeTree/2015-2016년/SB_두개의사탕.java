import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_두개의사탕 {
    static int N, M;
    static char[][] board;
    static int[] blue = new int[2];
    static int[] red = new int[2];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static Res tilt(int x, int y, int i) {    // 방향에 따라 기울이기
        int move = 0;
        while (board[x + dx[i]][y + dy[i]] != '#' && board[x][y] != 'O') {  // 다음에 위치할 값이 #이 아니고 현재 값이 도착점이 아니면 계속 움직임
            x += dx[i];
            y += dy[i];     // 좌표값 갱신 (i방향으로 이동)
            move++;
        }
        return new Res(x, y, move, board[x][y] == 'O');
    }

    private static int bfs() {
        Deque<Node> que = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        que.offer(new Node(red[0], red[1], blue[0], blue[1], 0));
        visited.add("" + red[0] + red[1] + blue[0] + blue[1]);

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.cnt >= 10) return -1;

            for (int i = 0; i < 4; i++) {
                // 구슬 각각 기울인 값
                Res nR = tilt(cur.rx, cur.ry, i);
                Res nB = tilt(cur.bx, cur.by, i);

                if (nB.isGoal) continue;
                if (nR.isGoal) return cur.cnt + 1;
                if (nB.x == nR.x && nB.y == nR.y) {     // 구슬이 같은 위치에 있다고 나왔을땐, 거리를 통해 실제 위치 파악
                    if (nR.mv > nB.mv){                 // 이동거리가 많은 것이 늦게 온것, 따라서 기존 위치보다 한 칸 뒤에 존재
                        nR.x -= dx[i];
                        nR.y -= dy[i];
                    } else{
                        nB.x -= dx[i];
                        nB.y -= dy[i];
                    }
                }

                String state = "" + nR.x + nR.y + nB.x + nB.y;
                if (!visited.contains(state)) {
                    visited.add(state);
                    que.offer(new Node(nR.x, nR.y, nB.x, nB.y, cur.cnt + 1));
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j]=='B') {
                    blue[0] = i;
                    blue[1] = j;
                }
                else if (board[i][j]=='R') {
                    red[0] = i;
                    red[1] = j;
                }
            }
        }

        System.out.println(bfs());
    }

    private static class Node{
        int rx, ry, bx, by, cnt;

        public Node(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    private static class Res{
        int x, y, mv;
        boolean isGoal;

        public Res(int x, int y, int mv, boolean isGoal) {
            this.x = x;
            this.y = y;
            this.mv = mv;
            this.isGoal = isGoal;
        }
    }
}
