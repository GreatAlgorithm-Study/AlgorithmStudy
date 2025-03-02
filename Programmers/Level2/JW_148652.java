class Solution {
    public int solution(int n, long l, long r) {
        return recursive(1, (long) Math.pow(5, n), l, r);
    }
    
    private int recursive(long s, long e, long l, long r) {
        // 유효하지 않은 범위라면 종료
        if (e <= l || r < s)
            return 0;
        // 최대까지 내려왔다면 1반환
        if (e - s == 1)
            return 1;
        long size = (e - s + 1) / 5;
        int count = 0;
        // 다음 칸토어 재귀
        for (int i = 0; i < 5; i++) {
            // 0인 부분은 제외
            if (i == 2)
                continue;
            count += recursive(s + i * size, s + (i + 1) * size, l, r);
        }
        return count;
    }
}