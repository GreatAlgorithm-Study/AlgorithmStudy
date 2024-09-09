import java.io.*;
import java.util.*;

public class DH_자율주행_자동차 {
    // 자동차의 위치, 바라보는 방향을 저장하는 클래스
    static class Car {
        int r, c, d;
        public Car(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static Car car;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};
    // map: 도로와 자동차가 움직인 정보를 가지고 있음
    //  - 0: 갈 수 있는 도로, 1: 인도, 2: 이미 지나간 도로
    // result: 자동차가 움직인 도롤의 총 면적
    static int map[][], result = 1;

    static void solution() {
        map[car.r][car.c] = 2;

        while(true) {
            // 네 방향을 기준으로 왼쪽으로 갈 수 있는지 확인하하기 위한 변수
            int cnt = 0;
            // 왼쪽으로 갈 수 있는지 확인
            if(!canMoveLeft()) {
                // 자동차를 회전시키면서 왼쪽으로 갈 수 있는지 확인
                while(cnt++ < 3) if(canMoveLeft()) break;

                // cnt가 4라면 모든 방향에 대해 확인해봤지만 갈 수 없는 상황
                if(cnt == 4) {
                    int nr = car.r - dr[car.d];
                    int nc = car.c - dc[car.d];

                    updateCarInfo(nr, nc, car.d);
                    if(!check(nr, nc) || map[nr][nc] == 1) break;
                }
            }
        }

        System.out.println(result);
    }

    // 왼쪽으로 갈 수 있는지 확인
    static boolean canMoveLeft() {
        int d = (car.d - 1 + 4) % 4;
        int nr = car.r + dr[d];
        int nc = car.c + dc[d];

        car.d = d;
        if(!check(nr, nc) || map[nr][nc] != 0) return false;
        updateCarInfo(nr, nc, d);
        map[nr][nc] = 2;
        result++;

        return true;
    }

    // 자동차의 정보 업데이트
    static void updateCarInfo(int r, int c, int d) {
        car.r = r;
        car.c = c;
        car.d = d;
    }
    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int cr = Integer.parseInt(st.nextToken());
        int cc = Integer.parseInt(st.nextToken());
        int cd = Integer.parseInt(st.nextToken());

        car = new Car(cr, cc, cd);
        for(int r = 0; r < map.length; r++) {
            String s = br.readLine();
            for(int c = 0; c < map[0].length; c++) {
                map[r][c] = s.charAt(c * 2) - '0';
            }
        }

        solution();
    }
}
