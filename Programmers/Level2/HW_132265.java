import java.util.*;

// 시간 복잡도 : topping길이 : 백만 O(NM) X , O(N), O(N logN) ...
// 일반 정렬을 사용하면 시간초과 날 것
// 롤케이크를 공평하게 자르는 방법의 수 출력

// 항상 궁금했던거.. 자바에서는 다른 원소를 어떻게 확인할까? - 중복 제거 : HashSet!

class HW_132265 {
    public int solution(int[] topping) {
        int answer = 0;
        int N = topping.length;
        int left[] = new int[N];
        int right[] = new int[N];

        HashSet<Integer> leftSet = new HashSet<>();
        HashSet<Integer> rightSet = new HashSet<>();
        for(int i=0; i<N; i++){
            leftSet.add(topping[i]);
            left[i] = leftSet.size();
        }

        for(int i=N-1; i>0; i--){
            rightSet.add(topping[i]);
            right[i] = rightSet.size();
        }


        for(int i=0; i<N-1; i++){
            if(left[i] == right[i+1]){ // 철수 0~i / 동생 : i+1 ~ N
                answer++;
            }
        }

        return answer;

    }
}