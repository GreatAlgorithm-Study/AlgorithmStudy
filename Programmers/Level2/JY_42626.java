import java.util.*;
class JY_42626 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++){
            pq.add(scoville[i]);
        }
        while (pq.size() != 1){
            int s1 = pq.poll();
            if (s1 >= K) break;
            else{
                int s2 = pq.poll();
                int newS = s1 + (s2*2);
                pq.add(newS);
                answer += 1;
            }
        }
        
        // System.out.println(pq);
        // 가장 맵지않은 스코빌 지수가 K보다 작으면 -1
        if (pq.peek() < K) return -1;
        return answer;
    }
}