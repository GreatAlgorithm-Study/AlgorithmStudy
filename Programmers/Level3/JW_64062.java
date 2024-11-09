import java.util.ArrayDeque;
import java.util.Deque;
class JW_64062 {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        // 슬라이딩 윈도우를 만들 큐
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < stones.length; i++) {
            // 최댓값(큐의 맨 앞)이 슬라이딩 윈도우의 크기를 벗어났다면 제거
            if (!dq.isEmpty() && dq.peekFirst() == i - k)
                dq.pollFirst();
            // 최댓값을 유지하며 현재 들어갈 요소보다 작은 값들 제거
            while (!dq.isEmpty() && stones[dq.peekLast()] <= stones[i])
                dq.pollLast();
            dq.offerLast(i); // 현재 돌의 인덱스 추가
            // 슬라이딩 윈도우가 완성되는 순간부터 최솟값 계산
            if (i >= k - 1)
                answer = Math.min(answer, stones[dq.peekFirst()]);
        }
        return answer;
    }
}