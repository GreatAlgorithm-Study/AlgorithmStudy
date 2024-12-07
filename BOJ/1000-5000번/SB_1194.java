import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_1194 {
    static int N, M;
    static char[][] board;
    static Node start;
    static int ans = Integer.MAX_VALUE;
    static int[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void bfs() {
        Queue<Node> que = new ArrayDeque<>();
        visited[start.x][start.y][0] = 0;
        que.offer(new Node(start.x, start.y, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (board[cur.x][cur.y] == '1') {
                ans = Math.min(ans, visited[cur.x][cur.y][cur.keys]);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nk = cur.keys;
                if (!isValid(nx, ny) || board[nx][ny]=='#' || visited[nx][ny][nk]!=-1) continue;

                char nxt = board[nx][ny];
                // 이동할 곳이 알파벳일 경우
                if (Character.isAlphabetic(nxt)){
                    if (Character.isLowerCase(nxt)) nk |= (1 << (nxt - 'a'));           // 키면 키 줍기
                    else if (Character.isUpperCase(nxt)) {
                        if ((nk & (1<< (nxt-'A')))==0) continue;                         // 키 없는 문은 못감
                    }
                }

                // 이동 가능할 경우 이동
                que.offer(new Node(nx, ny, nk));
                visited[nx][ny][nk] = visited[cur.x][cur.y][cur.keys] + 1;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new int[N][M][1 << 6];        // 키 6개 존재 2^6
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j]=='0'){
                    start = new Node(i, j, 0);
                    board[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < N; i++) {           // 방문배열 초기화
            for (int j = 0; j < M; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        bfs();
        System.out.println(ans==Integer.MAX_VALUE ? -1 : ans);
    }
    static class Node{
        int x, y, keys;

        public Node(int x, int y, int keys) {
            this.x = x;
            this.y = y;
            this.keys = keys;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", keys=" + keys +
                    '}';
        }
    }
}
