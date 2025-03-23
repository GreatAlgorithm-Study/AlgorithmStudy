import java.util.Arrays;

/*
 * 봉인된 주문
 */

public class DH_389481 {

    static String solution(long n, String[] bans) {
        
        // 사전순으로 정렬하기
        Arrays.sort(bans, (o1, o2) -> {
        	if(o1.length() == o2.length()) return o1.compareTo(o2);
        	return o1.length() - o2.length();
        });
        
        // bans[i]의 순서를 확인하면서, n보다 작으면 n의 크기 늘려주기
        for(String b: bans) {
        	long cnt =  0;
        	
        	for(int i = 0; i < b.length(); i++) {
        		cnt += (b.charAt(i) - 'a' + 1) * (long) Math.pow(26, b.length() - (i + 1));
        	}
        	
        	if(cnt <= n) n++;
        }
        

        // n번째 주문 찾기
        StringBuilder sb = new StringBuilder();
        
        // 26으로 계속 나누어주면서 맨 뒤에서부터 문자 채워주기
        while(n > 0) {
        	long ch = n % 26; 
        	n /= 26;

        	if(ch == 0) {
        		n--;
        		sb.insert(0, 'z');
        	} else {
        		sb.insert(0, (char) ('a' + ch - 1));
        	}
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	
    	int n = 26 + 26;
    	String[] bans = new String[] {};
    	
		System.out.println(solution(n, bans));
	}
}
