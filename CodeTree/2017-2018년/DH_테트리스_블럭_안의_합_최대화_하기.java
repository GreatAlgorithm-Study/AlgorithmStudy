import java.io.*;
import java.util.*;

public class DH_테트리스_블럭_안의_합_최대화_하기 {
    static int N, M, result, max;
    static int[][] map;
    // 중복을 최대한 줄여주기 위해 상, 좌, 우 방향만 사용
    static int[] dr = {0, 1, 0}, dc = {-1, 0, 1};
    static boolean[][] v;

    static void solution() {
        // dfs 과정에서 원상복구 되기 때문에
        // 새로 생성하지 않아도 됨
        // dfs를 할 때마다 new boolean을 한다면 메모리 초과 발생
        v = new boolean[N][M];

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                // r, c 지점에서 dfs 시작
                v[r][c] = true;
                dfs(r, c, map[r][c], 0);
                v[r][c] = false;

            }
        }

        System.out.println(result);
    }

    static void dfs(int r, int c, int sum, int depth) {
        // 현재까지의 합 + (전체 - 남은 depth 값) * map에서 최대값 <= result라면
        // 현재 r, c 지점에서 남은 depth값 보다 더 dfs를 하더라도 최대값이 될 수 없으므로
        // return
        if(sum + (3 - depth) * max <= result) return;
        // depth가 3이라면 return
        if(depth == 3) {
            result = Math.max(result, sum);
            return;
        }

        for(int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!check(nr, nc) || v[nr][nc]) continue;

            // T자형 블럭을 만들어주기 위한 부분
            // nr, nc 지점은 갔다고 방문 체크해주고
            // 다음 위치 값은 nr, nc가 아닌 r, c로 설정해주기
            if(depth == 1) {
                v[nr][nc] = true;
                dfs(r, c, sum + map[nr][nc], depth + 1);
                v[nr][nc] = false;
            }

            // T자 외의 부분을 만들어 주기 위한 부분
            v[nr][nc] = true;
            dfs(nr, nc, sum + map[nr][nc], depth + 1);
            v[nr][nc] = false;
        }

    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                max = Math.max(map[r][c], max);
            }
        }
    }
}
