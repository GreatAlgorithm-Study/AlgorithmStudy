import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_22944 {
    static int N, H, D;
    static int K = 0;
    static char[][] board;
    static Node start;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int bfs() {
        Queue<Node> que = new ArrayDeque<>();
        int[][][] visited = new int[N][N][K + 1];
        for (int[][] box : visited){
            for (int[] row : box){
                Arrays.fill(row, -1);
            }
        }
        que.offer(new Node(start.x, start.y, start.h, start.u, start.cntU));
        visited[start.x][start.y][0] = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.h==0) continue;                 // 체력 0이면 죽음
            if (board[cur.x][cur.y]=='E') {         // 도착하면 탈출
                return visited[cur.x][cur.y][cur.cntU];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isValid(nx, ny) || visited[nx][ny][cur.cntU]!=-1) continue;
                if (board[nx][ny] == 'U') {
                    if (cur.cntU + 1 <= K && visited[nx][ny][cur.cntU + 1] == -1) {         // 우산 안넘치게 조건걸기
                        que.add(new Node(nx, ny, cur.h, D, cur.cntU + 1));
                        visited[nx][ny][cur.cntU+1] = visited[cur.x][cur.y][cur.cntU] + 1;
                    }
                }
                else {
                    if (cur.u > 0) que.add(new Node(nx, ny, cur.h, cur.u - 1, cur.cntU));
                    else que.add(new Node(nx, ny, cur.h-1, cur.u, cur.cntU));
                    visited[nx][ny][cur.cntU] = visited[cur.x][cur.y][cur.cntU] + 1;
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j]=='S') start = new Node(i, j, H, 0, 0);
                if (board[i][j]=='U') K++;
            }
        }

        System.out.println(bfs());
    }

    static class Node{
        int x, y, h, u;
        int cntU;       // 우산 주운 개수

        public Node(int x, int y, int h, int u, int cntU) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.u = u;
            this.cntU = cntU;
        }
    }
}
