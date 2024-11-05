import java.util.PriorityQueue;
import java.util.Queue;

class SB_42626 {
    public int solution(int[] scoville, int K) {
        Queue<Integer> pq = new PriorityQueue<>();

        for (int sc : scoville) {
            pq.offer(sc);
        }

        int cnt = 0;
        while (!pq.isEmpty() && pq.size() >=2) {
            int cur = pq.poll();
            if (cur >=K) break;
            int cur2 = pq.poll();
            int nw = cur + (cur2 * 2);
            pq.offer(nw);
            cnt++;
        }
        if (!pq.isEmpty() && pq.peek() < K) return -1;
        return cnt;
    }
}