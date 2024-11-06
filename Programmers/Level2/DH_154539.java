import java.util.*;

/*
뒤에 있는 큰 수 찾기
 */

class DH_154539 {
    public int[] solution(int[] numbers) {

        int[] answer = new int[numbers.length];
        Arrays.fill(answer, - 1);

        Stack<Integer> stack = new Stack();
        for(int i = numbers.length - 1; i >= 0; i--) {

            if(!stack.isEmpty() && numbers[i] >= stack.peek()) {
                while(!stack.isEmpty() && numbers[i] >= stack.peek()) {
                    stack.pop();
                }
            }

            if(!stack.isEmpty()) answer[i] = stack.peek();
            stack.push(numbers[i]);
        }

        return answer;
    }
}

