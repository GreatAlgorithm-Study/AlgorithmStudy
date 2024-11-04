import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class JW_20007 {

    // 간선 정보를 저장할 객체
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        int n = read(), m = read(), x = read(), y = read();
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>(); // 간선들을 저장할 리스트
        for (int i = 0; i < n; i++)
            edges.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = read(), v = read(), w = read();
            edges.get(u).add(new Edge(v, w));
            edges.get(v).add(new Edge(u, w));
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE); // 가중치 초기화
        dist[y] = 0; // 시작 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> dist[o1] - dist[o2]); // 가중치로 정렬하는 우선순위 큐
        pq.offer(y);
        // 다익스트라
        while (!pq.isEmpty()) {
            int u = pq.poll();
            for (Edge edge : edges.get(u)) {
                int v = edge.to;
                int w = edge.weight;
                // 현재 가중치보다 이동했을 때 가중치가 작다면
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(v);
                }
            }
        }
        // 거리가 짧은 집부터 들려야 하기 때문에 정렬
        Arrays.sort(dist);
        // 마지막 집까지 도달할 수 없을 경우 -1 출력
        if (dist[n - 1] * 2 > x) {
            System.out.println(-1);
            return;
        }
        int idx = 0;
        int day = 0;
        while (idx < n) {
            day++;
            int hp = x;
            // 남은 체력으로 다른 집도 갈 수 있는지 확인
            while (idx < n && hp >= dist[idx] * 2) {
                hp -= dist[idx] * 2;
                idx++;
            }
        }
        System.out.println(day);
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}