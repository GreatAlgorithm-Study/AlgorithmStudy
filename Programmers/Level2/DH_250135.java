
/*
 * 아날로그 시계
 */

public class DH_250135 {
	
    static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

    	int answer = 0;
    	
    	// 정각에 시작하는 경우
        if(s1 == 0 && m1 == 0) answer += 1;
        
        // 시작 시간이 끝나는 시간 이전일 때 까지
        while(h1 * 3600 + m1 * 60 + s1 < h2 * 3600 + m2 * 60 + s2) {
        	// 자정, 정오가 되는 경우
        	if((h1 == 11 || h1 == 23) && m1 == 59 && s1 == 59) answer += 1;
        	else {
        		// 분침과 초침이 만난 경우
        		// 정각 제외
        		if(m1 == s1 && !(m1 == 0 && s1 == 0)) answer += 1;
        		
        		// 시침과 초침이 만난 경우
        		// 자정, 정오가 아닌 시간
        		if(((h1 % 12) * 5 + m1 / 12) == s1 && !((h1 % 12 == 0) && m1 == 0 && s1 == 0)) answer += 1;
        	}
        	
        	// 초 업데이트
        	s1 += 1;
        	if(s1 == 60) m1 += 1;
        	if(m1 == 60) h1 += 1;

        	s1 %= 60;
        	m1 %= 60;
        }
                
        return answer;
    }
    
    public static void main(String[] args) {
    	int h1 = 0, m1 = 5, s1 = 30, h2 = 0, m2 = 7, s2 = 0;
		System.out.println(solution(h1, m1, s1, h2, m2, s2));
	}
}
