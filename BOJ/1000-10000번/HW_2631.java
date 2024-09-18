import java.util.Scanner;

// 현재 순서 중에서 이미 증가하는 부분 수열을 최대한 유지 + 나머지 요소 이동
public class HW_2631 {
    public static int N =0;
    public static void main(String[] args) {
        // 1) 아이들 N명 입력 받음
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 아이들 수 N
        int[] A = new int[N]; // 인덱스마다 각 입력값
        int[] dp = new int[N]; // 인덱스마다 각 증가 수열의 길이

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int LIS = 0;
        dp[0] = 1;
        for(int i=0; i<N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (dp[i] > LIS) {// LIS = Math.max(LIS, dp[i]);
                LIS = dp[i];
            }
        }
        System.out.println(N-LIS); // 나머지 아이들이 이동해야할 수 : N - LIS
    }
}


