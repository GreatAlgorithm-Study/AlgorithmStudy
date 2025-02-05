import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class HW_3109 {
    static int R, C;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1}; // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int[] dy = {1, 1, 1};
    static int cnt; // 파이프라인의 최대 개수
    // 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for(int i=0; i<R; i++){
            board[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<R; i++){
            if(dfs(i, 0)){ // 각 행의 첫 열부터 시작
                cnt++;
            }
        }
        System.out.println(cnt);
        // 첫째 열은 근처 빵집의 가스관이고, 마지막 열은 원웅이의 빵집이다.
    }
    static boolean dfs(int x, int y){
        if(y==C-1){ // 마지막 열에 도달시 종료
            return true;
        }
        for(int d=0; d<3; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isValid(nx, ny) && !visited[nx][ny] && board[nx][ny]=='.'){
                visited[nx][ny]= true;
                if(dfs(nx, ny)){
                    return true;
                }
            }
        }
        return false;
    }
    static boolean isValid(int x, int y){
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}