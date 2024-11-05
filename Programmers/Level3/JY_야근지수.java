import java.util.*;

class JY_야근지수 {
    
    static class State implements Comparable<State>{
        int work;
        public State(int work){
            this.work = work;
        }
        @Override
        public int compareTo(State other){
            return other.work - this.work;
        }
        @Override
        public String toString(){
            return "w:"+this.work;
        }
    }
    
    public long solution(int n, int[] works) {
        long answer = 0;
        long total = 0;
        
        PriorityQueue<State> pq = new PriorityQueue<>();
        for(int work: works){
            total += work;
            pq.add(new State(work));
        }
        // 작업량이 n보다 작으면 피로도 없음
        if(total <= n){
            return answer;
        }
        
        // 우선순위 큐를 이용해서 작업량이 많은 순으로 출력
        for(int i=0; i<n; i++){
            State now = pq.poll();
            now.work--;
            pq.add(now);
        }
        // System.out.println(pq);
        
        while(!pq.isEmpty()){
            State now = pq.poll();
            if(now.work > 0){
                answer += (now.work*now.work);
            }
        }
              
        return answer;
    }
}