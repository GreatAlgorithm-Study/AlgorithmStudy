import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// pop한 값의 높이를 최소로하는 사각형 만들기
public class SB_1725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 2];                 // 인덱스 1부터 시작, 마지막 막대 0추가

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);                                                  // 0값 먼저 넣어주기

        long mx = 0;
        for (int i = 1; i <= N + 1; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {        // push할 막대가 스택의 값보다 작다면, pop하면서 사각형 넓이 계산 (스택은 항상 오름차순으로 유지)
                int idx = stack.pop();
                int h = arr[idx];                                           // 높이
                int w = stack.isEmpty() ? i - 1 : i - stack.peek() - 1;     // 넓이
                mx = Math.max(mx, (long) h *w);
            }
            stack.push(i);
        }
        System.out.println(mx);
    }
}
