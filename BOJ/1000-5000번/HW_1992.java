import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HW_1992 {
    static int N;
    static char[][] board;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            board[i] = line.toCharArray();
        }
        Quad(0, 0, N);
        System.out.println(sb);
    }
    static void Quad(int x, int y, int size){
        if(isValid(x, y, size)){
            sb.append(board[x][y]);
            return;
        }
        int half = size/2;

        sb.append('(');
        Quad(x, y, half); // 왼쪽 위
        Quad(x, y+half, half);
        Quad(x+half, y, half);
        Quad(x+half, y+half, half);
        sb.append(')');
    }
    static boolean isValid(int x, int y, int size){ // 압축 가능한지 확인
        char value = board[x][y]; // 첫번째 값 기준
        // 0 + 1 섞여있으면 4개 영역으로 나눠야함
        for(int i=x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                if(value != board[i][j]){
                    return false; // 한 문자라도 다르면 false
                }
            }
        }
        return true; // 모두 같은 값일 경우 true
    }
}