import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> right = new HashMap<>();
        for(int t: topping){
            right.put(t, right.getOrDefault(t, 0)+1);
        }
        // System.out.println(right);
        
        Map<Integer, Integer> left = new HashMap<>();
        for(int i=0; i<topping.length; i++){
            int t = topping[i];
            // left에 삽입
            left.put(t, left.getOrDefault(t, 0)+1);
            // right에서 제거
            right.put(t, right.get(t)-1);
            if(right.get(t) == 0){
                right.remove(t);
            }
            
            if(left.keySet().size() == right.keySet().size()) answer++;
        }
        
        return answer;
    }
}