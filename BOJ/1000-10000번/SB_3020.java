import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 개똥벌레
* 문제 설명: 석순, 종유석이 주어질때, 몇번째 라인으로 가야 개똥이가 최소 장애물 파괴하는지. 최소 장애물 파괴 수 + 해당 하는 구간의 갯수
* 설계
*   - 해당하는 구간의 갯수를 모두 구해야하기에 높이 H만큼 다 탐색해야함
*   - N*H불가, logN 또는 N 시간복잡도로 해결하기 >> 투포인터? (: 반복 여러번일때 사용)
*   - 종유석은(홀수) H-값 형태로 높이h에 위치하는지 확인
*   - 순서 상관없이 특정 위치에서 부딪히는 갯수 일정 >> 정렬 가능
* 구현
*   - M번째에 위치할때 몇개 부딪히는지 구하기
*       이분탐색으로 현재 리스트에서 i가 들어가야할 위치 찾기 (중복된 수 존재하니까 low-bound)
*       전체 리스트 길이(M)-i == 부딪히는 갯수, (이때 리스트는 석순, 종유석 따로 관리)
*   - 석순, 종유석 리스트 따로 구하기
*       석순i에서 종유석의 위치는 H-i+1번째
*       시간복잡도 이분탐색O(NlonN)*2N
* */
public class SB_3020 {
    static int N, H, M;
    static int[] down;
    static int[] up;

    private static int findIdx(int target, int[] lst) {
        int left = 0;
        int right = M;
        while (left < right) {
            int mid = (left + right) / 2;
            if (lst[mid] < target){     // low-bound, target이 존재할 제일 왼쪽반환 >> target 직전 작은값+1 위치
                left = mid+1;
            }
            else {
                right = mid;
            }
        }
        return right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        M = N/2;
        // 석순, 종유석 리스트에 값 담기
        down = new int[M];
        up = new int[M];
        for (int i = 0; i < N; i++) {
            if (i%2==0) down[i / 2] = Integer.parseInt(br.readLine());
            else up[(i - 1) / 2] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(up);

        int mn = Integer.MAX_VALUE;
        int cnt = 0;

        // 각 층에서 부딪히는 갯수 구하기
        for (int i = 1; i < H + 1; i++) {
            int hitDown = M - findIdx(i, down);
            int hitUp = M - findIdx(H - i + 1, up);

            int totalHit = hitDown+hitUp;
            if (totalHit < mn){
                mn = totalHit;
                cnt = 1;
            } else if (totalHit==mn) cnt++;
        }

        System.out.println(mn + " " + cnt);
    }
}

/*
* 궁금증, 무한루프 방지를 위해 int mid = (left + (right-left)) / 2; 와 같이 적는걸로 아는데 이렇게 하면 무한루프 빠짐
* */
