import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_방화벽_설치하기 {
    static int N,M;
    static int[][] board;
    static List<Node> fires = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int mx = Integer.MIN_VALUE;

    private static void spreadFire() {
        int[][] tmpBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpBoard[i][j] = board[i][j];
            }
        }

        Queue<Node> que = new ArrayDeque<>(fires);
        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isValid(nx, ny) && tmpBoard[nx][ny]==0){
                    tmpBoard[nx][ny] = 2;
                    que.add(new Node(nx, ny));
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpBoard[i][j]==0) cnt++;
            }
        }
        mx = Math.max(mx, cnt);
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }

    private static void dfs(int sr, int sc, int depth) {
        if (depth==3){
            spreadFire();
            return;
        }

        for (int i = sr; i < N; i++) {
            for (int j = (i == sr ? sc : 0); j < M; j++) {
                if (board[i][j]==0) {
                    board[i][j] = 1;
                    dfs(i, j, depth+1);
                    board[i][j] = 0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j]==2) fires.add(new Node(i, j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==0){
                    board[i][j] = 1;
                    dfs(i, j, 1);
                    board[i][j] = 0;
                }
            }
        }
        System.out.println(mx);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}