import java.io.*;
import java.util.*;

public class HW_1613 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N+1][N+1]; // 방문 여부만 확인
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            visited[a][b] = true; // 전후 관계 알고 있음
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++){
                    if(visited[i][k] && visited[k][j]) {
                        visited[i][j] = true;
                    }
                }
            }
        }

        int S = Integer.parseInt(br.readLine()); // 사건의 전후 관계를 알고 싶은 사건 쌍의 수
        for(int i=0; i<S; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(visited[a][b]) {
                System.out.println(-1);
            }
            else if(visited[b][a]) {
                System.out.println(1);
            } else System.out.println(0);
        }
    }
}

