import java.io.*;
import java.util.*;

public class YJ_2533 {
    static List<Integer>[] graph;
    static int[][] dp;  //dp[index][0] : 얼리 아답터 X , dp[index][1] : 얼리 아답터 O
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n+1][2];
        visited = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int u) {
        visited[u] = true;
        dp[u][0] = 0;   //얼리 아답터 X
        dp[u][1] = 1;   //얼리 아답터 O

        for(int i=0; i<graph[u].size(); i++) {
            if(graph[u].get(i) == null || visited[graph[u].get(i)]) {   //연결된 친구가 없거나, 이미 친구를 방문했다면 패스
                continue;
            }
            dfs(graph[u].get(i));
            dp[u][0] += dp[graph[u].get(i)][1]; //얼리어답터가 아니라면 나와 연결된 노드는 모두 얼리어답터O
            dp[u][1] += Math.min(dp[graph[u].get(i)][1],dp[graph[u].get(i)][0]);    //얼리어답터라면 나와 연결된 노드는 얼리어답터O or 얼리어답터X
        }
    }

}
