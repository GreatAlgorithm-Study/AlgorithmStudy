class HW_60062 {
    public int solution(int n, int[] weak, int[] dist) {
        int length = weak.length;

        int[] extendedWeak = new int[length * 2]; // 원형 배열을 선형 배열로 변환

        for (int i = 0; i < length; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + length] = weak[i] + n;
        }

        int minFriends = Integer.MAX_VALUE; // 최소 친구 수

        for (int start = 0; start < length; start++) {
            int count = 1; // 투입된 친구 수
            int position = extendedWeak[start];
            for (int i = start; i < start + length; i++) {  // 취약 지점을 순회하며 확인
                if (extendedWeak[i] > position) { // 점검 불가능한 지점 발견한다면
                    count++; // 친구 추가
                }
            }
            minFriends = Math.min(minFriends, count); // 최소 친구 수 갱신
        }
    }
}