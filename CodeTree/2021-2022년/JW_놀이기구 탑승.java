import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] turn;      // 입장 순서를 저장할 배열
    static int[][] favor;   // 각 학생들이 좋아하는 학생의 번호를 저장할 배열
    static int[][] board;   // 학생들의 배치
    static int[] score = { 0, 1, 10, 100, 1000 }; // 주변에 좋아하는 학생의 수에 따른 점수 배열
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };

    // 각 위치별 정보를 저장할 오브젝트
    static class Node implements Comparable<Node> {
        int favor;
        int empty;
        int y, x;

        Node(int favor, int empty, int y, int x) {
            this.favor = favor;
            this.empty = empty;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Node o) {
            if (this.favor != o.favor)
                return o.favor - this.favor;
            else if (this.empty != o.empty)
                return o.empty - this.empty;
            else if (this.y != o.y)
                return o.y - this.y;
            return o.x - this.x;
        }
    }

    public static void main(String[] args) throws Exception {
        // 1. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        turn = new int[n * n];
        favor = new int[n * n + 1][4];
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            turn[i] = p;
            for (int j = 0; j < 4; j++)
                favor[p][j] = Integer.parseInt(st.nextToken());
        }
        board = new int[n][n];
        // 2. 각 학생별로 적절한 위치에 배치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int nowTurn = (i * n) + j;
                int p = turn[nowTurn];
                locate(p);
            }
        }
        // 3. 최종 점수 계산
        int totalScore = calculateScore();
        System.out.println(totalScore);
    }

    // 해당 학생의 적절한 위치에 배치하는 함수
    private static void locate(int p) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐를 이용해 최적의 자리를 선정
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                // 비어있지 않다면 스킵
                if (board[i][j] != 0)
                    continue;
                int[] arround = aroundFavor(i, j, p);  // 해당 자리의 주변 정보
                int favor = arround[0]; // 주변에 좋아하는 사람의 수
                int empty = arround[1]; // 주변에 빈 자리의 수
                pq.offer(new Node(favor, empty, i, j));
            }
        Node best = pq.poll();      // 최적의 자리를 꺼냄
        board[best.y][best.x] = p;  // 최적의 자리에 학생 배치
    }
    
    /**
     * 현재 위치의 주변 정보를 반환해주는 함수
     * @param y : 확인하고자 하는 자리의 열
     * @param x : 확인하고자 하는 자리의 행
     * @param p : 확인하고자 하는 학생의 번호
     * @return : 해당 자리의 주변 정보
     */
    private static int[] aroundFavor(int y, int x, int p) {
        int favorCnt = 0;   // 주변에 좋아하는 학생의 수
        int emptyCnt = 0;   // 주변에 비어있는 자리의 수
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            // 경계 체크
            if (!isValid(ny, nx))
                continue;
            int np = board[ny][nx]; // 다음 자리의 정보
            // 다음 자리가 0이라면 비어있는 수 증가 및 다음 자리 조회
            if (np == 0) {
                emptyCnt++;
                continue;
            }
            // 학생이 있다면 좋아하는 학생인지 확인
            for (int j = 0; j < 4; j++)
                if (np == favor[p][j])
                    favorCnt++;
        }
        return new int[] { favorCnt, emptyCnt };
    }

    // 주변에 좋아하는 학생의 수에 따라서 점수 계산
    private static int calculateScore() {
        int totalScore = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int p = board[i][j];
                int favorCnt = 0;
                // 해당 학생 주변에 좋아하는 사람이 몇 명 있는지 계산
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (!isValid(ny, nx))
                        continue;
                    int np = board[ny][nx]; // 다음 자리에 앉아있는 학생의 번호
                    for (int l = 0; l < 4; l++)
                        if (np == favor[p][l])
                            favorCnt++;
                }
                totalScore += score[favorCnt]; // 점수 계산
            }
        return totalScore;
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}