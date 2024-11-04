import java.util.PriorityQueue;
class JW_42626 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        // 우선순위 큐를 이용해서 최소 힙 구현
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++)
            pq.offer(scoville[i]);
        // 큐 안에 2개 이상의 원소가 있다면 합칠 수 있음
        while (pq.size() > 1 && pq.peek() < K) {
            pq.offer(pq.poll() + pq.poll() * 2);
            answer++;
        }
        // 큐의 최솟값이 K보다 작다면 NG
        if (pq.peek() < K)
            return -1;
        return answer;
    }
}