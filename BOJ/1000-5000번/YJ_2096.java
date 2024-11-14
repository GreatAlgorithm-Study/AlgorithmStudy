import java.io.*;
import java.util.*;

public class YJ_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int LIMIT = 3;
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][3];
        int[][] min = new int[n][3];
        int[][] max = new int[n][3];
        //입력값 초기화
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<LIMIT; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //최소,최대 배열 초기화
        for(int[] m :min){
            Arrays.fill(m, 999_999);
        }
        min[0] = board[0];
        max[0] = board[0];

        //dp
        for(int i=0; i<n-1; i++){
            for(int j=0; j<LIMIT; j++){
                if(j-1 >= 0){    //왼쪽사이드
                    min[i+1][j-1] = Math.min(min[i+1][j-1] , min[i][j] + board[i+1][j-1]);
                    max[i+1][j-1] = Math.max(max[i+1][j-1] , max[i][j] + board[i+1][j-1]);
                }
                if(j+1 < LIMIT){ //오른쪽사이드
                    min[i+1][j+1] = Math.min(min[i+1][j+1] , min[i][j] + board[i+1][j+1]);
                    max[i+1][j+1] = Math.max(max[i+1][j+1] , max[i][j] + board[i+1][j+1]);
                }
                //바로아래
                min[i+1][j] = Math.min(min[i+1][j] , min[i][j] + board[i+1][j]);
                max[i+1][j] = Math.max(max[i+1][j] , max[i][j] + board[i+1][j]);
            }
        }

        //최대점수
        Arrays.sort(max[n-1]);
        //최소점수
        Arrays.sort(min[n-1]);
        System.out.printf("%d %d",max[n-1][LIMIT-1],min[n-1][0]);
    }
}