import java.util.*;

// 최선의 선택 : enemy[i]가 큰거에 무적권 사용
// 큰게 우선순위, 내림차순 정렬

class HW_142085 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        for(int i=0; i<enemy.length; i++){
            n -= enemy[i];
            pq.add(enemy[i]);

            if(n<0){
                if(k>0){
                    n += pq.poll();
                    k--;
                }
                else{
                    break;
                }
            }
            answer++;
        }
        return answer;
    }
}