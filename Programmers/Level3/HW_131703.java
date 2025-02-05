// 검정 : 앞(0), 흰색 : 뒷면(1)
// 초기 상태에서 최소 몇 번의 동전을 뒤집어야 목표 상태가 되는지
// 시간복잡도 : 2^10 * 2^10 O(N^2) 가능
// 각 행 뒤집기 -> 비트마스크로 표현
class HW_131703 {
    public int solution(int[][] beginning, int[][] target) {
        int r = beginning.length;
        int c = beginning[0].length;
        int answer = Integer.MAX_VALUE;
        int[][] diff = new int[r][c]; // 초기 상태와 목표 상태가 같으면(0), 다르면 (1) 출력하는 배열
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                diff[i][j] = beginning[i][j] ^ target[i][j]; // XOR 연산 0인 값 찾기
            }
        }
        int rowMax = (1<<r); // 모든 행을 탐색하기 위한 최대값
        for(int rowMask=0; rowMask<rowMax; rowMask++){
            int[][] temp = new int[r][c];
            for(int j=0; j<r; j++){
                temp[j] = diff[j].clone(); // 각 행을 복사
            }

            // 행 뒤집기
            for(int j=0; j<r; j++){
                if((rowMask & (1<<j)) != 0){
                    for(int k=0; k<c; k++){
                        temp[j][k] ^= 1;
                    }
                }
            }
            boolean flag = true;
            int colMask = 0;
            for(int j=0; j<c; j++){
                int focus = temp[0][j];
                for(int k=0; k<r; k++){
                    if(temp[k][j] != focus){
                        flag = false;
                        break;
                    }
                }
                if(!flag){
                    break;
                }
                if(focus==1){
                    colMask |= (1<<j);
                }
            }
            if(flag){
                int rowCnt = Integer.bitCount(rowMask);
                int colCnt = Integer.bitCount(colMask);
                answer = Math.min(answer, rowCnt+colCnt);
            }
        }

        return (answer==Integer.MAX_VALUE) ? -1 : answer;
    }
}