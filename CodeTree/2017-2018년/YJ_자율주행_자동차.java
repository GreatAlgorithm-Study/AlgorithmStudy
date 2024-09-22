import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 알고리즘: BFS
 * 시간복잡도:3 ≤ n, m ≤ 50 범위로 BFS 의 O(n)로 가능
 * 아이디어:
 * BFS로 순회할 때 요구사항에 맞는 구현을 잘 정리해서 로직을 작성해야함
 * 1.자동차는 첫 시작을 기준으로 좌회전한다
 * 2.해당 방향이 방문했거나 인도인 경우 계속 좌회전한다
 * 3.좌회전 중 도로가 있을 경우 이동한다
 * 4.만약 4방향 모두 전진이 불가할 경우 바라보는 방향을 유지한 채로 한칸 후진한다
 * 5.후진한 후 4방향을 탐색한다
 * 6.후진이 불가하다면 작동을 멈춘다
 */
public class YJ_자율주행_자동차 {
    //자동차 방향: 상-좌-하-우
    static int[][] direction = {{-1,0}, {0,-1}, {1,0}, {0,1}};
    static int[][] road = null;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] firstLine = sc.nextLine().split("\\s");
        String[] secondLine = sc.nextLine().split("\\s");

        int N = Integer.parseInt(firstLine[0]); //세로
        int M = Integer.parseInt(firstLine[1]); //가로
        road = new int[N][M];
        for (int i = 0; i < N; i++) {  //길 데이터 채우기
            String[] line = sc.nextLine().split("\\s");
            for (int j = 0; j < M; j++) {
                road[i][j] = Integer.parseInt(line[j]);
            }
        }

        //자동차 현재위치
        int X = Integer.parseInt(secondLine[0]);
        int Y = Integer.parseInt(secondLine[1]);
        queue.offer(new int[]{X, Y});
        road[X][Y] = -1;

        int D = Integer.parseInt(secondLine[2]);    //북-동-남-서
        int result = 1;
        switch (D) {
            case 0:
                result = bfs(0);
                break;
            case 1:
                result = bfs(3);
                break;
            case 2:
                result = bfs(2);
                break;
            case 3:
                result = bfs(1);
                break;
        }

        System.out.println(result);
    }


    private static int bfs(int startD) {
        int visited = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int directionIndex = startD;

            for (int i = 0; i < 4; i++) {
                int nx = x + direction[directionIndex][0];
                int ny = y + direction[directionIndex][1];

                if (nx >= 0 && nx < road.length && ny >= 0 && ny < road[0].length && road[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    road[nx][ny] = -1;
                    visited++;
                    break;
                }

                directionIndex = (directionIndex + 1) % 4;
            }

            // 후진
            if(queue.isEmpty()){
                int backX = x - direction[startD][0];
                int backY = y - direction[startD][1];
                if (backX >= 0 && backX < road.length && backY >= 0 && backY < road[0].length && road[backX][backY] == 0) {
                    queue.offer(new int[]{backX, backY});
                    road[backX][backY] = -1;
                    visited++;
                } else {
                    break;
                }
            }
        }
        return visited;
    }
}
