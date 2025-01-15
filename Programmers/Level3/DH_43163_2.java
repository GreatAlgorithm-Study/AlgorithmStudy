import java.util.*;

/*
단어 변환
 */

class DH_43163_2 {
    
    public int solution(String begin, String target, String[] words) {
        
        int answer = 0;
        
        // bfs로 구현하기 위해 ArrayDeque와 방문 배열 선언
        // ArrayDeuqe [0]: words에서의 idx, [1]: 깊이
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        boolean v[] = new boolean[words.length];
        
        // begin의 idx는 -1로 설정
        q.add(new int[] {-1, 0});
        
        while(!q.isEmpty()) {
            
            int[] current = q.poll();
            String str = current[0] == -1 ? begin: words[current[0]];
    
            if(str.equals(target)) {
                answer = current[1];
                break;
            }
            
            for(int i = 0; i < words.length; i++) {
                if(v[i] || !check(words[i], str)) continue;
                v[i] = true;
                q.add(new int[] {i, current[1] + 1});
            }
        }
        
        return answer;
    }
    
    // 입력된 두 문자열이 문자 하나만 다를 때
    static boolean check(String s1, String s2) {
        
        int diffCnt = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) diffCnt += 1;
            if(diffCnt > 1) return false;
        }
        
        return true;
    }
}