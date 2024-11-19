import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_2096 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] dpMx = new int[2][3];                   // 이전값, 현재값
        int[][] dpMn = new int[2][3];

        st = new StringTokenizer(br.readLine());        // 초기 0행 값 설정
        for (int i = 0; i < 3; i++) {
            int x = Integer.parseInt(st.nextToken());
            dpMx[0][i] = x;
            dpMx[1][i] = x;
            dpMn[0][i] = x;
            dpMn[1][i] = x;
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dpMx[1][0] = Math.max(dpMx[0][0], dpMx[0][1]) + a;
            dpMx[1][1] = Math.max(dpMx[0][0], Math.max(dpMx[0][1], dpMx[0][2])) + b;
            dpMx[1][2] = Math.max(dpMx[0][1], dpMx[0][2]) + c;

            dpMn[1][0] = Math.min(dpMn[0][0], dpMn[0][1]) + a;
            dpMn[1][1] = Math.min(dpMn[0][0], Math.min(dpMn[0][1], dpMn[0][2])) + b;
            dpMn[1][2] = Math.min(dpMn[0][1], dpMn[0][2]) + c;

            // 이전값을 업데이트된 현재값 업데이트
            dpMx[0][0] = dpMx[1][0];
            dpMx[0][1] = dpMx[1][1];
            dpMx[0][2] = dpMx[1][2];

            dpMn[0][0] = dpMn[1][0];
            dpMn[0][1] = dpMn[1][1];
            dpMn[0][2] = dpMn[1][2];
        }

        int mx = Math.max(dpMx[0][0], Math.max(dpMx[0][1], dpMx[0][2]));
        int mn = Math.min(dpMn[0][0], Math.min(dpMn[0][1], dpMn[0][2]));

        System.out.println(mx + " " + mn);
    }
}
