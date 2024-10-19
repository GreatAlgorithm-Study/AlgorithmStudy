import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_2110 {
    static int N, C;
    static int[] house;

    private static boolean canWifi(int dist) {
        int cnt = 1;
        int pre = house[0];
        for (int i = 1; i < N; i++) {
            if (house[i]-pre >= dist){
                cnt++;
                pre = house[i];
            }
            if (cnt >=C) return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        // 거리: mid, 공유기 개수: C (조건)
        int s = 1;
        int e = house[N-1] - house[0];
        int parm = 0;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (canWifi(mid)) {     // 공유기 가능하면 거리 더 늘려보기
                parm = mid;
                s = mid+1;
            }else e = mid-1;
        }
        System.out.println(parm);
    }
}
