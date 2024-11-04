import java.util.*;
class JY_12938 {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(n > s) {
            return new int[] {-1};
        }
        
        // 원소의 곱을 최대로 만들때, 채울 수 있는 원소의 최솟값
        int num = s / n;
        for(int i=0; i<n; i++) {
            answer[i] = num;
        }
        
        // 나머지가 있다면 마지막 원소부터 1씩증가 (오름차순 정렬이니깐)
        int m = s % n;
        for(int i=n-1; i>=n-m; i--){
            answer[i]++;
        }
        
        return answer;
    }
}