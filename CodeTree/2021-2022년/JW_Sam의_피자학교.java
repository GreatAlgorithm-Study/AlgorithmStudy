import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_Sam의_피자학교 {

    static int n, k;
    static Deque<Integer> dq = new ArrayDeque<>();
    static int maxValue = 0, minValue = 3001;
    static int H, W, R; // 초기 크기
    static int h, w, r; // 변하는 크기
    static int[][] dough; // 피자 도우
    static int[] dy = { 0, -1, 0, 1 }, dx = { -1, 0, 1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int v;
        for (int i = 0; i < n; i++) {
            v = Integer.parseInt(st.nextToken());
            dq.offer(v);
            maxValue = Math.max(maxValue, v);
            minValue = Math.min(minValue, v);
        }
        calculateDoughSize(); // 빙글빙글 접을 때의 사이즈 계산
        int time = 0;
        while (maxValue - minValue > k) {
            initDough();    // 빙글빙글 접기
            press();        // 누르기
            outspread();    // 펼치기
            halfDough();    // 절반씩 접기
            press();        // 누르기
            outspread();    // 펼치기
            judge();        // 최대, 최솟값 갱신
            time++;
        }
        System.out.println(time);
    }
    
    // 빙글빙글 말 떄의 사이즈 계산
    private static void calculateDoughSize() {
        int i = 0;
        while ((int) ((i + 4) / 2) * (int) ((i + 3) / 2) <= n)
            i++;
        H = (i + 3) / 2;
        W = (i + 2) / 2;
        R = n - H * W;
    }

    // 빙글빙글 접기
    private static void initDough() {
        h = H;
        w = W;
        r = R;
        dough = new int[h][w + r];
        // 사용하면 안되는 곳에 -1값 삽입
        for (int i = 0; i < h - 1; i++)
            for (int j = 0; j < r; j++)
                dough[i][w + j] = -1;
        int y = h - 1, x = w + r - 1, dir = 0;
        while (!dq.isEmpty()) {
            dough[y][x] = dq.pollLast();
            if (dough[y][x] == minValue)
                dough[y][x]++;
            // 부딪히거나 값이 있는 곳이라면 회전
            if (!isValid(y + dy[dir], x + dx[dir]) || dough[y + dy[dir]][x + dx[dir]] != 0)
                dir = (dir + 1) % 4;
            y += dy[dir];
            x += dx[dir];
        }
    }

    // 절반씩 접기
    private static void halfDough() {
        h = 4;
        w = n / 4;
        r = 0;
        dough = new int[h][w];
        for (int i = w - 1; i >= 0; i--)
            dough[3][i] = dq.pollLast();
        for (int i = w - 1; i >= 0; i--)
            dough[2][i] = dq.pollFirst();
        for (int i = 0; i < w; i++)
            dough[1][i] = dq.pollFirst();
        for (int i = 0; i < w; i++)
            dough[0][i] = dq.pollLast();
    }
    
    // 누르기
    private static void press() {
        int[][] temp = new int[h][w + r];   // 변화량을 저장할 배열
        int ny, nx, d;
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w + r; x++) {
                // 사용하지 않는 칸 스킵
                if (dough[y][x] == -1)
                    continue;
                for (int dir = 2; dir < 4; dir++) {
                    ny = y + dy[dir];
                    nx = x + dx[dir];
                    // 좌 하에서 평균 계산 후 맞춰주기
                    if (isValid(ny, nx) && dough[ny][nx] != -1) {
                        d = Math.abs(dough[y][x] - dough[ny][nx]) / 5;
                        if (dough[y][x] > dough[ny][nx]) {
                            temp[y][x] -= d;
                            temp[ny][nx] += d;
                        } else if (dough[y][x] < dough[ny][nx]) {
                            temp[y][x] += d;
                            temp[ny][nx] -= d;
                        }
                    }
                }
            }
        // 변화량 계산
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w + r; x++) {
                if (temp[y][x] == 0)
                    continue;
                dough[y][x] += temp[y][x];
            }
    }

    // 펼치기
    private static void outspread() {
        maxValue = 0;
        minValue = 3001;
        for (int x = 0; x < w + r; x++)
            for (int y = h - 1; y >= 0; y--) {
                if (dough[y][x] == -1)
                    break;
                dq.offer(dough[y][x]);
            }
    }
    

    // 최댓값 최솟값 갱신
    private static void judge() {
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                maxValue = Math.max(maxValue, dough[y][x]);
                minValue = Math.min(minValue, dough[y][x]);
            }
    }

    // 유효성 검사
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w + r;
    }
}