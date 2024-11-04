import java.util.Stack;
class JW_42584 {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        // 초기값 설정
        for (int i = 0; i < n; i++) {
            answer[i] = n - 1 - i;
        }
        // 작은 값만 유지할 스택
        Stack<Integer> stk = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 스택 내에 현재 값보다 큰 값이 있으면 pop
            while (!stk.isEmpty() && prices[stk.peek()] >= prices[i])
                stk.pop();
            // 현재 인덱스보다 작은 값이 있다면
            if (!stk.isEmpty())
                // 그 인덱스까지는 증가한 것이므로 값 계산
                answer[i] -= n - 1 - stk.peek();
            // 현재 인덱스 삽입
            stk.push(i);
        }
        return answer;
    }
}