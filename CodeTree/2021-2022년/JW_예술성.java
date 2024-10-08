import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int n;                       // 보드의 길이
    static int[][] board;               // 보드 저장할 배열
    static int[][] group;               // 각 타일 별 그룹 저장할 배열
    static ArrayList<int[]> groupInfo;  // 그룹 별 정보 저장할 리스트
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };
    static int total = 0;               // 총 예술 점수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        // 총 4번의 그룹을 만든 후 합을 계산해야함
        for (int i = 0; i < 4; i++) {
            group = new int[n][n];              // 그룹을 나타내줄 공간
            groupInfo = new ArrayList<>();      // 그룹별 값 및 개수 저장
            divideGroup();                      // 을 나누는 BFS
            total += calculate();               // 예술 점수 계산
            rotateCross();                      // 십자 회전
            rotateSubGrid();                    // 서브그리드 회전
        }
        System.out.println(total);
    }

    // 그룹을 나누는 BFS
    private static void divideGroup() {
        boolean[][] visited = new boolean[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                // 방문하지 않은 점 = 새로운 그룹
                if (!visited[i][j]) {
                    int cnt = 0;                            // 그룹에 해당하는 타일의 개수
                    int value = board[i][j];                // 그룹의 값
                    // BFS 진행
                    Deque<int[]> dq = new ArrayDeque<>();
                    dq.offer(new int[] { i, j });
                    group[i][j] = idx;
                    visited[i][j] = true;
                    while (!dq.isEmpty()) {
                        int[] cur = dq.poll();
                        cnt++;
                        visited[cur[0]][cur[1]] = true;
                        for (int k = 0; k < 4; k++) {
                            int y = cur[0] + dy[k];
                            int x = cur[1] + dx[k];
                            if (isValid(y, x) && !visited[y][x] && board[y][x] == value) {
                                dq.offer(new int[] { y, x });
                                group[y][x] = idx;
                                visited[y][x] = true;
                            }
                        }
                    }
                    groupInfo.add(new int[] { value, cnt }); // 그룹 정보 저장
                    idx++;  // 다음 그룹을 위해 인덱스 증가
                }
    }

    // 예술 점수 계산
    private static int calculate() {
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    int y = i + dy[k];
                    int x = j + dx[k];
                    // 현재 좌표의 그룹과 다음 좌표의 그룹이 다를 경우
                    if (isValid(y, x) && group[i][j] != group[y][x]) {
                        int a = group[i][j];
                        int b = group[y][x];
                        // 그 부분이 경계이므로 합해주면 됨
                        sum += (groupInfo.get(a)[1] + groupInfo.get(b)[1]) * groupInfo.get(a)[0] * groupInfo.get(b)[0];
                    }
                }
            }
        return sum;
    }

    // 십자 회전
    private static void rotateCross() {
        int m = n / 2;
        int[] temp = new int[n];        // 값을 임시로 저장해둘 
        for (int i = 0; i < n; i++) {
            temp[i] = board[m][i];
            board[m][i] = board[i][m];
        }
        for (int i = 0; i < n; i++)
            board[i][m] = temp[n - 1 - i];
    }

    // 서브그리드 회전
    private static void rotateSubGrid() {
        int m = n / 2;
        rotateSubGrid(0, 0);
        rotateSubGrid(0, m + 1);
        rotateSubGrid(m + 1, 0);
        rotateSubGrid(m + 1, m + 1);
    }

    // 각 서브그리드의 좌 상단을 기준으로 값을 처리하도록하기 위해 오버로딩
    private static void rotateSubGrid(int y, int x) {
        int m = n / 2;
        int[][] temp = new int[m][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                temp[i][j] = board[y + i][x + j];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                board[y + j][x + m - 1 - i] = temp[i][j];
    }
    
    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}