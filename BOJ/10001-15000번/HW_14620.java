import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 복잡도 : 6<=N<=10 -> O(N^2) -> 완전 탐색 가능
// 꽃을 심기위한 최소 비용을 출력
public class HW_14620 {
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] garden;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        garden = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){ // 화단 지점당 가격 (0<=G<=200)
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                garden[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }
    public static void dfs(int depth, int cost) {
        if (depth == 3) { // depth : 현재까지 심은 꽃의 개수
            min = Math.min(min, cost);
            return;
        }
        for(int i=1; i<N-1; i++){
            for(int j=1; j<N-1; j++){
                if(check(i, j)){
                    int cur = plant(i, j);
                    dfs(depth+1, cost + cur);
                    backtrack(i, j); // 백트래킹으로 심었던 꽃 제거
                }
            }
        }
    }
    public static boolean check(int x, int y){ // 꽃을 심을 수 있는지 확인
        if(visited[x][y]) // 이미 꽃이 심어진 경우
            return false;
        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(!isValid(nx, ny)|| visited[nx][ny])
                return false;
        }
        return true;
    }

    public static int plant(int x, int y){ // 꽃 심으면서 비용 계산하기
        int cost = garden[x][y];
        visited[x][y] = true;

        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            cost += garden[nx][ny];
            visited[nx][ny] = true;
        }
        return cost;
    }

    public static void backtrack(int x, int y){ // 심었던 꽃 제거
        visited[x][y] = false;
        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            visited[nx][ny] = false;
        }
    }

    public static boolean isValid(int nx, int ny){
        return nx >= 0 && ny >= 0 && nx < N && ny < N;
    }
}