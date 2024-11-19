import java.util.*;
import java.io.*;

//LCA(최소 공통조상)+DFS
//N(2 ≤ N ≤ 40,000)
public class YJ_1761 {
    static class Node{
        int num;
        int distance;
        Node(int num, int distance){
            this.num = num;
            this.distance = distance;
        }
    }

    static int n;
    static int m;
    static int[][] dp;
    static int[] depth;
    static int[] distances; //노드 1~N 까지의 합
    static boolean[] visited;
    static List<List<Node>> graph = new ArrayList<>();

    static final int MAX = 16;  //트리의 높이 log2^N 으로 최대 log2^40000 은 15.3

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        //초기화
        dp = new int[n+1][MAX+1];
        depth = new int[n+1];
        distances = new int[n+1];
        visited = new boolean[n+1];
        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }
        //그래프 모델링
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph.get(to).add(new Node(from, distance));
            graph.get(from).add(new Node(to, distance));
        }
        dfs(1, 0);
        saveParents();
        //정답 출력
        m = Integer.parseInt(br.readLine());
        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int common = lca(to, from); //루트와 가장 가까운 공통 조상 찾기
            System.out.println(distances[to] + distances[from] - (2*distances[common]));
        }
    }

    static void dfs(int to, int from){
        visited[to] = true; //★현재 시작점에 방문체크
        depth[to] = depth[from] + 1;

        for(Node next : graph.get(to)){
            if(!visited[next.num]){  //★현재와 이어진 다음 노드의 방문 유무 확인
                visited[next.num] = true;
                dp[next.num][0] = to;   //각 노드 별 직속 조상 저장으로 초기화
                distances[next.num] = distances[to] + next.distance;    //다음 노드에서 정점까지의 누적거리
                dfs(next.num,to);
            }
        }
    }

    //전체 트리에서 각 노드별로 다중 차수 부모 노드들 저장
    static void saveParents(){
        for(int from=1; from<MAX; from++){
            for(int to=1; to<n+1; to++){
                int parent = dp[to][from-1];
                dp[to][from] = dp[parent][from-1];
            }
        }
    }

    static int lca(int to, int from){
        if(depth[to] > depth[from]){    // from 이 항상 더 깊은 노드로 기준점 잡기
            int temp = to;
            to = from;
            from = temp;    //더 깊은 노드로 교체
        }
        //각 노드 간 레벨이 다를 경우 깊이 맞추기
        for(int i = MAX; i>=0; i--){
            if(Math.pow(2,i) <= depth[from] - depth[to]){   //Math.pow 를 통해 깊이 레벨을 표현 (하나의 레벨에 있는 모든 가짓수)
                from = dp[from][i]; // 더 깊은 노드인 from 를 위로 올림
            }
        }
        //깊이 레벨이 같고, 부모가 같다면 공통 조상 반환(to 를 반환해도 됨)
        if(to == from){
            return from;
        }
        //깊이가 같은 상태지만, 부모가 다른 경우 루트와 가장 가까운 레벨의 공통 조상 찾기
        for(int i=MAX; i>=0; i--){
            if(dp[to][i] != dp[from][i]){
                //to와 from의 부모가 다르면 상위 레벨로 계속 이동
                to = dp[to][i];
                from = dp[from][i];
            }
        }
        return dp[from][0]; //to와 from의 부모가 같기 때문에 dp[to][0]를 반환해도 됨
    }

}
