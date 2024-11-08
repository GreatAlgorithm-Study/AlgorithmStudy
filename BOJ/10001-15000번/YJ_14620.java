import java.io.*;
import java.util.*;

public class YJ_14620 {
    static int N;
    static int[][] garden;
    static boolean[][] visited;
    static int result = 3001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        garden = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(result);
    }

    static final int SEED = 3;
    static int[] nx = {0,1,0,-1};
    static int[] ny = {1,0,-1,0};
    static void dfs(int depth, int price){
        if(depth == SEED){
            result = Math.min(result, price);
            return;
        }

        for(int i=1; i<N-1; i++){
            for(int j=1; j<N-1; j++){
                if(stop(i,j)){
                    break;
                }
                int flower = plant(i,j);
                dfs(depth+1,price+flower);
                remove(i,j);
            }
        }
    }

    //꽃을 심을 수 있는 구간인지 확인
    static boolean stop(int i, int j){
        if(visited[i][j]){
            return true;
        }
        for(int d=0; d<4; d++) {
            int x = i + nx[d];
            int y = j + ny[d];
            //꽃을 심을 수 없는 장소
            if(x < 0 || y < 0 || x >= N || y >= N || visited[x][y]){
                return true;
            }
        }
        return false;
    }

    //5평에 꽃 심기
    static int plant(int i , int j){
        int price = garden[i][j];

        for(int d=0; d<4; d++) {
            int x = i + nx[d];
            int y = j + ny[d];
            price += garden[x][y];
            visited[x][y] = true;
            //System.out.printf("garden[%d][%d] = %d %n",x,y,garden[x][y]);
        }

        return price;
    }

    //꽃 하나 없애기
    static void remove(int i, int j){
        for(int d=0; d<4; d++) {
            int x = i + nx[d];
            int y = j + ny[d];
            visited[x][y] = false;
        }
    }

}
