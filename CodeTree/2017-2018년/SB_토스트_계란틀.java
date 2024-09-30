import java.io.*;
import java.util.*;

public class SB_토스트_계란틀 {
    static int N, L, R;
    static int[][] board;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int bfs(int x, int y, int idx){
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(x, y));
        visited[x][y] = idx;

        int cnt = 1;
        int eggs = board[x][y];
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i=0; i<4; i++){
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if(isValid(nx, ny) && visited[nx][ny]==0){
                    int dif = Math.abs(board[cur.x][cur.y]-board[nx][ny]);
                    if(dif >= L && dif <=R) {
                        que.offer(new Node(nx, ny));
                        visited[nx][ny] = idx;
                        eggs+=board[nx][ny];
                        cnt++;
                    }
                }
            }
        }
        return (int)eggs/cnt;
    }

    private static boolean isValid(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while(true){
            visited = new int[N][N];
            int[] split = new int[N*N+1];
            int idx = 1;
            int flag = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(visited[i][j]==0){
                        int tmp = bfs(i, j, idx);
                        split[idx] = tmp;
                        if(tmp==board[i][j]) flag++;    // 나눈 결과==원래자신, 계란이동X
                        idx++;
                    }
                }
            }

            if(flag==N*N) break;

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    board[i][j] = split[visited[i][j]];  // 새로운 값은, split[idx]에 해당하는 값(visited에 idx값 가지고 있음)
                }
            }
            ans++;
        }

        System.out.println(ans);
    }

    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}