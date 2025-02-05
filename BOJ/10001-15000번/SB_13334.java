package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SB_13334 {
    static int N, d;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.offer(new Node(Math.min(s, e), Math.max(s, e)));     // 집과 사무실 중 작은게 시작, 큰게 끝
        }
        d = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pre = new PriorityQueue<>();         // 이전 사람들의 정보를 저장하는 우선순위 큐
        int mx = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int start = cur.e - d;      // 철로의 시작 지점
            pre.offer(cur.s);           // 현재 노드의 시작지점을 pre큐에 넣어주기
            while (!pre.isEmpty() && pre.peek() < start) {      // 이전 사람들이 현재 철로에 속하지 못하면 빼주기
                pre.poll();
            }
            mx = Math.max(mx, pre.size());
        }

        System.out.println(mx);
    }

    static class Node implements Comparable<Node>{
        int s, e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }
        @Override
        public int compareTo(Node o) {
            if (this.e-o.e!=0) return this.e - o.e;
            return this.s-o.s;
        }
    }
}
