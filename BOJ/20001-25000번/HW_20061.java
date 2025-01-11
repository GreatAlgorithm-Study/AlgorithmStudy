import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HW_20061{
    static int N, t, x, y, scores;
    static int[][] greenBoard;
    static int[][] blueBoard;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        greenBoard = new int[6][4]; // 아래로 떨어짐
        blueBoard = new int[4][6]; // 오른쪽으로 떨어짐

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dropGreen(t, y, greenBoard);
            dropBlue(t, x, blueBoard);

            scores += calGreen(greenBoard);
            scores += calBlue(blueBoard);

            simulationGreen(greenBoard);
            simulationBlue(blueBoard);

        }
        System.out.println(scores);
        System.out.println(countBlock(greenBoard) + countBlock(blueBoard));
    }
    static void dropGreen(int block, int y, int[][] greenBoard){ // 4x6
        if(block==1){ // 1x1
            int x = 0;
            while(x+1<6 && greenBoard[x+1][y]==0){ // 보드 범위를 초과x && 다음칸이 비어 있어야함
                x++; // 아래로 떨어짐 : 행 번호 증가
            }
            greenBoard[x][y]=1; // 최종 위치에 블록 고정
        } else if(block==2){ // 1x2
            int x =0;
            while(x+1<6 && greenBoard[x+1][y]==0 && greenBoard[x+1][y+1]==0){
                x++;
            }
            greenBoard[x][y]=1;
            greenBoard[x][y+1]=1;
        } else if(block==3){ // 2x1
            int x = 0;
            while(x+2<6 && greenBoard[x+1][y]==0 && greenBoard[x+2][y]==0){
                x++;
            }
            greenBoard[x][y]=1;
            greenBoard[x+1][y]=1;
        }
    }
    static void dropBlue(int block, int x, int[][] blueBoard) {
        if (block == 1) {  // 1x1 블록
            int y = 0;
            while (y + 1 < 6 && blueBoard[x][y + 1] == 0) {
                y++;
            }
            blueBoard[x][y] = 1;
        } else if (block == 2) {  // 1x2 블록 (가로 블록)
            int y = 0;
            while (y + 2 < 6 && blueBoard[x][y + 1] == 0 && blueBoard[x][y + 2] == 0) {
                y++;
            }
            blueBoard[x][y] = 1;
            blueBoard[x][y + 1] = 1;
        } else if (block == 3) {  // 2x1 블록 (세로 블록)
            int y = 0;
            while (y + 1 < 6 && blueBoard[x][y + 1] == 0 && blueBoard[x + 1][y + 1] == 0) {
                y++;
            }
            blueBoard[x][y] = 1;
            blueBoard[x + 1][y] = 1;
        }
    }

    // 3. 점수 계산
    static int calGreen(int[][] greenBoard){
        int score = 0;
        for(int i=2; i<6; i++){
            boolean isValid = true;
            for(int j=0; j<4; j++){
                if(greenBoard[i][j]==0){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                score++;
                for(int k=i; k>0; k--){ // 행 삭제
                    for(int j=0; j<4; j++){
                        greenBoard[k][j] = greenBoard[k - 1][j];
                    }
                }
                for(int j=0; j<4; j++){
                    greenBoard[0][j]=0;
                }
            }
        }
        return score;
    }
    static int calBlue(int[][] blueBoard){
        int score = 0;
        for (int j = 2; j < 6; j++) {  // 2~5열만 확인
            boolean isValid = true;
            for (int i = 0; i < 4; i++) {
                if (blueBoard[i][j] == 0) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                score++;
                for (int k = j; k > 0; k--) {  // 열 삭제
                    for (int i = 0; i < 4; i++) {
                        blueBoard[i][k] = blueBoard[i][k - 1];
                    }
                }
                for (int i = 0; i < 4; i++) {
                    blueBoard[i][0] = 0;
                }
            }
        }
        return score;
    }
    // 4. 연한칸(특수 구역) 처리
    static void simulationGreen(int[][] greenBoard){
        int cnt = 0;
        for(int i=0; i<=1; i++){
            for(int j=0; j<4; j++){
                if(greenBoard[i][j]==1){
                    cnt++;
                    break;
                }
            }
        }
        for(int k=0; k<cnt; k++){
            for(int i=5; i>0; i--){
                for(int j=0; j<4; j++){
                    greenBoard[i][j] = greenBoard[i - 1][j];
                }
            }
            for(int j=0; j<4; j++){
                greenBoard[0][j] = 0;
            }
        }
    }

    static void simulationBlue(int[][] blueBoard) {
        int count = 0;
        for (int j = 0; j <= 1; j++) {
            for (int i = 0; i < 4; i++) {
                if (blueBoard[i][j] == 1) {
                    count++;
                    break;
                }
            }
        }
        for (int k = 0; k < count; k++) {
            for (int j = 5; j > 0; j--) {
                for (int i = 0; i < 4; i++) {
                    blueBoard[i][j] = blueBoard[i][j - 1];
                }
            }
            for (int i = 0; i < 4; i++) {
                blueBoard[i][0] = 0;
            }
        }
    }
    static int countBlock(int[][] board) {
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}