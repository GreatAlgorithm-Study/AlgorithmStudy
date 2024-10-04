package Programmers.Level2;

import java.util.*;

/*
구명보트
 */

public class DH_42885 {
    class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;

            Deque<Integer> q = new ArrayDeque<>();

            Arrays.sort(people); // people 배열을 정렬 후 deque에 담음
            for (int p : people) q.add(p);

            while (!q.isEmpty()) { 
                // pollLast를 하며 숫자가 큰 것부터 처리
                int current = q.pollLast();
                // 제한을 넘는다면 continue
                if (current > limit) continue;

                answer++;

                if (!q.isEmpty()) {
                    // 큐에 있는 제일 큰 값과, 제일 작은 값을 비교하며
                    // 구명보트 태우기
                    if (current + q.peekLast() <= limit) q.pollLast();
                    else if (current + q.peekFirst() <= limit) q.pollFirst();
                }

            }
            return answer;
        }
    }
}