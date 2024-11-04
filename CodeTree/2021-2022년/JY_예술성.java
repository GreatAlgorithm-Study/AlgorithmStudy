import java.util.*;
import java.io.*;

public class JY_예술성 {
    
    static int N;
    static int gNum;
    static int[][] board;
    static Map<Integer, Group> gMap;
    static int[][] g;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] nb;
    static class Pos {
        int x, y;
        public Pos(int x, int y){
            this.x=x;
            this.y=y;
        }
        @Override
        public String toString(){
            return "("+this.x+","+this.y+")";
        }
    }
    static class Group {
        int val, size;
        public Group(int val, int size){
            this.val = val;
            this.size = size;
        }
        @Override
        public String toString(){
            return "["+this.val+"]:"+this.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int t=0; t<4; t++){
            // System.out.println(t);
            // print(board);

            // 그룹 생성
            generateGroup();
            // print(g);
            // System.out.println(gMap);

            // 이웃 그룹 계산 & 예술 점수 계산
            ans += calScore();

            nb = new int[N][N];
            // 십자가 회전
            turnTen();

            // 정사각형 회전
            turnBox(0, 0);
            turnBox(N/2+1, 0);
            turnBox(0, N/2+1);
            turnBox(N/2+1, N/2+1);

            // 그래프 변경
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    board[i][j] = nb[i][j];
                }
            }

        }
        System.out.println(ans);
        
    }
    public static void print(int[][] arr){
        for(int i=0; i<N; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }
    // generateGroup(): 그룹 확인 및 생성
    public static void generateGroup() {
        visited = new boolean[N][N];
        gMap = new HashMap<>();
        g = new int[N][N];
        gNum = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]){
                    int s = bfs(i, j, board[i][j], gNum);
                    gMap.put(gNum, new Group(board[i][j], s));
                    gNum++;
                }
            }
        }
    }
    public static boolean inRange(int x, int y) {
        return x>=0 && x<N && y>=0 && y<N;
    }
    public static int bfs(int x, int y, int num, int gNum){
        visited[x][y] = true;
        g[x][y] = gNum;
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(x, y));
        int size = 1;
        while(!q.isEmpty()){
            Pos now = q.poll();
            for(int i=0; i<4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(inRange(nx, ny) && !visited[nx][ny] && num == board[nx][ny]){
                    visited[nx][ny] = true;
                    g[nx][ny] = gNum;
                    size++;
                    q.add(new Pos(nx, ny));
                }
            }
        }
        return size;
    }
    // 이웃 그룹 계산
    public static int calScore(){
        visited = new boolean[N][N];
        int score = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]){
                    score += find(i, j, g[i][j]);
                }
            }
        }
        // System.out.println("score: "+score);
        return score / 2;
    }
    public static int find(int x, int y, int num){
        visited[x][y] = true;
        int[] neighbors = new int[gNum];
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(x, y));
        while(!q.isEmpty()){
            Pos now= q.poll();
            for(int i=0; i<4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(inRange(nx, ny)){
                    if(g[nx][ny] == num && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new Pos(nx, ny));
                    }
                    else if(g[nx][ny] != num){
                        neighbors[g[nx][ny]]++;
                    }
                }
            }
        }
        // 예술 점수 계산
        int ans = 0;
        Group g1 = gMap.get(num); 
        for(int i=0; i<gNum; i++){
            if(neighbors[i] != 0){
                Group g2 = gMap.get(i);
                ans += cal(g1, g2, neighbors[i]);
            }
        }
        return ans;
    }
    public static int cal(Group g1, Group g2, int sides){
        return (g1.size+g2.size)*g1.val*g2.val*sides;
    }
    // 십자가 회전
    public static void turnTen(){
        // 세로 줄 회전
        for(int i=0; i<N; i++){
            nb[N/2][i] = board[i][N/2];
        }
        // 가로 줄 회전
        for(int j=0; j<N; j++){
            nb[N-j-1][N/2] = board[N/2][j];
        }
    }
    public static void turnBox(int sx, int sy) {
        int size = N / 2;
        for(int i=sx; i<sx+size; i++){
            for(int j=sy; j<sy+size; j++){
                // (0, 0)으로 옮겨주기
                int ox = i-sx;
                int oy = j-sy;
                // x, y -> y, size-1-x
                int rx = oy;
                int ry = size-1-ox;
                nb[rx+sx][ry+sy] = board[i][j];
            }
        }
    }
}