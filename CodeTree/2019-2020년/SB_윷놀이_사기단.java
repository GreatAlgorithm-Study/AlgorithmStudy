import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_윷놀이_사기단 {
    static int[] arr = new int[10];
    static HashMap<Integer, int[]> moveIdx = new HashMap<>();
    static HashMap<Integer, Integer> score = new HashMap<>();

    private static void init() {
        // 보드 초기화
        moveIdx.put(0, new int[]{1, 2, 3, 4, 5});       // 시작
        moveIdx.put(1, new int[]{2, 3, 4, 5, 6});
        moveIdx.put(2, new int[]{3, 4, 5, 6, 7});
        moveIdx.put(3, new int[]{4, 5, 6, 7, 8});
        moveIdx.put(4, new int[]{5, 6, 7, 8, 9});
        moveIdx.put(5, new int[]{21, 22, 23, 24, 25});      // 첫번째 파란 지점
        moveIdx.put(6, new int[]{7, 8, 9, 10, 11});
        moveIdx.put(7, new int[]{8, 9, 10, 11, 12});
        moveIdx.put(8, new int[]{9, 10, 11, 12, 13});
        moveIdx.put(9, new int[]{10, 11, 12, 13, 14});
        moveIdx.put(10, new int[]{27, 28, 24, 25, 26});     // 두번째 파란 지점
        moveIdx.put(11, new int[]{12, 13, 14, 15, 16});
        moveIdx.put(12, new int[]{13, 14, 15, 16, 17});
        moveIdx.put(13, new int[]{14, 15, 16, 17, 18});
        moveIdx.put(14, new int[]{15, 16, 17, 18, 19});
        moveIdx.put(15, new int[]{29, 30, 31, 24, 25});     // 세번째 파란 지점
        moveIdx.put(16, new int[]{17, 18, 19, 20, -1});
        moveIdx.put(17, new int[]{18, 19, 20, -1, -1});
        moveIdx.put(18, new int[]{19, 20, -1, -1, -1});
        moveIdx.put(19, new int[]{20, -1, -1, -1, -1});
        moveIdx.put(20, new int[]{-1, -1, -1, -1, -1});     // 점수 40에 해당하는 도착 바로전
        moveIdx.put(21, new int[]{22, 23, 24, 25, 26});     // 첫번째 파란 지점에서 빨간 화살표 이동(13)
        moveIdx.put(22, new int[]{23, 24, 25, 26, 20});
        moveIdx.put(23, new int[]{24, 25, 26, 20, -1});
        moveIdx.put(24, new int[]{25, 26, 20, -1, -1});
        moveIdx.put(25, new int[]{26, 20, -1, -1, -1});
        moveIdx.put(26, new int[]{20, -1, -1, -1, -1});
        moveIdx.put(27, new int[]{28, 24, 25, 26, 20});     // 두번째 파란 지점에서 빨간 화살표 이동(22)
        moveIdx.put(28, new int[]{24, 25, 26, 20, -1});
        moveIdx.put(29, new int[]{30, 31, 24, 25, 26});
        moveIdx.put(30, new int[]{31, 24, 25, 26, 20});
        moveIdx.put(31, new int[]{24, 25, 26, 20, -1});

        // 점수 초기화
        score.put(-1, 0);
        for (int i = 0; i < 21; i++) {
            score.put(i, i * 2);
        }
        score.put(21, 13);
        score.put(22, 16);
        score.put(23, 19);
        score.put(24, 25);
        score.put(25, 30);
        score.put(26, 35);
        score.put(27, 22);
        score.put(28, 24);
        score.put(29, 28);
        score.put(30, 27);
        score.put(31, 26);
    }

    static int ans = 0;

    private static void dfs(int hidx, int cnt, int total, List<Integer> pos) {      // 움직일 말 번호, 주사위 던진 횟수, 현재까지 점수, 말들의 위치
        List<Integer> tmpPos = new ArrayList<>(pos);
        int curDice = arr[cnt] - 1;                                     // 현재 주사위 값
        tmpPos.set(hidx, moveIdx.get(tmpPos.get(hidx))[curDice]);       // 선택한 말 이동 (현재 말의 위치에서 주사위 값만큼 이동)
        int curScore = score.get(tmpPos.get(hidx));                     // 말 이동 후 점수

        if (cnt == 9) {                                                 // 주사위 10번 모두 던지면 최종 점수 계산 후 리턴
            ans = Math.max(ans, total + curScore);
            return;
        }

        int nxtDice = arr[cnt + 1] - 1;                                 // 다음 주사위 값
        for (int i = 0; i < 4; i++) {                                   // 4개말 중 선택
            if (tmpPos.get(i)==-1) continue;                            // 이미 도착한 말은 패쓰
            int nxtPos = moveIdx.get(tmpPos.get(i))[nxtDice];           // 선택한 말의 다음 위치
            if (nxtPos !=-1 && tmpPos.contains(nxtPos)) continue;       // 다음 위치에 다른 말이 이미 있으면 패쓰
            dfs(i, cnt + 1, total + curScore, tmpPos);      // 선택한 말로 dfs 수행
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init();

        List<Integer> pos = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        dfs(0, 0, 0, pos);

        System.out.println(ans);
    }
}
