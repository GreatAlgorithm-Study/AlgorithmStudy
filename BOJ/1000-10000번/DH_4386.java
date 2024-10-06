import java.io.*;
import java.util.*;

/*
별자리 만들기
 */

public class DH_4386 {
    static class Point {
        double r, c;
        public Point(double r, double c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Edge {
        int s, e;
        double w;
        public Edge(int s, int e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    static int N;
    static int[] p, rank;
    static Point[] points;
    static PriorityQueue<Edge> pq;

    static void mst() {
        int cnt = 0;
        double result = 0;

        while(!pq.isEmpty() && cnt < N - 1) {
            Edge current = pq.poll();

            if(find(current.e) == find(current.s)) continue;
            union(current.e, current.s);
            result += current.w;
            cnt++;
        }

        System.out.println(((int) ((result * 100)) * 1.0) / 100);
    }

    // rank를 사용한 union
    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        // rank가 작은 것을 큰 쪽으로 붙이기
        if(rank[a] < rank[b]) p[a] = b;
        else if(rank[a] > rank[b]) p[b] = a;
        // rank가 같다면, 한 쪽 rank 늘려주고 거기에 붙이기
        else {
            p[b] = a;
            rank[a]++;
        }
    }

    // find: 경로 압축
    static int find(int a) {
        return p[a] = a == p[a] ? a: find(p[a]);
    }

    // 입력받은 별의 위치에 대해 모든 경우의 수를 pq에 넣어줌
    static void setQ() {
        for(int i = 0; i < points.length; i++) {
            for(int j = i + 1; j < points.length; j++) {
                pq.add(new Edge(i, j, getDis(i, j)));
            }
        }
    }

    // 두 별 사이 거리를 구하는 함수
    static double getDis(int i, int j) {
        double diffR = Math.pow(points[i].r - points[j].r, 2);
        double diffC = Math.pow(points[i].c - points[j].c, 2);

        return Math.sqrt(diffR + diffC);
    }

    public static void main(String[] args) throws Exception {
        initInput();
        setQ();
        mst();
    }

    static void initInput() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        points = new Point[N];
        p = new int[N];
        rank = new int[N];

//        pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
//        pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.w, o2.w));
        pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.w));

        for(int i = 0; i < p.length; i++) p[i] = i;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double r = Double.parseDouble(st.nextToken());
            double c = Double.parseDouble(st.nextToken());

            points[i] = new Point(r, c);
        }
    }
}
