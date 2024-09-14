package Programmers.Level2;

import java.util.Arrays;

public class SB_뒤에있는_큰_수_찾기 {
    public static int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] dp = new int[N];
        dp[N-1] = -1;

        // 거꾸로 채워가기
        for (int i = N - 2; i >= 0; i--) {
            if (numbers[i+1] > numbers[i]) {        // i 바로 뒤에 수가 크면 넣기
                dp[i] = numbers[i + 1];
                continue;
            }

            int k = 1;
            while (i+k<N-1 && dp[i+k] <= numbers[i]) {  // 아닐경우, dp값 돌면서 제일 처음만나는 큰 값 넣기
                if (dp[i+k] == -1) break;               // -1이면 더이상 없는 것으로 탈출
                k++;
            }
            dp[i] = dp[i+k];
        }
        return dp;

    }
    public static void main(String[] args) {
        int[] numbers = {2, 3, 3, 5};
        System.out.println(Arrays.toString(solution(numbers)));
    }
}
