import java.util.*;

/**
 * 알고리즘: 우선순위 Queue
 * 시간복잡도: O(n)
 * 아이디어:
 * 내림차순 정렬을 통해 가장 큰 숫자들부터 작게 만들어서 최소값 구하기
 * PriorityQueue를 사용하면 우선순위에 따라 숫자가 먼저 반환된다
 */
public class YJ_야근지수 {
    public static void main(String[] args) {
        int n = 1000;
        int[] works = {3,3,3,3,2};
        System.out.println(solution(n, works));
    }

    static long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        //내림차순 정렬
        for(int w : works){
            queue.offer(w);
        }

        while(!queue.isEmpty() && n>0){
            int current = queue.poll();
            if(current > 0){
                current = current-1;
                n--;
                if(current == 0){
                    continue;
                }
                queue.offer(current);
            }
        }

        while(!queue.isEmpty()){
            answer += (long) Math.pow(queue.poll(),2);
        }
        return answer;  //야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값
    }

}
