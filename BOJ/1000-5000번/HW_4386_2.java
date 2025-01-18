

/*
별자리 만들기

별자리를 만드는 최소 비용 구하기
 MST : 그래프에서 모든 노드를 연결할 때 사용된 에지들의 가중치 합을 최소로 하는 트리
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HW_4386_2 {
    static class Edge implements Comparable<Edge>{
        int s, e;
        double v;
        public Edge(int s, int e, double v){
            this.s = s;
            this.e = e;
            this.v = v;
        }
        @Override
        public int compareTo(Edge o){
            return Double.compare(this.v, o.v); // 가중치 기준 오름차순 정렬
        }
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 별의 개수
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }
        double[][] stars = new double[n][2];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                double distance = calculate(stars[i], stars[j]);
                pq.add(new Edge(i, j, distance)); // 모든 간선을 pq에 추가
            }
        }
        double ans = 0.0;
        // MST 구성
        while(!pq.isEmpty()){
            Edge edge = pq.poll(); // 가장 짧은 간선부터 꺼내서
            if(find(edge.s) != find(edge.e)){ // 사이클 발생하지 않으면
                union(edge.s, edge.e); // 두 노드를 연결함
                ans += edge.v; // 거리 추가
            }
        }
        System.out.printf("%.2f\n", ans);
    }
    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a!=b){
            parent[b] = a;
        }
    }
    private static int find(int a){
        if(a==parent[a]){
            return a;
        } else{
            return parent[a] = find(parent[a]); // 경로 압축
        }
    }
    private static double calculate(double[] a, double[] b){
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}
