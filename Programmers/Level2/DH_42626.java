package Programmers.Level2;
import java.util.*;

/*
더 맵게
 */

public class DH_42626 {
    class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for(int i: scoville) pq.add(i);

            boolean isMake = false;
            while(true) {
                if(pq.peek() >= K) {
                    isMake = true;
                    break;
                }
                if(pq.size() == 1) break;

                int p1 = pq.poll(), p2 = pq.poll();
                pq.add(p1 + (p2 * 2));
                answer++;
            }

            if(!isMake) answer = -1;
            return answer;
        }
    }
}
