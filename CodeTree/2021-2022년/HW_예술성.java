import java.util.Scanner;

public class HW_예술성 {
    public static int n;
    public static int[][] arr;
    public static int[][] nextArr;

    public static int groupN; // 그룹의 개수
    public static int[][] group;
    public static int[] groupCnt;

    public static boolean[][] visited;

    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static boolean inRange(int x, int y) { // 범위 확인
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            // 인접한 칸 중 숫자가 동일하면서 방문한 적이 없는 칸으로만 이동
            if (inRange(nx, ny) && !visited[nx][ny] && arr[nx][ny] == arr[x][y]) {
                visited[nx][ny] = true;
                group[nx][ny] = groupN;
                groupCnt[groupN]++;
                dfs(nx, ny);
            }
        }
    }

    // 그룹을 만들어줍니다.
    public static void makeGroup() {
        groupN = 0;

        visited = new boolean[n][n];
        group = new int[n][n];
        groupCnt = new int[n * n + 1];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    groupN++;
                    visited[i][j] = true;
                    group[i][j] = groupN;
                    groupCnt[groupN] = 1;
                    dfs(i, j);
                }
            }
    }

    public static int getArtScore() {
        int artScore = 0; // 예술 점수

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k], ny = j + dy[k];
                    if (inRange(nx, ny) && arr[i][j] != arr[nx][ny]) {
                        int g1 = group[i][j], g2 = group[nx][ny];
                        int num1 = arr[i][j], num2 = arr[nx][ny];
                        int cnt1 = groupCnt[g1], cnt2 = groupCnt[g2];

                        artScore += (cnt1 + cnt2) * num1 * num2;
                    }
                }
            }
        }
        return artScore / 2; // 중복 계산 제외
    }

    public static int getScore() {
        makeGroup();
        return getArtScore();
    }

    public static void rotateSquare(int sx, int sy, int squareN) {
        for (int x = sx; x < sx + squareN; x++) {
            for (int y = sy; y < sy + squareN; y++) {
                int ox = x - sx, oy = y - sy; // (sx, sy) -> (0, 0)
                int rx = oy, ry = squareN - ox - 1; //(x, y) -> (y, squareN - x -1)
                nextArr[rx + sx][ry + sy] = arr[x][y];
            }
        }
    }

    public static void rotate() {
        nextArr = new int[n][n];

        // 십자 모양에 대한 반시계 회전
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n / 2) // 세로줄 (i, j) -> (j, i)
                    nextArr[j][i] = arr[i][j];
                else if (i == n / 2)  // 가로줄에 대해서는 (i, j) -> (n - j - 1, i)
                    nextArr[n - j - 1][i] = arr[i][j];
            }
        }

        // 4개의 정사각형에 대해 시계 방향 회전
        int squareN = n / 2;
        rotateSquare(0, 0, squareN);
        rotateSquare(0, squareN + 1, squareN);
        rotateSquare(squareN + 1, 0, squareN);
        rotateSquare(squareN + 1, squareN + 1, squareN);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = nextArr[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n][n];
        nextArr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans += getScore();
            rotate();
        }

        System.out.print(ans);
    }
}