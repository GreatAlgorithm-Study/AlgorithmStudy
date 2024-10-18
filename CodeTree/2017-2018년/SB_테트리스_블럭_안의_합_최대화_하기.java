import java.io.*;
import java.util.*;

public class SB_테트리스_블럭_안의_합_최대화_하기 {
    static int N,M;
    static int[][] board;
    static boolean[][] visited;
    static int mx = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void dfs(int x, int y, int depth, int total) {
        // if (mx > mx*(4-depth)+total) return;
        if(depth==4){
            mx = Math.max(mx, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (!isValid(nx, ny) || visited[nx][ny]) continue;
            if (depth == 2) {
                visited[nx][ny] = true;
                dfs(x, y, depth+1, total+board[nx][ny]);        // ㅜ 가 갈라지는 중점에서 양옆 탐색
                visited[nx][ny] = false;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, depth+1, total+board[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(mx);
    }
}