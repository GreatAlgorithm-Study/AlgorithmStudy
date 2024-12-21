import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_1030 {
    static int s, N, K, r1, r2, c1, c2;
    static char[][] ans;

    private static void recursive(int x, int y, int t, int color) {     // 현재 재귀에서 행, 열, 시간, 색
        int p = (int) Math.pow(N, s - t);                   // 현재 시간에서 평면 크기 (현재 단계에서 한 격자가 차지하는 실제 크기)
        if (!isValid(x*p, x*p+p-1, y*p, y*p+p-1)) return;

        if (t == s) {                                       // 종료조건에서 색 기록
            ans[x - r1][y - c1] = (char) (color + '0');     // x,y좌표를 출력범위에 맞게 조정 후 값 넣기
            return;
        }

        for (int i = 0; i < N; i++) {            // 현재 격자 N*N으로 분열하기 (i,j는 나뉜 하위 격자내 상대좌표)
            for (int j = 0; j < N; j++) {
                int nColor;
                if (color==1) nColor = 1;
                else nColor = (isBlackArea(i) && isBlackArea(j)) ? 1 : 0;
                recursive(x * N + i, y * N + j, t + 1, nColor);     // 하위 좌표값을 전체 큰 사각형에서 본 절대좌표 값으로 변환해 보냄
            }
        }
    }

    private static boolean isBlackArea(int i) {         // 현재 격자가 검정인지 판단 (i: 현재 행의 인덱스, ((N - K) / 2): 중앙의 검정 구역 시작점)
        return !((i < (N - K) / 2) || (((N - 1 - i) < (N - K) / 2)));   // 행(i)을 기준으로 해당 행i가 검정구역 위, 아래 존재하는지 판단
    }

    private static boolean isValid(int tr1, int tr2, int tc1, int tc2) {        // 탐색범위가 출력범위에 포함되는지 확인
        if (tr1 > r2 || tr2 < r1) return false;
        if (tc1 > c2 || tc2 < c1) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        s = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        int row = r2 - r1 + 1;
        int col = c2 - c1 + 1;
        ans = new char[row][col];

        // 가장 큰 사각형에서 재귀로 분할해 작은 사각형으로 처리하기
        recursive(0, 0, 0, 0);

        for (char[] c : ans) {
            sb.append(c).append('\n');
        }
        System.out.println(sb);
    }
}
