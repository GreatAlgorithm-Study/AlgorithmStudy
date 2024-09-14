import java.io.*;
import java.util.*;

public class DH_불안한_무빙워크 {
    // cnt: 몇 번째 실험인지 저장하는 변수
    // arr: 무빙워크의 안정성
    static int cnt, N, K, arr[];
    // v: 무빙워크에 사람이 있는지 확인
    // 어짜피 [0][N - 1] 위치에 가면 사람은 무빙워크에서 내려가기 때문에
    // arr의 0번째 행만 고려함 -> v: 1차원 배열
    static boolean v[];

    static void movePeople() {
        for (int i = N - 1; i > 0; i--) {
            if(!v[i]) continue;
            // 해당 칸에 사람이 있으면서 안정성이 0보다 크다면
            // 사람 이동
            if(v[i + 1] || arr[i + 1] == 0) continue;
            v[i + 1] = true;
            v[i] = false;
            arr[i + 1]--;

            if(arr[i + 1] == 0) cnt++;
        }
    }

    static void moveMovingWalk() {
        // 무빙워크 안정성 옆 칸으로 이동
        arr[0] = arr[arr.length - 1];
        for(int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        // 무빙워크 이동할 떄, 무빙워크 위에 있는 사람도 같이 이동
        for(int i = v.length - 2; i > 0; i--) v[i + 1] = v[i];
        // N 위치에 가면 내림
        v[N] = false;
        // 첫 번째도 사람이 없어야 됨
        v[1] = false;
    }

    static void addPerson() {
        if(arr[1] == 0) return;
        v[1] = true;
        arr[1]--;
        if(arr[1] == 0) cnt++;
    }

    static void solution() {
        int turn = 0;

        while(true) {
            turn++;
            // 무빙워크 이동
            moveMovingWalk();
            // 사람 이동
            movePeople();
            // 처음 칸의 안정성 확인 이후 사람 추가
            addPerson();

            if(cnt >= K) break;
        }

        System.out.println(turn);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[2 * N + 1];
        v = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }
}
