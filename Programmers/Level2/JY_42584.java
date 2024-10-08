import java.util.*;
class Solution {
    static class State {
        int price, time;
        public State(int price, int time){
            this.price = price;
            this.time = time;
        }
    }
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];
        Stack<State> stack = new Stack<>();
        for(int i=0; i<N; i++){
            // top보다 작은 가격이 나올때까지 반복
            while(!stack.isEmpty() && stack.peek().price > prices[i]){
                State now = stack.pop();
                answer[now.time] = i-now.time;
            }
            stack.add(new State(prices[i], i));
        }
        // 마지막까지 떨어지지 않은 가격들 처리
        while(!stack.isEmpty()){
            State now = stack.pop();
            answer[now.time] = (N-1)-now.time;
        }
        return answer;
    }
}