import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            int[][] dist = new int[n + 2][2];
            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                dist[i][0] = Integer.parseInt(st.nextToken());
                dist[i][1] = Integer.parseInt(st.nextToken());
            }
            // 각 위치에서 다음 위치로의 거리가 1000이하인 경우 움직일 수 있음을 저장
            boolean[][] possible = new boolean[n + 2][n + 2];
            for (int i = 0; i < n + 2; i++)
                for (int j = 0; j < n + 2; j++) {
                    if (canGo(dist[i], dist[j]))
                        possible[i][j] = true;
                }
            // 시작 지점에서 도착 지점까지 갈 수 있는지 확인
            if (isReachable(possible)) {
                sb.append("happy");
            } else {
                sb.append("sad");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 맨해튼 거리 계산
    private static boolean canGo(int[] A, int[] B) {
        return Math.abs(A[0] - B[0]) + Math.abs(A[1] - B[1]) <= 1000;
    }

    // BFS를 이용하여 목적지(마지막 좌표)로 갈 수 있는지 확인하는 함수
    private static boolean isReachable(boolean[][] possible) {
        int len = possible.length;
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[len];
        dq.offer(0);
        visited[0] = true;
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            if (cur == len - 1) 
                return true;
            // 이동하지 않은 1000 이하의 거리에 편의점이 있다면 이동
            for (int i = 0; i < len; i++) 
                if (possible[cur][i] && !visited[i]) {
                    dq.offer(i);
                    visited[i] = true;
                }
        }
        return false;
    }
}