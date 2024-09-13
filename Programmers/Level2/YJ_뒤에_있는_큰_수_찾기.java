import java.util.Stack;

/**
 * 알고리즘: Stack
 * 시간복잡도: O(n)
 * 아이디어:
 * 전체에서 하나씩 더 큰수를 찾으며 비교하는게 아니라,
 * 전체 반복을 돌면서 기존 수과 다음에 오는 수를 계속해서 비교하는데 이때 이전에 누적된 수도 함께 갱신할 수 있어야한다.
 * 또한 배열에 담긴 수를 가져오기 위해 index 를 활용해서 Stack에 index를 담고 이전에 누적된 수를 가져오도록 한다
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
        for(i=1; i<n; i++){ //O(n)
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){  //O(n)
                numbers[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){    //O(n)
            numbers[stack.pop()] = -1;
        }

        return numbers;
    }
}
