/*
롤케이크_자르기
 */

class DH_132265 {

    public int solution(int[] topping) {
        int length = topping.length;
        int toppingType = 0, answer = 0;

        int cnt[] = new int[10_001];
        for(int i = 0; i < length; i++) {
            if(cnt[topping[i]] == 0) toppingType++;
            cnt[topping[i]]++;
        }

        int cntA = 0;
        boolean check[] = new boolean[10_001];

        for(int i = 0; i < length; i++) {
            if(!check[topping[i]]) {
                cntA++;
               check[topping[i]] = true;
            }

            cnt[topping[i]]--;
            if(cnt[topping[i]] == 0) toppingType--;
            if(cntA == toppingType) answer++;
        }
        return answer;
    }
}