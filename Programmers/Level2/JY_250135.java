class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        // 초시간으로 변환
        double st = changeSec(h1, m1, s1);
        double et = changeSec(h2, m2, s2);
        
        // 정오, 자정일때 카운트
        int half = 12 * 3600;
        if(st == 0 || st == half) answer++;
        
        while(st < et) {
            // 현재 각도 계산
            // 초침 -> 1초에 6도 (360도/60초)
            // 분침 -> 1초에 1/10도 (6도 / 60)
            // 시침 -> 1초에 1/120도 (6도 / 60*12)
            double h = (st / 120) % 360;
            double m = (st / 10) % 360;
            double s = (st * 6) % 360;
            
            // 다음 각도
            // 0도가 된다는 것은 360도와 같음
            double nh = reAngle(((st+1) / 120) % 360);
            double nm = reAngle(((st+1) / 10) % 360);
            double ns = reAngle(((st+1) * 6) % 360);
            
            // 초침 == 시침
            if(s < h && nh <= ns) answer++;
            // 초침 == 분침
            if(s < m && nm <= ns) answer++;
            // 시침, 분침이 모두 겹칠때
            if(nh == ns && nm == ns) answer--;
            
            st++;
        }
        
        return answer;
    }
    public static double changeSec(int h, int m, int s) {
        return (double) (h*3600 + m*60 + s);
    }
    public static double reAngle(double a){
        if(a == 0.0) return 360.0;
        return a;
    }
}