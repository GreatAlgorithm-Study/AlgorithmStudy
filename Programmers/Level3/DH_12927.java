package Programmers.Level3;

import java.util.*;

/*
야근 지수
 */

class DH_12927 {

    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < works.length; i++) pq.add(works[i]);

        while(n > 0) {
            int current = pq.poll();
            if(current == 0) break;

            current--;
            n--;
            pq.add(current);
        }

        while(!pq.isEmpty()) {
            answer += (long) Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}