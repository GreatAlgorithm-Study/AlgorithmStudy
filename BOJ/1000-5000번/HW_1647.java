import java.io.*;
import java.util.*;

// 시간 복잡도 : 2 <= N <= 100,000
// 1 <= M <= 1,000,000
// O(N) or O(N log N)
// 두 마을을 분리, 마을 사이의 길의 유지비 합을 최소로
// 크루스칼 알고리즘 : 가장 적은 비용으로 모든 노드를 연결하기 위해 사용하는 알고리즘 O(E log V)


public class HW_1647 {
    static class Edge implements Comparable<Edge>{int cost;
        int start;
        int end;


        Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }
    static int N, M;
    static List<Edge> arr = new ArrayList<>();
    static int[] parent;
    static int ans =0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 경로 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        for(int i=0;  i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr.add(new Edge(start, end, cost));
        }

        parent = new int[N + 1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        Collections.sort(arr);
        // 2) 두 마을을 분리한다
        // 모든길을 유지비 기준 오름차순 정렬

        int max_ans = 0;
        for(int i=0; i<arr.size(); i++){
            Edge edge = arr.get(i);
            if(find(edge.start) != find(edge.end)){
                union(edge.start, edge.end);
                ans += edge.cost;
                max_ans = edge.cost;
            }
        }
        System.out.println(ans - max_ans); // 가장 비용이 큰 간선을 제거해서 두 마을을 분리

        // 3) 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없애기


        // 4) 마을 사이의 길의 유지비 합을 최소로
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y){
            parent[y] = x;
        }
    }

    public static int find(int x){
        if(x==parent[x]){
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }
}


