import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
class JW_118669 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++)
            graph.add(new ArrayList<>());
        for (int[] path : paths) {
            graph.get(path[0]).add(new int[] { path[1], path[2] });
            graph.get(path[1]).add(new int[] { path[0], path[2] });
        }

        HashSet<Integer> gateSet = new HashSet<>();
        HashSet<Integer> summitSet = new HashSet<>();
        for (int gate : gates)
            gateSet.add(gate);
        for (int summit : summits)
            summitSet.add(summit);

        int[] result = { 0, Integer.MAX_VALUE };
        Arrays.sort(summits);
        for (int summit : summits) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            boolean[] visited = new boolean[n + 1];
            pq.offer(new int[] { summit, 0 });
            while (!pq.isEmpty()) {
                int[] u = pq.poll();
                if (gateSet.contains(u[0])) {
                    if (result[1] > u[1]) {
                        result = new int[] { summit, u[1] };
                    }
                    break;
                }
                visited[u[0]] = true;
                for (int[] v : graph.get(u[0])) {
                    if (!visited[v[0]] && !summitSet.contains(v[0])) {
                        pq.offer(new int[] { v[0], Math.max(u[1], v[1]) });
                    }
                }
            }
        }
        return result;
    }
}