import java.util.*;
class Solution {
    
    static final int N = 100;
    static int X, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Robot[] robots;
    static boolean[] isDied;
    static int[][] g;
    static class Robot {
        int x, y, ex, ey;
        int sp, rCnt;
        // sp: 현재 진행중인 포인터
        // rCnt: 진행한 루트 카운터 (rCnt == M이면 모든 루트를 탐색함)
        public Robot(int x, int y, int ex, int ey, int sp, int rCnt) {
            this.x = x;
            this.y = y;
            this.ex = ex;
            this.ey = ey;
            this.sp = sp;
            this.rCnt = rCnt;
        }
        @Override
        public String toString() {
            return "R: ("+this.x+","+this.y+") -> "+"("+this.ex+","+this.ey+") sp:"+this.sp+" rCnt:"+this.rCnt;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        // 로봇 초기화
        X = routes.length;
        M = routes[0].length;
        robots = new Robot[X+1];
        for(int i=0; i<X; i++) {
            int[] r = routes[i];
            int sp = r[0];
            int np = r[1];
            int sx = points[sp-1][0];
            int sy = points[sp-1][1];
            int ex = points[np-1][0];
            int ey = points[np-1][1];
            
            robots[i+1] = new Robot(sx, sy, ex, ey, sp, 1);
        }
        
        isDied = new boolean[X+1];
        // -- while문
        while(true) {
            // 로봇들 완료 여부 체크
            if(isDone()) break;
            
            // 충돌 위험 체크하기
            answer += check();
                    
            // 이동하기
            move(points, routes);

        }

        return answer;
    }
    public static int calDist(int sx, int sy, int ex, int ey) {
        return Math.abs(sx-ex) + Math.abs(sy-ey);
    }
    public static void move(int[][] points, int[][] routes) {
        for(int i=1; i<X+1; i++) {
            Robot now = robots[i];
            if(isDied[i]) continue;     // 모든 루트 탐색 끝난 로봇
            int p = now.sp;
            
            // 목적지를 갱신 및 현재 포인터 변경
            if(now.x == now.ex && now.y == now.ey) {
                if(now.rCnt == M) {
                    isDied[i] = true;
                    continue;
                }
                int nextP = routes[i-1][now.rCnt];
                int tx = points[nextP-1][0];
                int ty = points[nextP-1][1];
                now.ex = tx;
                now.ey = ty;
                now.sp = nextP;
            }
            
            // int minDist = calDist(now.x, now.y, now.ex, now.ey);
            int minDist = Integer.MAX_VALUE;
            int nextX = -1;
            int nextY = -1;
            for(int d=0; d<4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if(!inRange(nx, ny)) continue;
                
                int nDist = calDist(nx, ny, now.ex, now.ey);
                if(minDist > nDist) {
                    minDist = nDist;
                    nextX = nx;
                    nextY = ny;
                }
            }
            // 이동
            now.x = nextX;
            now.y = nextY;
            
            // 만약 목적지에 도착했다면 다음 목적지 갱신위헤 카운트 증가
            if(now.x == now.ex && now.y == now.ey) now.rCnt++;
        }
    }
    public static boolean inRange(int x, int y) {
        return x>0 && x<=N && y>0 && y<=N;
    }
    public static int check() {
        g = new int[N+1][N+1];
        for(int i=1; i<X+1; i++) {
            Robot now = robots[i];
            if(isDied[i]) continue;     // 모든 루트 탐색 끝난 로봇
            g[now.x][now.y] += 1;
        }
        // printG();
        
        int cnt = 0;
        for(int i=0; i<N+1; i++) {
            for(int j=0; j<N+1; j++) {
                if(g[i][j] > 1) cnt++;
            }
        }
        
        return cnt;
    }
    public static boolean isDone() {
        for(int i=1; i<X+1; i++) {
            if(!isDied[i]) return false;
        }
        return true;
    }
    public static void printR() {
        for(int i=1; i<X+1; i++) {
            System.out.println(robots[i]);
        }
    }
    public static void printG() {
        for(int i=0; i<N+1; i++) {
            System.out.println(Arrays.toString(g[i]));
        }
        System.out.println();
    }
}