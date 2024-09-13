class Solution {
    boolean[][][] board = new boolean[11][11][4];   // 방향을 확인할 추가 공간
    int[] dy = { 1, -1, 0, 0 }; // 상 하 우 좌
    int[] dx = { 0, 0, 1, -1 };
    char[] dirsArr;             // 주어진 문자열을 캐릭터형 배열로 저장
    int area;                   // 방문한 길이
    
    public int solution(String dirs) {
        dirsArr = dirs.toCharArray();
        recursion(5, 5, 0);     // 재귀 호출
        return area;
    }
    
    // 캐릭터의 움직임을 구현할 재귀함수
    private void recursion(int y, int x, int depth) {
        if (depth == dirsArr.length)        // 종료 조건
            return;
        int to = 0;                         // to : 현재 위치에서 들어가는 방향
        int from = 1;                       // from : 다음 위치에서 들어오는 방향
        if (dirsArr[depth] == 'D') {
            to = 1;
            from = 0;
        } else if (dirsArr[depth] == 'R') {
            to = 2;
            from = 3;
        } else if (dirsArr[depth] == 'L') {
            to = 3;
            from = 2;
        }
        int ny = y + dy[to], nx = x + dx[to];
        if (isValid(ny, nx)) {              // 다음 위치 경계 체크
            // 다음 지점의 들어오는 방향의 방문 체크가 안되어있다면
            if (!board[ny][nx][from])
                area++;                     // 방문 길이 증가
            board[y][x][to] = true;         // 현재 방향 방문 체크
            board[ny][nx][from] = true;     // 다음 방향 방문 체크
            recursion(ny, nx, depth + 1);   // 다음 재귀 함수 호출
        } else
            recursion(y, x, depth + 1);     // 경계를 넘으면 무시하고 다음 재귀 함수 호출
    }

    // 경계 조건
    private boolean isValid(int y, int x) {
        return 0 <= y && y < 11 && 0 <= x && x < 11;
    }
}