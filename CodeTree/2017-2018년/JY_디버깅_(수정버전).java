import java.util.*;
import java.io.*;

public class Main {
    static int N, M, H;
    static int ans;
    static int MAX = Integer.MAX_VALUE;
    static int[][] g;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        g = new int[H+1][N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a][b] = 1;
            g[a][b+1] = 2;
        }

        ans = MAX;
        dfs(0);
        if(ans == MAX) System.out.println(-1);
        else System.out.println(ans);
    }
    public static void print() {
        for(int i=1; i<H+1; i++){
            System.out.println(Arrays.toString(g[i]));
        }
        System.out.println();
    }
    public static boolean isPossible() {
        for(int j=1; j<N+1; j++) {
            int now = j;
            int h = 1;
            while(h <= H) {
                // 왼 -> 오
                if(g[h][now] == 1){
                    now++;
                }
                // 오 -> 왼
                else if(g[h][now] == 2) {
                    now--;
                }
                h++;
            }
            if(now != j) return false;
        }
        return true;

    }
    public static void dfs(int cnt) {
        if(cnt >= ans || cnt > 3) return;

        if(isPossible()) {
            ans = Math.min(ans, cnt);
        }

        for(int i=1; i<H+1; i++) {
            for(int j=1; j<N; j++) {
                // 사다리 설치할 수 있음
                if(g[i][j] == 0 && g[i][j+1] == 0) {
                    g[i][j] = 1;
                    g[i][j+1] = 2;
                    dfs(cnt+1);
                    g[i][j] = 0;
                    g[i][j+1] = 0;
                }
            }
        }
    }
}