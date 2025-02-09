import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SB_2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.offer(new Node(s, e));
        }

        Node first = pq.poll();
        int start = first.s;
        int end = first.e;
        int cnt = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (start <= cur.s && cur.s <= end) {   // 현재 선분이랑 겹칠 경우
                end = Math.max(end, cur.e);         // 더 큰 끝값으로 업데이트
                continue;
            }

            // 겹치지 않음 (== 새로운 선분 시작)
            cnt += end - start;     // 지금까지의 선분 길이 더해주기
            start = cur.s;
            end = cur.e;
        }
        cnt += end-start;   // 마지막 선분 길이 업데이트

        System.out.println(cnt);
    }
    static class Node implements Comparable<Node>{
        int s, e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            return this.s - o.s;
        }
    }
}
