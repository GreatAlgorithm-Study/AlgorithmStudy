import java.util.*;

public class SB_142085 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = enemy.length;
        
        if(k>=N) return N;                 // 다 무조건으로 이길 수 있을 경우 
        
        for(int i=0; i<N; i++){
            pq.offer(enemy[i]);            // 큐에 현재 라운드 추가
            if(pq.size() > k){             // 큐의 크기가 k보다 크면, 무조건 사용 불가로 
                int weak = pq.poll();      // 제일 약한애랑 싸우기
                if(n-weak < 0) return i;   // i는 현재라운드-1 값을 나타내기에 현재 라운드에서 지면 i반환
                n-=weak;                   // 싸우고 병사 수 줄기
            }
        }
        return N;
    }
}
