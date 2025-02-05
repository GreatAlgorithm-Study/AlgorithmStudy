import java.io.*;
import java.util.*;

public class HW_보도블럭 {
    static int n, L;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 보도블럭의 크기
        L = Integer.parseInt(st.nextToken()); // 경사로의 길이
        board = new int[n][n];

        for (int i = 0; i < n; i++) { // 보드 블럭 높이 정보가 숫자로 주어짐
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(countPath(board, L));
    }

    static int countPath(int[][] board, int L) { // 지나갈 수 있는 행과 열의 총 개수를 출력
        int cnt = 0;

        for (int i = 0; i < n; i++) { // 행 검사
            if (canGo(board[i], L)) {
                cnt++;
            }
        }

        for (int j = 0; j < n; j++) { // 열 검사
            int[] col = new int[n];
            for (int i = 0; i < n; i++) {
                col[i] = board[i][j];
            }
            if (canGo(col, L)) {
                cnt++;
            }
        }
        return cnt;
    }

    static boolean canGo(int[] line, int L) { // 지나갈 수 있는지 확인
        boolean[] placed = new boolean[n]; // 경사로 설치 여부를 추적

        for (int i = 0; i < n - 1; i++) {
            int diff = line[i] - line[i + 1]; // 인접 칸의 높이 차이 계산

            if (diff == 0) { // 높이가 같으면 문제 없음
                continue;
            } else if (diff == 1) { // 낮아지는 경우
                if (!canInstall(line, L, i + 1, 1, placed)) {
                    return false; // 경사로 설치 불가
                }
            } else if (diff == -1) { // 높아지는 경우
                if (!canInstall(line, L, i, -1, placed)) {
                    return false; // 경사로 설치 불가
                }
            } else { // 높이 차이가 2 이상인 경우
                return false; // 설치 불가
            }
        }
        return true;
    }

    static boolean canInstall(int[] line, int L, int start, int height, boolean[] check) {
        for (int i = 0; i < L; i++) {
            int idx = 0;
            if(height==1){ // 경사가 낮아지는 경우
                idx = start +i;
            } else{ // 경사가 높아지는 경우
                idx = start -i;
            }
            // 조건1 : 배열 범위를 벗어나는지
            if(idx <0 || n<= idx){
                return false;
            }
            // 조건2 : 이미 경사로가 설치된 곳인지
            if(check[idx]){
                return false;
            }
            // 조건3 : 경사로 높이가 동일한지
            if(line[start] != line[idx]){
                return false;
            }
        }
        // 경사로 설치 표시
        for (int i = 0; i < L; i++) {
            int idx = 0;
            if(height==1){ // 경사가 낮아지는 경우
                idx = start +i;
            } else{ // 경사가 높아지는 경우
                idx = start -i;
            }
            check[idx] = true;
        }
        return true;
    }
}