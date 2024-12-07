class JW_150367 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String str = toBinaryString(numbers[i]);
            // 표현 가능한 포화 이진 트리일 경우 '1'
            answer[i] = chkInOrder(str, 0, str.length() - 1) ? 1 : 0;
        }
        return answer;
    }

    // 포화 이진 트리 -> 문자열 길이가 2^n - 1 꼴이 되어야 함
    private String toBinaryString(long number) {
        StringBuilder sb = new StringBuilder(Long.toBinaryString(number));
        int n = 1;
        while (sb.length() > (1 << n) - 1) {
            n++;
        }
        // 빈 부분 채우기
        sb.insert(0, "0".repeat((1 << n) - 1 - sb.length()));
        return sb.toString();
    }

    // 중위 탐색을 진행하면서 불가능한 트리인지 확인
    private boolean chkInOrder(String str, int l, int r) {
        // 리프노드 일 경우는 True
        if (l == r)
            return true;
        int rootIdx = (l + r) / 2;
        // 루트 노드가 0'일 경우에는 자식에 '1'이 있으면 안됨
        if (str.charAt(rootIdx) == '0')
            for (int i = l; i <= r; i++)
                if (str.charAt(i) == '1')
                    return false;
        // 다음 중위 탐색
        return chkInOrder(str, l, rootIdx - 1) && chkInOrder(str, rootIdx + 1, r);
    }
}