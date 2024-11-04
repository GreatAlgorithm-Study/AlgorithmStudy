// 시간 복잡도 : stones.length<=200_000 -> O(N^2) 불가
// 완전 탐색 -> 시간 초과 -> 이분 탐색으로 탐색 범위 줄여줌 -> O(N logN)

// 징검다리를 건널 수 있는 최대 니니즈 친구들의 수 구하기
// 한명씩 징검다리를 건너는 경우하여 돌을 줄이는 과정 -> 시간초과
// 이분탐색으로 특정 인원이 건널 수 있는지 여부만 확인
class HW_64062 {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int start = 0;
        int end = Integer.MAX_VALUE;

        while(start <= end){
            int mid = (start + end)/2; // 건널 수 있는 사람의 수
            if(check(mid, k, stones)){ // true (mid명까지 건널 수 있는 경우)
                answer = mid;
                start = mid+1; // (mid+1)명까지 건널 수 있는지 확인
            } else{ // false
                end = mid-1;
            }
        }
        return answer;
    }
    public boolean check(int mid, int k, int[] stones){ // mid명이 건널 수 있는지 판단
        int cnt = 0; // 연속으로 건널 수 없는 돌의 개수
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] < mid) { // 현재 돌에 mid명이 건너려고할 때
                cnt++;
                if(cnt>=k) // 연속으로 건널 수 없는 돌이 k개 이상일 경우
                    return false;
            } else cnt=0; // 연속성 끊김
        }
        return true;
    }
}
