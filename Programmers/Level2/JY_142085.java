import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        // 무적권 개수 == 적의 수이면 모든 라운드 진행 가능
        if(k >= enemy.length) return answer;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 무적권 개수가 k보다 커지면 pq에서 가장 약한 라운드로 소모하기
        int tmp = 0;
        for(int i=0; i<enemy.length; i++) {
            pq.add(enemy[i]);
            tmp++;
            if(tmp > k) {
                int e = pq.poll();
                if(n - e < 0) {
                    answer = i;
                    break;
                }
                n -= e;
                tmp--;
            } 
    
        }
        
        
        return answer;
    }
}