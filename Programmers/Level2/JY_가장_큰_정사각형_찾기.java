import java.util.*;
class Solution
{
    static int N, M;
    
    public int solution(int [][]board)
    {
        int answer = 0;
        N = board.length;
        M = board[0].length; 
        
        for(int i=1; i<N; i++) {
            for(int j=1; j<M; j++) {
                if(board[i][j] == 1) {
                    // 현재 위치에서 간으한 최대크기 한변의 길이를 저장
                    board[i][j] = Math.min(board[i-1][j-1], Math.min(board[i-1][j], board[i][j-1])) + 1;
                }
            }
        }
        
        
        // 가장 큰 정사각형 한 변의 길이 찾기
        for(int i=0; i<N; i++) {
            int tmp = 0;
            for(int j=0; j<M; j++) {
                tmp = Math.max(tmp, board[i][j]);
            }
            answer = Math.max(answer, tmp);
        }
        

        return answer * answer;
    }
}
