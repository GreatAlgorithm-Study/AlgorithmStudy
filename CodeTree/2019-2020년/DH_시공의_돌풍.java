import java.io.*;
import java.util.*;

public class DH_시공의_돌풍 {
    static class Sigong {
        int r, c;
        public Sigong(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, T;
    static int[][] map; // 먼지량을 저장하는 배열
    static Sigong[] sigong; // 시공의 돌풍 위치를 저장하기 위한 배열
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 상, 우, 하, 좌

    public static void main(String[] args) throws Exception {
        initInput();

        while(T-- > 0) {
            step1(); // (1) 먼지가 인접한 4방향으로 확산
            step2(); // (2) 시공의 돌풍 청소
        }

        System.out.println(getSum());
    }

    // (3) 확산이 모두 끝난 후, 먼지의 합계
    static int getSum() {
        int sum = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(map[r][c] == -1 || map[r][c] == 0) continue;
                sum += map[r][c];
            }
        }

        return sum;
    }

    // (2) 사공의 돌풍 청소
    static void step2() {
        for(int i = 0; i < sigong.length; i++) move(i);
    }

    // 바람의 방향대로 먼지 이동
    static void move(int idx) {
        // d: 바람이 부는 방향
        // idx: 0이면 상, idx: 1이면 하
        int d = idx == 0 ? 0: 3;

        // 시작 좌표
        int r = sigong[idx].r, c = sigong[idx].c;

        while(true) {
            // 현재 좌표를 다음 좌표로 업데이트 함
            // 다음 좌표를 현재 좌표로 업데이트하면 수정된 값으로 계속 수정되므로 올바르지 않은 결과가 나옴
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 시공의 돌풍의 좌표를 확인하면서 바람이 불 수 있는 곳을 제어해줌
            // 바람이 갈 수 있는 곳이 벗어난다면 바람의 방향을 바꿔줌
            if(!check(nr, nc, idx)) {
                d = idx == 0 ? (d + 1) % 4: (d - 1 + 4) % 4;
                nr = r + dr[d];
                nc = c + dc[d];
            }

            // 현재 위치가 시공의 돌풍의 위치와 같다면
            // nr, nc 위치에 있는 먼지는 시공의 돌풍으로 들어감
            if(r == sigong[idx].r && c == sigong[idx].c) {
                map[r][c] = -1;
            }
            // nr, nc의 위치가 시공의 돌풍의 위치와 같다면
            // 현재 위치에 있는 곳은 청정한 바람이 오므로 0으로 설정
            else if(nr == sigong[idx].r && nc == sigong[idx].c) {
                map[r][c] = 0;
                break;
            }
            // 그 외 경우는 map[r][c]의 값을 map[nr][nc]값으로 변경해줌
            else {
                map[r][c] = map[nr][nc];
            }
            r = nr;
            c = nc;
        }
    }

    static void step1() {
        int[][] tmp = new int[N][M]; // 좌표별 먼지의 변화량을 저장하는 변수

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(map[r][c] == -1) continue;
                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc) || map[nr][nc] == -1) continue;
                    int m = map[r][c] / 5; // 확산되는 먼지량
                    tmp[r][c] -= m;
                    tmp[nr][nc] += m;
                }
            }
        }

        // 먼지의 변화량을 모두 확인했다면
        // map의 먼지량 갱신
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                map[r][c] += tmp[r][c];
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    // 시공의 돌풍의 위치를 고려하여 현재 좌표의 유효 범위를 확인하는 함수
    static boolean check(int r, int c, int idx) {
        int sr = idx == 0 ? 0 : sigong[idx].r;
        int er = idx == 0 ? sigong[idx].r + 1 : N;
        return r >= sr && r < er && c >= 0 && c < M;
    }
    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        sigong = new Sigong[2];

        int idx = 0; // 시공의 돌풍의 인덱스

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if(map[r][c] == -1) {
                    sigong[idx++] = new Sigong(r, c); // 시공의 돌풍 위치 저장
                }
            }
        }
    }
}
