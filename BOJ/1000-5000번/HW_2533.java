import java.util.*;
import java.io.*;

// 시간 복잡도 : N<=1,000,000 -> O(V+E)
// 최소한의 얼리어 답터로 모든 노드 아이디어 수용

// 조건 1 : 자신이 얼리어 답터O -> 모든 자식 노드 아이디어 수용O
// 조건 2 : 자신이 얼리어 답터X -> 모든 자식 노드가 얼리어 답터O
public class HW_2533 {
    static List<List<Integer>> tree = new ArrayList<>();
    static int[][] dp;
    static boolean[] visited; // 방문 처리
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1][2];
        visited = new boolean[n+1];
        for(int i=0; i<=n; i++){ // 트리 초기화 : ArrayList에서 노드 번호를 리스트 인덱스로 사용하기 위함
            tree.add(new ArrayList<>());
        }

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 양방향 연결
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        dfs(1); // 1번 노드를 루트로 설정하여 탐색 시작

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    static void dfs(int node){
        visited[node] = true; // 방문 처리
        dp[node][0] = 0; // 1) 얼리어 답터X
        dp[node][1] = 1; // 2) 얼리어 답터O

        List<Integer> child = tree.get(node);
        for(int i=0; i<child.size(); i++){
            int c = child.get(i);
            if(!visited[c]){
                dfs(c); // 자식 노드 방문
                dp[node][0] = dp[node][0] + dp[c][1]; // 자식이 얼리어 답터
                dp[node][1] = dp[node][1] + Math.min(dp[c][0], dp[c][1]); // 얼리어답터O or 얼리어답터X
            }
        }
    }
}