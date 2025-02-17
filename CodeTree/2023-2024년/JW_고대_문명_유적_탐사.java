import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JW_고대_문명_유적_탐사 {

    static int k, m, total = 0;
    static int[][] board = new int[5][5], nextBoard = new int[5][5]; // 각종 보드
    static Deque<Integer> dq = new ArrayDeque<>();                   // 벽면에 적힌 수를 저장할 덱
    static PriorityQueue<int[]> pq = new PriorityQueue<int[]>(       // 적힌 수를 삽입하기 위한 좌표
            (o1, o2) -> o2[1] != o1[1] ? o1[1] - o2[1] : o2[0] - o1[0]);

    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        // 벽면에 적힌 수
        for (int i = 0; i < m; i++)
            dq.offer(Integer.parseInt(st.nextToken()));
        StringBuilder sb = new StringBuilder();
        while (k-- > 0) {
            // 회전 -> 열 -> 행 순으로 입력
            for (int t = 0; t < 4; t++)
                for (int i = 1; i < 4; i++)
                    for (int j = 1; j < 4; j++) {
                        int[][] tempBoard = rotate(j, i, t);
                        PriorityQueue<int[]> nPq = earnValue(tempBoard);
                        // 더 많은 가치를 얻을 수 있다면
                        if (nPq.size() > pq.size()) {
                            pq = nPq;
                            nextBoard = tempBoard;
                        }
                    }
            // 종료 조건
            if (pq.size() == 0)
                break;
            int totalValue = pq.size();
            board = nextBoard;
            fillValue();
            // 유물 연쇄 획득
            while (true) {
                pq = earnValue(board);
                // 더 이상 얻을 수 없다면 종료
                if (pq.isEmpty())
                    break;
                totalValue += pq.size();
                fillValue();
            }
            sb.append(totalValue).append(" ");
        }
        System.out.println(sb);
    }

    // 회전
    private static int[][] rotate(int sy, int sx, int time) {
        int[][] tempBoard = new int[5][5]; // 임시 배열
        int[][] nextBoard = new int[5][5]; // 회전 배열
        // 깊은 복사
        for (int i = 0; i < 5; i++)
            nextBoard[i] = board[i].clone();
        int y = sy - 1, x = sx - 1; // 초깃값 보정
        // 회전 카운트만큼 회전
        while (time-- > 0) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tempBoard[y + j][x + 2 - i] = nextBoard[y + i][x + j];

            // 임시 배열에서 회전한 값을 원래 배열에 적용
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    nextBoard[y + i][x + j] = tempBoard[y + i][x + j];
        }
        return nextBoard;
    }

    // 총 가칠
    private static PriorityQueue<int[]> earnValue(int[][] nextBoard) {
        boolean[][] visited = new boolean[5][5];
        // 열과 행을 기준으로 값이 채워져야하므로 우선순위 큐를 사용
        PriorityQueue<int[]> nPq = new PriorityQueue<int[]>((o1, o2) -> o2[1] != o1[1] ? o1[1] - o2[1] : o2[0] - o1[0]);
        for (int y = 0; y < 5; y++)
            for (int x = 0; x < 5; x++)
                bfs(y, x, nextBoard, visited, nPq); // 유물의 조각이 연결되었는지 확인
        return nPq;
    }

    // 유물 조각 연결 유무를 판단하기 위한 BFS
    private static void bfs(int sy, int sx, int[][] nextBoard, boolean[][] visited, PriorityQueue<int[]> nPq) {
        Deque<int[]> dq = new ArrayDeque<>();
        ArrayList<int[]> al = new ArrayList<>(); // 사용된 좌표 리스트
        dq.offer(new int[] { sy, sx });
        al.add(new int[] { sy, sx });
        visited[sy][sx] = true;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0], x = cur[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if (isValid(ny, nx) && !visited[ny][nx] && nextBoard[ny][nx] == board[y][x]) {
                    dq.offer(new int[] { ny, nx });
                    al.add(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
            }
        }
        if (al.size() > 2) {
            for (int[] pos : al)
                nPq.offer(pos);
        }
    }

    private static void fillValue() {
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int y = cur[0], x = cur[1], v = dq.poll();
            board[y][x] = v;
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }
}
