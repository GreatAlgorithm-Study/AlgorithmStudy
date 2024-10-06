import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SB_4386 {
    static int N;
    static double[][] dots;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] parents;

    private static void makeEdge(int a, int b){
        double cost = Math.sqrt(Math.pow(Math.abs(dots[a][0]-dots[b][0]),2) + Math.pow(Math.abs(dots[a][1]-dots[b][1]),2));
        pq.offer(new Node(a, b, cost));
    }

    private static int find(int x) {
        if (x!=parents[x]) parents[x] = find(parents[x]);
        return parents[x];
    }

    private static void union(int a, int b) {
        if (a>b) parents[b] = a;
        else parents[a] = b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        dots = new double[N][2];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            dots[i] = new double[]{a, b};
        }

        // 간선 만들어서 넣기
        for (int i = 0; i < N-1; i++) {
            for (int j = i + 1; j < N; j++) {
                makeEdge(i, j);
            }
        }

        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        double ans = 0;
        int cnt = 1;
        while (!pq.isEmpty() && cnt<N) {
            Node node = pq.poll();
            int p_u = find(node.u);
            int p_v = find(node.v);

            if (p_u != p_v) {
                union(p_u, p_v);
                ans+=node.cost;
                cnt++;
            }
        }
        ans = Math.round(ans * 100) / 100.0;
        System.out.println(ans);
    }

    static class Node implements Comparable<Node>{
        int u,v;
        double cost;
        Node(int u, int v, double cost){
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Double.compare(this.cost, o.cost);
        }
    }
}
