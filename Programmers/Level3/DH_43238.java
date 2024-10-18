import java.util.*;

/*
입국심사
 */

class DH_43238 {
    public long solution(int n, int[] times) {
        long answer = 0;

        // 입국 심사 기간이 주어졌을 때
        // 가장 오래 걸리는 시간을 구하기 위해 정렬해줌
        Arrays.sort(times);

        int length = times.length;
        long s = 1, e = times[length - 1] * (long) n + 1; // 시간초
        while(s < e) {
            long m = (s + e) / 2;

            // m 시간일 때, 가능한 사람 수
            long peopleCnt = getPeopleCnt(m, times);

            if(peopleCnt < n) s = m + 1;
            else e = m;
        }

        return s;
    }

    static long getPeopleCnt(long time, int[] times) {
        long peopleCnt = 0;

        for(long t: times) peopleCnt += time / t;

        return peopleCnt;
    }
}