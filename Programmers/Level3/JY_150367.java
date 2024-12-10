import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++) {
            long num = numbers[i];
            
            // 0 추가하기
            String ts = makeTree(Long.toBinaryString(num));
            // System.out.println(ts);
            
            // 포화이진트리가 가능한지 체크
            // 부모가 없는데 -> 자식은 있음
            if(checkS(ts)) answer[i] = 1;
        }
        
        return answer;
    }
    public static String makeTree(String s) {
        int size = s.length();
        int n = 1;
        while(size > (1 << n)-1) {
            n++;
        }
        int cnt = (1 << n) - size - 1;
        return "0".repeat(cnt) + s;
    }
    public static boolean checkS(String now) {
        // 리프노드
        if(now.length() == 1) return true;
        
        int rIdx = now.length() / 2;
        // 부모가 없는데 자식이 존재
        String left = now.substring(0, rIdx);
        String right = now.substring(rIdx+1);
        if(now.charAt(rIdx) == '0' 
           && (left.contains("1") || right.contains("1"))) return false;
        
        // 가능하다면 다시 하위 트리 탐색
        return checkS(left) && checkS(right);
    }
}