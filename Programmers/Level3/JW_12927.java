import java.util.PriorityQueue;

class JW_12927 {
    public long solution(int[] topping, int[] works, int n) {
        long answer = 0;
        // work의 길이가 1일 경우
        if (works.length == 1)
            return (long) Math.pow(Math.max(0, works[0] - n), 2); // 음수 처리 및 출력
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < works.length; i++)
            pq.offer(works[i]);          // 우선순위 큐를 사용하여 내림차순 정렬
        while (n > 0) {
            int most = pq.poll();        // 현재 가장 작업량이 많은 원소
            // 해당 원소가 0이라면 종료
            if (most == 0)
                return 0;
            int diff = most - pq.peek(); // 다음 원소와의 차이를 계산
            // diff에 따라 값 계산
            if (diff == 0) {
                most--;
                n--;
            } else if (n < diff) {
                most -= n;
                n = 0;
            } else {
                most -= diff;
                n -= diff;
            }
            pq.offer(most);             // 계산된 값을 큐에 다시 넣음
        }
        for (int i = 0; i < works.length; i++)
            answer += (long) Math.pow(pq.poll(), 2);
        return answer;
    }
}