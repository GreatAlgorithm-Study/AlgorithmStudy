import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
시간복잡도 : n<=14, 0 < 각 확률 < 100
단순함의 기준 : 한번 방문한 경로를 방문하지 않을 때 -> 백트래킹
모든 확률을 더하면 100
 */
class HW_1405{
    static int n;
    static int[] dx = {0, 0, 1, -1}; // 동서남북
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static double[] distance;
    static double ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 로봇의 행동 횟수
        distance = new double[4];
        visited = new boolean[30][30];
        ans = 0.0;
        for(int i=0; i<4; i++){
            distance[i] = Integer.parseInt(st.nextToken()) / 100.0; // 확률을 소수로 변환해야 함
        }
        visited[15][15] = true; // (15, 15) 방문 처리
        dfs(15, 15, 0, 1.0); // 참고 : 초기 확률을 1로 해야함(전체 확률, 모든 경로로 이동 가능하기 때문에)
        System.out.println(ans); // 확률 출력
    }
    private static void dfs(int x, int y, int cnt, double prob){
        if(cnt==n){
            ans += prob;
            return;
        }
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(nx, ny, cnt+1, prob*distance[i]); // 재귀호출
                visited[nx][ny] = false; // 백트래킹
            }
        }
    }
}