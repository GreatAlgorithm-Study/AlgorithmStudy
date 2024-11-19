import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_팩맨 {

    static ArrayList<ArrayList<ArrayList<Integer>>> monsters = new ArrayList<>();   // 보드에 몬스터 저장
    static Deque<int[]> eggs = new ArrayDeque<>();          // 몬스터 복제의 위치 저장할 큐
    static int[][] corpses = new int[5][5];                 // 몬스터 시체의 카운트
    static int py, px;                                      // 팩맨의 위치
    
    static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };     // 각 방향 벡터
    static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    
    static int[][] trace = new int[3][2];   // 팩맨의 이동 경로
    static int maxKillCnt = -1;             // 팩맨의 위치를 저장할 때 사용할 최대값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        py = Integer.parseInt(st.nextToken());
        px = Integer.parseInt(st.nextToken());
        // 보드 준비
        for (int i = 0; i < 5; i++) {
            monsters.add(new ArrayList<>());
            for (int j = 0; j < 5; j++)
                monsters.get(i).add(new ArrayList<>());
        }
        // 각 보드에 몬스터 초기값 저장
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            monsters.get(y).get(x).add(dir);
        }
        while (t-- > 0) {
            removeCorpses();    // 몬스터 시체 카운터 감소
            addEggs();          // 몬스터 복제 시작
            moveMonster();      // 몬스터 이동
            movePackMan();      // 팩맨 이동
            popEggs();          // 몬스터 복제 끝
        }
        System.out.println(calculate());
    }

    // 몬스터 복제 시작
    private static void addEggs() {
        for (int i = 1; i <= 4; i++)
            for (int j = 1; j <= 4; j++)
                for (int dir : monsters.get(i).get(j))
                    eggs.add(new int[] { i, j, dir });
    }

    // 몬스터 이동
    private static void moveMonster() {
        ArrayList<ArrayList<ArrayList<Integer>>> next = new ArrayList<>();  // 몬스터의 이동을 저장할 임시 리스트
        for (int i = 0; i < 5; i++) {
            next.add(new ArrayList<>());
            for (int j = 0; j < 5; j++)
                next.get(i).add(new ArrayList<>());
        }
        for (int i = 1; i < 5; i++)
            for (int j = 1; j < 5; j++)
                // 기존 보드에 있는 몬스터들 이동
                next: for (int now : monsters.get(i).get(j)) {
                    int y = i, x = j, dir = now;
                    // 움직일 수 있는 방향 찾기
                    for (int k = 0; k < 8; k++) {
                        int ny = y + dy[dir];
                        int nx = x + dx[dir];
                        // 시체 및 팩맨이 존재하지 않는 곳으로 이동할 수 있다면 임시 리스트에 이동 저장
                        if (isValid(ny, nx) && canMove(ny, nx)) {
                            next.get(ny).get(nx).add(dir);
                            continue next;
                        }
                        // 한바꾸 돌기
                        dir++;
                        dir %= 9;
                        if (dir == 0)
                            dir++;
                    }
                    // 움직일 수 없었다면 현재 위치에서 대기
                    next.get(y).get(x).add(now);
                }
        // 기존 리스트를 임시 리스트로 변경
        monsters = next;
    }

    // 팩맨 이동
    private static void movePackMan() {
        maxKillCnt = -1;
        makeMove(py, px, 0, 0, new int[3][2]);
        
        // 팩맨 이동 경로가 정해졌다면 이동
        for (int[] move : trace) {
            py = move[0];
            px = move[1];
            
            // 이동 경로에 몬스터가 존재한다면 죽.여.
            if (!monsters.get(py).get(px).isEmpty()) {
                monsters.get(py).get(px).clear();
                corpses[py][px] = 3;
            }
        }
    }

    // 재귀로 팩맨의 이동 경로 결정
    private static void makeMove(int y, int x, int depth, int killCnt, int[][] moves) {
        // 최대 깊이
        if (depth == 3) {
            // 더 많은 몬스터를 죽일 수 있다면
            if (maxKillCnt < killCnt) {
                maxKillCnt = killCnt;
                // 팩맨 이동 경로 저장
                for (int i = 0; i < 3; i++) {
                    trace[i][0] = moves[i][0];
                    trace[i][1] = moves[i][1];
                }
            }
            return;
        }
        for (int i = 0; i < 8; i+=2) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            // 경계 체크
            if (isValid(ny, nx)) {
                // 미리 방문했었는지 체크
                boolean visited = isVisited(moves, ny, nx);
                moves[depth][0] = ny;
                moves[depth][1] = nx;
                int monsterCnt = monsters.get(ny).get(nx).size();
                // 방문 했었다면
                if (visited)
                    // 죽인 횟수는 증가하지 않고 다음 깊이로 진행
                    makeMove(ny, nx, depth + 1, killCnt, moves);
                else
                    // 죽인 횟수 증가하고 다음 깊이로 진행
                    makeMove(ny, nx, depth + 1, killCnt + monsterCnt, moves);
                // 백트래킹
                moves[depth][0] = 0;
                moves[depth][1] = 0;
            }
        }
    }

    // 몬스터 시체 카운터 감소
    private static void removeCorpses() {
        for (int i = 1; i < 5; i++)
            for (int j = 1; j < 5; j++)
                if (corpses[i][j] > 0)
                    corpses[i][j]--;
    }

    // 몬스터 복제 끝
    private static void popEggs() {
        while (!eggs.isEmpty()) {
            int[] egg = eggs.poll();
            int y = egg[0];
            int x = egg[1];
            int dir = egg[2];
            monsters.get(y).get(x).add(dir);
        }
    }

    // 남은 몬스터 수 계산
    private static int calculate() {
        int result = 0;
        for (int i = 1; i < 5; i++)
            for (int j = 1; j < 5; j++)
                result += monsters.get(i).get(j).size();
        return result;
    }

    // 이미 방문했는지 확인
    private static boolean isVisited(int[][] moves, int y, int x) {
        for (int[] move : moves) {
            if (move[0] == y && move[1] == x)
                return true;
        }
        return false;
    }

    // 시체 및 팩맨이 존재하는지 확인
    private static boolean canMove(int y, int x) {
        return corpses[y][x] == 0 && (y != py || x != px);
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 1 <= y && y <= 4 && 1 <= x && x <= 4;
    }
}