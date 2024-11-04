class HW_12913 {
    int solution(int[][] land) {

        // 이전 행 기반 최대값을 기반으로하여 현재 행에서 어떤 칸을 밟을지 결정
        for(int i=1; i<land.length; i++){
            for(int j=0; j<4; j++){
                int max = 0;
                for(int k=0; k<4; k++){
                    if(k != j) { // 이전 열과 같은 열이 아닐 때
                        max = Math.max(max, land[i-1][k]);
                    }
                }
                land[i][j] += max;
            }
        }

        int answer = 0;
        for(int j=0; j<4; j++){
            answer = Math.max(answer, land[land.length-1][j]);
        }

        return answer;
    }
}