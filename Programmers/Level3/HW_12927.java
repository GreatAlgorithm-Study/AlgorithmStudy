import java.util.*;

// n : 퇴근시간까지 남은 시간
// works : 작업량
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0; // 야근 피로도 = 시작한 시점에서 (남은 일의 작업량)^2
        PriorityQueue<Integer> Queue = new PriorityQueue<>(Comparator.reverseOrder()); // 높은 숫자 우선순위 큐 정의

        for(int i=0; i<works.length; i++){
            Queue.offer(works[i]); // offer() : 우선순위 큐에 원소 추가, 실패시 false 반환
        }

        while(n>0){
            int work = Queue.poll();
            if(work==0) {
                break;
            }
            work -= 1;
            Queue.offer(work);
            n -=1;
        }

        int size = Queue.size(); // Queue size 줄어드는 것 방지
        for(int i=0; i<size; i++){
            int work = Queue.poll();
            answer += work * work;
        }

        return answer;
    }
}