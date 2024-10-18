import java.util.*;

/*
섬 연결하기
 */

class DH_42861 {

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

    static int[] p;
    static PriorityQueue<Edge> q;

    static int mst() {
        int cnt = 0, result = 0;

        // 간선이 N - 1개 만들어질 때까지 진행
        while(!q.isEmpty() && cnt < (p.length - 1) - 1) {
            Edge current = q.poll();

            if(find(current.s) == find(current.e)) continue;
            union(current.s, current.e);
            result += current.w;
        }

        return result;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) p[b] = a;
    }
    
    static int find(int a) {
        return p[a] = a == p[a] ? a: find(p[a]);
    }

    public int solution(int n, int[][] costs) {
        q = new PriorityQueue<>();
        p = new int[n + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;

        for(int[] c: costs) {
            int s = c[0];
            int e = c[1];
            int w = c[2];

            // Queue에 Edge 저장
            q.add(new Edge(s, e, w));
            q.add(new Edge(e, s, w));
        }

        return mst();
    }
}