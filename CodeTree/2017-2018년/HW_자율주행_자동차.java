import java.io.*;
import java.util.*;

public class HW_자율주행_자동차 {
    public static final int[] dx = {-1, 0, 1, 0};  // 북, 동, 남, 서
    public static final int[] dy = {0, 1, 0, -1};

    public static int n, m; // 세로 크기 n, 가로 크기 m
    public static int[][] grid; // 도로 상태 (0: 도로, 1: 인도)
    public static boolean[][] visited; // 방문 여부 체크
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); // 시작 위치 x
        int y = Integer.parseInt(st.nextToken()); // 시작 위치 y
        int d = Integer.parseInt(st.nextToken()); // 시작 방향 d (0: 북, 1: 동, 2: 남, 3: 서)

        // 도로 상태 입력 받기
        grid = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = simulate(x, y, d); // 방문한 칸의 개수를 계산
        System.out.println(result);
    }
    public static int simulate(int x, int y, int d) {
        visited[x][y] = true; // 현재 위치 방문 표시
        int count = 1; // 방문한 칸의 수 (처음 위치 포함)

        while (true) {
            boolean moved = false;

            for (int i = 0; i < 4; i++) {  // 4방향 탐색 (왼쪽부터 차례대로 탐색)
                d = (d + 3) % 4; // 왼쪽 방향으로 회전
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && grid[nx][ny] == 0) {  // 왼쪽 방향으로 갈 수 있다면 -> 이동
                    visited[nx][ny] = true;
                    x = nx;
                    y = ny;
                    count++; // 방문한 칸 증가
                    moved = true;
                    break; // 왼쪽 방향으로 이동했으므로 탐색 종료
                }
            }

            if (!moved) {  // 4방향 모두 이동할 수 없는 경우, 현재 방향에서 후진
                int backX = x - dx[d];
                int backY = y - dy[d];

                if (backX >= 0 && backX < n && backY >= 0 && backY < m && grid[backX][backY] == 0) { // 후진 가능 -> 후진
                    x = backX;
                    y = backY;
                } else {
                    break;
                }
            }
        }
        return count; // 방문한 칸의 총 개수 반환
    }
}