import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_자율주행_자동차 {
    static int N,M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};    // 북동남서
    static int[] dy = {0, 1, 0, -1};

    static Car car;

    private static boolean moveCar() {
        for (int i = 0; i < 4; i++) {
            int nd = (car.d + 3 - i) % 4;
            int nx = car.x + dx[nd];
            int ny = car.y + dy[nd];
            if (!(0<=nx && nx<N && 0<=ny && ny<M) || visited[nx][ny]) continue;

            if (board[nx][ny]==0 && !visited[nx][ny]) {
                car.moveGo(nx, ny, nd);
                visited[nx][ny] = true;
                return true;
            }
        }
        return false;
    }

    private static boolean backCar() {
        int bd = (car.d + 2) % 4;
        int bx = car.x + dx[bd];
        int by = car.y + dy[bd];
        if (!(0<=bx && bx<N && 0<=by && by<M)|| board[bx][by] == 1) return false;
        car.moveBack(bx, by, car.d);
        return true;
    }

    private static void play() {
        visited[car.x][car.y] = true;           // 시작 위치 방문 처리
        boolean canGo = moveCar();

        // 좌회전해서 움직일 수 있을때까지 이동
        while (canGo) {
            canGo = moveCar();

            // 더이상 못가면 후진
            if (!canGo) {
                if (backCar()) canGo = true;    // 후진가능하면, 후진 후 다시 이동
                else break;
            }
        }
        System.out.println(car.cnt);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        car = new Car(x, y, d);

        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play();
    }

    static class Car{
        int x, y, d, cnt;

        Car(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = 1;
        }

        public void moveGo(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt++;
        }

        public void moveBack(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}

/*
 * 세부조건 확인 잘하기
 * - moveCar에서 바로 좌회전하면서 이동여부 확인 (현재 위치부터 아님)
 */