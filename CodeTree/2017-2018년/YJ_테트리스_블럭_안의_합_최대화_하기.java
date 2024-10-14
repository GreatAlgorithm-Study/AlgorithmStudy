import java.io.*;

public class YJ_테트리스_블럭_안의_합_최대화_하기 {
    static int n = 0;
    static int m = 0;

    static int[][] block;
    static boolean[][] visited;
    static int maxSum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split("\\s");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        block = new int[n][m];
        for(int i=0; i<n; i++){
            String[] t = br.readLine().split("\\s");
            for(int j=0; j<m; j++){
                block[i][j] =  Integer.parseInt(t[j]);
            }
        }

        visited = new boolean[n][m];
        //블럭의 한칸마다 전체를 DFS 로 탐색 * 전체블럭 수
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                visited[i][j] = true;
                dfs(1,i,j,block[i][j]);
                visited[i][j] = false;
                //ㅏㅗㅜㅓ 도형

            }
        }

        System.out.println(maxSum);
    }

    //좌우상하
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static void dfs(int depth, int x, int y, int sum){
        if(depth == 4){
            maxSum = Math.max(maxSum, sum);
            return;
        }

        //상하좌우 모두 탐색
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(stop(nx,ny)){
                continue;
            }
            visited[nx][ny] = true;
            if(depth == 2){
                dfs(depth+1, x,y,sum+block[nx][ny]);
            }
            dfs(depth+1, nx, ny, sum + block[nx][ny]);
            visited[nx][ny] = false;
        }

    }

    private static boolean stop(int nx, int ny){
        return nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny];
    }
}