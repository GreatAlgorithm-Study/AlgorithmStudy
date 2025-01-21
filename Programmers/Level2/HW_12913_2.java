// 시간 복잡도 : N<=10^5, 열의 개수<4, O(N)
// dp[i][j] : (i, j) 밟았을 때 얻을 수 있는 최대 점수
// 초기값 설정 : dp[0][j] = land[0][j]
class HW_12913_2 {
    int solution(int[][] land) {
        int answer = 0;

        for(int i=1; i<land.length; i++){
            for(int j=0; j<4; j++){
                int max = 0;
                for(int k=0; k<4; k++){
                    if(k!=j){ // 같은 열을 제외 최댓값 갱신
                        max = Math.max(max, land[i-1][k]);
                    }
                }
                land[i][j] += max; // 이전 행 && 다른 열의 최댓값을 더해줌
            }
        }

        for(int i=0; i<4; i++){
            answer = Math.max(answer, land[land.length-1][i]);
        }
        return answer;
    }
}