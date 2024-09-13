import java.util.*;
class Solution {
    // 뒷큰수 : 신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수
    // O(N^2) 불가 
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];    
        Stack<Integer> stack = new Stack<>();
        
        int size = numbers.length;
        stack.push(0);
        
        for(int i=1; i<size; i++){
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i); // numbers[stack.peek()] > numbers[i]
        }
        
        while(!stack.empty()){
            answer[stack.pop()] = -1;
        }
        
        return answer;
    }
}