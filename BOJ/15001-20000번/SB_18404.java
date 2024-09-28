import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SB_18404 {
    static int N, M;
    static int[][] board;
    static int[] caught;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    private static void bfs(int x, int y) {
        Queue<Node> que = new ArrayDeque<>();
        int[][] visited = new int[N+1][N+1];
        for (int[] row : visited){
            Arrays.fill(row, -1);
        }
        visited[x][y] = 0;
        que.offer(new Node(x, y));

        int cnt = 0;
        while (!que.isEmpty()){
            Node knit = que.poll();
            if (board[knit.x][knit.y] > 0) {
                caught[board[knit.x][knit.y]] = visited[knit.x][knit.y];
                cnt++;
            }
            if (cnt==M) break;

            for (int i = 0; i < 8; i++) {
                int nx = knit.x + dx[i];
                int ny = knit.y + dy[i];
                if (!(0<nx && nx<=N && 0<ny && ny<=N)) continue;
                if (visited[nx][ny]==-1){
                    que.offer(new Node(nx, ny));
                    visited[nx][ny] = visited[knit.x][knit.y] + 1;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N+1][N+1];
        caught = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        int idx = 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = idx++;
        }

        bfs(sx, sy);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            sb.append(caught[i]).append(' ');
        }
        System.out.println(sb);
    }

    static class Node{
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
