import java.util.Scanner;

public class HW_불안한_무빙워크 {
    public static int MAX_N = 100;
    public static int n, k;
    public static int[] top = new int[MAX_N * 2]; // 레일 길이가
    public static boolean[] check = new boolean[MAX_N]; // 위쪽 레일에서 사람이 있는지 여부 확인

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 레일의 길이
        k = sc.nextInt(); // 안정성이 0인 칸이 k개 이상이면 과정 종료

        for (int i = 0; i < 2 * n; i++) { // 위쪽과 아래쪽 레일을 한 배열로 입력 받음
            top[i] = sc.nextInt();
        }

        int tryCount = 0;
        while (!done()) {
            simulate();
            tryCount++;
        }
        System.out.println(tryCount);
    }

    // 무빙워크를 시계 방향으로 한 칸 회전
    public static void shift() {
        int tempStability = top[2 * n - 1];

        // 위쪽 레일과 아래쪽 레일을 한 배열로 관리하기 때문에 전체적으로 한 칸씩 회전
        for (int i = 2 * n - 1; i >= 1; i--) {
            top[i] = top[i - 1];
        }
        top[0] = tempStability;

        // 사람의 위치도 함께 회전
        for (int i = n - 1; i >= 1; i--) {
            check[i] = check[i - 1];
        }
        check[0] = false; // 첫 번째 칸에 사람은 없음
        check[n - 1] = false; // 마지막 칸에 사람이 있으면 내림
    }

    // 사람이 현재 위치에서 다음 위치로 이동 가능한지 확인
    public static boolean canGo(int idx) {
        return top[idx] > 0 && !check[idx]; // 안정성이 0보다 크고 사람이 없으면 이동 가능
    }

    // 뒤에서부터 사람이 이동할 수 있는지 확인하며 이동
    public static void movePerson() {
        for (int i = n - 2; i >= 0; i--) { // 마지막 칸은 사람이 무조건 내려가기 때문에 n-2부터 시작
            if (check[i] && canGo(i + 1)) {
                check[i] = false; // 현재 위치에서 사람이 이동
                check[i + 1] = true; // 다음 위치로 이동
                top[i + 1]--; // 이동한 칸의 안정성 감소
            }
        }
        check[n - 1] = false; // 마지막 칸에 도달한 사람은 내려감
    }

    public static void add() {
        if (top[0] > 0 && !check[0]) {
            check[0] = true; // 첫 번째 칸에 사람 추가
            top[0]--; // 안정성 1 감소
        }
    }

    public static void simulate() {
        shift();      // 1. 무빙워크 한 칸 회전
        movePerson(); // 2. 사람 이동
        add();        // 3. 새로운 사람 추가
    }

    // 안정성이 0인 칸이 k개 이상인지를 확인
    public static boolean done() {
        int unstableCount = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (top[i] == 0) {
                unstableCount++;
            }
        }
        return unstableCount >= k;
    }
}
