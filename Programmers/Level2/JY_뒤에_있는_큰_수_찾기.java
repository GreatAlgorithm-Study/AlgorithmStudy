import java.util.*;

class Solution {
    
    static class State implements Comparable<State>{
        int num, idx;
        public State(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
        @Override
        public int compareTo(State other){
            if(this.num == other.num){
                return this.idx-other.idx;
            }
            return this.num - other.num;
        }
        @Override
        public String toString(){
            return "n:"+this.num+" i:"+this.idx;
        }
    }
    
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];
        for(int i=0; i<N; i++){
            answer[i] = -1;
        }
        
        PriorityQueue<State> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            int val = numbers[i];
            while(!pq.isEmpty() && pq.peek().num < val){
                State pre = pq.poll();
                answer[pre.idx] = val;
            }
            pq.add(new State(val, i));
        }
        
        return answer;
    }
}