import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_1405 {
    static int N;
    static int[] dx = {0, 0, 1, -1};    // 동, 서, 남, 북
    static int[] dy = {1, -1, 0, 0};
    static double[] prob = new double[4];
    static boolean[][] visited = new boolean[30][30];
    static double ans = 0;

    private static void dfs(int x, int y, int depth, double total) {
        if (depth==N) {
            ans += total;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!visited[nx][ny]){      // 방문하지 않은 곳이라면
                visited[nx][ny] = true;
                dfs(nx, ny, depth+1, total*prob[i]);
                visited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            prob[i] = Integer.parseInt(st.nextToken())*0.01;        // 확률로 표현
        }

        visited[15][15] = true;
        dfs(15, 15, 0, 1);
        System.out.println(ans);
    }
}
