import java.util.Stack;

/**
 * Stack 문제
 */
public class YJ_뒤에_있는_큰_수_찾기 {
    public static void main(String[] args) {
        int[] numbers1 = {2, 3, 3, 5};  //3, 5, 5, -1
        int[] numbers2 = {9, 1, 5, 3, 6, 2};    //-1, 5, 6, 6, -1, -1
        int[] result = solution(numbers2);
        for (int j : result) {
            System.out.print(j + " ");
        }
    }

    static int[] solution(int[] numbers) {
        int n = numbers.length;

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        stack.push(i);
        for(i=1; i<n; i++){
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){
                numbers[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            numbers[stack.pop()] = -1;
        }

        return numbers;
    }
}
