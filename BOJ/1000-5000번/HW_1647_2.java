import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 나머지 길의 유지비의 합을 최소로
// 시간 복잡도 : N<=10^5, M<=10^6, O(M logM)
// MST : 그래프에서 모든 노드를 연결할 때 사용된 에지들의 가중치 합을 최소로 하는 트리
// 도시 분할 할 때 유지비 합의 최솟값을 구해야함, 모든 도시들 연결되어있음 -> MST

// 분리된 두 마을 사이의 길은 없애야 함
public class HW_1647_2 {
    static class Edge implements Comparable<Edge>{ // 참고한 부분 에지 리스트 구현
        int s, e, v;
        public Edge(int s, int e, int v){
            this.s = s;
            this.e = e;
            this.v = v;
        }
        @Override
        public int compareTo(Edge o){
            return this.v - o.v; // 가중치 기준 오름차순 정렬
        }
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=1; i<=N; i++){ // 도시 번호 1부터 시작
            parent[i] = i;
        }
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }
        Collections.sort(edges); // 오름차순 정렬

        int total = 0;
        int maxEdge = 0; // 가장 큰 간선

        // MST 구성
        for(Edge edge : edges){ // 사이클이 없는 경우에만
            if(find(edge.s) != find(edge.e)){
                union(edge.s, edge.e); // 두 노드 연결
                total += edge.v; // 가중치를 누적
                maxEdge = Math.max(maxEdge, edge.v); // 최대 간선 구함
            }
        }
        System.out.println(total-maxEdge);
    }
    private static void union(int a, int b){
        a = find(a); // 부모노드를 찾음
        b = find(b);
        if(a!=b){ // 부모가 다르면
            parent[b] = a; // 대표 노드끼리 대표노드 설정
        }
    }
    private static int find(int a){
        if(a==parent[a]){ // 부모 노드일 경우
            return a;
        } else {
            return parent[a] = find(parent[a]); // 경로 압축
        }
    }
}