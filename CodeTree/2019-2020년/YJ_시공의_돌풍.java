import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class YJ_시공의_돌풍 {
    static class Dust{
        int x;
        int y;
        int data;

        Dust(int x, int y, int data){
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }

    static int[][] room;
    static int[][] temp;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int t = Integer.parseInt(data[2]);

        visited = new boolean[n][m];
        room = new int[n][m];
        int[] tornado = new int[2];
        int z = 0;
        for(int i=0; i<n; i++){
            String[] d = br.readLine().split("\\s");
            for(int j=0; j<m; j++){
                int dust = Integer.parseInt(d[j]);
                if(dust == -1){
                    visited [i][j] = true;
                    tornado[z++] = i;
                }
                room[i][j] = dust;
            }
        }

        int result = 0;
        while(t-- > 0){
            //먼지가 인접한 4방향의 상하좌우 칸으로 확산: bfs 너비탐색
            bfs(n,m);
            //시공의 돌풍이 청소를 시작
            goTornado(n,m,tornado);
            //확산된 먼지 합산
            result = calculateTotalDust(n,m);
        }
        System.out.println(result);
    }

    static void bfs(int n, int m){
        final int SPREAD = 5;
        Queue<Dust> queue = new LinkedList<>();
        int[] nx = {1,0,-1,0};
        int[] ny = {0,1,0,-1};

        temp = new int[n][m];
        queue.offer(new Dust(0,0,room[0][0]));

        while(!queue.isEmpty()){
            Dust dust = queue.poll();
            int share = dust.data/SPREAD;

            int currX = dust.x;
            int currY = dust.y;
            if(visited[currX][currY]){
                continue;
            }

            for(int i=0; i<4; i++){
                int x = currX + nx[i];
                int y = currY + ny[i];

                if(stop(x,y,n,m)){
                    continue;
                }
                int next = room[x][y];
                if(next == -1){
                    continue;
                }

                temp[x][y] += share;
                room[currX][currY] -= share;
                queue.offer(new Dust(x,y,next));
            }
            visited[currX][currY] = true;
        }

        sumDust(n,m);
    }

    private static boolean stop(int x, int y, int n, int m){
        return x<0 || y<0 || x>=n || y>=m;
    }

    private static void sumDust(int n, int m){
        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                room[x][y] += temp[x][y];
            }
        }
    }

    static void goTornado(int n, int m, int[] tornado){
        up(0,0,tornado[0],m-1);
        down(tornado[1],0,n-1,m-1);
        cleaning(tornado);
    }
    // 0,0,윗태풍r,윗태풍c
    private static void up(int startR, int startC, int endR, int endC){
        int corner = room[startR][startC];
        //맨윗 줄 ←
        for(int c = startC; c<endC; c++){
            room[startR][c] = room[startR][c+1];
        }
        //맨오른쪽 줄 ↑
        for(int r = startR; r<endR; r++){
            room[r][endC] = room[r+1][endC];
        }
        //맨아랫 줄 →
        for(int c = endC; c>startC; c--){
            room[endR][c] = room[endR][c-1];
        }
        //맨왼쪽 줄 ↓
        for(int r = endR; r>startR;  r--){
            room[r][startR] = room[r-1][startR];
        }
        room[startR+1][startC] = corner;
    }

    // 아랫태풍r,0,끝r,끝c
    private static void down(int startR, int startC, int endR, int endC){
        int corner = room[startR][startC];
        //맨왼쪽 줄 ↓
        for(int r = startR; r<endR;  r++){
            room[r][startC] = room[r+1][startC];
        }
        //맨아랫 줄 ←
        for(int c = startC; c<endC; c++){
            room[endR][c] = room[endR][c+1];
        }
        //맨오른쪽 줄 ↑
        for(int r = endR; r>startR; r--){
            room[r][endC] = room[r-1][endC];
        }
        //맨윗쪽 줄 →
        for(int c = endC; c>startC; c--){
            room[startR][c] = room[startR][c-1];
        }
        room[startR][startC+1] = corner;
    }

    private static void cleaning(int[] tornado){
        room[tornado[0]][0] = -1;
        room[tornado[1]][0] = -1;
        room[tornado[0]][1] = 0;
        room[tornado[1]][1] = 0;
    }

    static int calculateTotalDust(int n, int m){
        int result = 0;
        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                if(room[x][y] == -1){
                    continue;
                }
                visited [x][y] = false;
                result += room[x][y];
            }
        }
        return result;
    }
}
