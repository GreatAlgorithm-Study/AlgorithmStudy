import java.io.*;
import java.util.*;

/*
 * 나무 자르기
 */

public class BOJ2805 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        int M = Integer.parseInt(st.nextToken()); // 적어도 M 미터의 나무를 집에 가져가기 위해 설정할 수 있는 높이의 최댓값

        // 1 <= M <= 2_000_000_000이기 때문에 long 으로 설정
        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < arr.length; i++) arr[i] = Long.parseLong(st.nextToken());

        Arrays.sort(arr);

        // 누적합 저장
        // 잘린 나무들의 합을 구해야 되는데, 반복문을 사용한다면
        // 시간 초과가 발생할 수 있을 것이라 판단하여 누적합 사용
        long[] sum = new long[N + 1];
        for(int i = 0; i < N; i++) {
            sum[i + 1] = sum[i]+ arr[i];
        }

        // 절단기에 설정할 수 있는 높이의 최댓값
        // 많이 잘라야 됨 - upperIdx구하기
        long s = 0, e = arr[N - 1] + 1;
        long result = 0;

        while(s < e) {
            long m = (s + e) / 2;
            // m이 있을 수 있는 가장 오른쪽 idx 반환
            int cutStartIdx = getUpperIdx(arr, m);
            // 잘린 나무들의 합 구하기
            long cutSum = (sum[N] - sum[cutStartIdx]) - (N - cutStartIdx) * m;

            if(cutSum >= M) {
                // 만약 s = 1, e = 5 일 경우
                // 이후 s는 4가 되기 때문에 result 값을 저장해줘야 함
                result = m;
                s = m + 1;
            } else {
                e = m;
            }
        }

        System.out.println(result);
    }

    // target이 있을 수 있는 가장 오른쪽 idx 반환
    static int getUpperIdx(long[] arr, long target) {
        int s = 0, e = arr.length;

        while(s < e) {
            int m = (s + e) / 2;

            if(arr[m] <= target) s = m + 1;
            else e = m;
        }
        return s;
    }
}
