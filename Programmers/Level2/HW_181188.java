import java.util.*;
// 구하는 것 : 모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일 수의 최솟값
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1])); // e구간 기준 정렬

        int cnt = 0; // 요격 미사일 개수
        double endShoot = Integer.MIN_VALUE; // 미사일 쏘는 위치

        for(int i=0; i<targets.length; i++){
            int s = targets[i][0]; // s
            int e = targets[i][1]; // e

            if(endShoot<s){ // 마지막 발사 위치보다 현재 시작 구간이 더 크면
                cnt++; // 새로운 미사일 설치
                endShoot = e-0.5; // 개구간 = 범위 포함X : 임의로 실수를 빼줌
            }
        }
        return cnt;
    }
}