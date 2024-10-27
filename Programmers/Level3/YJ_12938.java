public class YJ_12938 {
    static int[] solution(int n, int s) {
        int[] answer = new int[n];

        int tempN= n;
        int remainder = s%n;
        while(n-- > 0){
            answer[n] = s/tempN;

            if(answer[n] == 0){
                return new int[] {-1};
            }
            if(remainder > 0){
                answer[n]++;
                remainder--;
            }
        }

        return answer;
    }
}
