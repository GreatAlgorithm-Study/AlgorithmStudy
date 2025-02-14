import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_18430 {
    static int N,M;
    static int[][] board;
    static int[][] wings = {
            {0, -1, 1, 0},
            {-1, 0, 0, -1},
            {-1, 0, 0, 1},
            {0, 1, 1, 0}
    };
    static int ans = 0;

    private static void dfs(int x, int y, int val, boolean[][] visited) {
        if(x==N) {  // 모든 칸 탐색
            ans = Math.max(ans, val);
            return;
        }

        int nx = (y+1 == M) ? x+1 : x;      // 다음 위치, 다음 행으로 이동 or 열로 이동
        int ny = (y+1 == M) ? 0 : y+1;

        if (visited[x][y]){
            dfs(nx, ny, val, visited);
            return;
        }

        visited[x][y] = true;
        for (int[] w : wings){
            int lx = x + w[0];           // 날개 좌표
            int ly = y + w[1];
            int rx = x + w[2];
            int ry = y + w[3];

            if (!isValid(lx, ly) || !isValid(rx, ry) || visited[lx][ly] || visited[rx][ry]) continue;

            visited[lx][ly] = true;
            visited[rx][ry] = true;

            int curVal = val + board[x][y]*2 + board[lx][ly] + board[rx][ry];
            dfs(nx, ny, curVal, visited);
            visited[lx][ly] = false;
            visited[rx][ry] = false;
        }
        visited[x][y] = false;

        // 부메랑 안만드는 경우
        dfs(nx, ny, val, visited);

    }

    private static boolean isValid(int x, int y){
        return 0<=x && x<N && 0<=y && y<M;
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
            }
        }

        boolean[][] visited = new boolean[N][M];
        dfs(0, 0, 0, visited);
        System.out.println(ans);
    }
}
