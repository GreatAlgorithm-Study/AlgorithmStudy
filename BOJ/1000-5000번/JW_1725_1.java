import java.util.ArrayDeque;
import java.util.Deque;

public class JW_1725_1 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        Deque<Integer> dq = new ArrayDeque<>(); // 가질 수 있는 최대 길이를 저장할 스택(덱)
        int[] left = new int[n];                // 각 인덱스 별로 가질 수 있는 왼쪽으로의 최대 길이
        for (int i = 0; i < n; i++) {
            left[i] = 1;    // 초기값 = 자기 자신의 너비
            // 스택(덱)에 자신보다 큰 높이의 값이 있다면 해당 너비를 포함할 수 있음
            while (!dq.isEmpty() && arr[dq.peekLast()] >= arr[i])
                left[i] += left[dq.pollLast()]; // i 인덱스의 왼쪽 최대 길이에 합
            dq.offerLast(i); // 사용한 인덱스를 삽입
        }
        dq.clear(); // 스택(덱) 초기화
        // 왼쪽과 동일하게 진행
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            right[i] = 1;
            while (!dq.isEmpty() && arr[dq.peekLast()] >= arr[i])
                right[i] += right[dq.pollLast()];
            dq.offerLast(i);
        }
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, arr[i] * (left[i] + right[i] - 1)); // 넓이 계산
        System.out.println(max);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}