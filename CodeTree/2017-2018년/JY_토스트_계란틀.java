import java.util.*;
import java.io.*;
public class JY_토스트_계란틀 {

    static int N, L, R;
    static int[][] g;
    static boolean[][] visited;
    static List<List<Egg>> eList;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static class Egg {
        int x, y, size;
        public Egg(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
        @Override
        public String toString() {
            return "("+this.x+","+this.y+") "+this.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        g = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while(ans < 2000) {
            // 1. 검사
            if(isDone()) break;

            // 2. 합칠 계란 덩어리들 구하기
            eList = new ArrayList<>();
            makeEggs();

            // 3. 합치기
            move();

            ans++;
        }
        
        System.out.println(ans);

    }
    public static void print() {
        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(g[i]));
        }
    }
    public static boolean inRange(int x, int y) {
        return x>=0 && x<N && y>=0 && y<N;
    }
    public static boolean isDone() {
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                for(int d=0; d<4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if(!inRange(nx, ny)) continue;
                    // 아직 계란의 이동이 필요함
                    if(Math.abs(g[nx][ny]-g[x][y]) >= L && Math.abs(g[nx][ny]-g[x][y]) <= R) return false;
                }
            }
        }
        return true;
    }
    public static void bfs(int x, int y) {
        List<Egg> eggs = new ArrayList<>();
        Queue<Egg> q = new LinkedList<>();
        q.add(new Egg(x, y, g[x][y]));
        eggs.add(new Egg(x, y, g[x][y]));
        
        while(!q.isEmpty()) {
            Egg now = q.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!inRange(nx, ny)) continue;
                int diff = Math.abs(g[nx][ny]-now.size);
                if((diff >= L && diff <= R) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    eggs.add(new Egg(nx, ny, g[nx][ny]));
                    q.add(new Egg(nx, ny, g[nx][ny]));
                } 
            }
        }
        // System.out.println(eggs);
        // 덩어리 사이즈가 1이면 이동 X
        if(eggs.size() != 1) eList.add(eggs);

    }
    public static void makeEggs() {
        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]){
                    visited[i][j] = true;
                    bfs(i, j);
                }
            }
        }
    }
    public static void move() {
        for(List<Egg> eggs : eList) {
            int total = 0;
            for(Egg egg: eggs) {
                total += egg.size;
            }
            
            int ns = total / eggs.size();
            for(Egg egg: eggs) {
                g[egg.x][egg.y] = ns;
            }
        }
    }
}