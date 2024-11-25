import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JW_생명과학부_랩_인턴 {

    // 바이러스의 정보
    static class Virus {
        int s, d, b;

        public Virus(int s, int d, int b) {
            this.s = s;
            this.d = d;
            this.b = b;
        }
    }

    static int n, m;    // 세로, 가로
    static int[] dr = { 0, -1, 1, 0, 0 };
    static int[] dc = { 0, 0, 0, 1, -1 };
    static List<List<List<Virus>>> board = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n + 1; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < m + 1; j++)
                board.get(i).add(new ArrayList<>());
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board.get(r).get(c).add(new Virus(s, d, b));
        }
        int total = 0;
        // 열의 수 만큼 반복
        for (int i = 1; i < m + 1; i++) {
            total += extractVirus(i);   // 바이러스 채집
            virusMove();                // 바이러스 이동
        }
        System.out.println(total);
    }

    // 가장 먼저 만나는 바이러스를 채집
    private static int extractVirus(int c) {
        for (int r = 1; r < n + 1; r++) {
            if (!board.get(r).get(c).isEmpty()) {
                // 해당 칸에 있는 바이러스의 크기를 반환
                return board.get(r).get(c).remove(0).b;
            }
        }
        return 0;
    }
    
    // 바이러스 이동
    private static void virusMove() {
        // 다음 턴의 보드
        List<List<List<Virus>>> nextBoard = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            nextBoard.add(new ArrayList<>());
            for (int j = 0; j < m + 1; j++)
                nextBoard.get(i).add(new ArrayList<>());
        }
        for (int r = 1; r < n + 1; r++)
            for (int c = 1; c < m + 1; c++)
                // 기존 보드에 바이러스가 존재했다면
                if (!board.get(r).get(c).isEmpty()) {
                    Virus virus = board.get(r).get(c).get(0);
                    int d = virus.d;
                    int s = virus.s;
                    int b = virus.b;
                    int nr = r + dr[d] * virus.s;
                    int nc = c + dc[d] * virus.s;
                    // 유효한 범위가 될 때까지 반대쪽으로 이동
                    while (!isValid(nr, nc)) {
                        d = nextDir(d); // 다음 방향
                        int over = 0;   // 넘어간 칸
                        // 방향에 따라 반대쪽으로 이동
                        if (d == 1 || d == 2) {
                            if (nr < 1) {
                                over = 1 - nr;
                                nr = 1 + over;
                            } else if (nr > n) {
                                over = nr - n;
                                nr = n - over;
                            }
                        } else {
                            if (nc < 1) {
                                over = 1 - nc;
                                nc = 1 + over;
                            } else if (nc > m) {
                                over = nc - m;
                                nc = m - over;
                            }
                        }
                    }
                    // 저장할 칸에 바이러스가 없다면 바로 저장
                    if (nextBoard.get(nr).get(nc).isEmpty())
                        nextBoard.get(nr).get(nc).add(new Virus(s, d, b));
                    // 저장할 칸에 바이러스가 존재한다면 비교 후 저장
                    else {
                        Virus other = nextBoard.get(nr).get(nc).get(0);
                        // 현재 바이러스와 비교하여 큰 값을 저장
                        if (other.b < virus.b) {
                            nextBoard.get(nr).get(nc).set(0, new Virus(virus.s, d, virus.b));
                        }
                    }
                }
        board = nextBoard;
    }

    // 방향 전환
    private static int nextDir(int dir) {
        if (dir == 1)
            return 2;
        if (dir == 2)
            return 1;
        if (dir == 3)
            return 4;
        return 3;
    }

    // 경계 확인
    private static boolean isValid(int r, int c) {
        return 0 < r && r <= n && 0 < c && c <= m;
    }
}