package boj;

import java.util.Arrays;

public class JW_2110_2 {

    static int n, c;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        n = read();
        c = read();
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        Arrays.sort(arr);
        int l = 1, r = arr[n - 1];
        while (l <= r) {
            int m = (l + r) / 2;
            if (isPossible(m))
                l = m + 1;
            else
                r = m - 1;
        }
        System.out.println(r);
    }

    // 결정 함수
    private static boolean isPossible(int target) {
        int cnt = 1, prev = 0;
        for (int i = 1; i < n; i++)
            if (arr[i] - arr[prev] >= target) {
                cnt++;
                prev = i;
            }
        return cnt >= c;
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