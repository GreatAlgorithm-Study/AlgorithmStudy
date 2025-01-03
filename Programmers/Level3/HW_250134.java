// 퍼즐을 푸는데 필요한 턴의 최솟값 구하기
// 최단 경로 (BFS)
// 시간 복잡도 : 구현 문제라 고려X
// 퍼즐을 풀 수 없는 경우 0 return
// 조건1) 벽(5)은 지나갈 수 없음
// 조건2) 동시에 수레를 같은 칸으로 움직일 수는 없음
// 조건3) 자리를 교환하며 이동 불가
import java.util.*;
class HW_250134 {
    static int answer = Integer.MAX_VALUE; // 최소 이동 횟수를 저장
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] redVisited, blueVisited; // 방문 배열
    static int[][] map; // 퍼즐판 복사
    static int n, m; // 퍼즐판 크기
    static int redEndX, redEndY, blueEndX, blueEndY; // 도착 위치

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        map = new int[n][m];
        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];

        int redStartX = 0, redStartY = 0;
        int blueStartX = 0, blueStartY = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = maze[i][j];
                if (maze[i][j] == 1) { // 빨간 수레 시작점
                    redStartX = i;
                    redStartY = j;
                    redVisited[i][j] = true;
                } else if (maze[i][j] == 2) { // 파란 수레 시작점
                    blueStartX = i;
                    blueStartY = j;
                    blueVisited[i][j] = true;
                } else if (maze[i][j] == 3) { // 빨간 수레 도착점
                    redEndX = i;
                    redEndY = j;
                } else if (maze[i][j] == 4) { // 파란 수레 도착점
                    blueEndX = i;
                    blueEndY = j;
                }
            }
        }
        backtrack(redStartX, redStartY, blueStartX, blueStartY, 0, false, false);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    static void backtrack(int redX, int redY, int blueX, int blueY, int moveCount, boolean redArrived, boolean blueArrived) {
        if (!redArrived && redX == redEndX && redY == redEndY) { // 도착
            redArrived = true;
        }
        if (!blueArrived && blueX == blueEndX && blueY == blueEndY) {
            blueArrived = true;
        }
        if (redArrived && blueArrived) {
            answer = Math.min(answer, moveCount); // 최솟값 반환
            return;
        }

        List<int[]> redMoves = new ArrayList<>(); // 이동 가능한 리스트
        List<int[]> blueMoves = new ArrayList<>();

        // 빨간 수레 이동
        if (!redArrived) {
            for (int i = 0; i < 4; i++) {
                int nx = redX + dx[i];
                int ny = redY + dy[i];
                if (isValid(nx, ny) && !redVisited[nx][ny] && map[nx][ny] != 5) { // 조건1) 벽(5)은 지날 수 없음
                    redMoves.add(new int[]{nx, ny}); // 이동 가능한 위치 추가
                }
            }
        } else { // 이미 도착한 경우 현재 위치 유지
            redMoves.add(new int[]{redX, redY});
        }

        // 파란 수레 이동
        if (!blueArrived) {
            for (int i = 0; i < 4; i++) {
                int nx = blueX + dx[i];
                int ny = blueY + dy[i];
                if (isValid(nx, ny) && !blueVisited[nx][ny] && map[nx][ny] != 5) {
                    blueMoves.add(new int[]{nx, ny}); // 이동 가능한 위치 추가
                }
            }
        } else { // 이미 도착한 경우 현재 위치 유지
            blueMoves.add(new int[]{blueX, blueY});
        }

        // 두 수레의 이동 시뮬레이션
        for (int[] redMove : redMoves) {
            for (int[] blueMove : blueMoves) {
                int nRedX = redMove[0], nRedY = redMove[1];
                int nBlueX = blueMove[0], nBlueY = blueMove[1];

                // 조건2) 동시에 수레를 같은 칸으로 움직일 수는 없음
                if (nRedX == nBlueX && nRedY == nBlueY) {
                    continue;
                }

                // 조건3) 자리를 교환하며 이동 불가
                if (nRedX == blueX && nRedY == blueY && nBlueX == redX && nBlueY == redY) continue;

                // 방문 처리
                redVisited[nRedX][nRedY] = true;
                blueVisited[nBlueX][nBlueY] = true;

                backtrack(nRedX, nRedY, nBlueX, nBlueY, moveCount + 1, redArrived, blueArrived);                 // 재귀 호출

                // 방문 복구
                redVisited[nRedX][nRedY] = false;
                blueVisited[nBlueX][nBlueY] = false;
            }
        }
    }

    static boolean isValid(int x, int y) {
        return 0 <= x && x < n  && 0 <= y && y < m;
    }
}