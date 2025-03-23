import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SB_2151 {
    static int N;
    static char[][] board;
    static Node start;
    static int[][] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int bfs() {
        visited[start.x][start.y] = 0;
        board[start.x][start.y] = '*';

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (board[cur.x][cur.y]=='#') return cur.cnt;

            int nx = cur.x + dx[cur.d];
            int ny = cur.y + dy[cur.d];

            if (!isValid(nx, ny) || board[nx][ny] == '*') continue;
            if (visited[nx][ny] >= cur.cnt) continue;

            visited[nx][ny] = cur.cnt;
            pq.offer(new Node(nx, ny, cur.d, cur.cnt));

            if (board[nx][ny] == '!') {
                pq.offer(new Node(nx, ny, (cur.d + 1) % 4, cur.cnt + 1));
                pq.offer(new Node(nx, ny, (cur.d + 3) % 4, cur.cnt + 1));
            }
        }
        return -1;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new char[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == '#') {
                    start = new Node(i, j, 0, 0);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        for (int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];

            if (!isValid(nx, ny) || board[nx][ny] == '*') continue;
            pq.offer(new Node(start.x, start.y, i, 0));
        }

        System.out.println(bfs());
    }

    static class Node implements Comparable<Node> {
        int x, y, d, cnt;

        public Node(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }
}
