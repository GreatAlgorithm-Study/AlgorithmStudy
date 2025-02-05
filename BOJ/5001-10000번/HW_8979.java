import java.io.*;
import java.util.StringTokenizer;

public class HW_8979 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 국가의 수
        int K = Integer.parseInt(st.nextToken()); // 등수를 알고 싶은 국가

        int[][] board = new int[N+1][3];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            board[index][0]= Integer.parseInt(st.nextToken()); // gold
            board[index][1] = Integer.parseInt(st.nextToken()); // silver
            board[index][2] = Integer.parseInt(st.nextToken()); // bronze
        }

        int rnk = 1;
        for(int i=1; i<=N; i++){
            if(i==K) continue; // 자기 자신 건너뛰기
            if(board[i][0] > board[K][0]){
                rnk++;
            } else if(board[i][0] == board[K][0] && board[i][1] > board[K][1]){
                rnk++;
            } else if(board[i][0] == board[K][0] && board[i][1] == board[K][1] &&board[i][2] > board[K][2]){
                rnk++;
            }
        }

        System.out.println(rnk);
    }
}