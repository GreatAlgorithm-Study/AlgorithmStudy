import java.io.*;
import java.util.*;

/*
디버깅
(1) powerset을 이용해서 사다리를 놓을 수 있는 곳, 놓을 수 없는 곳 모두 고려
    - 주의 사항: 처음 사다리가 놓여져 있는 곳은 사다리를 없앨 수 없음
    - 사다리의 개수가 3개 이상이라면 return;
(2) 조합을 이용해서 0, 1, 2, 3개 고를 수 있는 경우 구하고 가능한지 확인
 */

public class DH_디버깅 {
    static boolean[][] line;
    static int N, M, H;

    static void solution() {
        for(int i = 0; i < 4; i++) func(0, 0, i, line);
        System.out.println(-1);
    }

    static void func(int depth, int idx, int cnt, boolean[][] check) {
        if(depth == cnt) {
            for(int c = 0; c < N; c++) {
                int currentC = c;
                for(boolean[] booleans : line) {
                    if (currentC < N - 1 && booleans[currentC]) currentC++;
                    else if (currentC > 0 && booleans[currentC - 1]) currentC--;
                }
                if(c != currentC) return;
            }

            System.out.println(cnt);
            System.exit(0);
            return;
        }

        for(int i = idx; i < check.length * check[0].length; i++) {
            int r = i / line[0].length, c = i % line[0].length;
            if(check(i) || (c > 0 && check[r][c - 1])) continue;
            check[r][c] = true;
            func(depth + 1, i + 1, cnt, check);
            check[r][c] = false;
        }
    }

    static boolean check(int idx) {
        int len = line[0].length;
        int r = idx / len, c = idx % len;
        return line[r][c];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 고객의 수
        M = Integer.parseInt(st.nextToken()); // 메모리 유실 선의 개수
        H = Integer.parseInt(st.nextToken()); // 취약 점의 개수

        line = new boolean[H][N - 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            line[a][b] = true;
        }
        solution();
    }
}