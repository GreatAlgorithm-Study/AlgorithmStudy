import java.util.*;
import java.io.*;

public class JY_자율주행_자동차 {

    static int N, M;
    static int[][] g;
    static Pos now;
    static boolean[][] visited;
    // 북, 동, 남, 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Pos {
        int x, y, d;
        public Pos(int x, int y, int d){
            this.x=x;
            this.y=y;
            this.d=d;
        }
        @Override
        public String toString(){
            return this.x+","+this.y+" : "+this.d;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int nowX = Integer.parseInt(st.nextToken());
        int nowY = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        now = new Pos(nowX, nowY, dir);

        g = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][M];
        visited[now.x][now.y] = true;

        // 자율주행 시작
        while(true){
            // 좌회전
            Pos np = turnLeft(now.d);
            // 1번
            if(g[np.x][np.y] == 0 && !visited[np.x][np.y]){
                go(np);
            } else {
                boolean isOk = false;
                for(int i=0; i<3; i++){
                    np = turnLeft(np.d);
                    if(g[np.x][np.y] == 0 && !visited[np.x][np.y]) {
                        isOk = true;
                        break;
                    }
                }
                if(isOk){
                    go(np);
                } 
                // 3번
                else{
                    np = back(now.d); 
                    if(g[np.x][np.y] == 1){
                        break;
                    }
                    go(np);
                }
            }
        }
        // for(int i=0; i<N; i++){
        //     System.out.println(Arrays.toString(visited[i]));
        // }
        int ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
    public static Pos turnLeft(int d){
        int left = (d + 3) % 4;
        int nx = now.x + dx[left];
        int ny = now.y + dy[left];
        return new Pos(nx, ny, left);
    }
    public static Pos back(int d){
        int bDir = (d + 2) % 4;
        int nx = now.x + dx[bDir];
        int ny = now.y + dy[bDir];
        return new Pos(nx, ny, d);
    }
    public static void go(Pos np){
        visited[np.x][np.y] = true;
        now.x = np.x;
        now.y = np.y;
        now.d = np.d;
    }
}

