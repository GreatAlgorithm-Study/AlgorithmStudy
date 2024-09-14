import java.util.Stack;
class Solution {
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];
        Stack<Integer> stk = new Stack<>();                     // 큰 수만 저장할 스택
        for (int i = len - 1; i >= 0; i--) {
            // 가까운 큰 수를 찾아야하기 때문에
            while (!stk.isEmpty() && stk.peek() <= numbers[i])  // 현재 숫자보다 작은 값들은 제거
                stk.pop();
            answer[i] = stk.isEmpty() ? -1 : stk.peek();        // 스택이 비어있다면 -1 아니면 가까운 큰 수 출력
            stk.add(numbers[i]);                                // 현재 값을 스택에 넣어 줌
        }
        return answer;
    }
}