/*
최고의 집합
집합 원소를 곱해서 최대가 되기 위해서는 원소들이 옹기종기 모여있어야 됨!
 */

class DH_12938 {
    public int[] solution(int n, int s) {
        int[] answer = {};

        if(n > s) answer = new int[] {-1};
        else {
            answer = new int[n];

            int div = s / n;
            int mod = s % n;

            for(int i = 0; i < n; i++) {
                answer[i] = div;
                if(n - i <= mod) answer[i] += 1;
            }
        }
        return answer;
    }
}