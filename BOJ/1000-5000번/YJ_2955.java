import java.io.*;

public class YJ_2955 {
    static final int NUM = 9;
    static final String ERROR = "ERROR";

    static int[][] board;
    static boolean error = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[NUM][NUM];

        for(int i=0; i<NUM; i++){
            String[] data = br.readLine().split("");
            for(int j=0; j<NUM; j++){
                board[i][j] = ".".equals(data[j])? 0 : Integer.parseInt(data[j]);
            }
        }

        //cross-hatching
        boolean flag;
        do {
            flag = false;
            for (int target = 1; target <= NUM; target++) {
                if (crossHatching(target)) {
                    flag = true;
                }
            }
        } while (flag);

        //결과 출력
        if(error){
            System.out.println(ERROR);
        }else{
            for(int[] b : board){
                for (int j : b) {
                    System.out.print(j == 0? "." : j);
                }
                System.out.println();
            }
        }
    }

    static boolean[] rowCheck;
    static boolean[] colCheck;
    static boolean[][] gridCheck;
    static boolean crossHatching (int target){
        rowCheck = new boolean[NUM];
        colCheck = new boolean[NUM];
        gridCheck = new boolean[3][3];

        for(int i=0; i<NUM; i++) {
            for (int j=0; j < NUM; j++) {
                //특정 숫자가 있다면 해당 배열의 가로,세로줄 모두 방문처리
                if(target == board[i][j]){
                    if (rowCheck[i] || colCheck[j] || gridCheck[i / 3][j / 3]) {
                        error = true;
                        return false;
                    }
                    rowCheck[i] = true;
                    colCheck[j] = true;
                    gridCheck[i / 3][j / 3] = true;
                }
            }
        }
        return findSpace(target);
    }

    static boolean findSpace(int target){
        boolean flag = false;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(gridCheck[i][j]){
                    continue;
                }
                if (findInGrid(i*3, j*3, target)) {    //i*3, j*3??
                    flag = true;
                }
            }
        }
        //자리가 너무많은 경우, 타겟 숫자가 어떤 가로줄, 3*3박스, 세로줄에 있을 수 있는 곳이 전혀 없는 경우
        return flag;
    }

    private static boolean findInGrid(int sr, int sc, int target) {
        int nr = -1;
        int nc = -1;
        int cnt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(rowCheck[i + sr] || colCheck[j + sc]){
                    continue;
                }
                if(board[i + sr][j + sc] == 0){
                    if (cnt == 0) {
                        nr = i + sr;
                        nc = j + sc;
                        cnt++;
                    } else {    //자리가 너무많은 경우
                        return false;
                    }
                }
            }
        }
        //타겟 숫자가 어떤 가로줄, 3*3박스, 세로줄에 있을 수 있는 곳이 전혀 없는 경우
        if (cnt == 0) {
            error = true;
            return false;
        }
        //스도쿠 추가 및 방문체크
        board[nr][nc] = target;
        rowCheck[nr] = true;
        colCheck[nc] = true;

        return true;
    }
}