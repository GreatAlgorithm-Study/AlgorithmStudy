import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 별과 별 사이의 거리를 저장할 객체
    static class Edge {
        int to;
        double distance;

        Edge(int to, double distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[][] stars = new double[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }
        // 별들의 거리 정보를 저장할 리스트
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        // 모든 별들 사이의 거리를 계산하여 리스트에 저장
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++) {
                double distance = calDistance(stars[i], stars[j]);
                graph.get(i).add(new Edge(j, distance));
                graph.get(j).add(new Edge(i, distance));
            }
        // mst를 계산하기 위해 방문 처리할 배열
        boolean[] visited = new boolean[n];
        // 간선들의 가중치를 오름차순으로 정렬
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.distance, o2.distance));
        pq.offer(new Edge(0, 0));   // 시작 점(아무 곳이나 설정)
        double mstDistance = 0;     // 모든 점을 최소로 이었을 때 거리의 합
        // Prim algorithm
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int u = cur.to;
            // 방문한 배열은 스킵
            if (visited[u])
                continue;
            visited[u] = true;
            mstDistance += cur.distance;        // 거리의 합에 더해줌
            for (Edge next : graph.get(u)) {
                int v = next.to;    // 다음으로 이동할 별
                // 방문하지 않았다면
                if (!visited[v])
                    pq.offer(next); // 큐에 삽입
            }
        }
        System.out.println(mstDistance);
    }

    // 별과 별 사이의 거리 계산
    private static double calDistance(double[] A, double[] B) {
        return Math.sqrt(Math.pow(B[0] - A[0], 2) + Math.pow(B[1] - A[1], 2));
    }
}