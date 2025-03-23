import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        String answer = "";
        int cnt = 0;
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            }
        });
        
        for(String s: bans) {
            pq.add(s);
        }
        
        while(!pq.isEmpty()) {
            String s = pq.poll();
            long dNum = 0;
            // 문자열을 10진수로 변환
            // 예) "abc"는 26진수로 1*26^2 + 2*26^1 + 3*26^0에 해당
            for(int i=s.length()-1; i>=0; i--) {
                dNum += (s.charAt(i)-'a'+1) * (Math.pow(26, s.length()-1-i));
            }
            // 변환된 10진수가 cnt+n보다 작음 : 가능한 문자열의 수 증가
            if(dNum <= cnt + n) {
                cnt++;
            }
        }
        
        n += cnt;
        
        // 10진수 -> 26진수로 바꿈
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long remained = n % 26;
            n /= 26;
            if (remained == 0) {
                n--;
                sb.append('z');
            } else {
                sb.append((char)('a' + remained - 1));
            }
        }
        
        // System.out.println(sb.reverse().toString());
        answer = sb.reverse().toString();
        return answer;
    }
}