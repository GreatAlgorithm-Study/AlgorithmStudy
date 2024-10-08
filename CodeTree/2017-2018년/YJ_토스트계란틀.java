import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 알고리즘:BFS + 구현
 * 시간복잡도: 계란틀 크기 1 ≤ n ≤ 50 > n*n=250, 계란의 이동이 발생하는 총 횟수는 2000번
 * 아이디어:
 * 합칠 수 있는 계란틀에 대해 BFS를 사용 해 그룹을 나누고, 같은 그룹끼리 계란틀 검사 후 결과를 똑같이 분배
 > 이 과정이 계란의 1회 이동횟수
 > 그룹을 나눌 수 없을때 까지 반복
 * 그룹을 나누는 방법?
 > 하나의 계란마다 BFS 를 탐색하면서 그룹찾기
 * 같은 그룹끼리 계란틀 검사 후 다시 분배하는 방법?
 > 같은 그룹끼리 List 에 담아서 계산
 */
public class YJ_토스트계란틀 {
    static class Egg {
        int x;
        int y;

        Egg(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N = 0;
    static int L = 0;
    static int R = 0;
    static int[][] eggToast;
    static boolean[][] visited;
    static Queue<Egg> queue = new LinkedList<>();
    static List<Egg> eggGroup = new ArrayList<>();

    static void bfs() {
        int[] nx = {0, 1, 0, -1};
        int[] ny = {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            Egg egg = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = egg.x + nx[i];
                int nextY = egg.y + ny[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) {
                    continue;
                }

                if (isDifferent(egg, nextX, nextY)) {
                    Egg newEgg = new Egg(nextX, nextY);
                    queue.offer(newEgg);
                    eggGroup.add(newEgg);
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    static boolean isDifferent(Egg current, int nextX, int nextY) {
        int difference = Math.abs(eggToast[current.x][current.y] - eggToast[nextX][nextY]);
        return L <= difference && R >= difference;
    }

    //같은 그룹끼리 계란틀 검사
    static void mergeEggs() {
        int total = eggGroup.stream()
                .mapToInt(egg -> eggToast[egg.x][egg.y])
                .sum();
        int quantity = total / eggGroup.size();

        eggGroup.forEach(egg ->
                eggToast[egg.x][egg.y] = quantity);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split("\\s");
        N = Integer.parseInt(first[0]);
        L = Integer.parseInt(first[1]);
        R = Integer.parseInt(first[2]);

        eggToast = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] second = br.readLine().split("\\s");
            for (int j = 0; j < N; j++) {
                eggToast[i][j] = Integer.parseInt(second[j]);
            }
        }


        int moveCount = 0;
        boolean isMoved = false;
        do {
            visited = new boolean[N][N];
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (visited[x][y]) {
                        continue;
                    }
                    eggGroup = new ArrayList<>();
                    //계란 하나당 bfs 탐색
                    Egg egg = new Egg(x, y);
                    queue.offer(egg);
                    eggGroup.add(egg);
                    visited[x][y] = true;

                    bfs();

                    if (eggGroup.size() > 1) {
                        mergeEggs();
                        isMoved = true;
                    }
                }
            }
            //더 이상 이동할 계란토스트가 없을 경우
            if(!isMoved){
                break;
            }
            //이동된 계란토스트 반복을 위한 초기화
            moveCount++;
            isMoved = false;
        } while (true);
        System.out.println(moveCount);
    }

}
