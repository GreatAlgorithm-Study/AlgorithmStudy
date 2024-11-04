import java.util.*;

/*
주식가격
 */

public class DH_42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < prices.length; i++) {
            // 스택에는 점점 증가하는 순서로만 저장되어야 함
            // price[i] < price[stack.peek()] 라면
            //    price[i] >= prices[stack.peek()]이 될 때까지 stack.pop()연산을 하면서 기간 구해주기
            while (!stack.isEmpty() && (prices[i] < prices[stack.peek()])) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int current = stack.pop();
            answer[current] = prices.length - 1 - current;
        }

        return answer;
    }
}
