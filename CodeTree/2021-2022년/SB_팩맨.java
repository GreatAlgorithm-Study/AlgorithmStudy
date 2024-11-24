import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 팩맨 게임: 몬스터의 복제, 이동, 팩맨의 이동, 시체 소멸, 몬스터 부화 단계를 구현
 */
public class st_팩맨 {
    static int M, T; // 몬스터 수, 턴 수
    static final int N = 4; // 격자 크기 (4x4)
    static Monster pacMan;
    static Queue<Monster> active = new ArrayDeque<>();
    static Queue<Monster> eggs = new ArrayDeque<>();
    static HashMap<Integer, Monster> liveInfo = new HashMap<>();
    static List<List<Integer>> board = new ArrayList<>();
    static int[] ghost = new int[N * N]; // 시체 시간 기록
    static int idx = 0; // 몬스터 ID
    static int time; // 현재 턴
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int mx; // 팩맨이 먹은 최대 몬스터 수
    static Monster tmpPac; // 팩맨 임시 위치
    static Set<Integer> eatMon; // 팩맨이 먹은 몬스터 위치


    // 몬스터 복제 시도
    private static void duplicate() {
        for (Monster mon : liveInfo.values()) {
            eggs.offer(new Monster(++idx, mon.r, mon.c, mon.d));
        }
    }

    // 몬스터 이동
    private static void moveMonsters() {
        int pacPos = getPos(pacMan.r, pacMan.c);        // 팩맨 위치
        int size = active.size();

        for (int i = 0; i < size; i++) {
            Monster cur = active.poll();
            int curPos = getPos(cur.r, cur.c);
            boolean moved = false;

            for (int j = 0; j < 8; j++) {
                int nd = (cur.d + j) % 8;
                int nr = cur.r + dx[nd];
                int nc = cur.c + dy[nd];
                int newPos = getPos(nr, nc);

                if (isValid(nr, nc) && newPos != pacPos && ghost[newPos] == 0) {
                    cur.move(nr, nc, nd);
                    board.get(curPos).remove(Integer.valueOf(cur.idx));
                    board.get(newPos).add(cur.idx);
                    active.offer(cur);
                    moved = true;
                    break;
                }
            }

            if (!moved) {           // 움직이지 않았으면 다시 활동큐에 넣기
                active.offer(cur);
            }
        }
    }

    // 팩맨 이동
    private static void movePac() {
        mx = -1;
        tmpPac = new Monster(0, pacMan.r, pacMan.c, 0);

        for (int i = 0; i < 8; i += 2) {
            List<Integer> tmp = new ArrayList<>();
            Monster tPac = new Monster(0, pacMan.r, pacMan.c, i);
            dfs(0, tPac, tmp, 0);
        }

        for (int pos : eatMon) {
            List<Integer> lst = board.get(pos);
            Iterator<Integer> itr = lst.iterator();
            while (itr.hasNext()) {
                int mon = itr.next();
                liveInfo.remove(mon);
                ghost[pos] = time;
                itr.remove();
            }
        }

        pacMan.move(tmpPac.r, tmpPac.c, tmpPac.d);
    }

    // DFS로 팩맨의 최적 경로 탐색
    private static void dfs(int depth, Monster tPac, List<Integer> tmp, int cnt) {
        if (depth == 3) {
            if (cnt > mx) {
                mx = cnt;
                eatMon = new HashSet<>(tmp); // 최종 먹은 몬스터 위치 저장
                tmpPac = new Monster(tPac.idx, tPac.r, tPac.c, tPac.d);
            }
            return;
        }

        int od = tPac.d; // 원래 방향 저장
        for (int i = 0; i < 8; i += 2) {
            int nr = tPac.r + dx[i];
            int nc = tPac.c + dy[i];
            if (!isValid(nr, nc)) continue;

            int pos = getPos(nr, nc);
            int addCnt = 0;

            if (!tmp.contains(pos)) {   // 중복된 위치는 다시 먹지 않음
                addCnt = board.get(pos).size();
                if (addCnt > 0) tmp.add(pos);           // 먹은 곳의 위치 추가
            }

            tPac.move(nr, nc, i);          // 팩맨 이동
            dfs(depth + 1, tPac, tmp, cnt + addCnt);
            tPac.move(tPac.r - dx[i], tPac.c - dy[i], od);  // 팩맨 위치 복구

            if (addCnt > 0) tmp.remove(tmp.size() - 1); // 방문 기록 복구
        }
    }


    // 시체 소멸
    private static void clearGhosts() {
        for (int i = 0; i < N * N; i++) {
            if (time - ghost[i] > 2) {
                ghost[i] = 0;
            }
        }
    }

    // 몬스터 부화
    private static void hatchingEggs() {
        while (!eggs.isEmpty()) {
            Monster mon = eggs.poll();
            liveInfo.put(mon.idx, mon);
            board.get(getPos(mon.r, mon.c)).add(mon.idx);
            active.offer(mon);
        }
    }

    // 유효한 위치 확인
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    // 2차원 좌표를 1차원 인덱스로 변환
    private static int getPos(int r, int c) {
        return r * N + c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int pr = Integer.parseInt(st.nextToken()) - 1;
        int pc = Integer.parseInt(st.nextToken()) - 1;
        pacMan = new Monster(0, pr, pc, 0);

        // 보드 초기화
        for (int i = 0; i < N * N; i++) {
            board.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            Monster monster = new Monster(++idx, r, c, d);
            liveInfo.put(idx, monster);
            active.offer(monster);
            board.get(getPos(r, c)).add(monster.idx);
        }

        time = 1;
        while (T-- > 0) {
            // 1. 몬스터 복제 시도
            duplicate();

            // 2. 몬스터 이동
            moveMonsters();

            // 3. 팩맨 이동
            movePac();

            // 4. 시체 소멸
            clearGhosts();

            // 5. 몬스터 부화
            hatchingEggs();

            time++;
        }

        System.out.println(liveInfo.size());
    }

    static class Monster {
        int idx, r, c, d;

        Monster(int idx, int r, int c, int d) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.d = d;
        }

        void move(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Monster{" +
                    "idx=" + idx +
                    ", r=" + r +
                    ", c=" + c +
                    ", d=" + d +
                    '}';
        }
    }
}
