class SB_64062 {
    private static boolean canGo(int[] stones, int k, int num) {
        int seq = 0;
        for (int s : stones) {
            if (s-num <=0) seq++;
            else seq = 0;
            if (seq >= k) return false;
        }
        return true;
    }
    public static int solution(int[] stones, int k) {
        int st = 1;             // 가장 적게 건너는 사람 수
        int ed = 0;             // 가장 많이 건널 수 있는 경우
        for (int s : stones) {
            ed = Math.max(ed, s);
        }

        while (st < ed) {
            int mid = (st + ed) / 2;
            if (canGo(stones, k, mid)) st = mid+1;
            else ed = mid;
        }

        return st;
    }
}