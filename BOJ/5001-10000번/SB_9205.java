import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SB_9205 {
    static Node start;
    static Node end;
    private static boolean canGo(int N, Node[] stores) {
        Queue<Node> que = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        que.add(new Node(start.x, start.y));

        while (!que.isEmpty()){
            Node cur = que.poll();

            // 현재 위치에서 바로 페스티벌 도착할 수 있는지 확인
            if (calDist(cur.x, cur.y, end.x, end.y) <= 1000) return true;

            // 아니라면 편의점 탐색
            for (int i = 0; i < N; i++) {
                if (visited[i])continue;
                Node nxt = stores[i];
                if (calDist(cur.x, cur.y, nxt.x, nxt.y) <= 1000){
                    que.add(nxt);
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    private static int calDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());            // 편의점 개수
            Node[] stores = new Node[N];

            for (int i = 0; i < N+2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (i==0) start = new Node(x, y);
                else if (i==N+1) end = new Node(x, y);
                else stores[i-1] = new Node(x, y);
            }

            if (canGo(N, stores)) sb.append("happy").append('\n');
            else sb.append("sad").append('\n');
        }
        System.out.println(sb);
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
