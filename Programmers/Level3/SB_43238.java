import java.util.*;

class SB_43238 {
    private static boolean canCheck(long target, int[] times, int n) {
        long cnt = 0;
        for (int tm : times) {
            cnt += target / tm;
            if (cnt >= n) return true;
        }
        return false;
    }
    public static long solution(int n, int[] times) {
        Arrays.sort(times);

        // 매개변수 탐색, 주어진 시간 내 n명 이상의 사람 처리할 수 있는지
        long left = 0;
        long right = (long) times[times.length - 1] * n;       // 최대 시간

        long parm = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (canCheck(mid, times, n)) {
                parm = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return parm;
    }
}