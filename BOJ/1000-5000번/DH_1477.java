import java.io.*;
import java.util.*;

/*
휴게소 세우기
 */

public class DH_1477 {
    static int N, M, L;
    static int[] arr;

    static int binarySearch() {
        int s = 1, e = L;

        // lowerIdx 구해야 됨
        while(s < e) {
            int m = (s + e) / 2;

            // m 길이 간격으로 휴게소 추가하기
            // 휴게소 개수 구하기
            int restCnt = getRestCnt(m);

            // 추가할 수 있는 개수가  설치해야되는 개수보다 많다면, 길이 늘리기
            if(restCnt > M) s = m + 1;
            else e = m;
        }

        return s;
    }

    static int getRestCnt(int dis) {
        int cnt = 0;
        for(int i = 0; i < N + 1; i++) {
            int current = i == N ? L: arr[i];
            int prev = i == 0 ? 0: arr[i - 1];

            cnt += ((current - prev - 1) / dis);
        }

        return cnt;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        System.out.println(binarySearch());
    }
}
