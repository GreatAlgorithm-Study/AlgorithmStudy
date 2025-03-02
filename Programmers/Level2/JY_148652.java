class JY_148652 {
    static final int S = 5;
    static int answer;
    static long L, R;
    public long solution(int n, long l, long r) {
        answer = 0;
        L = l-1;
        R = r-1;
        long size = (long)Math.pow(S, n);
        div(0, size);
        return answer;
    }
    public static void div(long idx, long size) {
        if(size == 1) {
            if(L <= idx && idx <= R) {
                answer += 1;
            }
            return;
        }
        long fifth = size / S;
        
        for(int i=0; i<S; i++) {
            if(i == 2) continue;
            // 다음 분할 구간 찾기
            long sIdx = idx + (fifth*i);
            long eIdx = sIdx + fifth -1;
            // [l, r] 이외라면 탐색 X
            if(L > eIdx || sIdx > R) continue;
            
            div(sIdx, fifth);
        }
        
    }
}