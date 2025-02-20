class JY_389479 {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int P = players.length;
        int[] crr = new int[P+k+1];
        
        int cnt = 0;
        int s = 0;
        int e = 0;
        for(int i=0; i<P; i++) {
            int p = players[i];
            
            // i시간에 종료되는 서버 처리
            if(crr[i] != 0) {
                cnt -= crr[i];
            }
            
            // 서버를 증설해야 함
            if(p >= ((cnt+1)*m)) {
                s = i;
                e = s + k;
                
                // 추가해야할 서버 개수
                int nc = (p / m) - cnt;
                crr[e] += nc;   // 추가한 서버들이 끝나는 시간에 개수 저장
                cnt += nc;      // 서버 추가 증설
                
                answer += nc;   // 증설 횟수 카운트
            }
            
        }
        
        
        return answer;
    }
}