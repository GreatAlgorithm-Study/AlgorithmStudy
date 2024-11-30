// 인턴이 채취한 곰팡이 크기의 총 합을 출력
import java.util.*;
import java.io.*;
public class HW_생명과학부_랩_인턴 {
    static class Node{  // 곰팡이 정보 노드 클래스로 관리?
        int x, y, s, d, b;
        public Node(int x, int y, int s, int d, int b){
            this.x = x;
            this.y = y;
            this.s = s; // 속도
            this.d = d; // 이동 방향
            this.b = b; // 곰팡이의 크기
        }
    }
    static int n, m, k;
    static List<Node>[][] grid;
    static int ans;
    static int[] dx = {-1, 1, 0, 0}; // 상하우좌
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 격자판 크기 n x m
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 곰팡이 수
        grid = new ArrayList[n][m]; // 곰팡이 크기 가변적 
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                grid[i][j] = new ArrayList<>(); // ArrayList로 초기화(null값 방지)
            }
        }

        for(int i=0; i<k; i++){ // 곰팡이 정보 입력 받기
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken()); // 1초동안 움직이는 거리
            int d = Integer.parseInt(st.nextToken()); // 이동 방향
            int b = Integer.parseInt(st.nextToken()); // 곰팡이의 크기
            grid[x][y].add(new Node(x, y, s, d - 1, b));
        }

        for(int i=0; i<m; i++){ // 승용이 열기준 탐색
            collect(i); // 1. 곰팡이 채취
            move(); // 2. 곰팡이 이동
            collision(); // 3. 곰팡이 충돌
        }
        System.out.println(ans);
    }

    static void collect(int c){ // 곰팡이 채취
        for(int r=0; r<n; r++){
            if(!grid[r][c].isEmpty()){
                Node mold = grid[r][c].get(0);
                ans += mold.b; // 곰팡이 크기 합산
                grid[r][c].clear(); // 곰팡이 제거
                return;
            }
        }
    }

    static void move(){
        List<Node>[][] nextGrid = new ArrayList[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                nextGrid[i][j] = new ArrayList<>();
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(Node mold : grid[i][j]){
                    int x = mold.x;
                    int y = mold.y;
                    int d = mold.d;
                    int s = mold.s;
                    for(int speed = 0; speed<s; speed++){
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        // 벽에 부딪힐 경우
                        if(nx<0 || nx>=n){ // 상 or 하
                            d = (d==0) ? 1 : 0;
                            nx = x + dx[d]; // 반대 방향으로 이동
                        }
                        if (ny<0 || ny>=m) {
                            d = (d==2) ? 3 : 2;
                            ny = y + dy[d]; // 반대 방향으로 이동
                        }
                        x = nx;
                        y = ny;
                    }
                    mold.x = x;
                    mold.y = y;
                    mold.d = d;
                    nextGrid[x][y].add(mold);
                }
            }
        }
        grid = nextGrid;
    }
    static void collision(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j].size() > 1){ // 한칸에 곰팡이가 2개 이상 존재할 경우
                    Node max = grid[i][j].get(0);
                    for(Node mold : grid[i][j]) { // 가장 큰 곰팡이만 남김
                        if (mold.b > max.b) {
                            max = mold;
                        }
                    }
                    grid[i][j].clear();
                    grid[i][j].add(max);
                }
            }
        }
    }
}