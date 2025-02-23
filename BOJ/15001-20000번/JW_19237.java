import java.util.ArrayDeque;
import java.util.Deque;

public class JW_19237 {

    // 상어 객체
    static class Shark {
        int num, idx, dir; // 상어의 번호, 인덱스, 방향
        int[][] priority; // 해당 상어의 위치에 따른 방향 우선 순위

        Shark(int num, int idx, int dir, int[][] priority) {
            this.num = num;
            this.idx = idx;
            this.dir = dir;
            this.priority = priority;
        }

        // 방향 우선 순위 반환
        int[] getPriority() {
            return priority[dir];
        }
    }

    static int n, m, k;
    static int[] scentBoard, remainBoard, move; // 냄새, 남은 시간, 방향 벡터
    static boolean[] visited;
    static Deque<Shark> sharks = new ArrayDeque<>(); // 상어 큐
    static Deque<int[]> scentDeque = new ArrayDeque<>(); // 냄새 큐

    public static void main(String[] args) throws Exception {
        n = read();
        m = read();
        k = read();
        move = new int[] { 0, -n, n, -1, 1 };
        scentBoard = new int[n * n];
        remainBoard = new int[n * n];
        visited = new boolean[n * n];

        // 기본 정보 입력
        init();

        int time = 0;
        while (time <= 1000) {
            int size = sharks.size();

            // 1번 상어만 남았다면 종료
            if (size == 1) {
                System.out.println(time);
                return;
            }

            visited = new boolean[n * n]; // 방문 배열 초기화
            // 상어를 작은 번호 순대로 꺼내서 탐색
            while (size-- > 0) {
                Shark shark = sharks.poll();
                int num = shark.num, idx = shark.idx;
                int nextDir = getNextDir(shark); // 다음 방향 결정
                int next = idx + move[nextDir]; // 이동

                // 이미 도착해있는 상어가 있다면
                // 즉, 자신보다 작은 번호의 상어가 도착해있다면 쫓겨남
                if (visited[next])
                    continue;

                visited[next] = true;
                shark.idx = next;
                shark.dir = nextDir;
                sharks.offer(shark); // 상어 큐에 삽입
                scentDeque.offer(new int[] { next, num }); // 냄새 큐에 삽입
            }
            removeScent(); // 냄새 카운트 감소
            putScent(); // 냄새 배열에 적용
            time++;
        }
        System.out.println(-1);
    }

    // 기본 정보 입력
    private static void init() throws Exception {
        int[] tempSharks = new int[m + 1];
        for (int i = 0; i < n * n; i++) {
            int num = read();
            if (num != 0) {
                tempSharks[num] = i; // 상어의 인덱스 저장
                scentBoard[i] = num; // 냄새 삽입
                remainBoard[i] = k; // 남은 시간 기록
            }
        }
        int[] tempDir = new int[m + 1];
        for (int i = 1; i < m + 1; i++)
            tempDir[i] = read();

        // 상어 객체 생성 및 큐에 삽입
        for (int i = 1; i < m + 1; i++) {
            int idx = tempSharks[i], dir = tempDir[i];
            int[][] priority = new int[5][5]; // 우선 순위 입력
            for (int j = 1; j < 5; j++)
                for (int k = 1; k < 5; k++)
                    priority[j][k] = read();
            Shark shark = new Shark(i, idx, dir, priority);
            sharks.offer(shark);
        }
    }

    // 다음 방향 결정
    private static int getNextDir(Shark shark) {
        int[] priority = shark.getPriority(); // 우선 순위에 따라 방향 결정
        int myScent = 0;
        for (int i = 1; i < 5; i++) {
            int dir = priority[i];
            int next = shark.idx + move[dir];
            if (isValid(shark.idx, next)) {
                if (scentBoard[next] == 0)
                    return dir;
                else if (scentBoard[next] == shark.num && myScent == 0)
                    myScent = dir;
            }
        }
        return myScent;
    }

    // 냄새 카운트 감소
    private static void removeScent() {
        for (int i = 0; i < n * n; i++)
            if (scentBoard[i] != 0 && --remainBoard[i] == 0)
                scentBoard[i] = 0;
    }

    // 냄새 큐에 있는 좌표에 냄새 삽입
    private static void putScent() {
        while (!scentDeque.isEmpty()) {
            int[] scent = scentDeque.poll();
            int idx = scent[0], num = scent[1];
            scentBoard[idx] = num;
            remainBoard[idx] = k;
        }
    }

    // 인덱스 유효성 검사
    private static boolean isValid(int cur, int next) {
        // 전체 인덱스를 벗어나는 경우
        if (next < 0 || next >= n * n)
            return false;
        // 좌, 우로 움직일 때, 같은 행에 있는지 확인
        int curRow = cur / n;
        int nextRow = next / n;
        if (Math.abs(cur - next) == 1 && curRow != nextRow)
            return false;
        return true;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
