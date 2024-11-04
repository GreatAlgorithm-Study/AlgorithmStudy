class JW_12938 {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        // 1로만 이루어진 집합으로도 만들 수 없을 경우 -1 리턴
        if (n > s)
            return new int[] { -1 };
        // 오름차순으로 정렬하기 위해 뒤에서 부터 값 설정
        for (int i = n - 1; i >= 0; i--) {
            // 마지막 위치에 만들 수 있는 최댓값 설정
            answer[i] = s / n;
            // 나머지가 존재한다면 +1
            if (s % n != 0)
                answer[i]++;
            // n과 s값 변경
            n--;
            s -= answer[i];
        }
        return answer;
    }
}