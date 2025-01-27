import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class JW_9205_2 {

    static int n;
    static int[][] distance;
    static ArrayList<ArrayList<Integer>> graph; // 간선 정보

    public static void main(String[] args) throws Exception {
        int t = read();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            n = read();
            distance = new int[n + 2][];
            for (int i = 0; i < n + 2; i++)
                distance[i] = new int[] { read(), read() };
            graph = new ArrayList<>();
            for (int i = 0; i < n + 2; i++)
                graph.add(new ArrayList<>());
            // 간선 정보 입력
            for (int i = 0; i < n + 1; i++)
                for (int j = i + 1; j < n + 2; j++)
                    if (isMovable(distance[i], distance[j])) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
            if (isPossible())
                sb.append("happy\n");
            else
                sb.append("sad\n");
        }
        System.out.println(sb);
    }

    // 페스티벌 좌표(n+1) 까지 이동할 수 있는지 BFS
    private static boolean isPossible() {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 2];
        dq.offer(0);
        visited[0] = true;
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            // 종료 조건
            if (cur == n + 1)
                return true;
            for (int next : graph.get(cur))
                if (!visited[next]) {
                    dq.offer(next);
                    visited[next] = true;
                }
        }
        return false;
    }

    // 맨해튼 거리로 이동할 수 있는지 확인
    private static boolean isMovable(int[] A, int[] B) {
        return Math.abs(A[0] - B[0]) + Math.abs(A[1] - B[1]) <= 1_000;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)
            n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return m ? ~n + 1 : n;
    }
}