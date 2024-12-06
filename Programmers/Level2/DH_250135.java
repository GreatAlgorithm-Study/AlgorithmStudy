
/*
 * 아날로그 시계
 */

public class DH_250135 {
	
    static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

    	int answer = 0;
    	
    	int startTimeToSec = getSec(h1, m1, s1);
    	int endTimeToSec = getSec(h2, m2, s2);
    	
    	// 이전 시, 분 초침 각도
    	double phr = 0, pmr = 0, psr = 0;
    	
    	for(int i = startTimeToSec; i < endTimeToSec + 1; i++) {
    		double chr = (i / 120.0) % 360;
    		double cmr = (i / 10.0) % 360;
    		double csr = (i % 60.0) * 6;
    		
    		// 시침과 초침이 겹치는 경우
    		if(chr < csr && phr > psr) answer += 1;
    		else if(chr == csr) answer += 1;
    		// 정각
    		else if(phr > psr && csr == 0) answer += 1;
    		
    		/// 분침과 겹치는 경우
    		if(cmr < csr && pmr > psr) answer += 1;
    		else if(cmr == csr) answer += 1;
    		else if(pmr > psr && csr == 0) answer += 1;
    		
    		// 모두 겹치는 경우
    		if(chr == 0 && cmr == 0 && csr == 0) answer -= 1;
    		
    		phr = chr; 
    		pmr = cmr; 
    		psr = csr;
    	}
        return answer;
    }
    
    static int getSec(int h, int m, int s) {
    	return h * 60 * 60 + m * 60 + s;
    }
    
    public static void main(String[] args) {
    	int h1 = 0, m1 = 6, s1 = 1, h2 = 0, m2 = 6, s2 = 6;
		System.out.println(solution(h1, m1, s1, h2, m2, s2));
	}
}
