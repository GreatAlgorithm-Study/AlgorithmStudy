import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 휴게소 세우기
 * 휴게소가 없는 구간이 최소값
 */
public class SB_1477 {
    static int N, M, L;
    static int[] restHouse;
    static int[] betweenDist;

    private static boolean canBuild(int x) {    // 거리x로 휴게소 M개 지을 수 있는지
        int cnt = 0;
        for (int d : betweenDist) {
            if (d%x==0) cnt+= d / x > 0 ? d / x - 1 : 0;
            else cnt += d/x;
        }
        return cnt>M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        restHouse = new int[N+2];
        betweenDist = new int[N+2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            restHouse[i] = Integer.parseInt(st.nextToken());
        }

        restHouse[N+1] = L;
        Arrays.sort(restHouse);

        // 휴게소간 거리
        for (int i = 1; i < N+2; i++) {
            betweenDist[i] = restHouse[i] - restHouse[i - 1];
        }

        // 이분탐색
        int left = 1;
        int right = L;
//        int ans = 0;
        while (left < right) {
            int distance = (left + right) / 2;
            if (canBuild(distance)){    // 해당 거리로 휴게소 M만큼 지을 수 있다면, 거리 늘려보기
//                ans = Math.max(ans, distance);
                left = distance+1;
            }
            else right = distance;
        }

//        System.out.println(ans+1);
        System.out.println(left);
    }
}
