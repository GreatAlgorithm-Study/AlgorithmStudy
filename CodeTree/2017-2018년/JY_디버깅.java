import java.util.*;
import java.io.*;

public class Main {
    
    static int N, M, H;
    static int ans;
    static int MAX = Integer.MAX_VALUE;
    static int[][] g;
    static int[] nrr;
    static int[] dx = {0, 0}; //  우, 좌
    static int[] dy = {1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        g = new int[H+1][N+1];
        nrr = new int[N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a][b] = 1;
            g[a][b+1] = 1;
            nrr[b]++;
            nrr[b+1]++;
        }
        // for(int i=1; i<H+1; i++){
        //     System.out.println(Arrays.toString(g[i]));
        // }
        // System.out.println(Arrays.toString(nrr));
        // System.out.println(isPossible());

        ans = MAX;
        dfs(0);
        if(ans == MAX) System.out.println(-1);
        else System.out.println(ans);
    }
    public static boolean isOk() {
        for(int i=1; i<N+1; i++){
            if(nrr[i] % 2 != 0) return false;
        }
        return true;
    }
    public static void dfs(int cnt){
        if(cnt > 3) return;
        // if(isPossible()) {
        if(isOk()) {
            ans = Math.min(ans, cnt);
            return;
        }

        for(int i=1; i<N+1; i++){
            if(nrr[i] % 2 != 0) {
                for(int h = 1; h<H+1; h++){
                    if(g[h][i] == 0){
                        // i-1과 연결
                        if(i>1 && g[h][i-1] == 0){
                            nrr[i]++;
                            nrr[i-1]++;
                            g[h][i] = 1;
                            g[h][i-1] = 1;
                            dfs(cnt+1);
                            nrr[i]--;
                            nrr[i-1]--;
                            g[h][i] = 0;
                            g[h][i-1] = 0;
                        }

                        // i+1과 연결
                        if(i<N-1 && g[h][i+1] == 0){
                            nrr[i]++;
                            nrr[i+1]++;
                            g[h][i] = 1;
                            g[h][i+1] = 1;
                            dfs(cnt+1);
                            nrr[i]--;
                            nrr[i+1]--;
                            g[h][i] = 0;
                            g[h][i+1] = 0;
                        }
                    }
                }
            }
        }
    }
}