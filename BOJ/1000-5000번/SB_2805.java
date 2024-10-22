import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_2805 {
    static int N, M;
    static int[] trees;

    // 파라미터 서치: 잘린 길이가 M보다 크거나 같은가
    // 높이 최대값: mid변수
    private static boolean canGet(int idx, int h) {
        long cnt = 0;
        for (int i = idx; i < N; i++) {
            cnt += trees[i]-h;
            if (cnt>=M) return true;
        }
        return cnt>=M;
    }

    private static int findIdx(int h) {     // 얘도 upper-bound로 인덱스 위치 찾기
        int left = 0;
        int right = N;                     // upper-bound시 탐색범위는 [0,N)
        while (left < right){
            int mid = (left+right)/2;
            if (trees[mid] <= h) left = mid+1;
            else right = mid;
        }
        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);

        int left = 0;   // 절단기 높이값이 작을 수록 많이 자름
        int right = trees[N-1];
        int parm = 0;

        while (left <= right){       // 절단기 높이의 최대값이므로 upper-bound
            int height = (left+right)/2;
            int idx = findIdx(height);
            if (canGet(idx, height)) {
                parm = height;
                left = height+1;      // 나무 얻을 수 있으면 높이 조금 더 높여보기
            }
            else right = height-1;
        }
        System.out.println(parm);
    }
}
