import java.io.*;
import java.util.*;

/*
도시 분할 계획
 */

public class DH_1647 {
    static class Edge implements Comparable<Edge> {
        int s, e, w;
        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static PriorityQueue<Edge> pq;
    static int N, M, p[];
    static long result;

    static void mst() {
        int cnt = 0;
        while(!pq.isEmpty() && cnt < N - 2) {
            Edge current = pq.poll();

            int s = current.s;
            int e = current.e;

            if(find(s) == find(e)) continue;
            union(s, e);
            cnt++;
            result += current.w;
        }

        System.out.println(result);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }

    static int find(int a) {
        return p[a] = a == p[a] ? a : find(p[a]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;

        pq = new PriorityQueue<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, c));
        }

        mst();
    }
}