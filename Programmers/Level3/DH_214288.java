import java.util.*;

public class DH_214288 {
    // 멘토 인원을 적절히 배정했을 때, 참가자들이 상담을 받기까지 기다린 시간을 모두 합한 최소값
    // n: 멘토의 수, k: 상담 유형
    // 유형별 멘토 인원이 적어도 한 명 이상
    public int solution(int k, int n, int[][] reqs) {
        int[] people = new int[k]; // 상담에 대해서 몇 명까지 상담 받을 수 있는지 저장
        int answer = func(0, n, k, people, reqs);

        return answer;
    }

    static int func(int depth, int n, int k, int[] people, int[][] reqs) {
        int result = Integer.MAX_VALUE;

        if(depth == k) {
            if(n == 0) return getWaitingTime(people, k, reqs);
            return result;
        }

        for(int i = 1; i < n + 1; i++) {
            people[depth] = i;
            result = Math.min(result, func(depth + 1, n - i, k, people, reqs));
        }

        return result;
    }

    static int getWaitingTime(int[] people, int k, int[][] reqs) {
        int waitingTime = 0;

        // 상담 유형별 끝나는 시간들 저장
        PriorityQueue<Integer>[] q = new PriorityQueue[k];
        for(int i = 0; i < q.length; i++) q[i] = new PriorityQueue<>();

        for(int i = 0; i < reqs.length; i++) {
            int a = reqs[i][0];
            int b = reqs[i][1];
            int c = reqs[i][2] - 1;

            // 해당 유형의 상담을 받고 있는 총 사람과 제한된 사람수가 같다면
            // 제일 빨리 끝나는 사람 poll하고, 대기 시간 구해주기
            if(q[c].size() == people[c]) {
                int endTime = q[c].poll();

                if(endTime > a) {
                    waitingTime += endTime - a;
                    a = endTime;
                }
            }

            q[c].add(a + b);
        }

        return waitingTime;
    }

}
