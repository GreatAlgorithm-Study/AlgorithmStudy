import java.util.*;

class Solution {
    
    static int N, x, y;
    static Pos[][] g;
    // U, R, D, L
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static class Pos {
        int x, y;
        boolean[] visited;
        public Pos(int x, int y, boolean[] visited){
            this.x = x;
            this.y = y;
            this.visited = visited;
        }
        @Override
        public String toString(){
            return "("+this.x+","+this.y+")";
        }
    }
    
    public int solution(String dirs) {
        int answer = 0;
        N = 11;
        g = new Pos[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                g[i][j] = new Pos(i, j, new boolean[4]);
            }
        }
        // for(int i=0; i<N; i++){
        //     System.out.println(Arrays.toString(g[i]));
        // }
        
        x = N/2;
        y = N/2;

        for(int i=0; i<dirs.length(); i++){
            char dir = dirs.charAt(i);
            if(dir == 'U'){
                answer += move(0);
            } else if(dir == 'R'){
                answer += move(1);
            } else if(dir == 'D'){
                answer += move(2);
            } else if(dir == 'L'){
                answer += move(3);
            }
        }
        // for(int i=0; i<N; i++){
        //     for(int j=0; j<N; j++){
        //         System.out.print(g[i][j]+" ");
        //         System.out.println(Arrays.toString(g[i][j].visited));
        //     }
        // }
        
        
        return answer;
    }
    public static boolean inRange(int x, int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
    public static int move(int d){
        int nx = x + dx[d];
        int ny = y + dy[d];
        // System.out.println("("+x+","+y+") -> "+nx+", "+ny);
        int cnt = 0;
        if(inRange(nx, ny)){
            int nd = (d+2) % 4;
            // (nx,ny)로 향하는 방향과 (x,y)에서 나가는 방향의 방문여부 확인
            if(!g[nx][ny].visited[nd] && !g[x][y].visited[d]){
                g[nx][ny].visited[nd] = true;
                g[x][y].visited[d] = true;
                cnt++;
            }
            x = nx;
            y = ny;
        }
        return cnt;
    }
}