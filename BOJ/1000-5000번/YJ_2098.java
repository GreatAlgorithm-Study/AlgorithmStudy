import java.io.*;
import java.util.*;

public class YJ_2098 {
    static int N;
    static int[][] route;
    static int[][] dp;
    static final int INF = 16_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        route = new int[N][N];
        dp = new int[1<<N][N]; //N개의 도시를 방문하는 모든 가능한 상태 (1<<N 은 2^N)

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                route[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(travelling(1,0));    //비트마스킹 1 은 방문완료
    }


    static int travelling(int visited, int now){
        if(visited == (1<<N)-1){    //모든 도시를 다 방문했는지 체크. 만약 N=4 일 경우 1<<N)-1 은 01111 즉, visited 가 1111 이라면 모든 도시를 다 방문했다는 뜻
            if(route[now][0] > 0){
                return route[now][0];
            }
            return INF;
        }

        if(dp[visited][now] > 0){   //값이 0 이상인 경우 메모리제이션 완료
            return dp[visited][now];
        }
        dp[visited][now] = INF; //해당 경로는 방문 전으로 최소값을 찾기위해 최대값으로 초기화

        for(int next=0; next < N; next++){
            if(stop(visited, now, next)){
                continue;
            }
            dp[visited][now] = Math.min(
                    dp[visited][now], travelling(visited |(1<<next),next) + route[now][next]
            );
        }

        return dp[visited][now];
    }

    // 도시 i에서 도시 j로 갈 수 없고, 다음에 이동할 도시를 이미 순회했을 경우
    private static boolean stop (int visited, int now, int next){
        return route[now][next] == 0 || (visited & (1<<next)) > 0;
    }
}