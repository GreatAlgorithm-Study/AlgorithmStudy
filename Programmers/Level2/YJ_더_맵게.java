import java.util.PriorityQueue;

/**
 * 알고리즘: 우선순위 큐
 * 시간복잡도: 2  <= scoville <= 1,000,000 크기로 O(NlogN)
 * 아이디어:
 * 두 수를 합친 후 스코빌 배열에서 오름차순 정렬이 되어야 하기 때문에
 * 우선순위 큐를 사용해서 두수를 빼내고, 합친 수를 다시 넣도록 함
 * 우선순위 큐는 기본적으로 원소를 오름차순 정렬하기 때문에 큐의 첫번째 원소가 K 보다 크거나 같다면 스코빌 모든 음식의 스코빌 지수가 K 이상 임
 */
public class YJ_더_맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int s : scoville){
            queue.offer(s);
        }

        while(queue.size() > 1){
            int first = queue.poll();
            if(first >= K){
                break;
            }
            int second = queue.poll();
            int mixed = calculateScoville(first,second);
            queue.offer(mixed);
            answer++;
        }

        if(queue.poll() < K){
            return -1;
        } else {
            return answer;
        }
    }

    //첫번째로 낮은 스코빌 지수 + (두번째로 낮은 스코빌 지수 * 2)
    private int calculateScoville(int first, int second){
        return first + (second*2);
    }
}
