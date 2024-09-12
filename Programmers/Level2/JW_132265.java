import java.util.HashSet;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int len = topping.length;
        // 종류 수를 계산하기 위한 Set
        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();
        int[] typeA = new int[len];             // 아래에서 위로 토핑의 수를 저장할 배열
        int[] typeB = new int[len];             // 위에서 아래로 토핑의 수를 저장할 배열
        for (int i = 0; i < len; i++) {
            setA.add(topping[i]);               // 종류 추가
            setB.add(topping[len - 1 - i]);     // Set이기에 중복을 허용하지 않음
            typeA[i] = setA.size();             // i번째까지의 종류 수를 저장
            typeB[len - 1 - i] = setB.size();   // 끝에서 i번째까지의 종류 수를 저장
        }
        for (int i = 0; i < len - 1; i++)
            // i와 i + 1의 사이를 잘랐는데 종류수가 같다면
            if (typeA[i] == typeB[i + 1])
                answer++;
        return answer;
    }
}