class JW_250134 {
    int n, m;
    int rEy, rEx, bEy, bEx; // 각 도착지점
    int[] dy = { 1, -1, 0, 0 }, dx = { 0, 0, 1, -1 };
    boolean[][][] visited; // [][][0]: Red의 방문 정보, [][][1]: Blue의 방문 정보
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        visited = new boolean[n][m][2];
        int rSy = 0, rSx = 0, bSy = 0, bSx = 0;
        // 정보 입력
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int v = maze[i][j];
                if (v == 1) {
                    rSy = i;
                    rSx = j;
                } else if (v == 2) {
                    bSy = i;
                    bSx = j;
                } else if (v == 3) {
                    rEy = i;
                    rEx = j;
                } else if (v == 4) {
                    bEy = i;
                    bEx = j;
                } else if (v == 5) {
                    visited[i][j][0] = true;
                    visited[i][j][1] = true;
                }
            }
        visited[rSy][rSx][0] = true;
        visited[bSy][bSx][1] = true;
        recursive(0, rSy, rSx, bSy, bSx);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    // 현재 수레의 좌표 상태에 따른 재귀 호출
    private void recursive(int turn, int ry, int rx, int by, int bx) {
        // 각 수레의 도착 정보
        boolean isRedGoal = ry == rEy && rx == rEx;
        boolean isBlueGoal = by == bEy && bx == bEx;
        // 둘 다 도착했을 경우 최솟값 갱신
        if (isRedGoal && isBlueGoal) {
            answer = Math.min(answer, turn);
            return;
        }
        // 파란색 수레만 도착했을 경우 
        if (isBlueGoal) {
            // 빨간색 수레를 움직임
            moveRed(turn, ry, rx, by, bx);
            return;
        }
        // 빨간색 수레만 도착했을 경우
        if (isRedGoal) {
            // 파란색 수레를 움직임
            moveBlue(turn, ry, rx, by, bx);
            return;
        }
        // 둘 다 움직임
        moveBoth(turn, ry, rx, by, bx);
    }

    private void moveRed(int turn, int ry, int rx, int by, int bx) {
        for (int i = 0; i < 4; i++) {
            int rNy = ry + dy[i], rNx = rx + dx[i];
            // 조건에 맞게 움직임
            if (isValid(rNy, rNx) && !visited[rNy][rNx][0] && !(rNy == by && rNx == bx)) {
                visited[rNy][rNx][0] = true;
                recursive(turn + 1, rNy, rNx, by, bx);
                visited[rNy][rNx][0] = false;
            }
        }
    }

    private void moveBlue(int turn, int ry, int rx, int by, int bx) {
        for (int i = 0; i < 4; i++) {
            int bNy = by + dy[i], bNx = bx + dx[i];
            // 조건에 맞게 움직임
            if (isValid(bNy, bNx) && !visited[bNy][bNx][1] && !(bNy == ry && bNx == rx)) {
                visited[bNy][bNx][1] = true;
                recursive(turn + 1, ry, rx, bNy, bNx);
                visited[bNy][bNx][1] = false;
            }
        }
    }

    private void moveBoth(int turn, int ry, int rx, int by, int bx) {
        for (int i = 0; i < 4; i++) {
            int rNy = ry + dy[i], rNx = rx + dx[i];
            if (isValid(rNy, rNx) && !visited[rNy][rNx][0]) {
                visited[rNy][rNx][0] = true;
                for (int j = 0; j < 4; j++) {
                    int bNy = by + dy[j], bNx = bx + dx[j];
                    if (isValid(bNy, bNx) && !visited[bNy][bNx][1]) {
                        // 두 수레가 움직일 경우에는
                        // 같은 지점으로의 이동과
                        if (rNy == bNy && rNx == bNx)
                            continue;
                        // 서로 엇갈리는 이동은 할 수 없음
                        if (rNy == by && rNx == bx && bNy == ry && bNx == rx)
                            continue;
                        visited[bNy][bNx][1] = true;
                        recursive(turn + 1, rNy, rNx, bNy, bNx);
                        visited[bNy][bNx][1] = false;
                    }
                }
                visited[rNy][rNx][0] = false;
            }
        }
    }

    private boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}